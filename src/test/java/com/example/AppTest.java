package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testApp() {
        // This test will always pass
        assertTrue(true);
    }

    @Test
    public void testAppMessage() {
        String expectedMessage = "Hello, GitHub Pages Deployment!";
        assertEquals(expectedMessage, App.getMessage());
    }
}
