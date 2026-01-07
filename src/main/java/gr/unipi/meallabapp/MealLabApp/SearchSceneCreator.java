package gr.unipi.meallabapp.MealLabApp;

import gr.unipi.meallab.MealApiClient;
import gr.unipi.meallab.Recipe;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class SearchSceneCreator implements EventHandler<MouseEvent> {

	// Fields for scene dimensions
    int width, height;

    // Fields that represent JavaFX UI components (Nodes)
    GridPane root;
    TextField searchInput; //Text field for user's input
    Button searchBtn; //Button to search for the recipe
    ListView<String> searchList; //List with all the results
    Button selectBtn; // Button to select a recipe
    Button backBtn; //Back button
    Label titleLabel; //Label for the title of the scene  

    // searchResults is an array that stores Recipe objects from the Recipe class
    Recipe[] searchResults;

    // Constructor of the class that receives the window dimensions
    public SearchSceneCreator(int width, int height) {
        this.width = width;
        this.height = height;

        // Initialize the GridPane and set centering and gaps between cells
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);

        // Initialize the UI nodes
        searchInput = new TextField();
        searchBtn = new Button("Search");
        selectBtn = new Button("Select");
        titleLabel = new Label("Search for a recipe!");
        searchList = new ListView<>();
        backBtn = new Button("Back to Main Menu");
        searchBtn.setMinSize(100, 20);
        selectBtn.setMinSize(100, 20);
        titleLabel.setFont(new Font("Arial", 16));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setMinWidth(200);

        // Position nodes in the grid: (column, row, columnSpan, rowSpan)
        root.add(titleLabel, 0, 0, 3, 1); 
        root.add(backBtn, 0, 2);
        root.add(searchInput, 0, 3);
        root.add(searchBtn, 1, 3);
        root.add(selectBtn, 3, 3);
        root.add(searchList, 0, 4, 4, 1); 

        // Connect the buttons to this class so that the handle() method runs when clicked
        searchBtn.setOnMouseClicked(this);
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
    	// SEARCH A RECIPE BUTTON
        if (event.getSource() == searchBtn) {
            // 1. Get recipes from the API using the user's input
            searchResults = MealApiClient.searchRecipes(searchInput.getText());
            
            // 2. Clear the ListView to prepare for new results
            searchList.getItems().clear();
            
            // 3. Check if results were found
            if (searchResults != null && searchResults.length > 0) {
            	// Fill the list with the titles of the found recipes
                for (int i = 0; i < searchResults.length; i++) {
                    searchList.getItems().add(searchResults[i].getTitle());}
            }
        
            // 4. If no recipes were found, show a friendly message
           else {
            // Get the text the user typed to include it in the message
            String userInput = searchInput.getText();
            searchList.getItems().add("No results found for: " +userInput);

            }
        }
        // BACK BUTTON
        else if (event.getSource() == backBtn) {
            // 1. Create a new object to build the Main Menu scene 
            MainSceneCreator mainCreator = new MainSceneCreator(width, height);   
            
            // 2. Generate the scene
            Scene mainScene = mainCreator.createScene();   
            
            // 3. Update the window
            App.window.setScene(mainScene);
        }
        
        // SELECT BUTTON
        else if (event.getSource() == selectBtn) {
            
            // 1. We find the row that the user clicked on
            int row = searchList.getSelectionModel().getSelectedIndex();
            
            // 2. Check if a row is actually selected.
            if (row >= 0) {
                // 3. Get the recipe from our array using that row number
                Recipe selectedRecipe = searchResults[row];
                
                // 4. Create the new scene and show it
                DetailsSceneCreator details = new DetailsSceneCreator(width, height, selectedRecipe);
                App.window.setScene(details.createScene());
            }
        }
    }
} 

