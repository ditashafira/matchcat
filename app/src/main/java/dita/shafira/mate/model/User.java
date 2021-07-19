package dita.shafira.mate.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity//table
public class User implements Parcelable {
    @ColumnInfo//menandakan nama kolom
    private String latitude;
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
    @ColumnInfo
    private String photo;
    @ColumnInfo
    private int status;


    public User(String latitude, String address, String updatedAt, String name, String createdAt, String remember_token, String emailVerifiedAt, String id, String email, String longitude, String photo,int status) {
        this.latitude = latitude;
        this.address = address;
        this.updatedAt = updatedAt;
        this.name = name;
        this.createdAt = createdAt;
        this.remember_token = remember_token;
        this.emailVerifiedAt = emailVerifiedAt;
        this.id = id;
        this.email = email;
        this.longitude = longitude;
        this.photo = photo;
        this.status=status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(String emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.latitude);
        dest.writeString(this.address);
        dest.writeString(this.updatedAt);
        dest.writeString(this.name);
        dest.writeString(this.createdAt);
        dest.writeString(this.remember_token);
        dest.writeString(this.emailVerifiedAt);
        dest.writeString(this.id);
        dest.writeString(this.email);
        dest.writeString(this.longitude);
        dest.writeString(this.photo);
        dest.writeInt(this.status);
    }

    public void readFromParcel(Parcel source) {
        this.latitude = source.readString();
        this.address = source.readString();
        this.updatedAt = source.readString();
        this.name = source.readString();
        this.createdAt = source.readString();
        this.remember_token = source.readString();
        this.emailVerifiedAt = source.readString();
        this.id = source.readString();
        this.email = source.readString();
        this.longitude = source.readString();
        this.photo = source.readString();
        this.status=source.readInt();
    }

    protected User(Parcel in) {
        this.latitude = in.readString();
        this.address = in.readString();
        this.updatedAt = in.readString();
        this.name = in.readString();
        this.createdAt = in.readString();
        this.remember_token = in.readString();
        this.emailVerifiedAt = in.readString();
        this.id = in.readString();
        this.email = in.readString();
        this.longitude = in.readString();
        this.photo = in.readString();
        this.status=in.readInt();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
