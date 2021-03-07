package coffee.testcase;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"outlets",
"total_items_quantity",
"beverages"
})
public class Machine implements Serializable {

@JsonProperty("outlets")
public Outlets outlets;
@JsonProperty("total_items_quantity")
public TotalItemsQuantity totalItemsQuantity;
@JsonProperty("beverages")
public Beverages beverages;

}