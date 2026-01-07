package gr.unipi.meallabapp.MealLabApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

//The App class extends the JavaFX Application class.
public class App extends Application {
	
    // Static field to allow other classes to access the main window
    public static Stage window;

	Scene mainScene;
	
	// We override the start method of the abstract Application class.
    @Override
    public void start(Stage primaryStage) {
    	
    	try {
    		
    	// We assign the primaryStage to our static window variable.
        window = primaryStage;

    	// We create an object using the MainSceneCreator constructor.
    	MainSceneCreator mainSceneCreator = new MainSceneCreator(1100,800);
    	
    	// We call the createScene() method to build the scene.
        mainScene = mainSceneCreator.createScene();
        
        
      // We use Java-fx methods of the Stage class to configure the main window
    	primaryStage.setTitle("Meal Lab App");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        
    	} catch (Exception e) {
            // Catch any errors that occur during the execution of the main scene
            System.err.println("Error launching the application: " + e.getMessage());
            e.printStackTrace();
        }
        
    }

    // The main method that launches the app.
    public static void main(String[] args) {
        launch();
    }

}