package dita.shafira.mate.model;

import com.google.gson.annotations.SerializedName;

public class Cat {

	@SerializedName("race")
	private Race race;

	@SerializedName("race_id")
	private int raceId;

	@SerializedName("birth")
	private String birth;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("vaccine")
	private int vaccine;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("name")
	private String name;

	@SerializedName("photos_2")
	private Object photos2;

	@SerializedName("photos_3")
	private Object photos3;

	@SerializedName("photos_4")
	private Object photos4;

	@SerializedName("id")
	private int id;

	@SerializedName("photos_5")
	private Object photos5;

	@SerializedName("status")
	private int status;

	@SerializedName("photos_1")
	private String photos1;

	public Race getRace(){
		return race;
	}

	public int getRaceId(){
		return raceId;
	}

	public String getBirth(){
		return birth;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public int getVaccine(){
		return vaccine;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public int getUserId(){
		return userId;
	}

	public String getName(){
		return name;
	}

	public Object getPhotos2(){
		return photos2;
	}

	public Object getPhotos3(){
		return photos3;
	}

	public Object getPhotos4(){
		return photos4;
	}

	public int getId(){
		return id;
	}

	public Object getPhotos5(){
		return photos5;
	}

	public int getStatus(){
		return status;
	}

	public String getPhotos1(){
		return photos1;
	}
}