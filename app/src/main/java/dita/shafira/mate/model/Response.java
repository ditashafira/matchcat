package dita.shafira.mate.model;

import com.google.gson.annotations.SerializedName;

public class Response{
	private String msg;
	private String errors;
	private String status;
	@SerializedName("mating")
	private Mating mating;

	public String getMsg(){
		return msg;
	}

	public String getErrors(){
		return errors;
	}

	public String getStatus(){
		return status;
	}

	public Mating getMating(){return  mating;}
}
