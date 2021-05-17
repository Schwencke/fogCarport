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
    public List<Object> calcPost(int carportWidth, int carportLength) throws UserException {

        // Get material from database
        int materialID = 1601;
        Material material = materialFacade.getMaterialById(materialID);

        // Calculate
        int minAmount = 4;

        int offsetW1 = 350;
        int offsetW2 = 350;
        int maxWidth = 5300;
        int countWidth = ((carportWidth - offsetW1 - offsetW2) / (maxWidth + 1)) * 2;

        int offsetL1 = 1000;
        int offsetL2 = 200;
        int maxLength = 3300;
        int countLength = ((carportLength - offsetL1 - offsetL2) / (maxLength + 1)) * 2;

        int quantity = minAmount + countWidth + countLength;

        // Create list
        List<Object> result = new ArrayList<>();
        result.add(materialID);
        result.add(quantity);
        result.add(material.getPrice());

        return result;
    }

    // Remme
    public List<Object> calcBeam(int carportWidth, int carportLength) throws UserException {

        // Get materials from database
        String description = "Remme i sider, sadles ned i stolper";
        List<Material> materialList = materialFacade.getMaterialByDescription(description);

        // Create list with availableLengths
        List<Integer> availableLenghts = new ArrayList<>();
        for (Material material : materialList) {
            availableLenghts.add(material.getLength());
        }

        // Calculate
        int minAmount = 2;

        int offsetW1 = 350;
        int offsetW2 = 350;
        int maxWidth = 5300;
        int countWidth = (carportWidth - offsetW1 - offsetW2) / (maxWidth + 1);

        int quantity = minAmount + countWidth;

        // Add the right lengths
        List<Object> result = new ArrayList<>();
        int length = carportLength;
        for (int i = availableLenghts.size() - 1; i > 0; i--) {
            if ((length) >= availableLenghts.get(i)) {
                result.add(materialList.get(i).getMaterialID());
                result.add(quantity);
                result.add(materialList.get(i).getPrice());
                length -= availableLenghts.get(i);
            }
        }

        // Minimum length
        if (length > 0) {
            result.add(materialList.get(0).getMaterialID());
            result.add(quantity);
            result.add(materialList.get(0).getPrice());
        }

        return result;
    }

    // Spær
    public List<Object> calcRafter(int carportWidth, int carportLength) throws UserException {

        // Get materials from database
        String description = "Spær, monteres på rem";
        List<Material> materialList = materialFacade.getMaterialByDescription(description);

        // Create list with availableLengths
        List<Integer> availableLenghts = new ArrayList<>();
        for (Material material : materialList) {
            availableLenghts.add(material.getLength());
        }

        // Calculate
        int minAmount = 2;

        int maxWidth = 550;
        int countWidth = (carportLength - maxWidth) / (maxWidth + 1);

        int quantity = minAmount + countWidth;

        // Add the right lengths
        List<Object> result = new ArrayList<>();
        int length = carportWidth;
        for (int i = availableLenghts.size() - 1; i > 0; i--) {
            if ((length) >= availableLenghts.get(i)) {
                result.add(materialList.get(i).getMaterialID());
                result.add(quantity);
                result.add(materialList.get(i).getPrice());
                length -= availableLenghts.get(i);
            }
        }

        // Minimum length
        if (length > 0) {
            result.add(materialList.get(0).getMaterialID());
            result.add(quantity);
            result.add(materialList.get(0).getPrice());
        }

        return result;
    }

    // Stern
    public List<Integer> calcStern(int minAmount, int size) {
        int[] sternLengths = {3600, 5400}; //{210, 240, 270, 300, 330, 360, 390, 420, 450, 480, 510, 540, 570, 600};

        List<Integer> sternList = new ArrayList<>();

        int length = size;
        int prevLength = 0;

        for (int i = sternLengths.length - 1; i > 0; i--) {
            if ((length) >= sternLengths[i]) {

                if (sternLengths[i] != prevLength) {
                    sternList.add(minAmount);
                    sternList.add(sternLengths[i]);
                    prevLength = sternLengths[i];
                } else {
                    int prevAmount = sternList.get(sternList.size() - 2);
                    sternList.set(sternList.size() - 2, prevAmount + minAmount);
                }
                length -= sternLengths[i];

                // Compare lengths to repeat current index
                if (length > sternLengths[i]) {
                    i++;
                }
            }
        }

        // Minimum length size
        if (length > 0) {
            sternList.add(minAmount);
            sternList.add(sternLengths[0]);
        }

        return sternList;
    }
    //</editor-fold>

    //<editor-fold desc="Roofing">
    // Tag
    public List<Integer> calcRoofing(int carportWidth, int carportLength) {
        int[] plateLengths = {3600, 6000}; //{240, 300, 360, 420, 480, 600};
        int overlap = 20;

        List<Integer> roofList = new ArrayList<>();

        // Width count
        int plateWidth = 1090 - 70; // The plate is by default 109 cm wide - Every added plate have an overlap of 7 cm
        int countWidth = (int) ceil((double) carportWidth / (double) plateWidth);
        roofList.add(countWidth);

        // Length sizes
        int length = carportLength;
        for (int i = plateLengths.length - 1; i > 0; i--) {
            if ((length) >= plateLengths[i]) {
                roofList.add(plateLengths[i]);
                length -= plateLengths[i] - overlap;
            }
        }

        // Minimum length size
        if (length > 0) {
            roofList.add(plateLengths[0]);
        }

        return roofList;
    }
    //</editor-fold>
}
