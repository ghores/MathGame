package com.example.mathgames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    private TextView tvPoints;
    private SharedPreferences sharedPreferences;
    private ImageView ivHighScore;
    private TextView tvHighScore;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        tvPoints = findViewById(R.id.tvPoints);
        ivHighScore = findViewById(R.id.ivHighScore);
        tvHighScore = findViewById(R.id.tvHighScore);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        Bundle extras = getIntent().getExtras();
        int points = 0;
        if (extras != null) {
            points = extras.getInt("points");
        }

        sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        int pointsSP = sharedPreferences.getInt("pointsSP", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (points > pointsSP) {
            pointsSP = points;
            editor.putInt("pointsSP", pointsSP);
            editor.apply();
            ivHighScore.setVisibility(View.VISIBLE);
        }
        tvPoints.setText("" + points);
        tvHighScore.setText("" + pointsSP);
    }

    public void restart(View view) {
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view) {
        finish();
    }
}