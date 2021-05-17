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

        int offsetW1 = 35;
        int offsetW2 = 35;
        int maxWidth = 530;
        int countWidth = ((carportWidth - offsetW1 - offsetW2) / (maxWidth + 1)) * 2;

        int offsetL1 = 100;
        int offsetL2 = 20;
        int maxLength = 330;
        int countLength = ((carportLength - offsetL1 - offsetL2) / (maxLength + 1)) * 2;

        int postTotal = minAmount + countWidth + countLength;

        // Create list
        List<Object> result = new ArrayList<>();
        result.add(materialID);
        result.add(postTotal);
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
        List<Object> result = new ArrayList<>();
        int minAmount = 2;
        int offsetW1 = 35;
        int offsetW2 = 35;
        int maxWidth = 530;
        int countWidth = (carportWidth - offsetW1 - offsetW2) / (maxWidth + 1);

        // Length sizes
        int length = carportLength;
        for (int i = availableLenghts.size() - 1; i > 0; i--) {
            if ((length) >= availableLenghts.get(i)) {
                result.add(materialList.get(i).getMaterialID());
                result.add(minAmount + countWidth);
                result.add(materialList.get(i).getPrice());
                length -= availableLenghts.get(i);
            }
        }

        // Minimum length size
        if (length > 0) {
            result.add(materialList.get(0).getMaterialID());
            result.add(minAmount + countWidth);
            result.add(materialList.get(0).getPrice());
        }

        //return minAmount + countWidth;
        return result;
    }

    // Spær
    public int calcRafter(int carportLength) {
        int minAmount = 2;

        int maxWidth = 55;
        int countWidth = carportLength / (maxWidth + 1);

        return minAmount + countWidth;
    }

    // Stern
    public List<Integer> calcStern(int minAmount, int size) {
        int[] sternLengths = {360, 540}; //{210, 240, 270, 300, 330, 360, 390, 420, 450, 480, 510, 540, 570, 600};

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
        int[] plateLengths = {360, 600}; //{240, 300, 360, 420, 480, 600};
        int overlap = 20;

        List<Integer> roofList = new ArrayList<>();

        // Width count
        int plateWidth = 109 - 7; // The plate is by default 109 cm wide - Every added plate have an overlap of 7 cm
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
