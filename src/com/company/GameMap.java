package com.company;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by thayer on 7/15/15.
 */
public class GameMap  extends Application implements Runnable {


    Group root = new Group();
    Scene scene = new Scene(root);
    Canvas canvas = new Canvas(800, 600);

    GraphicsContext gc = canvas.getGraphicsContext2D();

    Image[][] map = new Image[8][5];
    Image[] avimg = {new Image(this.getClass().getResourceAsStream("trianglesmp1.png")),
            new Image(this.getClass().getResourceAsStream("trianglesmp2.png")),
            new Image(this.getClass().getResourceAsStream("trianglesmp3.png")),
            new Image(this.getClass().getResourceAsStream("trianglesmp4.png")),
            new Image(this.getClass().getResourceAsStream("trianglesmp5.png")),
            new Image(this.getClass().getResourceAsStream("trianglesmp6.png"))};

    Image[] primg ={new Image(this.getClass().getResourceAsStream("trianglesp1.png")),
            new Image(this.getClass().getResourceAsStream("trianglesp2.png")),
            new Image(this.getClass().getResourceAsStream("trianglesp3.png")),
            new Image(this.getClass().getResourceAsStream("trianglesp4.png")),
            new Image(this.getClass().getResourceAsStream("trianglesp5.png")),
            new Image(this.getClass().getResourceAsStream("trianglesp6.png"))};


    Image[] orimg ={new Image(this.getClass().getResourceAsStream("triangleso1.png")),
            new Image(this.getClass().getResourceAsStream("triangleso2.png")),
            new Image(this.getClass().getResourceAsStream("triangleso3.png")),
            new Image(this.getClass().getResourceAsStream("triangleso4.png")),
            new Image(this.getClass().getResourceAsStream("triangleso5.png")),
            new Image(this.getClass().getResourceAsStream("triangleso6.png"))};

    Image[] chimg ={new Image(this.getClass().getResourceAsStream("trianglesc1.png")),
            new Image(this.getClass().getResourceAsStream("trianglesc2.png")),
            new Image(this.getClass().getResourceAsStream("trianglesc3.png")),
            new Image(this.getClass().getResourceAsStream("trianglesc4.png")),
            new Image(this.getClass().getResourceAsStream("trianglesc5.png")),
            new Image(this.getClass().getResourceAsStream("trianglesc6.png"))};



    Integer numplayers = 0;
    Boolean stop = false;
    Integer[] order;
    Integer[] startrolls;
    Integer playerturn;


    HashMap<Integer, player> pmap = new HashMap<Integer, player>();
    HashMap<Integer, Integer> posmap = new HashMap<Integer, Integer>();

    public GameMap(Integer nump) {
        numplayers = nump;

        startrolls = new Integer[numplayers];

        order = new Integer[numplayers];



    }

