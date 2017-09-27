package com.ubs.takehome.reader;

import com.ubs.takehome.exception.StrategyException;
import com.ubs.takehome.strategy.Strategy;

import java.util.Optional;

public interface Reader {

    /**
     * By reading the input, creating related Strategy implementation
     *
     * @param input Command for getting the Strategy
     * @return Strategy Optional if there is matched pattern
     * @throws StrategyException
     */
    Optional<Strategy> read(String input) throws StrategyException;

}
