package business.services;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;

public class CarportCalc {

    //<editor-fold desc="Wood">
    public int calcPost(int carportWidth, int carportLength) {
        int minAmount = 4;

        int offsetW1 = 35;
        int offsetW2 = 35;
        int maxWidth = 530;
        int countWidth = ((carportWidth - offsetW1 - offsetW2) / (maxWidth + 1)) * 2;

        int offsetL1 = 100;
        int offsetL2 = 20;
        int maxLength = 330;
        int countLength = ((carportLength - offsetL1 - offsetL2) / (maxLength + 1)) * 2;

        return minAmount + countWidth + countLength;
    }

    public int calcBeam(int carportWidth) {
        int minAmount = 2;

        int offsetW1 = 35;
        int offsetW2 = 35;
        int maxWidth = 530;
        int countWidth = (carportWidth - offsetW1 - offsetW2) / (maxWidth + 1);

        return minAmount + countWidth;
    }

    public int calcRafter(int carportLength) {
        int minAmount = 2;

        int maxWidth = 55;
        int countWidth = carportLength / (maxWidth + 1);

        return minAmount + countWidth;
    }
    //</editor-fold>

    //<editor-fold desc="Cladding">

    //</editor-fold>

    //<editor-fold desc="Roofing">
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

    //<editor-fold desc="Attachment">

    // </editor-fold>
}
