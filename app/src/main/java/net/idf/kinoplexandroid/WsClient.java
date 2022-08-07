package net.idf.kinoplexandroid;

import android.app.Activity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class WsClient {
    private final OkHttpClient client = new OkHttpClient();

    private WebSocket ws;
    private final Activity activity;
    private final String host;
    private final String user;
    private final String pass;
    public boolean auth = false;

    WsClient(String host, String user, String pass, Activity activity) {
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.activity = activity;
    }

    public void start() {
        System.out.println("Starting");
        Request request = new Request.Builder().url(host).build();
        WsAuthListener authListener = WsAuthListener.newAuthListener(this);
        System.out.println("Listening");
        ws = client.newWebSocket(request, authListener);
        System.out.println("Closing");
        client.dispatcher().executorService().shutdown();
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public WebSocket getWs() {
        return ws;
    }

    public Activity getActivity() {
        return activity;
    }
}
