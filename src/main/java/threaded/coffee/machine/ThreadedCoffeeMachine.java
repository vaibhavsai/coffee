package threaded.coffee.machine;

import coffee.machine.Response;
import coffee.machine.State;
import coffee.recipe.Recipe;
import threaded.coffee.IngredientRepository;
import threaded.coffee.Outlet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadedCoffeeMachine {
    private IngredientRepository ingredientRepository;
    private List<Outlet> outlets;
    private ConcurrentHashMap<Integer, Response> resultMap;
    public ThreadedCoffeeMachine(IngredientRepository ingredientRepository, Integer numOutlets) {
        this.ingredientRepository = ingredientRepository;
        this.resultMap = new ConcurrentHashMap<>();
        this.outlets = new ArrayList<>();
        for(int i =0;i< numOutlets; i++)
        {
            Outlet outlet = new Outlet(i,resultMap);
            outlets.add(outlet);
        }
    }

    public List<Response> start(List<Recipe> recipes)
    {
        List<Response> responseList = new ArrayList<>();
        for(Recipe recipe: recipes){
            outlets.forEach(outlet->{
                if(!resultMap.contains(outlet.getId())||
                !resultMap.get(outlet.getId()).getState().equals(State.BUSY)) {
                    responseList.add(resultMap.get(Math.toIntExact(outlet.getId())));
//                    resultMap.put(Math.toIntExact(outlet.getId()),new Response());
                    outlet.run(recipe, this.ingredientRepository);
                }
            });
        }
        return responseList;
    }

}
