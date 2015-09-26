package com.xyrality.gmbh.app;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEditText;

    private EditText passEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUserDevice();

        loginEditText = (EditText) findViewById(R.id.loginEditText);
        passEditText = (EditText) findViewById(R.id.passEditText);

        Button connectButton = (Button) findViewById(R.id.connectButton);
        connectButton.setOnClickListener(connectButtonClickListener);
    }

    private View.OnClickListener connectButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UserAccount.getInstance().setLogin(loginEditText.getText().toString());
            UserAccount.getInstance().setPassword(passEditText.getText().toString());
            startWorldsListActivity();
        }
    };

    private void startWorldsListActivity() {
        Intent gameWorldsIntent = new Intent(LoginActivity.this, ActivityGameWorldsList.class);
        startActivity(gameWorldsIntent);
    }

    private void initUserDevice() {
        UserDevice userDevice = new UserDevice();
        userDevice.setDeviceType(String.format("%s %s",
                android.os.Build.MODEL, android.os.Build.VERSION.RELEASE));
        userDevice.setDeviceId(getMacAddress());
        UserAccount.getInstance().setDevice(userDevice);
    }

    private String getMacAddress() {
        WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        return info.getMacAddress();
    }
}
