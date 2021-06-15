package dita.shafira.mate.model;

public class CatSearch {
    private int id ;
    private int user_id;
    private String name;
    private String birth;
    private String photo;
    private String race;
    private double distance;

    public CatSearch(int id,int user_id, String name, String birth, String photo, String race, double distance) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.birth = birth;
        this.photo = photo;
        this.race = race;
        this.distance = distance;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
