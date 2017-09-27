package com.ubs.takehome.reader;

import com.ubs.takehome.domain.Point;
import com.ubs.takehome.exception.StrategyException;
import com.ubs.takehome.strategy.RectangleStrategy;
import com.ubs.takehome.strategy.Strategy;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

public class RectangleReaderTest {

    @DataProvider(name = "test1")
    public static Object[][] test() {
        return new Object[][]{{"R 20 4 30 5", new RectangleStrategy(new Point(20, 4), new Point(30, 5))}};
    }


    @Test(dataProvider = "test1")
    public void testRead(String input, Strategy rectangleStrategy) throws StrategyException {
        Reader reader = new RectangleReader();
        Optional<Strategy> strategyOptional = reader.read(input);
        if (!strategyOptional.isPresent()) {
            Assert.fail();
        }
        Assert.assertEquals(strategyOptional.get(), rectangleStrategy);
    }
}