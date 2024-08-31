package com.coffee.taptap;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unity3d.player.GameViewActivity;

public class MainActivity extends GameViewActivity {

    private View vHideLoading;
    private TextView vScore;

    @Override
    public void onGameStarted() {
        vHideLoading.post(() -> vHideLoading.setVisibility(View.GONE));
    }

    @Override
    public void onGameFinished(int score) {
        vScore.post(() -> vScore.setText(String.valueOf(score)));
    }

    @Override
    public void setActivityContentView(View playerView) {

        setContentView(R.layout.activity_main);

        ((ViewGroup) findViewById(R.id.v_game_container)).addView(playerView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vHideLoading = findViewById(R.id.v_hide_loading);
        vScore = findViewById(R.id.v_score);

        findViewById(R.id.v_finish_game).setOnClickListener((v) -> finishGame());
    }
}