package dita.shafira.mate.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Race implements Parcelable {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.updatedAt);
		dest.writeString(this.createdAt);
		dest.writeInt(this.id);
		dest.writeString(this.title);
	}

	public void readFromParcel(Parcel source) {
		this.updatedAt = source.readString();
		this.createdAt = source.readString();
		this.id = source.readInt();
		this.title = source.readString();
	}

	public Race() {
	}

	protected Race(Parcel in) {
		this.updatedAt = in.readString();
		this.createdAt = in.readString();
		this.id = in.readInt();
		this.title = in.readString();
	}

	public static final Parcelable.Creator<Race> CREATOR = new Parcelable.Creator<Race>() {
		@Override
		public Race createFromParcel(Parcel source) {
			return new Race(source);
		}

		@Override
		public Race[] newArray(int size) {
			return new Race[size];
		}
	};
}