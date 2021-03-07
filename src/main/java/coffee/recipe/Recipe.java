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

    /**
     * Adds quantity 'q' of ingredient 'ingr' if 'ingr' present, else puts new entry
     * @param ingr
     * @param q
     */
    public void addIngredient(String ingr, Integer q)
    {
        this.ingredientMap.merge(ingr,q,Integer::sum);
    }
}
