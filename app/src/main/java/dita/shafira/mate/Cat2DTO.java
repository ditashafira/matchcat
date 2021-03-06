package dita.shafira.mate;

import java.io.Serializable;

public class Cat2DTO implements Serializable {
	private int raceId;
	private String birth;
	private int vaccine;
	private int userId;
	private String name;
	private int id;
	private int status;
	private String photos1;

	public void setRaceId(int raceId){
		this.raceId = raceId;
	}

	public int getRaceId(){
		return raceId;
	}

	public void setBirth(String birth){
		this.birth = birth;
	}

	public String getBirth(){
		return birth;
	}

	public void setVaccine(int vaccine){
		this.vaccine = vaccine;
	}

	public int getVaccine(){
		return vaccine;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	public void setPhotos1(String photos1){
		this.photos1 = photos1;
	}

	public String getPhotos1(){
		return photos1;
	}

	@Override
 	public String toString(){
		return 
			"Cat2DTO{" + 
			"race_id = '" + raceId + '\'' + 
			",birth = '" + birth + '\'' + 
			",vaccine = '" + vaccine + '\'' + 
			",user_id = '" + userId + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			",photos_1 = '" + photos1 + '\'' + 
			"}";
		}
}