package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Настя on 17.02.2017.
 */@Test
public class PointTests {
     public void TestDistance1() {
         Point pTest1 = new Point(10,10);
         Point pTest2 = new Point (8,7);
         Assert.assertEquals(pTest1.distance(pTest2),3.605551275463989);

    }

    public void TestDistance2() {
        Point pTest1 = new Point(0,0);
        Point pTest2 = new Point (0,0);
        Assert.assertEquals(pTest1.distance(pTest2),0.0);

    }

    public void TestDistance3() {
        Point pTest1 = new Point(0,0);
        Point pTest2 = new Point (-2,-2);
        Assert.assertEquals(pTest1.distance(pTest2),2.8284271247461903);

    }

}
