package dita.shafira.mate.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseCat {

	@SerializedName("ResponseCat")
	private List<Cat> responseCat;

	public List<Cat> getResponseCat(){
		return responseCat;
	}
}