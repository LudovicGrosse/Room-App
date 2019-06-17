package com.example.lg346.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// -------- Gestion de l'ensemble des activités ----------

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Activité principale ----------

        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.bouton1);

        // Activité des salles B4 ----------

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity1 = new Intent(getApplicationContext(), DestinationActivity.class);
                startActivity(activity1);
            }
        });
    }
}
