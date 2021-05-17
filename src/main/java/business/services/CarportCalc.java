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
    public List<Material> calcPost(int carportWidth, int carportLength) throws UserException {

        // Set material
        int materialID = 1601;

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
        int minAmount = 2;

        int offsetW1 = 350;
        int offsetW2 = 350;
        int maxWidth = 5300;
        int countWidth = (carportWidth - offsetW1 - offsetW2) / (maxWidth + 1);

        int quantity = minAmount + countWidth;

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
        int minAmount = 2;

        int maxWidth = 550;
        int countWidth = (carportLength - maxWidth) / (maxWidth + 1);

        int quantity = minAmount + countWidth;

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
