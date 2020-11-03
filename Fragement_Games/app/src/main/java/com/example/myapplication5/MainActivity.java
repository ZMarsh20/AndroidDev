package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void numberGameT(View view) {
        fragment = new GuessingGameFragment();
        launchFragment(R.id.frameLayoutTop);
    }

    public void ticTacToeT(View view) {
        fragment = new TicTacToeFragment();
        launchFragment(R.id.frameLayoutTop);
    }

    public void numberGameB(View view) {
        fragment = new GuessingGameFragment();
        launchFragment(R.id.frameLayoutBottom);
    }

    public void ticTacToeB(View view) {
        fragment = new TicTacToeFragment();
        launchFragment(R.id.frameLayoutBottom);
    }

    public void launchFragment(int tob){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(tob, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}