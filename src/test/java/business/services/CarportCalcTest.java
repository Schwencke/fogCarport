package business.services;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarportCalcTest {

    CarportCalc carportCalc = new CarportCalc();

    @Test
    void calcPost() {
        assertEquals(6, carportCalc.calcPost(600, 780));
    }

    @Test
    void calcBeam() {
        assertEquals(2, carportCalc.calcBeam(600));
    }

    @Test
    void calcRafter() {
        assertEquals(15, carportCalc.calcRafter(780));
    }

    @Test
    void calcRoofing() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(6);
        arrayList.add(600);
        arrayList.add(360);
        assertEquals(arrayList, carportCalc.calcRoofing(600, 780));
    }
}