    @Override
    public void run() {


        rollfirst();




    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                System.exit(0);

            }
        });


        stage.setScene(scene);

        root.getChildren().add(canvas);


        genmap();


        stage.setTitle("War of The Twelve Planes");
        stage.show();

        Thread thread = new Thread(this);

        thread.start();


    }

    void genmap() {
        for (int i = 0; i < 8; i++) {
            for (int t = 0; t < 5; t++) {
                map[i][t] = new Image(this.getClass().getResourceAsStream("triangles.jpg"));
                gc.drawImage(map[i][t], i * 90, t * 90);

            }
        }


    }

    Integer count = 0;

    void rollfirst() {
        instructions("click anywhere to roll dice to see who goes first");


        EventHandler<? super MouseEvent> mouseevent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                roll(count);

                instructions("Player " + (String.valueOf(count + 1)) + " Rolled " + String.valueOf(startrolls[count]) + " Player " + String.valueOf(count + 2) + " click anywhere to roll");


                if (count > numplayers - 2) {


                    instructions("Player " + (String.valueOf(count + 1)) + " Rolled " + String.valueOf(startrolls[count]) + " Click to continue");


                    clickcontinue();


                } else {


                    count += 1;

                }

            }
        };

        canvas.setOnMouseClicked(mouseevent);
    }


    void instructions(String instr) {

        gc.clearRect(100, 450, 400, 100);
        gc.fillText(instr, 100, 500);

    }

    void roll(Integer player) {
        Random rand1 = new Random();
        Random rand2 = new Random();

        int tmp1 = rand1.nextInt(6) + 1;

        int tmp2 = rand1.nextInt(6) + 1;
        startrolls[player] = tmp1 + tmp2;

    }


    void clickclear() {
        EventHandler<? super MouseEvent> mouseevent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        };
        canvas.setOnMouseClicked(mouseevent);
    }

    void avsetup() {
        instructions("Player " + String.valueOf(order[playerturn] + 1) + " click a space to put down avatar");


        EventHandler<? super MouseEvent> mouseevent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Double x = event.getX();
                Double y = event.getY();

                for (int i = 0; i < 8; i++) {
                    for (int t = 0; t < 5; t++) {
                        if (x > i * 90 && x < (i + 1) * 90 && y > t * 90 && y < (t + 1) * 90) {
                            if (playerturn < numplayers-1) {


                                if (posmap.get((i * 8) + t) == null) {

                                    pmap.get(order[playerturn]).addav(((i * 8) + t), i,t);
                                    posmap.put((i * 8) + t, order[playerturn]);
                                    drawmap(i, t, avimg[order[playerturn]]);

                                    playerturn += 1;
                                    instructions("Player " + String.valueOf(order[playerturn] + 1) + " click a space to put down avatar");


                                }

                            } else {

                                if (posmap.get((i * 8) + t) == null) {

                                    pmap.get(order[playerturn]).addav(((i * 8) + t), i,t);
                                    posmap.put((i * 8) + t, order[playerturn]);
                                    drawmap(i, t, avimg[order[playerturn]]);
                                    instructions("Click to lay Priestess");
                                    avclickcontinue();
                                }

                                playerturn = 0;

                            }
                        }

                    }
                }

            }
        };
        canvas.setOnMouseClicked(mouseevent);
    }

    void createplayers() {

        for (int i = 0; i < numplayers; i++) {
            pmap.put(i, new player());

        }
    }

    void clickcontinue() {
        EventHandler<? super MouseEvent> mouseevent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                order();

                createplayers();

                avsetup();
            }
        };
        canvas.setOnMouseClicked(mouseevent);
    }

    public void order() {
        Integer high = 0;
        Integer pos = -1;

     for (int i =0 ; i < startrolls.length; i++){
         if (startrolls[i] > high){
             high = startrolls[i];
             pos= i;

         }


     }
        for (int i = 0; i < order.length; i++){
            order[i] = i +pos;
            if (order[i] > numplayers-1){
                order[i] -= numplayers;

            }
        }
        playerturn = 0;

    }
   public void  drawmap(Integer x, Integer y , Image img){
        gc.clearRect(x*90, y*90, 90 , 90);
       gc.drawImage(img, x * 90, y * 90);

    }

    void avclickcontinue() {
        EventHandler<? super MouseEvent> mouseevent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                laypr();


            }
        };
        canvas.setOnMouseClicked(mouseevent);
    }
    void laypr(){
        instructions("Player " + String.valueOf(order[playerturn] + 1) + " click a space to put down priestess");


        EventHandler<? super MouseEvent> mouseevent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Double x = event.getX();
                Double y = event.getY();

                for (int i = 0; i < 8; i++) {
                    for (int t = 0; t < 5; t++) {
                        if (x > i * 90 && x < (i + 1) * 90 && y > t * 90 && y < (t + 1) * 90) {
                            Boolean bol = charcheck(i,t);
                            if (bol){


                            if (playerturn < numplayers-1) {

                                if (posmap.get((i * 8) + t) == null || posmap.get((i*8)+t) == order[playerturn]) {


                                    pmap.get(order[playerturn]).addpr(((i * 8) + t), i, t);
                                    posmap.put((i * 8) + t, order[playerturn]);

                                    drawmap(i, t, primg[order[playerturn]]);

                                    playerturn += 1;
                                    instructions("Player " + String.valueOf(order[playerturn] + 1) + " click a space to put down priestess");

                                }


                            } else {
                                if (posmap.get((i * 8) + t) == null || posmap.get((i*8)+t) == order[playerturn]) {


                                    pmap.get(order[playerturn]).addpr(((i * 8) + t), i,t);
                                    posmap.put((i * 8) + t, order[playerturn]);



                                    drawmap(i, t, primg[order[playerturn]]);
                                 instructions("Click to lay Oracle");
                                prclickcontinue();


                                playerturn = 0;
                                }

                            }
                        }
                        }
                    }
                }

            }
        };
        canvas.setOnMouseClicked(mouseevent);
    }
    void prclickcontinue() {
        EventHandler<? super MouseEvent> mouseevent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                layor();


            }
        };
        canvas.setOnMouseClicked(mouseevent);
    }

   public Boolean charcheck(Integer x, Integer y){
        boolean bol = false;
       Integer mothx = pmap.get(order[playerturn]).pcs.av.x;
       Integer mothy = pmap.get(order[playerturn]).pcs.av.y;

        Integer tmpx = Math.abs(mothx-x);
       Integer tmpy = Math.abs(mothy- y);
       if (tmpx <3 && tmpy < 3){
           bol = true;

       }

       return bol;

   }
