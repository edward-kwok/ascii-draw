package com.ubs.takehome;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Using junit here for the System Rule 's library
 *
 * Integration Test
 */
public class AppTest {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final ExpectedSystemExit systemExitRule = ExpectedSystemExit.none();


    @Test
    public void integrationTest() throws IOException {
        systemExitRule.expectSystemExitWithStatus(0);
        File file = new File("src/test/resources/test");
        String test = new String(Files.readAllBytes(Paths.get(file.toURI())));
        App app = new App();
        systemExitRule.checkAssertionAfterwards(() -> assertEquals(test, systemOutRule.getLog()));
        Scanner scanner = new Scanner("C 20 4\nL 1 2 6 2\nR 14 1 18 3\nB 10 3 o\nQ");
        app.start(scanner);
    }


}