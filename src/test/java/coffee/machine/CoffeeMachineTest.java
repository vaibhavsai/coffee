package coffee.machine;
import coffee.recipe.Recipe;
import coffee.testcase.Testcase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

public class CoffeeMachineTest {

    private ObjectMapper objectMapper;
    private CoffeeMachine coffeeMachine;
    private HashMap<String, Integer> totalIngredients;
    private List<Recipe> recipeList;
    private Integer outlets;
    private Testcase testcase;
    private Integer expected_count_success;
    /**
     * I have used a plain json2Pojo converter online to generate classes based on json input you have provided.
     * Pay no attention to the classes and their structure under src/test/java/coffee/testcase
     * @throws IOException
     */
    @Before
    public void setUp() throws IOException
    {
        objectMapper = new ObjectMapper();
//        Machine machine = objectMapper.readValue(new File("/Users/sai.vaibhav/Desktop/Career/coffeeMachine/src/test/resources/test.json"), Machine.class);
        testcase = objectMapper.readValue(new File("/Users/sai.vaibhav/Desktop/Career/coffeeMachine/src/test/resources/test.json"), Testcase.class);
        this.outlets = testcase.machine.outlets.countN;
        this.totalIngredients = new HashMap<>();

        //load total ingredients
        this.totalIngredients.put("ginger_syrup", testcase.machine.totalItemsQuantity.gingerSyrup);
        this.totalIngredients.put("hot_milk",testcase.machine.totalItemsQuantity.hotMilk);
        this.totalIngredients.put("hot_water",testcase.machine.totalItemsQuantity.hotWater);
        this.totalIngredients.put("sugar_syrup",testcase.machine.totalItemsQuantity.sugarSyrup);
        this.totalIngredients.put("tea_leaves_syrup",testcase.machine.totalItemsQuantity.teaLeavesSyrup);

        this.recipeList = new ArrayList<>();
        /**
         * Load recipes into request list
         */
        //load hot_tea
//        String hot_tea = testcase.machine.beverages.hotTea.toString();
        String hot_tea = "hot_tea";
        HashMap<String, Integer> hotTeaIngredientMap = new HashMap<>();
        hotTeaIngredientMap.put("ginger_syrup",testcase.machine.beverages.hotTea.gingerSyrup);
        hotTeaIngredientMap.put("hot_milk",testcase.machine.beverages.hotTea.hotMilk);
        hotTeaIngredientMap.put("hot_water",testcase.machine.beverages.hotTea.hotWater);
        hotTeaIngredientMap.put("sugar_syrup",testcase.machine.beverages.hotTea.sugarSyrup);
        hotTeaIngredientMap.put("tea_leaves_syrup",testcase.machine.beverages.hotTea.teaLeavesSyrup);
        Recipe hotTea = new Recipe(hot_tea,hotTeaIngredientMap);
        this.recipeList.add(hotTea);

        //load hot_coffee
//        String hot_coffee = testcase.machine.beverages.hotCoffee.toString();
        String hot_coffee = "hot_coffee";
        HashMap<String, Integer> hotCoffeeIngredientMap = new HashMap<>();
        hotCoffeeIngredientMap.put("ginger_syrup",testcase.machine.beverages.hotCoffee.gingerSyrup);
        hotCoffeeIngredientMap.put("hot_milk",testcase.machine.beverages.hotCoffee.hotMilk);
        hotCoffeeIngredientMap.put("hot_water",testcase.machine.beverages.hotCoffee.hotWater);
        hotCoffeeIngredientMap.put("sugar_syrup",testcase.machine.beverages.hotCoffee.sugarSyrup);
        hotCoffeeIngredientMap.put("tea_leaves_syrup",testcase.machine.beverages.hotCoffee.teaLeavesSyrup);
        Recipe hotCoffee = new Recipe(hot_coffee,hotCoffeeIngredientMap);
        this.recipeList.add(hotCoffee);

        //load black_tea
//        String black_tea = testcase.machine.beverages.blackTea.toString();
        String black_tea = "black_tea";
        HashMap<String, Integer> blackTeaIngredientMap = new HashMap<>();
        blackTeaIngredientMap.put("ginger_syrup",testcase.machine.beverages.blackTea.gingerSyrup);
        blackTeaIngredientMap.put("hot_water",testcase.machine.beverages.blackTea.hotWater);
        blackTeaIngredientMap.put("sugar_syrup",testcase.machine.beverages.blackTea.sugarSyrup);
        blackTeaIngredientMap.put("tea_leaves_syrup",testcase.machine.beverages.blackTea.teaLeavesSyrup);
        Recipe blackTea = new Recipe(black_tea,blackTeaIngredientMap);
        this.recipeList.add(blackTea);

        //load green_tea
//        String green_tea = testcase.machine.beverages.greenTea.toString();
        String green_tea = "green_tea";
        HashMap<String, Integer> greenTeaIngredientMap = new HashMap<>();
        greenTeaIngredientMap.put("ginger_syrup",testcase.machine.beverages.greenTea.gingerSyrup);
        greenTeaIngredientMap.put("hot_water",testcase.machine.beverages.greenTea.hotWater);
        greenTeaIngredientMap.put("sugar_syrup",testcase.machine.beverages.greenTea.sugarSyrup);
        greenTeaIngredientMap.put("green_mixture",testcase.machine.beverages.greenTea.greenMixture);
        Recipe greenTea = new Recipe(green_tea,greenTeaIngredientMap);
        this.recipeList.add(greenTea);

        this.coffeeMachine = new CoffeeMachine(this.totalIngredients,this.outlets);

        this.expected_count_success = 2;
    }

    @Test
    public void testCoffeeMachineWithGivenTestCase()
    {

        List<Response> responseList = this.coffeeMachine.pour(this.recipeList);
        responseList.size();
        AtomicReference<Integer> count_success = new AtomicReference<>(0);
        responseList.forEach(response -> {
            if(response.getState().equals(State.PROCESSED))
                count_success.set(count_success.get() + 1);
        });
        assertEquals("Expected is not actual", expected_count_success, count_success.get());
    }

}
