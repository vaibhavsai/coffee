package coffee.recipe;

import java.util.HashMap;


public class Recipe {

    private String name;
    private HashMap<String, Integer> ingredientMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Integer> getIngredientMap() {
        return ingredientMap;
    }

    public void setIngredientMap(HashMap<String, Integer> ingredientMap) {
        this.ingredientMap = ingredientMap;
    }

    public Recipe(String name, HashMap<String, Integer> ingredientMap) {
        this.name = name;
        this.ingredientMap = ingredientMap;
    }
}
