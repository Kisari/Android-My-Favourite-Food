package rmitcom.asm1.myfavouritefood.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Food implements Serializable {
    private String id;
    private String building_id;
    private String name;
    private String short_name;
    private String description;
    private String url;
    private ArrayList<String> tags;
    private String image;
    private String campus;
    private String lat;
    private String lng;
    private String address;
    private ArrayList<day> hours;

    public Food(String id, String name, String short_name, String description, String url, ArrayList<String> tags, String image, String campus, String lat, String lng, String address) {
        this.id = id;
        this.name = name;
        this.short_name = short_name;
        this.description = description;
        this.url = url;
        this.tags = tags;
        this.image = image;
        this.campus = campus;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShort_name() {
        return short_name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getImage() {
        return image;
    }

    public String getCampus() {
        return campus;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getAddress() {
        return address;
    }

    private static class day{
        private Boolean closed;
        private int open;
        private int close;

        public day(Boolean closed, int open, int close) {
            this.closed = closed;
            this.open = open;
            this.close = close;
        }
    }
}