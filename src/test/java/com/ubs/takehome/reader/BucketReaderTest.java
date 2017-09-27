package com.ubs.takehome.reader;

import com.ubs.takehome.domain.Point;
import com.ubs.takehome.exception.StrategyException;
import com.ubs.takehome.strategy.BucketStrategy;
import com.ubs.takehome.strategy.Strategy;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

public class BucketReaderTest {

    @DataProvider(name = "test1")
    public static Object[][] test() {
        return new Object[][]{{"B 20 4 c", new BucketStrategy(new Point(20, 4), 'c')}};
    }


    @Test(dataProvider = "test1")
    public void testRead(String input, Strategy bucketStrategy) throws StrategyException {
        Reader reader = new BucketReader();
        Optional<Strategy> strategyOptional = reader.read(input);
        if (!strategyOptional.isPresent()) {
            Assert.fail();
        }
        Assert.assertEquals(strategyOptional.get(), bucketStrategy);
    }

}
