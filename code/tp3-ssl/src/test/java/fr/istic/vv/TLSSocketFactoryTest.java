package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TLSSocketFactoryTest {

    /**
     * Test when the edge case when the both supported and enabled protocols are null.
     */
    /*@Test
    public void preparedSocket_NullProtocols()  {
        TLSSocketFactory f = new TLSSocketFactory();
        f.prepareSocket(new SSLSocket() {

            public String[] getSupportedProtocols() {
                return null;
            }

            public String[] getEnabledProtocols() {
                return null;
            }

            public void setEnabledProtocols(String[] protocols) {
                fail();
            }
        });
    }*/
    //réecriture de la fonction pour créer les mocks de façon automatique

    @Test
    public void preparedSocket_NullProtocols() {
    // Création du mock pour SSLSocket
    SSLSocket sslSocketMock = mock(SSLSocket.class);
    
    // Comportement simulé : retourne null pour getSupportedProtocols et getEnabledProtocols
    when(sslSocketMock.getSupportedProtocols()).thenReturn(null);
    when(sslSocketMock.getEnabledProtocols()).thenReturn(null);

    // 
    TLSSocketFactory factory = new TLSSocketFactory();
    factory.prepareSocket(sslSocketMock);

    // Vérification que setEnabledProtocols n'est jamais appelé
    verify(sslSocketMock, never()).setEnabledProtocols(any(String[].class));
}

    /*@Test
    public void typical()  {
        TLSSocketFactory f = new TLSSocketFactory();
        f.prepareSocket(new SSLSocket() {
            @Override
            public String[] getSupportedProtocols() {
                return shuffle(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
            }
            @Override
            public String[] getEnabledProtocols() {
                return shuffle(new String[]{"SSLv3", "TLSv1"});
            }
            @Override
            public void setEnabledProtocols(String[] protocols) {
                assertTrue(Arrays.equals(protocols, new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3" }));
            }
        });
    }*/

@Test
public void typical()  {
    // Création du mock pour SSLSocket
    SSLSocket sslSocketMock = mock(SSLSocket.class);

    when(sslSocketMock.getSupportedProtocols()).thenReturn(new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"});
    when(sslSocketMock.getEnabledProtocols()).thenReturn(new String[]{"SSLv3", "TLSv1"});

    // Appel de la méthode prepareSocket
    TLSSocketFactory factory = new TLSSocketFactory();
    factory.prepareSocket(sslSocketMock);

    // Vérification que setEnabledProtocols est appelé avec les bons protocoles
    verify(sslSocketMock).setEnabledProtocols(new String[] {"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
}



    private String[] shuffle(String[] in) {
        List<String> list = new ArrayList<String>(Arrays.asList(in));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

}