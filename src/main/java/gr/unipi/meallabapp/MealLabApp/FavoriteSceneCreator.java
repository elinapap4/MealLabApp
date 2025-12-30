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
import javafx.scene.control.ListView;
import javafx.scene.text.Font;

public class FavoriteSceneCreator implements EventHandler<MouseEvent>{

	// Fields for scene dimensions
    int width, height;

    // Fields that represent JavaFX UI components (Nodes)
    GridPane root;

    Label titleLabel; //Label for the title of the scene 
    ListView<String> favList; //List with all the favorite recipes
    Button removeBtn; //Remove from favorites button
    Button backBtn; //Back button
    
    
    // Constructor of the class that receives the window dimensions
    public FavoriteSceneCreator(int width, int height) {
        this.width = width;
        this.height = height;

        // Initialize the GridPane and set centering and gaps between cells
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);

        // Initialize the UI nodes
        titleLabel = new Label("My Favorite Recipes!");
        favList = new ListView<>();
        removeBtn = new Button("Remove recipe");
        backBtn = new Button("Back to Main Menu");
        backBtn.setMinSize(100, 20);
        removeBtn.setMinSize(100, 20);
        titleLabel.setFont(new Font("Arial", 16));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setMinWidth(200);

        // Position nodes in the grid: (column, row, columnSpan, rowSpan)
        root.add(titleLabel, 0, 0, 3, 1); 
        root.add(backBtn, 0, 2);
        root.add(removeBtn, 1, 3);
        root.add(favList, 0, 4, 4, 1); 

        // Connect the buttons to this class so that the handle() method runs when clicked
        removeBtn.setOnMouseClicked(this);
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
             System.out.println("Recipe removed from favorites.");
        }
        // BACK BUTTON
        else if (event.getSource() == backBtn) {
            MainSceneCreator mainCreator = new MainSceneCreator(width, height);   
            App.window.setScene(mainCreator.createScene());
        }
    }
}
