package com.ubs.takehome.reader;

import com.ubs.takehome.exception.CanvasStrategyException;
import com.ubs.takehome.exception.StrategyException;
import com.ubs.takehome.strategy.CanvasStrategy;
import com.ubs.takehome.strategy.Strategy;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

public class CanvasReaderTest {


    @DataProvider(name = "test1")
    public static Object[][] test() throws CanvasStrategyException {
        return new Object[][]{{"C 20 4", new CanvasStrategy(20, 4)}};
    }


    @Test(dataProvider = "test1")
    public void testRead(String input, Strategy canvasStrategy) throws StrategyException {
        Reader reader = new CanvasReader();
        Optional<Strategy> strategyOptional = reader.read(input);
        if (!strategyOptional.isPresent()) {
            Assert.fail();
        }
        Assert.assertEquals(strategyOptional.get(), canvasStrategy);
    }
}