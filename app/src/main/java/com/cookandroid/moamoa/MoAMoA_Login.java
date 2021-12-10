package com.cookandroid.moamoa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MoAMoA_Login extends AppCompatActivity {

    public static MoAMoA_Login LoginActivity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moamoa_login);

        Button loginButton = (Button) findViewById(R.id.login_button);
        Button resistButton = (Button) findViewById(R.id.go_resist);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                LoginActivity = MoAMoA_Login.this;
                startActivity(intent);
            }
        });

        resistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MoAMoA_Resist.class);
                startActivity(intent);
            }
        });
    }
}
