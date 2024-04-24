package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.api.mockito.PowerMockito;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ServerResponseTest {
    @Mock
    OutputStream outMock;
    @Mock
    Socket socketMock;
    @Mock
    ServerResponse serverResponse;
    @Mock
    PrintWriter printerMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        serverResponse = new ServerResponse(socketMock);
    }

//    @Test
//    void sendResponseTest() throws Exception {
//        when(socketMock.getOutputStream()).thenReturn(outMock);
//        PowerMockito.whenNew(PrintWriter.class).withAnyArguments().thenReturn(printerMock);
//
//        serverResponse.sendResponse("Server response");
//
//        verify(printerMock).println("Server response");
//        verify(printerMock).flush();
//    }
}
