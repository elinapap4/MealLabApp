package gr.unipi.meallabapp.MealLabApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

	Scene mainScene;
	
    @Override
    public void start(Stage primaryStage) {

    	MainSceneCreator mainSceneCreator = new MainSceneCreator(650,500);
        mainScene = mainSceneCreator.createScene();

    	primaryStage.setTitle("Meal Lab App");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}