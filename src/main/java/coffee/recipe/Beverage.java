package coffee.recipe;

import lombok.Data;
import java.util.HashMap;

@Data
public class Beverage extends Recipe {
    public Beverage(String name, HashMap<String, Integer> ingredientMap) {
        super(name,ingredientMap);
    }

    //To hold additional attributes, price, cup type, or other things that belong with a Beverage but are seperate from the recipe
    private HashMap<String, String> additionalAttributes;

}
