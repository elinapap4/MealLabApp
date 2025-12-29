package gr.unipi.meallabapp.MealLabApp;

import gr.unipi.meallab.MealApiClient;
import gr.unipi.meallab.Recipe; 
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;

public class MainSceneCreator implements EventHandler<MouseEvent> {
    GridPane root;
    int width, height;
    
    TextField searchInput;
    Button searchBtn;
    ListView<String> searchList;
    Recipe[] searchResults; 
    
    public MainSceneCreator(int width, int height) {
        this.width = width;
        this.height = height;
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        
        searchInput = new TextField();
        searchBtn = new Button("Αναζήτηση");         
        searchList = new ListView<>();
        
        root.add(searchInput, 0, 0);
        root.add(searchBtn, 1, 0);
        root.add(searchList, 0, 1, 2, 1);
        
      
        searchBtn.setOnMouseClicked(this);
    }
    
    public Scene createScene() {
        Scene s = new Scene(root, width, height); 
        return s;
    }
    
    @Override
    public void handle(MouseEvent event) {
        if (event.getSource() == searchBtn) {
            searchResults = MealApiClient.searchRecipes(searchInput.getText());
            searchList.getItems().clear();
            if (searchResults != null) {
                for (int i = 0; i < searchResults.length; i++) {
                    searchList.getItems().add(searchResults[i].getTitle());
                }
            }
        }
    } 
} 