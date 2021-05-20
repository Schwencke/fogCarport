package business.entities;

import java.util.List;

public class BoM {
    List<Material> materials;
    double basePrice;
    double margin = 80;

    public BoM() {
    }

    public BoM(List<Material> materials, double basePrice, double margin) {
        this.materials = materials;
        this.basePrice = basePrice;
        this.margin = margin;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }
}
