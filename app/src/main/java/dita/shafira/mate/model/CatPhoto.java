package dita.shafira.mate.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CatPhoto implements Parcelable {
    private int id;
    private int cat_id;
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.cat_id);
        dest.writeString(this.path);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.cat_id = source.readInt();
        this.path = source.readString();
    }

    public CatPhoto() {
    }

    protected CatPhoto(Parcel in) {
        this.id = in.readInt();
        this.cat_id = in.readInt();
        this.path = in.readString();
    }

    public static final Parcelable.Creator<CatPhoto> CREATOR = new Parcelable.Creator<CatPhoto>() {
        @Override
        public CatPhoto createFromParcel(Parcel source) {
            return new CatPhoto(source);
        }

        @Override
        public CatPhoto[] newArray(int size) {
            return new CatPhoto[size];
        }
    };
}
