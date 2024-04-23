package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServerConnectionTest {
    ServerConnection serverConnection;
    ServerSocket serverSocket;
    DataInputStream inputMock;
    Socket socketMock;

    @BeforeEach
    void setUp() {
        socketMock = mock(Socket.class);
        serverSocket = mock(ServerSocket.class);
        inputMock = mock(DataInputStream.class);
        serverConnection = new ServerConnection(socketMock);
    }

    @Test
    void initConnectionTest() throws IOException {
        when(serverSocket.accept()).thenReturn(socketMock);

        Socket result = serverConnection.initConnection(serverSocket);

        assertEquals(socketMock, result);
    }

    @Test
    void closeConnectionTest() throws IOException {
        doNothing().when(inputMock).close();
        doNothing().when(socketMock).close();

        serverConnection.closeConnection(inputMock);

        verify(inputMock).close();
        verify(socketMock).close();
    }
}
