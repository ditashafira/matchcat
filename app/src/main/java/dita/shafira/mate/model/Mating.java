package dita.shafira.mate.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Mating implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("cat_id_1")
    private int catId1;
    @SerializedName("cat_id_2")
    private int catId2;
    @SerializedName("status_chat")
    private int statusChat;
    @SerializedName("status_mate")
    private int statusMate;
    @SerializedName("user_id_1_read")
    private int userId1;
    @SerializedName("user_id_2_read")
    private int userId2;
    @SerializedName("last_chat")
    private String lastChat;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("cat_1")
    private Cat cat1;
    @SerializedName("cat_2")
    private Cat cat2;


    public Mating(int id, int catId1, int catId2, int statusChat, int statusMate, int userId1, int userId2, String lastChat, String createdAt, String updatedAt, Cat cat1, Cat cat2) {
        this.id = id;
        this.catId1 = catId1;
        this.catId2 = catId2;
        this.statusChat = statusChat;
        this.statusMate = statusMate;
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.lastChat = lastChat;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.cat1 = cat1;
        this.cat2 = cat2;
    }
    public Mating(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatId1() {
        return catId1;
    }

    public void setCatId1(int catId1) {
        this.catId1 = catId1;
    }

    public int getCatId2() {
        return catId2;
    }

    public void setCatId2(int catId2) {
        this.catId2 = catId2;
    }

    public int getStatusChat() {
        return statusChat;
    }

    public void setStatusChat(int statusChat) {
        this.statusChat = statusChat;
    }

    public int getStatusMate() {
        return statusMate;
    }

    public void setStatusMate(int statusMate) {
        this.statusMate = statusMate;
    }

    public int getUserId1() {
        return userId1;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    public int getUserId2() {
        return userId2;
    }

    public void setUserId2(int userId2) {
        this.userId2 = userId2;
    }

    public String getLastChat() {
        return lastChat;
    }

    public void setLastChat(String lastChat) {
        this.lastChat = lastChat;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Cat getCat1() {
        return cat1;
    }

    public void setCat1(Cat cat1) {
        this.cat1 = cat1;
    }

    public Cat getCat2() {
        return cat2;
    }

    public void setCat2(Cat cat2) {
        this.cat2 = cat2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.catId1);
        dest.writeInt(this.catId2);
        dest.writeInt(this.statusChat);
        dest.writeInt(this.statusMate);
        dest.writeInt(this.userId1);
        dest.writeInt(this.userId2);
        dest.writeString(this.lastChat);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeParcelable(this.cat1, flags);
        dest.writeParcelable(this.cat2, flags);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.catId1 = source.readInt();
        this.catId2 = source.readInt();
        this.statusChat = source.readInt();
        this.statusMate = source.readInt();
        this.userId1 = source.readInt();
        this.userId2 = source.readInt();
        this.lastChat = source.readString();
        this.createdAt = source.readString();
        this.updatedAt = source.readString();
        this.cat1 = source.readParcelable(Cat.class.getClassLoader());
        this.cat2 = source.readParcelable(Cat.class.getClassLoader());
    }

    protected Mating(Parcel in) {
        this.id = in.readInt();
        this.catId1 = in.readInt();
        this.catId2 = in.readInt();
        this.statusChat = in.readInt();
        this.statusMate = in.readInt();
        this.userId1 = in.readInt();
        this.userId2 = in.readInt();
        this.lastChat = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.cat1 = in.readParcelable(Cat.class.getClassLoader());
        this.cat2 = in.readParcelable(Cat.class.getClassLoader());
    }

    public static final Parcelable.Creator<Mating> CREATOR = new Parcelable.Creator<Mating>() {
        @Override
        public Mating createFromParcel(Parcel source) {
            return new Mating(source);
        }

        @Override
        public Mating[] newArray(int size) {
            return new Mating[size];
        }
    };
}
