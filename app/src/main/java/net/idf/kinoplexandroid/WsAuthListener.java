package net.idf.kinoplexandroid;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class WsAuthListener extends WebSocketListener {
    private final Gson gson = new Gson();
    private WsClient client;

    public static WsAuthListener newAuthListener(WsClient client) {
        WsAuthListener wsAuthListener = new WsAuthListener();
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

    @Override
    public void onMessage(WebSocket ws, String text) {
        Activity activity = client.getActivity();

        Protocol resp = gson.fromJson(text, Protocol.class);
        System.out.println(resp.kind);
        switch (resp.kind) {
            case "Joined":
                if (resp.joName.equals(client.getUser())) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "Logged in succesfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(activity, KinoActivity.class);
                            activity.startActivity(intent);
                        }
                    });
                    System.out.println(resp.joName + ',' + resp.role);
                    break;
                }
            case "Error":
                ws.close(1000, "Error");

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "Error: " + resp.reason, Toast.LENGTH_LONG).show();
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void onFailure(WebSocket ws, Throwable e, Response resp) {
        e.printStackTrace();
    }
}
