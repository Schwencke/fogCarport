package business.services;

public class CarportCalculator {

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
}
