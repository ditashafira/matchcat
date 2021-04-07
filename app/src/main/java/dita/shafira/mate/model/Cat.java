package dita.shafira.mate.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Cat implements Parcelable {

	@SerializedName("race")
	private Race race;

	@SerializedName("race_id")
	private int raceId;

	@SerializedName("birth")
	private String birth;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("vaccine")
	private int vaccine;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("name")
	private String name;

	@SerializedName("photos_2")
	private String photos2;

	@SerializedName("photos_3")
	private String photos3;

	@SerializedName("photos_4")
	private String photos4;

	@SerializedName("id")
	private int id;

	@SerializedName("photos_5")
	private String photos5;

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

	public String getCreatedAt(){
		return createdAt;
	}

	public int getVaccine(){
		return vaccine;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getUserId(){
		return userId;
	}

	public String getName(){
		return name;
	}

	public String getPhotos2(){
		return photos2;
	}

	public String getPhotos3(){
		return photos3;
	}

	public String getPhotos4(){
		return photos4;
	}

	public int getId(){
		return id;
	}

	public String getPhotos5(){
		return photos5;
	}

	public int getStatus(){
		return status;
	}

	public String getPhotos1(){
		return photos1;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.race, flags);
		dest.writeInt(this.raceId);
		dest.writeString(this.birth);
		dest.writeString(this.createdAt);
		dest.writeInt(this.vaccine);
		dest.writeString(this.updatedAt);
		dest.writeInt(this.userId);
		dest.writeString(this.name);
		dest.writeString(this.photos2);
		dest.writeString(this.photos3);
		dest.writeString(this.photos4);
		dest.writeInt(this.id);
		dest.writeString(this.photos5);
		dest.writeInt(this.status);
		dest.writeString(this.photos1);
	}

	public void readFromParcel(Parcel source) {
		this.race = source.readParcelable(Race.class.getClassLoader());
		this.raceId = source.readInt();
		this.birth = source.readString();
		this.createdAt = source.readString();
		this.vaccine = source.readInt();
		this.updatedAt = source.readString();
		this.userId = source.readInt();
		this.name = source.readString();
		this.photos2 = source.readString();
		this.photos3 = source.readString();
		this.photos4 = source.readString();
		this.id = source.readInt();
		this.photos5 = source.readString();
		this.status = source.readInt();
		this.photos1 = source.readString();
	}

	public Cat() {
	}

	protected Cat(Parcel in) {
		this.race = in.readParcelable(Race.class.getClassLoader());
		this.raceId = in.readInt();
		this.birth = in.readString();
		this.createdAt = in.readString();
		this.vaccine = in.readInt();
		this.updatedAt = in.readString();
		this.userId = in.readInt();
		this.name = in.readString();
		this.photos2 = in.readString();
		this.photos3 = in.readString();
		this.photos4 = in.readString();
		this.id = in.readInt();
		this.photos5 = in.readString();
		this.status = in.readInt();
		this.photos1 = in.readString();
	}

	public static final Parcelable.Creator<Cat> CREATOR = new Parcelable.Creator<Cat>() {
		@Override
		public Cat createFromParcel(Parcel source) {
			return new Cat(source);
		}

		@Override
		public Cat[] newArray(int size) {
			return new Cat[size];
		}
	};
}