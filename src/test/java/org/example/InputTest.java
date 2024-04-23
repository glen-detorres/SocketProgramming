package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InputTest {
    Socket socketMock;
    DataInputStream inputMock;
    Response responseMock;
    PoemReader poemReaderMock;
    Connection connectionMock;
    private Input input;


    @BeforeEach
    void setUp() {
        socketMock = mock(Socket.class);
        inputMock = mock(DataInputStream.class);
        poemReaderMock = mock(PoemReader.class);
        input = new Input(socketMock);
        responseMock = mock(Response.class);
        connectionMock = mock(Connection.class);
    }

    @Test
    void testReadInput_ValidInput(){
        try {
            when(socketMock.getInputStream()).thenReturn(mock(InputStream.class));
            when(inputMock.readUTF()).thenReturn("16");
            when(poemReaderMock.getLineFromPoem("16")).thenReturn("Yet knowing how way leads on to way,");

            input.readInput();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
