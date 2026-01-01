package gr.unipi.meallabapp.MealLabApp;

import gr.unipi.meallab.MealApiClient;
import gr.unipi.meallab.Recipe;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class DetailsSceneCreator implements EventHandler<MouseEvent> {

	// Fields for scene dimensions
    int width, height;

    // The recipe object that contains the data
    Recipe currentRecipe; 
    
    // Fields that represent JavaFX UI components (Nodes)
    GridPane root;
    Label titleLabel; //Label for selected recipe      
    Label categoryLabel; //Label for the category 
    Label AreaLabel; //Label for the origin of recipe 
    Label ingLabel; //Label for the ingredients 
    Label instrLabel; //Label for the instructions 
    TextArea ingredientsArea; //TextArea for the ingredients 
    TextArea instructionsArea; //TextArea for the instructions 
    Image recipeImage; //The photo of the recipe
    ImageView recipeImageView;  //ImageView for the photo frame
    Button randomBtn; //Button for random recipe 
    Button favBtn; //Add to favorites button
    Button cookedBtn; //Add to cooked button
    Button backBtn; //Back button

    // Constructor of the class that receives the window dimensions
    public DetailsSceneCreator(int width, int height, Recipe selectedRecipe) {
        this.width = width;
        this.height = height;

        // We assign the selectedRecipe to our class field
        this.currentRecipe = selectedRecipe;
        
        // 1. Initialize the GridPane and set centering and gaps between cells
        root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(40); 
        root.setVgap(30);
        root.setPadding(new javafx.geometry.Insets(30));
  
        // 2. Initialize Labels and set styling (Font, Bold, Size)
        titleLabel = new Label(currentRecipe.getTitle());
        categoryLabel = new Label("Category:" + currentRecipe.getCategory());
        AreaLabel = new Label("Origin: " + currentRecipe.getArea());
        ingLabel = new Label("Ingredients:");
        instrLabel = new Label("Instructions:");
        titleLabel.setFont(new Font("Arial Bold", 20));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setMinWidth(200);
        ingLabel.setFont(new Font("Arial Bold", 14));
        instrLabel.setFont(new Font("Arial Bold", 14));

        //3. Initialize TextAreas and make them non-editable
         String ingredientsList = ""; // String to hold the final formatted list
        
            // Get the arrays from the recipe object
            String[] ingredients = currentRecipe.getIngredientsArray();
            String[] measures = currentRecipe.getMeasuresArray();

            // Loop through the ingredients to build a single text block
            for (int i = 0; i < ingredients.length; i++) {
            // Check if the ingredient exists (is not null and not empty)
            if (ingredients[i] != null && !ingredients[i].isEmpty()) {
            ingredientsList += "- " + measures[i] + " " + ingredients[i] + "\n";
                }
           }
            // Setup the ingredients & instructions display area
            ingredientsArea = new TextArea(ingredientsList);
            ingredientsArea.setEditable(false); // Make it read-only
            ingredientsArea.setWrapText(true); 
            instructionsArea = new TextArea(currentRecipe.getInstructions());
            instructionsArea.setEditable(false); // Make it read-only
            instructionsArea.setWrapText(true); 

        //4. Initialize Image and ImageView
        recipeImage = new Image(currentRecipe.getThumbUrl());
        recipeImageView = new ImageView(recipeImage);
        recipeImageView.setFitWidth(260);
        recipeImageView.setPreserveRatio(true);

        //5. Initialize Buttons
        randomBtn = new Button("Random Recipe");
        favBtn = new Button("Add to Favorites");
        cookedBtn = new Button("Mark as Cooked");
        backBtn = new Button("Back to Main Menu");
        
        // 6. Position nodes in the grid: (column, row, columnSpan, rowSpan)
        root.add(titleLabel, 0, 0, 3, 1); 
        root.add(backBtn, 0, 1);
        root.add(categoryLabel, 2, 2);
        root.add(AreaLabel, 1, 2);
        root.add(recipeImageView, 0, 3, 1, 5); 
        root.add(ingLabel, 1, 3);
        root.add(ingredientsArea, 1, 4, 2, 4); 
        root.add(randomBtn, 4, 2); 
        root.add(favBtn, 4, 4);    
        root.add(cookedBtn, 4, 5);
        root.add(instrLabel, 0, 10);
        root.add(instructionsArea, 0, 11, 5, 1); 
        
        // 7. Connect the buttons to this class so that the handle() method runs when clicked
        randomBtn.setOnMouseClicked(this);
        favBtn.setOnMouseClicked(this);
        cookedBtn.setOnMouseClicked(this);
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
        // RANDOM BUTTON
        if (event.getSource() == randomBtn) {
            Recipe randomRecipe = MealApiClient.getRandomRecipe();
            
            if (randomRecipe != null) {
                DetailsSceneCreator detailsCreator = new DetailsSceneCreator(width, height, randomRecipe);
                App.window.setScene(detailsCreator.createScene());
            }
        }
        
        // FAVORITES BUTTON
        else if (event.getSource() == favBtn) {
            // Check if recipe does not exist already in ArrayList "favorites"
            if (FavoriteSceneCreator.favorites.contains(currentRecipe) == false) {   
                // Add recipe to arrayList favorites
                FavoriteSceneCreator.favorites.add(currentRecipe);
            }
        } 
        
        // COOKED BUTTON
        else if (event.getSource() == cookedBtn) {
            // Check if recipe does not exist already in ArrayList "cookedRecipes"
            if (CookedSceneCreator.cookedRecipes.contains(currentRecipe) == false) {   
                // Add recipe to arrayList "cookedRecipes"
                CookedSceneCreator.cookedRecipes.add(currentRecipe);
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
    }
} 