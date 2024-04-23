package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


public class PoemReaderTest {
    PoemReader poemReader;

    @BeforeEach
    void setUp() {
        poemReader = new PoemReader();
    }

    @Test
    void testGetLineFromPoem_ValidInput() {
        assertEquals("Yet knowing how way leads on to way,", poemReader.getLineFromPoem("16"));
    }

    @Test
    void testGetLineFromPoem_EmptyLine() {
        assertEquals("Number 6 is an empty line!", poemReader.getLineFromPoem("6"));
    }

    @Test
    void testGetLineFromPoem_OutOfRange() {
        assertEquals("Number out of range!", poemReader.getLineFromPoem("-1"));
        assertEquals("Number out of range!", poemReader.getLineFromPoem("100"));
    }

}
