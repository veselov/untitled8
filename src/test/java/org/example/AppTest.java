package org.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testApp() throws Exception {
        Assertions.assertTrue(new App().main());
    }
}
