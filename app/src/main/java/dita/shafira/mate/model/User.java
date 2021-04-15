package dita.shafira.mate.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity//table
public class User{
	@ColumnInfo//menandakan nama kolom
	private String langitude;
	@ColumnInfo
	private String address;
	@ColumnInfo
	private String updatedAt;
	@ColumnInfo
	private String name;
	@ColumnInfo
	private String createdAt;
	@ColumnInfo
	private String remember_token;
	@ColumnInfo
	private String emailVerifiedAt;
	@PrimaryKey
	@NonNull
	private String id;
	@ColumnInfo
	private String email;
	@ColumnInfo
	private String longitude;

	public User(String langitude, String address, String updatedAt, String name, String createdAt, String remember_token, String emailVerifiedAt, String id, String email, String longitude) {
		this.langitude = langitude;
		this.address = address;
		this.updatedAt = updatedAt;
		this.name = name;
		this.createdAt = createdAt;
		this.remember_token = remember_token;
		this.emailVerifiedAt = emailVerifiedAt;
		this.id = id;
		this.email = email;
		this.longitude = longitude;
	}

	public String getRemember_token() {
		return remember_token;
	}

	public void setRemember_token(String remember_token) {
		this.remember_token = remember_token;
	}

	public String getLangitude(){
		return langitude;
	}

	public String getAddress(){
		return address;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getName(){
		return name;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public String getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}

	public String getLongitude(){
		return longitude;
	}
}
