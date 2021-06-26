package dita.shafira.mate.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity//table
public class User {
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

    public User(String latitude, String address, String updatedAt, String name, String createdAt, String remember_token, String emailVerifiedAt, String id, String email, String longitude, String photo) {
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
}
