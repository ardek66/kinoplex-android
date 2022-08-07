package net.idf.kinoplexandroid;

import android.app.Activity;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class WsClient {
    private final OkHttpClient client = new OkHttpClient();

    private WebSocket ws;
    private Activity ctx;
    private final String host;
    private final String user;
    private final String pass;
    private boolean auth;

    WsClient(String host, String user, String pass, Activity ctx) {
        this.auth = false;
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.ctx = ctx;
    }

    public void start() {
        System.out.println("Starting");
        Request request = new Request.Builder().url(host).build();
        WsListener listener = WsListener.newWsListener(this);
        System.out.println("Listening");
        ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
        System.out.println("Closing");
    }

    public void close() {
        ws.close(1000, "Disconnect");
        auth = false;
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

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public Activity getContext() {
        return ctx;
    }

    public void setContext(Activity ctx) {
        this.ctx = ctx;
    }
}
