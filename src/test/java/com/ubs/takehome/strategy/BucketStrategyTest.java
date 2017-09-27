package com.ubs.takehome.strategy;

import com.ubs.takehome.domain.Drawing;
import com.ubs.takehome.domain.Point;
import com.ubs.takehome.exception.StrategyException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BucketStrategyTest {

    private final static String expectedString =
            "--------------------\n" +
                    "|hhhhhhhhhhhhhhhhhh|\n" +
                    "|hhhhhhhhhhhhhhhhhh|\n" +
                    "|hhhhhhhhhhhhhhhhhh|\n" +
                    "|hhhhhhhhhhhhhhhhhh|\n" +
                    "--------------------";

    /**
     * Fill by pointing 10,3 using h
     *
     * @return
     * @throws StrategyException
     */
    @DataProvider(name = "test1")
    public static Object[][] test() throws StrategyException {
        return new Object[][]{{new Point(10, 3), 'h', expectedString}};
    }

    /**
     * Filling outside the canvas for different cases
     *
     * @return
     * @throws StrategyException
     */
    @DataProvider(name = "exceptionalCase")
    public static Object[][] exceptionalCase() throws StrategyException {
        return new Object[][]{{new Point(20, 3), 'h'},
                {new Point(0, 0), 'h'}, {new Point(0, 5), 'h'},
                {new Point(20, 0), 'h'}, {new Point(0, 20), 'h'}};
    }


    @Test(dataProvider = "test1")
    public void testDraw(Point point, char fill, String expectedString) throws Exception {
        Drawing drawing = new Drawing();
        CanvasStrategy canvasStrategy = new CanvasStrategy(20, 4);
        canvasStrategy.draw(drawing);

        Strategy strategy = new BucketStrategy(point, fill);
        strategy.draw(drawing);
        drawing.output();
        Assert.assertEquals(drawing.output(), expectedString);
    }


    @Test(dataProvider = "exceptionalCase", expectedExceptions = StrategyException.class)
    public void testExceptionCase(Point point, char fill) throws StrategyException {
        Drawing drawing = new Drawing();
        Strategy canvasStrategy = new CanvasStrategy(20, 4);
        canvasStrategy.draw(drawing);

        Strategy bucketStrategy = new BucketStrategy(point, fill);
        bucketStrategy.draw(drawing);
    }
}