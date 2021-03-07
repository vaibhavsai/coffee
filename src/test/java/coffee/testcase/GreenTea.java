package coffee.testcase;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"hot_water",
"ginger_syrup",
"sugar_syrup",
"green_mixture"
})
public class GreenTea implements Serializable {

@JsonProperty("hot_water")
public Integer hotWater;
@JsonProperty("ginger_syrup")
public Integer gingerSyrup;
@JsonProperty("sugar_syrup")
public Integer sugarSyrup;
@JsonProperty("green_mixture")
public Integer greenMixture;

}