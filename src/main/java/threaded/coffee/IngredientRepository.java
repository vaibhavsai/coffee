package threaded.coffee;

import coffee.machine.Constants;
import coffee.machine.State;
import lombok.Data;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class IngredientRepository {
    private ConcurrentHashMap<String, Integer> ingredientRepo;
    private ConcurrentHashMap<String, State> ingredientStates;

    public IngredientRepository(ConcurrentHashMap<String, Integer> ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    public void addIngredients(HashMap<String, Integer> ingredientRefillMap)
    {
        ingredientRefillMap.forEach((ingredient,quantity)->{
            //adds quantity to current quantity of ingredient
            this.ingredientRepo.merge(ingredient,quantity,Integer::sum);

            if(this.ingredientRepo.get(ingredient).compareTo(Constants.INGREDIENT_THRESHOLD)>0)
                this.ingredientStates.put(ingredient,State.SUFFICIENT);
        });
    }

}
