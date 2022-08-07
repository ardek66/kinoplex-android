package net.idf.kinoplexandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WsClient client;
    private EditText hostText;
    private EditText userText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hostText = findViewById(R.id.hostText);
        userText = findViewById(R.id.userText);
        passwordText = findViewById(R.id.passwordText);
    }

    public void onClick(View view) {
        String url = "ws://" + hostText.getText().toString() + "/ws";
        String name = userText.getText().toString();
        String pass = passwordText.getText().toString();

        client = new WsClient(url, name, pass);
        WsListener listener = WsListener.newWsListener(client, this);
        client.setListener(listener);
        KinoClient.setClient(client);
        KinoClient.start();
    }

    public static void switchToKino(Activity ctx) {
        ctx.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ctx, "Logged in successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ctx, KinoActivity.class);
                ctx.startActivity(intent);
            }
        });
    }

    public static void showError(String reason, Activity ctx) {
        ctx.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ctx, "Error: " + reason, Toast.LENGTH_LONG).show();
            }
        });
    }
}