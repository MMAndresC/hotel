package domain;

public class Bicycle {

    private int cod;
    private String brand;
    private String model;
    private String type;
    private String size;
    private String image;

    public Bicycle(int cod, String brand, String model, String type, String size, String image){
        this.cod = cod;
        this.brand = brand;
        this.model = model;
        this.size = size;
        this.type = type;
        this.image = image;
    }

    public Bicycle() {}

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
