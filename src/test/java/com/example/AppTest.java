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
        App app = new App();
        assertEquals("Hello, GitHub Pages Deployment!", App.main(new String[0]));
    }
}
