package business.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarportCalculatorTest {

    CarportCalculator carportCalculator = new CarportCalculator();

    @Test
    void calcPost() {
        assertEquals(6, carportCalculator.calcPost(600, 780));
    }

    @Test
    void calcBeam() {
        assertEquals(2, carportCalculator.calcBeam(600));
    }

    @Test
    void calcRafter() {
        assertEquals(15, carportCalculator.calcRafter(780));
    }
}