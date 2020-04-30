package com.example.aplicacionesdistribuidas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

public class Carga extends AppCompatActivity {

    LottieAnimationView carga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga);

        carga = (LottieAnimationView) findViewById(R.id.carga);
        carga.playAnimation();
    }
}
