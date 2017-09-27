package com.ubs.takehome.strategy;

import com.ubs.takehome.domain.Drawing;
import com.ubs.takehome.exception.CanvasStrategyException;
import com.ubs.takehome.exception.StrategyException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Testing against normal and exceptional Cases
 */
public class CanvasStrategyTest {

    private static final String expectedString = "" +
            "--------------------\n" +
            "|                  |\n" +
            "|                  |\n" +
            "|                  |\n" +
            "|                  |\n" +
            "--------------------";


    /**
     * Draw a ordinary canvas
     *
     * @return
     * @throws StrategyException
     */
    @DataProvider(name = "test1")
    public static Object[][] test() throws StrategyException {
        return new Object[][]{{20, 4, expectedString}};
    }

    /**
     * Cannot Draw Width = 2 ,Height = 2
     * @return
     * @throws StrategyException
     */
    @DataProvider(name = "exceptionalCase")
    public static Object[][] exceptionalCase() throws StrategyException {
        return new Object[][]{{2, 2},
                {3, 1}
        };
    }


    @Test(dataProvider = "test1")
    public void testDraw(int x1, int y1, String expected) throws Exception {
        Drawing drawing = new Drawing();
        Strategy strategy = new CanvasStrategy(x1, y1);
        strategy.draw(drawing);
        drawing.output();
        Assert.assertEquals(drawing.output(), expected);
    }

    @Test(dataProvider = "exceptionalCase", expectedExceptions = CanvasStrategyException.class)
    public void testDraw(int x1, int y1) throws Exception {
        new CanvasStrategy(x1, y1);
    }
}