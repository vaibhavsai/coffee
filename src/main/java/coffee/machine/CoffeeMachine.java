package coffee.machine;

import coffee.recipe.Recipe;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CoffeeMachine {

    private HashMap<String, Integer> totalIngredients;
    private Integer outlets;
    private HashMap<String, State> ingredientStates;

    /** REFILL INGREDIENTS **/
    public List<Response> refillIngredients(HashMap<String, Integer> ingredientRefillMap)
    {
        List<Response> responseList = new ArrayList<>();
        ingredientRefillMap.forEach((ingredient,quantity)->{
            Response response = new Response();
            if(!this.totalIngredients.containsKey(ingredient)){
                response.setState(State.FAILED);
                response.setMessage(String.format(Constants.INGREDIENT_NOT_AVAILABLE,ingredient));
            }
            else
            {   //adds quantity to current quantity of ingredient
                this.totalIngredients.merge(ingredient,quantity,Integer::sum);

                if(this.totalIngredients.get(ingredient).compareTo(Constants.INGREDIENT_THRESHOLD)>0)
                    this.ingredientStates.put(ingredient,State.SUFFICIENT);

                response.setState(State.PROCESSED);
            }
            responseList.add(response);
        });
        return responseList;
    }

    /** POUR BEVERAGES **/
    public List<Response> pour(List<Recipe> beverageList){
        List<Response> responseList = new ArrayList<>();
        Integer beverageCount = 0;
        for (Recipe recipe: beverageList) {
            if(beverageCount < outlets)
            {
                HashMap<String, Integer> ingredientMap = recipe.getIngredientMap();
                Response response = new Response(State.PROCESSED);
                for (Map.Entry<String, Integer> entry : ingredientMap.entrySet()) {

                    if (!this.totalIngredients.containsKey(entry.getKey())) {
                        response.setState(State.FAILED);
                        response.setMessage(String.format(Constants.BEVERAGE_INGREDIENT_NOT_AVAILABLE,recipe.getName(),entry.getKey()));
                        break;
                    } else if (this.totalIngredients.get(entry.getKey()).compareTo(entry.getValue()) < 0) {
                        response.setState(State.FAILED);
                        response.setMessage(String.format(Constants.BEVERAGE_INGREDIENT_NOT_SUFFICIENT,recipe.getName(),entry.getKey()));
                        break;
                    } else {
                        this.totalIngredients.merge(entry.getKey(), (-1 * entry.getValue()), Integer::sum);
                        String test = String.format(Constants.BEVERAGE_PREPARED,recipe.getName());
                        response.setMessage(test);
                        if(this.totalIngredients.get(entry.getKey())<Constants.INGREDIENT_THRESHOLD)
                        {
                            this.ingredientStates.put(entry.getKey(),State.RUNNING_LOW);
                        }
                    }
                }
                responseList.add(response);
                if(response.getState().equals(State.PROCESSED))
                {
                    ++beverageCount;
                }
            }
            else
            {
                break;
            }
        }
        return responseList;
    }

    public CoffeeMachine(HashMap<String, Integer> totalIngredients, Integer outlets) {
        this.totalIngredients = totalIngredients;
        this.outlets = outlets;
        this.ingredientStates = new HashMap<>();
    }
}
