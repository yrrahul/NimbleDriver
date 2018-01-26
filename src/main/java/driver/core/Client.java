package driver.core;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class Client {
    public static void main(String[] args) {
        String destUri = "ws://localhost:8787/document";
        if (args.length > 0) {
            destUri = args[0];
        }

        WebSocketClient client = new WebSocketClient();
        SampleSocket socket = new SampleSocket();
        try {
            client.start();

            URI echoUri = new URI(destUri);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            client.connect(socket, echoUri, request);
            System.out.printf("Connecting to : %s%n", echoUri);
            Thread.sleep(5000);
            // wait for closed socket connection.
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                client.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
