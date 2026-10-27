package com.example.ggc_marketplace;

public class Model {

    //Sets up how each individual listing object is described
    private String price;
    private String name;
    private int image;
    private String type;

    //Main constructor
    public Model(String price, String name, int image, String type) {
        this.price = price;
        this.name = name;
        this.image = image;
        this.type = type;
    }

    //Getters/setters used to extract info from listings & display the info
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
