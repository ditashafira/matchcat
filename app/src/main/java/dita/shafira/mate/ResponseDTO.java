package dita.shafira.mate;

import java.io.Serializable;

public class ResponseDTO implements Serializable {
	private int catId1;
	private int catId2;
	private Cat2DTO cat2;
	private int id;
	private int status;

	public void setCatId1(int catId1){
		this.catId1 = catId1;
	}

	public int getCatId1(){
		return catId1;
	}

	public void setCatId2(int catId2){
		this.catId2 = catId2;
	}

	public int getCatId2(){
		return catId2;
	}

	public void setCat2(Cat2DTO cat2){
		this.cat2 = cat2;
	}

	public Cat2DTO getCat2(){
		return cat2;
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

	@Override
 	public String toString(){
		return 
			"ResponseDTO{" + 
			"cat_id_1 = '" + catId1 + '\'' + 
			",cat_id_2 = '" + catId2 + '\'' + 
			",cat_2 = '" + cat2 + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}