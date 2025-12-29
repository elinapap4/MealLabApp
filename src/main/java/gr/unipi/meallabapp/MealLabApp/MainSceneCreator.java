package gr.unipi.meallabapp.MealLabApp;

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
	
	//Fields for window dimensions
	int width, height;
	
	//Fields that represent JavaFX UI components (Nodes)
    GridPane root;
    Label welcomeLabel;
    Button searchMenuBtn;
    Button listsMenuBtn;
    Button randomMenuBtn;
    
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
        welcomeLabel= new Label("Αρχικό Μενού");
        searchMenuBtn = new Button("Αναζήτηση Συνταγής");
        listsMenuBtn = new Button("Οι Λίστες μου");
        randomMenuBtn = new Button("Τι να μαγειρέψω σήμερα;");
        
       // Configure the title's appearance: set font size, center text, and match button width
        welcomeLabel.setFont(new Font("Arial", 16));
        welcomeLabel.setAlignment(Pos.CENTER);
        welcomeLabel.setMinWidth(200);
        
        // Set minimum size for the buttons.
        searchMenuBtn.setMinSize(200, 40);
        listsMenuBtn.setMinSize(200, 40);
        randomMenuBtn.setMinSize(200, 40);
        
        
        // Position nodes in the grid: (column, row)
        root.add(welcomeLabel, 0, 0);
        root.add(searchMenuBtn, 0, 5);
        root.add(listsMenuBtn, 0, 7);
        root.add(randomMenuBtn, 0, 9);
        
        // Connect all buttons to this class so that the handle() method runs when clicked
        searchMenuBtn.setOnMouseClicked(this);
        listsMenuBtn.setOnMouseClicked(this);
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
        if (event.getSource() == searchMenuBtn) {
        	// 1. Create an object of the search scene creator class
            SearchSceneCreator searchCreator = new SearchSceneCreator(width, height);
            
            // 2. Call the createScene() method to get the new scene
            Scene searchScene = searchCreator.createScene();
            
            // 3. Set the search scene to the main window (stage)
            App.window.setScene(searchScene);
        }
        else if (event.getSource() == randomMenuBtn) {
            
            System.out.println("Random Recipe clicked!");
        }
        else if (event.getSource() == listsMenuBtn) {
           
            System.out.println("My Lists clicked!");
        }
    }
}