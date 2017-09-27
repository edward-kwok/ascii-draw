package com.ubs.takehome.reader;

import com.ubs.takehome.domain.Point;
import com.ubs.takehome.exception.StrategyException;
import com.ubs.takehome.strategy.LineStrategy;
import com.ubs.takehome.strategy.Strategy;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

public class LineReaderTest {


    @DataProvider(name = "test1")
    public static Object[][] test() throws StrategyException {
        return new Object[][]{{"L 1 2 6 2", new LineStrategy(new Point(1, 2), new Point(6, 2))}};
    }


    @Test(dataProvider = "test1")
    public void testRead(String input, Strategy lineStrategy) throws StrategyException {
        Reader reader = new LineReader();
        Optional<Strategy> strategyOptional = reader.read(input);
        if (!strategyOptional.isPresent()) {
            Assert.fail();
        }
        Assert.assertEquals(strategyOptional.get(), lineStrategy);
    }
}

