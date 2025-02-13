package com.movie.booking.management.gicmoviebooking;


import com.movie.booking.management.gicmoviebooking.constants.MessagePrompts;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class GicmoviebookingApplicationTest {

    @Test
    public void testNullInput() {
        String input = "\n"; // Simulate null or empty input
        String expectedOutput = MessagePrompts.tileAndSeatMapping+"\n" +MessagePrompts.ErrorWarningMsg.validInput + "\n";
        String actualOutput = executeMain(input);
        assertEquals(expectedOutput, actualOutput);
    }

    // Utility method to execute the main method with simulated input
    private String executeMain(String input) {
        InputStream stdin = System.in;
        PrintStream stdout = System.out;

        try {
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            System.setErr(new PrintStream(outContent));

            SystemInitialization.execute();

            return outContent.toString().replaceAll("\r", "");
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
            System.setErr(stdout);
        }
    }
}
