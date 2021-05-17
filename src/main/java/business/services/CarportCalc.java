package business.services;

import business.entities.Material;
import business.exceptions.UserException;
import business.persistence.Database;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;

public class CarportCalc {
    MaterialFacade materialFacade;

    public CarportCalc(Database database) {
        materialFacade = new MaterialFacade(database);
    }

    //<editor-fold desc="Wood">
    // Stolper
    public List<Material> calcPost(int carportWidth, int carportLength) {

        // Set material
        int materialID = 1601;

        // Calculate
        int quantityMin = 4;

        int offsetW1 = 350;
        int offsetW2 = 350;
        int maxWidth = 5300;
        int quantityByWidth = ((carportWidth - offsetW1 - offsetW2) / (maxWidth + 1)) * 2;

        int offsetL1 = 1000;
        int offsetL2 = 200;
        int maxLength = 3300;
        int quantityByLength = ((carportLength - offsetL1 - offsetL2) / (maxLength + 1)) * 2;

        int quantity = quantityMin + quantityByWidth + quantityByLength;

        // Create list
        List<Material> result = new ArrayList<>();
        result.add(new Material(materialID, quantity));

        return result;
    }

    // Remme
    public List<Material> calcBeam(int carportWidth, int carportLength) throws UserException {

        // Get materials from database
        String description = "Remme i sider, sadles ned i stolper";
        List<Material> materialList = materialFacade.getMaterialByDescription(description);

        // Create list with available lengths
        List<Integer> availableLengths = new ArrayList<>();
        for (Material material : materialList) {
            availableLengths.add(material.getLength());
        }

        // Calculate
        int quantityMin = 2;
        int offsetW1 = 350;
        int offsetW2 = 350;
        int maxWidth = 5300;
        int quantityByWidth = (carportWidth - offsetW1 - offsetW2) / (maxWidth + 1);

        int quantity = quantityMin + quantityByWidth;

        // Add the right lengths
        List<Material> result = new ArrayList<>();
        int length = carportLength;
        for (int i = availableLengths.size() - 1; i > 0; i--) {
            if ((length) >= availableLengths.get(i)) {
                result.add(new Material(materialList.get(i).getMaterialID(), quantity));
                length -= availableLengths.get(i);
            }
        }

        // Minimum length
        if (length > 0) {
            result.add(new Material(materialList.get(0).getMaterialID(), quantity));
        }

        return result;
    }

    // Spær
    public List<Material> calcRafter(int carportWidth, int carportLength) throws UserException {

        // Get materials from database
        String description = "Spær, monteres på rem";
        List<Material> materialList = materialFacade.getMaterialByDescription(description);

        // Create list with available lengths
        List<Integer> availableLengths = new ArrayList<>();
        for (Material material : materialList) {
            availableLengths.add(material.getLength());
        }

        // Calculate
        int quantityMin = 2;
        int maxWidth = 550;
        int quantityByWidth = (carportLength - maxWidth) / (maxWidth + 1);

        int quantity = quantityMin + quantityByWidth;

        // Add the right lengths
        List<Material> result = new ArrayList<>();
        int length = carportWidth;
        for (int i = availableLengths.size() - 1; i > 0; i--) {
            if ((length) >= availableLengths.get(i)) {
                result.add(new Material(materialList.get(i).getMaterialID(), quantity));
                length -= availableLengths.get(i);
            }
        }

        // Minimum length
        if (length > 0) {
            result.add(new Material(materialList.get(0).getMaterialID(), quantity));
        }

        return result;
    }

    // Stern
    public List<Material> calcStern(int quantity, int length, String description) throws UserException {

        // Get materials from database
        List<Material> materialList = materialFacade.getMaterialByDescription(description);

        // Create list with available lengths
        List<Integer> availableLengths = new ArrayList<>();
        for (Material material : materialList) {
            availableLengths.add(material.getLength());
        }

        // Calculate
        List<Material> result = new ArrayList<>();

        int prevLength = 0;

        for (int i = availableLengths.size() - 1; i > 0; i--) {
            if ((length) >= availableLengths.get(i)) {

                if (availableLengths.get(i) != prevLength) {
                    result.add(new Material(materialList.get(i).getMaterialID(), quantity));
                    prevLength = availableLengths.get(i);
                } else {
                    int prevAmount = result.get(result.size()-1).getQuantity();
                    result.get(result.size()-1).setQuantity(prevAmount + quantity);
                }
                length -= availableLengths.get(i);

                // Compare lengths to repeat current index
                if (length > availableLengths.get(i)) {
                    i++;
                }
            }
        }

        // Minimum length size
        if (length > 0) {
            result.add(new Material(materialList.get(0).getMaterialID(), quantity));
        }

        return result;
    }

    public List<Material> calcSternUnderFrontAndBack(int carportWidth) throws UserException {
        String description = "Understernbrædder til for- & bagende";
        int surfaceNo = 2;
        return calcStern(surfaceNo, carportWidth, description);
    }

    public List<Material> calcSternUnderSides(int carportLength) throws UserException {
        String description = "Understernbrædder til siderne";
        int surfaceNo = 1;
        return calcStern(surfaceNo, carportLength, description);
    }

    public List<Material> calcSternOverFront(int carportWidth) throws UserException {
        String description = "Oversternbrædder til forende";
        int surfaceNo = 1;
        return calcStern(surfaceNo, carportWidth, description);
    }

    public List<Material> calcSternOverSides(int carportLength) throws UserException {
        String description = "Oversternbrædder til siderne";
        int surfaceNo = 1;
        return calcStern(surfaceNo, carportLength, description);
    }

    public List<Material> calcSternWaterFront(int carportWidth) throws UserException {
        String description = "Oversternbrædder til siderne";
        int surfaceNo = 1;
        return calcStern(surfaceNo, carportWidth, description);
    }

    public List<Material> calcSternWaterSides(int carportLength) throws UserException {
        String description = "Oversternbrædder til siderne";
        int surfaceNo = 1;
        return calcStern(surfaceNo, carportLength, description);
    }
    //</editor-fold>

    //<editor-fold desc="Roofing">
    // Tag
    public List<Material> calcRoofing(int carportWidth, int carportLength) throws UserException {

        // Get materials from database
        String description = "Tagplader monteres på spær";
        List<Material> materialList = materialFacade.getMaterialByDescription(description);

        // Create list with available lengths
        List<Integer> availableLengths = new ArrayList<>();
        for (Material material : materialList) {
            availableLengths.add(material.getLength());
        }

        // Calculate
        int overlapWidth = 70;
        int overlapLength = 200;
        int quantity = 0;

        // Add the right lengths
        List<Material> result = new ArrayList<>();
        int length = carportLength;
        for (int i = availableLengths.size() - 1; i > 0; i--) {
            if ((length) >= availableLengths.get(i)) {

                // Width count
                int itemWidth = materialList.get(i).getWidth() - overlapWidth;
                quantity = (int) ceil((double) carportWidth / (double) itemWidth);

                result.add(new Material(materialList.get(i).getMaterialID(), quantity));
                length -= availableLengths.get(i) - overlapLength;
            }
        }

        // Minimum length
        if (length > 0) {

            // Width count
            int itemWidth = materialList.get(0).getWidth() - overlapWidth;
            quantity = (int) ceil((double) carportWidth / (double) itemWidth);

            result.add(new Material(materialList.get(0).getMaterialID(), quantity));
        }

        return result;
    }
    //</editor-fold>
}
