package threaded.coffee;

import coffee.machine.Constants;
import coffee.machine.Response;
import coffee.machine.State;
import coffee.recipe.Recipe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Outlet extends Thread{
    /**
     * lock total resources, decrement total resources by recipe.ingredients, remove lock, append to result list, return
     */
    private Integer Id;
    private ConcurrentHashMap<Integer, Response> resultMap;
    public Outlet(Integer id, ConcurrentHashMap resultMap){
        this.Id = id;
        this.resultMap = resultMap;
    }

    public void run(Recipe recipe, IngredientRepository ingredientRepository){
        Response response = new Response();
        try{
            this.resultMap.put(this.Id,new Response(coffee.machine.State.BUSY));
            HashMap<String, Integer> ingredientMap = recipe.getIngredientMap();
            for (Map.Entry<String, Integer> entry : ingredientMap.entrySet()) {

                if (!ingredientRepository.getIngredientRepo().contains(entry.getKey())) {
                    response.setState(coffee.machine.State.FAILED);
                    response.setMessage(String.format(Constants.BEVERAGE_INGREDIENT_NOT_AVAILABLE,recipe.getName(),entry.getKey()));
                    break;
                } else if (ingredientRepository.getIngredientRepo().get(entry.getKey()).compareTo(entry.getValue()) < 0) {
                    response.setState(coffee.machine.State.FAILED);
                    response.setMessage(String.format(Constants.BEVERAGE_INGREDIENT_NOT_SUFFICIENT,recipe.getName(),entry.getKey()));
                    break;
                } else {
                    ingredientRepository.getIngredientRepo().merge(entry.getKey(), (-1 * entry.getValue()), Integer::sum);
                    String test = String.format(Constants.BEVERAGE_PREPARED,recipe.getName());
                    response.setMessage(test);
                    if(ingredientRepository.getIngredientRepo().get(entry.getKey())<Constants.INGREDIENT_THRESHOLD)
                    {
                        ingredientRepository.getIngredientStates().put(entry.getKey(),coffee.machine.State.RUNNING_LOW);
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(String.format("Exception has occured, %s",e.getCause()));
        }
        resultMap.put(this.Id,response);
    }

    @Override
    public long getId() {
        return this.Id;
    }
}
