package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TLSSocketFactoryTestMocks {
    
    @Test
    public void testPrepareSocketMethod() {
        // Création du mock pour SSLSocket
        SSLSocket sslSocketMock = mock(SSLSocket.class);

        // Simuler le comportement attendu
        when(sslSocketMock.getSupportedProtocols()).thenReturn(new String[]{"TLSv1", "TLSv1.1"});
        when(sslSocketMock.getEnabledProtocols()).thenReturn(new String[]{"TLSv1"});

        // Appel de la méthode prepareSocket
        TLSSocketFactory factory = new TLSSocketFactory();
        factory.prepareSocket(sslSocketMock);

        // Vérification que setEnabledProtocols est bien appelée
        verify(sslSocketMock).setEnabledProtocols(any(String[].class));
    }
}