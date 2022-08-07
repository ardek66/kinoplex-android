package net.idf.kinoplexandroid;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class WsClient {
    private final OkHttpClient client = new OkHttpClient();

    private WebSocket ws;
    private WsListener listener;
    private final String host;
    private final String user;
    private final String pass;
    private boolean auth;

    WsClient(String host, String user, String pass) {
        this.auth = false;
        this.host = host;
        this.user = user;
        this.pass = pass;
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

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public void setListener(WsListener listener) {
        this.listener = listener;
    }

    public String getHost() {
        return host;
    }

    public WsListener getListener() {
        return listener;
    }

    public void newWebSocket(Request request, WsListener listener) {
        ws = client.newWebSocket(request, listener);
    }

    public void dispatch() {
        client.dispatcher().executorService().shutdown();
    }
}
