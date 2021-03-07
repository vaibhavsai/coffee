package coffee.recipe;

import lombok.Data;
import java.util.HashMap;

/**
 * This class can be used if any future requirements for a beverage arise that do not necessarily come under a recipe
 * i.e. price, maybe coffee machine also dispenses drinks with cups and each beverage has a cup type, etc
 */
@Data
public class Beverage extends Recipe {
    public Beverage(String name, HashMap<String, Integer> ingredientMap) {
        super(name,ingredientMap);
    }

    //To hold additional attributes, price, cup type, or other things that belong with a Beverage but are seperate from the recipe
    private HashMap<String, String> additionalAttributes;

}
