package net.idf.kinoplexandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
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
        String url = "ws://" + hostText.getText().toString();
        String name = userText.getText().toString();
        String pass = passwordText.getText().toString();

        WsClient wsClient = new WsClient(url, name, pass, this);
        wsClient.start();
    }
}