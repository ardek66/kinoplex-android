package net.idf.kinoplexandroid;

import com.google.gson.Gson;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WsListener extends WebSocketListener {
    private final Gson gson = new Gson();
    private WsClient client;

    public static WsListener newWsListener(WsClient client) {
        WsListener wsAuthListener = new WsListener();
        wsAuthListener.client = client;

        return wsAuthListener;
    }

    @Override
    public void onOpen(WebSocket ws, Response resp) {
        System.out.println("client opening...");
        Protocol msg = Protocol.auth(client.getUser(), client.getPass());
        String json = gson.toJson(msg);
        System.out.println(json);
        ws.send(json);
    }

    private void handleAuth(WsClient client, Protocol resp) {
        switch (resp.kind) {
            case "Joined":
                if (resp.joName.equals(client.getUser())) {
                    client.setAuth(true);
                    MainActivity.switchToKino(client.getContext());
                    break;
                }
            case "Error":
                client.close();
                MainActivity.showError(resp.reason, client.getContext());
                break;
            default:
                break;
        }
    }

    public void handleMain(WsClient client, Protocol resp) {
        System.out.println(resp.kind);
    }

    @Override
    public void onMessage(WebSocket ws, String text) {
        Protocol resp = gson.fromJson(text, Protocol.class);
        System.out.println(resp.kind);

        if (client.isAuth()) {
            handleMain(client, resp);
        } else {
            handleAuth(client, resp);
        }
    }

    @Override
    public void onFailure(WebSocket ws, Throwable e, Response resp) {
        e.printStackTrace();
    }
}
