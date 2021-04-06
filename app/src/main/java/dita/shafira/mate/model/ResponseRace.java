package dita.shafira.mate.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseRace {

	@SerializedName("Response")
	private List<Race> response;

	public List<Race> getResponse(){
		return response;
	}
}