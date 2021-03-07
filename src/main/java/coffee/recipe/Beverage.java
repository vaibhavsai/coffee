package coffee.recipe;

import java.util.HashMap;

public class Beverage extends Recipe {
    public Beverage(String name, HashMap<String, Integer> ingredientMap) {
        super(name, ingredientMap);
    }

    //To hold additional attributes, price, cup type, or other things that belong with a Beverage but are seperate from the recipe
    private HashMap<String, String> additionalAttributes;

    public HashMap<String, String> getAdditionalAttributes() {
        return additionalAttributes;
    }

    public void setAdditionalAttributes(HashMap<String, String> additionalAttributes) {
        this.additionalAttributes = additionalAttributes;
    }
}
