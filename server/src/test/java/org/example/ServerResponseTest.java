package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import static org.mockito.Mockito.*;

public class ServerResponseTest {
    PrintWriter printerMock;
    OutputStream outMock;
    Socket socketMock;
    ServerResponse serverResponse;

    @BeforeEach
    void setUp() {
        socketMock = mock(Socket.class);
        printerMock = mock(PrintWriter.class);
        outMock = mock(OutputStream.class);
        serverResponse = new ServerResponse(socketMock);
    }

    @Test
    void sendResponseTest() throws IOException {
        when(socketMock.getOutputStream()).thenReturn(outMock);

        serverResponse.sendResponse("Server response");

//        WIP
//        verify(printerMock).println("Server response");
//        verify(printerMock).flush();
    }
}
