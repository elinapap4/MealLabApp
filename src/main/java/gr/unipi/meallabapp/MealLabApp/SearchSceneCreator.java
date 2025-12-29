package gr.unipi.meallabapp.MealLabApp;

import gr.unipi.meallab.MealApiClient;
import gr.unipi.meallab.Recipe;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class SearchSceneCreator implements EventHandler<MouseEvent> {

	// Fields for window dimensions
    int width, height;

    // Fields that represent JavaFX UI components (Nodes)
    GridPane root;
    TextField searchInput;
    Button searchBtn;
    ListView<String> searchList;
    Button backBtn;

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
        searchBtn = new Button("Αναζήτηση");
        searchList = new ListView<>();
        backBtn = new Button("Πίσω");

        // Position nodes in the grid: (column, row, columnSpan, rowSpan)
        root.add(backBtn, 0, 0);
        root.add(searchInput, 0, 1);
        root.add(searchBtn, 1, 1);
        root.add(searchList, 0, 2, 2, 1); 

        // Connect the button to this class so that the handle() method runs when clicked
        searchBtn.setOnMouseClicked(this);
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
        // Check if the click event came from the search button
        if (event.getSource() == searchBtn) {
            // Get recipes from the API using the text from the search field
            searchResults = MealApiClient.searchRecipes(searchInput.getText());
            
            // Use getItems().clear() to remove all previous entries from the ListView
            searchList.getItems().clear();
            
            // If results exist, loop through the array and add titles to the ListView
            if (searchResults != null) {
                for (int i = 0; i < searchResults.length; i++) {
                    searchList.getItems().add(searchResults[i].getTitle());
                }
            }
        }
        
        // Check if the click event came from the back button
        else if (event.getSource() == backBtn) {
            // 1. Create the main menu scene creator object
            MainSceneCreator mainCreator = new MainSceneCreator(width, height);   
            
            // 2. Generate the scene from the main creator
            Scene mainScene = mainCreator.createScene();   
            
            // 3. Set the scene to the main window
            App.window.setScene(mainScene);
        }
    }
} 

