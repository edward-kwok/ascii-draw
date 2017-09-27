package com.ubs.takehome;

import com.ubs.takehome.domain.Drawing;
import com.ubs.takehome.exception.StrategyException;
import org.testng.annotations.Test;

public class CommandMatcherTest {


    @Test(expectedExceptions = StrategyException.class, expectedExceptionsMessageRegExp = "Cannot find appropriate strategy for command: O 1 2 3 4")
    public void testExceptionalCase() throws StrategyException {
        CommandMatcher.execute("O 1 2 3 4", new Drawing());
    }


}