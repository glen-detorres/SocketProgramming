package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ServerPoemReaderTest {
    ServerPoemReader serverPoemReader;

    @BeforeEach
    void setUp() {
        serverPoemReader = new ServerPoemReader();
        serverPoemReader.readPoemLines();
    }
    @Test
    void testGetLineFromPoem_ValidInput() {
        assertEquals("Yet knowing how way leads on to way,", serverPoemReader.getLineFromPoem("16"));
    }

    @Test
    void testGetLineFromPoem_EmptyLine() {
        assertEquals("Number 6 is an empty line!", serverPoemReader.getLineFromPoem("6"));
    }

    @Test
    void testGetLineFromPoem_OutOfRange() {
        assertEquals("Number out of range!", serverPoemReader.getLineFromPoem("100"));
    }

    @Test
    void testGetLineFromPoem_OutOfRange_Zero() {
        assertEquals("Number out of range!", serverPoemReader.getLineFromPoem("0"));
    }

    @Test
    void testGetLineFromPoem_OutOfRange_Negative() {
        assertEquals("Number out of range!", serverPoemReader.getLineFromPoem("-1"));
    }

}
