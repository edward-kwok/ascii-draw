package com.ubs.takehome.strategy;

import com.ubs.takehome.domain.Drawing;
import com.ubs.takehome.domain.Point;
import com.ubs.takehome.exception.LineStrategyException;
import com.ubs.takehome.exception.StrategyException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LineStrategyTest {

    private static final String expectedHorizontalLine = "" +
            "--------------------\n" +
            "|                  |\n" +
            "|xxxxxx            |\n" +
            "|                  |\n" +
            "|                  |\n" +
            "--------------------";

    private static final String expectedVerticalLine =
            "--------------------\n" +
                    "|                  |\n" +
                    "|     x            |\n" +
                    "|     x            |\n" +
                    "|                  |\n" +
                    "--------------------";
    private static final String expectedPoint =
            "--------------------\n" +
                    "|                  |\n" +
                    "|                  |\n" +
                    "|                  |\n" +
                    "|                 x|\n" +
                    "--------------------";


    /**
     * Testing drawing a line, reversing two points and draw a line, and a "point" line
     *
     * @return
     * @throws StrategyException
     */
    @DataProvider(name = "test1")
    public static Object[][] test() throws StrategyException {
        return new Object[][]{{new Point(1, 2), new Point(6, 2), expectedHorizontalLine},
                {new Point(6, 2), new Point(6, 3), expectedVerticalLine},
                {new Point(6, 3), new Point(6, 2), expectedVerticalLine},
                {new Point(18, 4), new Point(18, 4), expectedPoint}
        };
    }

    /**
     * Testing against non-straight line, outside canvas
     *
     * @return
     * @throws StrategyException
     */
    @DataProvider(name = "exceptionCase")
    public static Object[][] exceptionCase() throws StrategyException {
        return new Object[][]{{new Point(21, 4), new Point(2, 2)},
                {new Point(19, 5), new Point(19, 5)},
                {new Point(0, 0), new Point(0, 0)}
        };
    }


    @Test(dataProvider = "test1")
    public void testDraw(Point x1, Point x2, String expected) throws Exception {
        Drawing drawing = new Drawing();
        CanvasStrategy canvasStrategy = new CanvasStrategy(20, 4);
        canvasStrategy.draw(drawing);
        Strategy strategy = new LineStrategy(x1, x2);
        strategy.draw(drawing);
        drawing.output();
        Assert.assertEquals(drawing.output(), expected);
    }


    @Test(dataProvider = "exceptionCase", expectedExceptions = StrategyException.class)
    public void testException(Point x1, Point x2) throws StrategyException {
        Drawing drawing = new Drawing();
        CanvasStrategy canvasStrategy = new CanvasStrategy(20, 4);
        canvasStrategy.draw(drawing);
        Strategy strategy = new LineStrategy(x1, x2);
        strategy.draw(drawing);
    }

    @Test(expectedExceptions = LineStrategyException.class)
    public void testNotStraightLine() throws StrategyException {
        Drawing drawing = new Drawing();
        CanvasStrategy canvasStrategy = new CanvasStrategy(20, 4);
        canvasStrategy.draw(drawing);
        Strategy strategy = new LineStrategy(new Point(3, 4), new Point(1, 2));
        strategy.draw(drawing);
    }

}