package com.ubs.takehome.strategy;

import com.ubs.takehome.domain.Drawing;
import com.ubs.takehome.domain.Point;
import com.ubs.takehome.exception.DrawingStrategyException;
import com.ubs.takehome.exception.PointException;
import com.ubs.takehome.exception.StrategyException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RectangleStrategyTest {

    private static final String expectedString = "--------------------\n" +
            "|             xxxxx|\n" +
            "|             x   x|\n" +
            "|             xxxxx|\n" +
            "|                  |\n" +
            "--------------------";

    private static final String onePointRect =
            "--------------------\n" +
                    "|              x   |\n" +
                    "|                  |\n" +
                    "|                  |\n" +
                    "|                  |\n" +
                    "--------------------";

    private static final String lineRect =
            "--------------------\n" +
                    "|              x   |\n" +
                    "|              x   |\n" +
                    "|                  |\n" +
                    "|                  |\n" +
                    "--------------------";


    //Testing against 1 point, rectangle having a line, reversing two points
    @DataProvider(name = "test1")
    public static Object[][] test() throws StrategyException {
        return new Object[][]{{new Point(14, 1), new Point(18, 3), expectedString},
                {new Point(18, 3), new Point(14, 1), expectedString},
                {new Point(15, 1), new Point(15, 1), onePointRect},
                {new Point(15, 1), new Point(15, 2), lineRect}};
    }
    //Testing outside the canvas for 1 points and 2 points
    @DataProvider(name = "exceptionCase")
    public static Object[][] exceptionCase() throws StrategyException {
        return new Object[][]{{new Point(22, 2), new Point(1, 3)},
                {new Point(22, 2), new Point(22, 4)}

        };
    }


    @Test(dataProvider = "test1")
    public void testDraw(Point upperLeft, Point lowerRight, String expected) throws Exception {
        Drawing drawing = new Drawing();

        CanvasStrategy canvasStrategy = new CanvasStrategy(20, 4);
        canvasStrategy.draw(drawing);


        RectangleStrategy rectangleStrategy = new RectangleStrategy(upperLeft, lowerRight);
        rectangleStrategy.draw(drawing);

        Assert.assertEquals(drawing.output(), expected);
    }

    @Test(dataProvider = "exceptionCase", expectedExceptions = PointException.class)
    public void testExceptionCase(Point upperLeft, Point lowerRight) throws StrategyException {
        Drawing drawing = new Drawing();

        CanvasStrategy canvasStrategy = new CanvasStrategy(20, 4);
        canvasStrategy.draw(drawing);

        RectangleStrategy rectangleStrategy = new RectangleStrategy(upperLeft, lowerRight);
        rectangleStrategy.draw(drawing);
    }

    @Test(expectedExceptions = DrawingStrategyException.class)
    public void testWithoutCanvas() throws StrategyException {
        Drawing drawing = new Drawing();
        RectangleStrategy rectangleStrategy = new RectangleStrategy(new Point(18, 4), new Point(18, 4));
        rectangleStrategy.draw(drawing);
    }
}