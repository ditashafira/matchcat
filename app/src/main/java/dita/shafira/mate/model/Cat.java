package dita.shafira.mate.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Cat implements Parcelable {


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
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("vaccine")
    private int vaccine;
    @SerializedName("last_vaccine")
    private String lastVaccine;
    @SerializedName("last_parasite")
    private String lastParasite;
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
    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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


    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLastVaccine() {
        return lastVaccine;
    }

    public void setLastVaccine(String lastVaccine) {
        this.lastVaccine = lastVaccine;
    }

    public String getLastParasite() {
        return lastParasite;
    }

    public void setLastParasite(String lastParasite) {
        this.lastParasite = lastParasite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.race, flags);
        dest.writeInt(this.sex);
        dest.writeInt(this.raceId);
        dest.writeString(this.birth);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeInt(this.vaccine);
        dest.writeString(this.lastVaccine);
        dest.writeString(this.lastParasite);
        dest.writeInt(this.userId);
        dest.writeString(this.name);
        dest.writeString(this.photo);
        dest.writeInt(this.id);
        dest.writeInt(this.status);
        dest.writeParcelable(this.user, flags);
    }

    public void readFromParcel(Parcel source) {
        this.race = source.readParcelable(Race.class.getClassLoader());
        this.sex = source.readInt();
        this.raceId = source.readInt();
        this.birth = source.readString();
        this.createdAt = source.readString();
        this.updatedAt = source.readString();
        this.vaccine = source.readInt();
        this.lastVaccine = source.readString();
        this.lastParasite = source.readString();
        this.userId = source.readInt();
        this.name = source.readString();
        this.photo = source.readString();
        this.id = source.readInt();
        this.status = source.readInt();
        this.user = source.readParcelable(User.class.getClassLoader());
    }

    public Cat() {
    }

    protected Cat(Parcel in) {
        this.race = in.readParcelable(Race.class.getClassLoader());
        this.sex = in.readInt();
        this.raceId = in.readInt();
        this.birth = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.vaccine = in.readInt();
        this.lastVaccine = in.readString();
        this.lastParasite = in.readString();
        this.userId = in.readInt();
        this.name = in.readString();
        this.photo = in.readString();
        this.id = in.readInt();
        this.status = in.readInt();
        this.user = in.readParcelable(User.class.getClassLoader());
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