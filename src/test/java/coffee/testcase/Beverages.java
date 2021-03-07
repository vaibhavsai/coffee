package coffee.testcase;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"hot_tea",
"hot_coffee",
"black_tea",
"green_tea"
})
public class Beverages implements Serializable {

@JsonProperty("hot_tea")
public HotTea hotTea;
@JsonProperty("hot_coffee")
public HotCoffee hotCoffee;
@JsonProperty("black_tea")
public BlackTea blackTea;
@JsonProperty("green_tea")
public GreenTea greenTea;
private final static long serialVersionUID = 4932862283257860780L;
}