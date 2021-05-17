package business.entities;

public class Material {
    private int materialID;
    private String name;
    private String description;
    private double price;
    private int unitId;
    private int width;
    private int length;
    private int height;

    public Material(int materialID, String name, String description, double price, int unitId, int width, int length, int height) {
        this.materialID = materialID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.unitId = unitId;
        this.width = width;
        this.length = length;
        this.height = height;
    }

    //<editor-fold desc="Getters and setters">
    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    //</editor-fold>
}
