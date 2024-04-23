package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.mockito.Mockito.*;

public class ServerInputTest {
    Socket socketMock;
    DataInputStream inputMock;
    ServerResponse responseMock;
    ServerPoemReader poemReaderMock;
    ServerConnection connectionMock;
    private ServerInput serverInput;

    @BeforeEach
    void setUp() {
        socketMock = mock(Socket.class);
        inputMock = mock(DataInputStream.class);
        poemReaderMock = mock(ServerPoemReader.class);
        serverInput = new ServerInput(socketMock);
        responseMock = mock(ServerResponse.class);
        connectionMock = mock(ServerConnection.class);
    }

    @Test
    void testReadInput_ValidInput(){
        try {
            when(socketMock.getInputStream()).thenReturn(mock(InputStream.class));
            when(inputMock.readUTF()).thenReturn("16");
            when(poemReaderMock.getLineFromPoem("16")).thenReturn("Yet knowing how way leads on to way,");

            serverInput.readInput();

//            verify(responseMock).sendResponse("Yet knowing how way leads on to way,");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
