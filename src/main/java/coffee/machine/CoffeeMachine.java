package coffee.machine;

import coffee.recipe.Beverage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                response.setMessage(String.format(Constants.INGREDIENT_NOT_FOUND,ingredient));
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
    public List<Response> pour(List<Beverage> beverageList){
        List<Response> responseList = new ArrayList<>();
        Integer beverageCount = 0;
        for (Beverage beverage: beverageList) {
            if(beverageCount < outlets)
            {
                HashMap<String, Integer> ingredientMap = beverage.getIngredientMap();
                Response response = new Response(State.PROCESSED);
                for (Map.Entry<String, Integer> entry : ingredientMap.entrySet()) {

                    if (!this.totalIngredients.containsKey(entry.getKey())) {
                        response.setState(State.FAILED);
                        response.setMessage(String.format(Constants.BEVERAGE_NOT_PREPARED,beverage.getName(),entry.getKey()));
                        break;
                    } else if (this.totalIngredients.get(entry.getKey()).compareTo(entry.getValue()) < 0) {
                        response.setState(State.FAILED);
                        response.setMessage(String.format(Constants.BEVERAGE_NOT_PREPARED,beverage.getName(),entry.getKey()));
                        break;
                    } else {
                        this.totalIngredients.merge(entry.getKey(), (-1 * entry.getValue()), Integer::sum);
                        if(this.totalIngredients.get(entry.getKey())<Constants.INGREDIENT_THRESHOLD)
                        {
                            this.ingredientStates.put(entry.getKey(),State.RUNNING_LOW);
                        }
                        response.setMessage(String.format(Constants.BEVERAGE_PREPARED,beverage.getName()));
                    }

                }
                responseList.add(response);
                ++beverageCount;
            }
            else
            {
                break;
            }
        }
        return responseList;
    }

    /** GETTERS AND SETTERS  **/

    public HashMap<String, State> getIngredientStates() {
        return ingredientStates;
    }

    public void setIngredientStates(HashMap<String, State> ingredientStates) {
        this.ingredientStates = ingredientStates;
    }


    public CoffeeMachine(HashMap<String, Integer> totalIngredients, List<Response> responses, Integer outlets) {
        this.totalIngredients = totalIngredients;
        this.outlets = outlets;
    }

    public HashMap<String, Integer> getTotalIngredients() {
        return totalIngredients;
    }

    public void setTotalIngredients(HashMap<String, Integer> totalIngredients) {
        this.totalIngredients = totalIngredients;
    }

    public Integer getOutlets() {
        return outlets;
    }

    public void setOutlets(Integer outlets) {
        this.outlets = outlets;
    }
}
