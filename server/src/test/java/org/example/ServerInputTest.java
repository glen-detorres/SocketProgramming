package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.api.mockito.PowerMockito;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ServerInputTest {
    @Mock
    Socket socketMock;
    @Mock
    DataInputStream inputMock;
    @Mock
    ServerResponse responseMock;
    @Mock
    PrintWriter printerMock;
    @Mock
    ServerConnection connectionMock;
    @InjectMocks
    ServerInput serverInput;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        serverInput = new ServerInput(socketMock);
    }

    @Test
    void testReadInput_InvalidInput() throws Exception {
        when(socketMock.getInputStream()).thenReturn(mock(InputStream.class));
        when(inputMock.readUTF()).thenReturn("qwerty");
        PowerMockito.whenNew(PrintWriter.class).withAnyArguments().thenReturn(printerMock);

        serverInput.readInput();

        verify(responseMock).sendResponse("Enter a valid number!");
    }

//    @Test
//    void testReadInput_End() throws Exception {
//        when(socketMock.getInputStream()).thenReturn(mock(InputStream.class));
//
//        when(inputMock.readUTF()).thenReturn("end");
//        PowerMockito.whenNew(ServerConnection.class).withAnyArguments().thenReturn(connectionMock);
//        doNothing().when(inputMock).close();
//        doNothing().when(socketMock).close();
//
//        serverInput.readInput();
//
//        verify(connectionMock).closeConnection(inputMock);
//        verify(inputMock).close();
//        verify(socketMock).close();
//    }
}