void layor(){
    instructions("Player " + String.valueOf(order[playerturn] + 1) + " click a space to put down Oracle");


    EventHandler<? super MouseEvent> mouseevent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            Double x = event.getX();
            Double y = event.getY();

            for (int i = 0; i < 8; i++) {
                for (int t = 0; t < 5; t++) {
                    if (x > i * 90 && x < (i + 1) * 90 && y > t * 90 && y < (t + 1) * 90) {
                        Boolean bol = charcheck(i,t);
                        if (bol){


                            if (playerturn < numplayers-1) {

                                if (posmap.get((i * 8) + t) == null || posmap.get((i*8)+t) == order[playerturn]) {


                                    pmap.get(order[playerturn]).addor(((i * 8) + t), i, t);
                                    posmap.put((i * 8) + t, order[playerturn]);

                                    drawmap(i, t, orimg[order[playerturn]]);

                                    playerturn += 1;
                                    instructions("Player " + String.valueOf(order[playerturn] + 1) + " click a space to put down Oracle");

                                }


                            } else {
                                if (posmap.get((i * 8) + t) == null || posmap.get((i*8)+t) == order[playerturn]) {


                                    pmap.get(order[playerturn]).addor(((i * 8) + t), i, t);
                                    posmap.put((i * 8) + t, order[playerturn]);



                                    drawmap(i, t, orimg[order[playerturn]]);
                                    instructions("Click to lay Champion");
                                    orclickcontinue();


                                    playerturn = 0;
                                }

                            }
                        }
                    }
                }
            }

        }
    };
    canvas.setOnMouseClicked(mouseevent);









}
    void orclickcontinue(){
        EventHandler<? super MouseEvent> mouseevent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                laych();


            }
        };
        canvas.setOnMouseClicked(mouseevent);
    }
    void laych(){
        instructions("Player " + String.valueOf(order[playerturn] + 1) + " click a space to put down Champion");


        EventHandler<? super MouseEvent> mouseevent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Double x = event.getX();
                Double y = event.getY();

                for (int i = 0; i < 8; i++) {
                    for (int t = 0; t < 5; t++) {
                        if (x > i * 90 && x < (i + 1) * 90 && y > t * 90 && y < (t + 1) * 90) {
                            Boolean bol = charcheck(i,t);
                            if (bol){


                                if (playerturn < numplayers-1) {

                                    if (posmap.get((i * 8) + t) == null || posmap.get((i*8)+t) == order[playerturn]) {


                                        pmap.get(order[playerturn]).addch(((i * 8) + t), i, t);
                                        posmap.put((i * 8) + t, order[playerturn]);

                                        drawmap(i, t, chimg[order[playerturn]]);

                                        playerturn += 1;
                                        instructions("Player " + String.valueOf(order[playerturn] + 1) + " click a space to put down Champion");

                                    }


                                } else {
                                    if (posmap.get((i * 8) + t) == null || posmap.get((i*8)+t) == order[playerturn]) {


                                        pmap.get(order[playerturn]).addch(((i * 8) + t), i, t);
                                        posmap.put((i * 8) + t, order[playerturn]);



                                        drawmap(i, t, chimg[order[playerturn]]);
                                        instructions("Click to lay veterans");
                                        chclickcontinue();


                                        playerturn = 0;
                                    }

                                }
                            }
                        }
                    }
                }

            }
        };
        canvas.setOnMouseClicked(mouseevent);

    }
   void  chclickcontinue(){
       EventHandler<? super MouseEvent> mouseevent = new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               layvet();


           }
       };
       canvas.setOnMouseClicked(mouseevent);
    }
    void layvet(){

    }
}



