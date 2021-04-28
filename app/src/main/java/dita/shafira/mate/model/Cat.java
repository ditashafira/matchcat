package dita.shafira.mate.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Cat implements Parcelable {

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
    @SerializedName("race")
    private Race race;
    @SerializedName("sex")
    private int sex;
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
    @SerializedName("photo")
    private String photo;
    @SerializedName("id")
    private int id;
    @SerializedName("status")
    private int status;

    public Cat() {
    }

    protected Cat(Parcel in) {
        this.race = in.readParcelable(Race.class.getClassLoader());
        this.raceId = in.readInt();
        this.sex = in.readInt();
        this.birth = in.readString();
        this.createdAt = in.readString();
        this.vaccine = in.readInt();
        this.updatedAt = in.readString();
        this.userId = in.readInt();
        this.name = in.readString();
        this.id = in.readInt();
        this.status = in.readInt();
        this.photo = in.readString();
    }

    public Race getRace() {
        return race;
    }

    public int getRaceId() {
        return raceId;
    }

    public String getBirth() {
        return birth;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getVaccine() {
        return vaccine;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.race, flags);
        dest.writeInt(this.raceId);
        dest.writeInt(this.sex);
        dest.writeString(this.birth);
        dest.writeString(this.createdAt);
        dest.writeInt(this.vaccine);
        dest.writeString(this.updatedAt);
        dest.writeInt(this.userId);
        dest.writeString(this.name);

        dest.writeInt(this.id);

        dest.writeInt(this.status);
        dest.writeString(this.photo);
    }

    public void readFromParcel(Parcel source) {
        this.race = source.readParcelable(Race.class.getClassLoader());
        this.raceId = source.readInt();
        this.sex = source.readInt();
        this.birth = source.readString();
        this.createdAt = source.readString();
        this.vaccine = source.readInt();
        this.updatedAt = source.readString();
        this.userId = source.readInt();
        this.name = source.readString();
        this.id = source.readInt();
        this.status = source.readInt();
        this.photo = source.readString();
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}