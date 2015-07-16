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


public class Main extends Application implements Runnable {


    Group root = new Group();
    Scene scene = new Scene(root);
    Canvas canvas = new Canvas(800, 600);

    Image startimage = new Image(this.getClass().getResourceAsStream("start.png"));



    GraphicsContext gc = canvas.getGraphicsContext2D();

    public static void main(String[] args) {
	// write your code here
        launch();

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


        gc.drawImage(startimage, 0, 0);






        stage.setTitle("War of The Twelve Planes");
        stage.show();

        Thread thread = new Thread(this);
        thread.start();
















    }

    @Override
    public void run() {



        EventHandler<? super MouseEvent> mouseevent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();


                if (x>256 && x < 514 && y >252 && y <452){

                    nextpage();

                }




            }
        };


canvas.setOnMouseClicked(mouseevent);





    }

    void nextpage(){
    PlayerSett play = new PlayerSett();
        try {
            play.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        stopwindow();


    }
    void stopwindow(){
        this.scene.getWindow().hide();

    }
}
