package coffee.recipe;

import lombok.Data;

import java.util.HashMap;

@Data
public class Recipe {

    private String name;
    private HashMap<String, Integer> ingredientMap;

    public Recipe(String name, HashMap<String, Integer> ingredientMap) {
        this.name = name;
        this.ingredientMap = ingredientMap;
    }
    public Recipe() {

    }
}
