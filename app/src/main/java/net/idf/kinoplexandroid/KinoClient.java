package net.idf.kinoplexandroid;

import okhttp3.Request;

public class KinoClient {
    private static WsClient client;

    public static WsClient getClient() {
        return client;
    }

    public static void setClient(WsClient client) {
        KinoClient.client = client;
    }

    public static void start() {
        System.out.println("Starting");
        Request request = new Request.Builder().url(client.getHost()).build();
        System.out.println("Listening");
        client.newWebSocket(request, client.getListener());
        client.dispatch();
        System.out.println("Closing");
    }

    public static void stop() {
        client.close();
    }
}
