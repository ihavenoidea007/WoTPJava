package com.company;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by thayer on 7/15/15.
 */
public class PlayerSett extends Application implements Runnable {


    Group root = new Group();
    Scene scene = new Scene(root);
    Canvas canvas = new Canvas(800, 600);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    Image playsett = new Image(this.getClass().getResourceAsStream("numplay.png"));

    Integer players = 0;


    @Override
    public void run() {
        EventHandler<? super MouseEvent> mouseevent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();


                if (x>286 && x < 411 && y >183 && y <291){
players = 2;


                }


                if (x>508 && x < 657 && y >175 && y <286){

players = 3;
                }

                if (x>90 && x < 195 && y >331 && y <460){
players = 4;


                }

                if (x>286 && x < 411 && y >331 && y <460){

players =5;
                }

                if (x>508 && x < 657 && y >331 && y <460){
players = 6;

                }


if (players!= 0){
    nextpage();

}

            }
        };


        canvas.setOnMouseClicked(mouseevent);













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


        gc.drawImage(playsett, 0, 0);



        stage.setTitle("War of The Twelve Planes");
        stage.show();

    Thread thread = new Thread(this);
        thread.start();





    }
    void nextpage(){
        GameMap gm = new GameMap(players);
        try {
            gm.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }


        stopwindow();

    }

    void stopwindow(){
        this.scene.getWindow().hide();

    }
}
