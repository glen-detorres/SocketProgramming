package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ServerInputTest {
    ServerInput serverInput;

    @BeforeEach
    void setUp() {
        serverInput = new ServerInput();
    }

    @Test
    void testReadInput_ValidInput() {
        assertTrue(serverInput.validate("12"));
    }

    @Test
    void testReadInput_InvalidInput() {
        assertFalse(serverInput.validate("qwerty"));
    }

    @Test
    void testReadInput_EmptyInput() {
        assertFalse(serverInput.validate(""));
    }
}
