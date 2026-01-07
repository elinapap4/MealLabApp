package gr.unipi.meallabapp.MealLabApp;

import gr.unipi.meallab.MealApiClient;
import gr.unipi.meallab.Recipe;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

//The class implements the EventHandler interface handle mouse clicks.
public class MainSceneCreator implements EventHandler<MouseEvent> {
	
	//Fields for scene dimensions
	int width, height;
	
	//Fields that represent JavaFX UI components (Nodes)
    GridPane root;
    Label welcomeLabel; //Welcome label
    Button searchMenuBtn; //Button for searching 
    Button favMenuBtn; //Button for my favorites 
    Button cookedMenuBtn; //Button for my cooking history 
    Button randomMenuBtn; //Button for random recipe 
    
 // Constructor of the class that receives the window dimensions.
    public MainSceneCreator(int width, int height) {
        this.width = width;
        this.height = height;
        
        // Initialize the GridPane and set centering and gaps between cells
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        
        // Initialize the nodes
        welcomeLabel= new Label("Main Menu");
        searchMenuBtn = new Button("Search for a recipe");
        favMenuBtn = new Button("My Favorites");
        cookedMenuBtn = new Button("My Cooking History");
        randomMenuBtn = new Button("Get a random recipe");
        
       // Configure the title's appearance: set font size, center text, and match button width
        welcomeLabel.setFont(new Font("Arial", 16));
        welcomeLabel.setAlignment(Pos.CENTER);
        welcomeLabel.setMinWidth(200);
        
        // Set minimum size for the buttons.
        searchMenuBtn.setMinSize(200, 40);
        favMenuBtn.setMinSize(200, 40);
        cookedMenuBtn.setMinSize(200, 40);
        randomMenuBtn.setMinSize(200, 40);
              
        // Position nodes in the grid: (column, row)
        root.add(welcomeLabel, 0, 0);
        root.add(searchMenuBtn, 0, 5);
        root.add(favMenuBtn, 0, 7);
        root.add(cookedMenuBtn, 0, 9);
        root.add(randomMenuBtn, 0, 11);
        
        // Connect all buttons to this class so that the handle() method runs when clicked
        searchMenuBtn.setOnMouseClicked(this);
        favMenuBtn.setOnMouseClicked(this);
        cookedMenuBtn.setOnMouseClicked(this);
        randomMenuBtn.setOnMouseClicked(this);
    }
    
 // Create and return the Scene object
    public Scene createScene() {
        Scene s = new Scene(root, width, height); 
        return s;
    }
    
 // We override the handle method from the EventHandler interface
    @Override
    public void handle(MouseEvent event) {
    	// SEARCH BUTTON
        if (event.getSource() == searchMenuBtn) {
            // 1. Create a new object to build the Search scene 
            SearchSceneCreator searchCreator = new SearchSceneCreator(width, height);
            
            // 2. Generate the scene from the creator
            Scene searchScene = searchCreator.createScene();
            
            // 3. Update the main window (stage) with the new scene
            App.window.setScene(searchScene);
        }
        
        // RANDOM BUTTON
        else if (event.getSource() == randomMenuBtn) {
        	// 1. Get a random recipe using the MealApiClient
            Recipe randomRecipe = MealApiClient.getRandomRecipe();
            
            // 2. If a recipe was found, create and show the details scene
            if (randomRecipe != null) {
                DetailsSceneCreator detailsCreator = new DetailsSceneCreator(width, height, randomRecipe);
                App.window.setScene(detailsCreator.createScene());
            }
        }
        
        // MY FAVORITES BUTTON
        else if (event.getSource() == favMenuBtn) {    
        	
        	// 1. Create the creator object for the Favorite scene
            FavoriteSceneCreator favCreator = new FavoriteSceneCreator(width, height);
            
            // 2. Generate the scene from the creator
            Scene favScene = favCreator.createScene();
            
            // 3. Update the main window (stage) with the new scene
            App.window.setScene(favScene);
        }
        
        // MY COOKING HISTORY BUTTON
        else if (event.getSource() == cookedMenuBtn) { 
        	
        	// 1. Create the creator object for the Cooking History scene
            CookedSceneCreator cookedCreator = new CookedSceneCreator(width, height);
            
            // 2. Generate the scene from the creator
            Scene cookedScene = cookedCreator.createScene();
            
            // 3. Update the main window (stage) with the new scene
            App.window.setScene(cookedScene);
        }
    }
}