package coffee.testcase;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"hot_water",
"hot_milk",
"ginger_syrup",
"sugar_syrup",
"tea_leaves_syrup"
})
public class HotTea implements Serializable {

@JsonProperty("hot_water")
public Integer hotWater;
@JsonProperty("hot_milk")
public Integer hotMilk;
@JsonProperty("ginger_syrup")
public Integer gingerSyrup;
@JsonProperty("sugar_syrup")
public Integer sugarSyrup;
@JsonProperty("tea_leaves_syrup")
public Integer teaLeavesSyrup;

}