package com.example.myapplication5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GuessingGameFragment extends Fragment {
    TextView textView;
    TextView textViewR;
    TextView textViewW;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    int right = 0;
    int wrong = 0;
    int pick;
    Random r;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guessing_game, container, false);
        init(view);
        textView = view.findViewById(R.id.textViewNumberAnswer);
        textViewR = view.findViewById(R.id.textViewRight);
        textViewW = view.findViewById(R.id.textViewWrong);
        r = new Random();
        pick = r.nextInt(9) + 1;
        return view;
    }

    public void init(View view){
        one = view.findViewById(R.id.buttonOne);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(1);
            }
        });
        two = view.findViewById(R.id.buttonTwo);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(2);
            }
        });
        three = view.findViewById(R.id.buttonThree);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(3);
            }
        });
        four = view.findViewById(R.id.buttonFour);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(4);
            }
        });
        five = view.findViewById(R.id.buttonFive);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(5);
            }
        });
        six = view.findViewById(R.id.buttonSix);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(6);
            }
        });
        seven = view.findViewById(R.id.buttonSeven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(7);
            }
        });
        eight = view.findViewById(R.id.buttonEight);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(8);
            }
        });
        nine = view.findViewById(R.id.buttonNine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(9);
            }
        });
    }

    public void check(int i){
        if (i == pick){
            right++;
            textView.setText("You Guessed it! Try again!");
            pick = r.nextInt(9) + 1;
        } else if (i < pick){
            wrong++;
            textView.setText("too low");
        } else {
            wrong++;
            textView.setText("too high");
        }
        textViewR.setText("Right: " + Integer.toString(right));
        textViewW.setText("Wrong: " + Integer.toString(wrong));
    }
}