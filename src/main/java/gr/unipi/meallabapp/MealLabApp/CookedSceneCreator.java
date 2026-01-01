package gr.unipi.meallabapp.MealLabApp;

import java.util.ArrayList;
import gr.unipi.meallab.Recipe;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class CookedSceneCreator  implements EventHandler<MouseEvent>{

		// Fields for scene dimensions
	    int width, height;
	    
	    // We use the generic ArrayList class to ensure it only accepts Recipe objects
	    public static ArrayList<Recipe> cookedRecipes = new ArrayList<>();
	    
	    // Fields that represent JavaFX UI components (Nodes)
	    GridPane root;
	    Label titleLabel; //Label for the title of the scene 
	    ListView<String> cookedList; //List with all the cooked recipes
	    Button removeBtn; //Remove from the list button
	    Button selectBtn; //Button to select the recipe
	    Button backBtn; //Back button
	   
	    // Constructor of the class that receives the window dimensions
	    public CookedSceneCreator(int width, int height) {
	        this.width = width;
	        this.height = height;

	        // Initialize the GridPane and set centering and gaps between cells
	        root = new GridPane();
	        root.setAlignment(Pos.CENTER);
	        root.setHgap(20);
	        root.setVgap(20);

	        // Initialize the UI nodes
	        titleLabel = new Label("My Cooking History!");
	        removeBtn = new Button("Remove recipe");
	        selectBtn = new Button("Select");
	        backBtn = new Button("Back to Main Menu");
	        backBtn.setMinSize(100, 20);
	        removeBtn.setMinSize(100, 20);
	        selectBtn.setMinSize(100, 20);
	        titleLabel.setFont(new Font("Arial", 16));
	        titleLabel.setAlignment(Pos.CENTER);
	        titleLabel.setMinWidth(200);
	        
	        // Initialize the list 
	        cookedList = new ListView<>();

	        // Go through the 'favorites' ArrayList and add each recipe title to the ListView
	        for (int i = 0; i < cookedRecipes.size(); i++) {           
	        	// Add the title of the recipe to the UI list (favList)
	            Recipe r = cookedRecipes.get(i);
	            cookedList.getItems().add(r.getTitle());
	        }
	        
	        // Position nodes in the grid: (column, row, columnSpan, rowSpan)
	        root.add(titleLabel, 0, 0, 3, 1); 
	        root.add(backBtn, 0, 1);
	        root.add(selectBtn, 0, 2);
	        root.add(removeBtn, 2, 2);
	        root.add(cookedList, 0, 3, 3, 1);
	        
	        
	        // Connect the buttons to this class so that the handle() method runs when clicked
	        removeBtn.setOnMouseClicked(this);
	        selectBtn.setOnMouseClicked(this);
	        backBtn.setOnMouseClicked(this);
	    }

	    // Create and return the Scene object for the search screen
	    public Scene createScene() {
	        Scene s = new Scene(root, width, height);
	        return s;
	    }
	    
	 // We override the handle method from the EventHandler interface
	    @Override
	    public void handle(MouseEvent event) {
	        // REMOVE A RECIPE
	        if (event.getSource() == removeBtn) {
	            // 1. We find the row that the user clicked on.
	            int row = cookedList.getSelectionModel().getSelectedIndex();
	            
	            // 2. Check if a row is actually selected.
	            if (row >= 0) {

	                // 3. Remove the recipe from the array list
	            	cookedRecipes.remove(row);
	                
	             // 4.Remove the recipe from the screen instantly
	                cookedList.getItems().remove(row);
	            }
	        }
	        
	        // SELECT BUTTON
	        else if (event.getSource() == selectBtn) {      
	            // 1. We find the row that the user clicked on.
	            int row = cookedList.getSelectionModel().getSelectedIndex();
	            
	            // 2. Check if a row is actually selected.
	            if (row >= 0) {
	                
	                // 3. Get the recipe from our "favorites" ArrayList using that row number.
	                Recipe selectedRecipe = cookedRecipes.get(row);
	                
	                // 4. Create the details scene for the selected recipe and show it.
	                DetailsSceneCreator details = new DetailsSceneCreator(width, height, selectedRecipe);
	                App.window.setScene(details.createScene());
	            }
	        }	            
	            
	        // BACK BUTTON
	        else if (event.getSource() == backBtn) {
	            MainSceneCreator mainCreator = new MainSceneCreator(width, height);   
	            App.window.setScene(mainCreator.createScene());
	        }
	    }
}
