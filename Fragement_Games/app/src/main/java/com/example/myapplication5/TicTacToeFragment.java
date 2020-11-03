package com.example.myapplication5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.asList;

public class TicTacToeFragment extends Fragment {
    TextView textView;
    TextView Xwins;
    TextView Owins;
    boolean xTurn = true;
    ArrayList<Button> buttons;
    Button tl;
    Button tm;
    Button tr;
    Button ml;
    Button mm;
    Button mr;
    Button bl;
    Button bm;
    Button br;
    int count = 0;
    int Xcount = 0;
    int Ocount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        textView = view.findViewById(R.id.textViewTurn);
        Xwins = view.findViewById(R.id.textViewXW);
        Owins = view.findViewById(R.id.textViewOW);
        init(view);
        buttons = new ArrayList<>(Arrays.asList(tl,tm,tr,ml,mm,mr,bl,bm,br));
        return view;
    }

    public void init(View view) {
        tl = view.findViewById(R.id.buttonTL);
        tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if (xTurn) {
                    tl.setText("X");
                }
                else {
                    tl.setText("O");
                }
                tl.setEnabled(false);
                check();
                xTurn = !xTurn;
            }
        });
        tm = view.findViewById(R.id.buttonTM);
        tm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if (xTurn) {
                    tm.setText("X");
                } else {
                    tm.setText("O");
                }
                tm.setEnabled(false);
                check();
                xTurn = !xTurn;
            }
        });
        tr = view.findViewById(R.id.buttonTR);
        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if (xTurn) {
                    tr.setText("X");
                } else {
                    tr.setText("O");
                }
                tr.setEnabled(false);
                check();
                xTurn = !xTurn;
            }
        });
        ml = view.findViewById(R.id.buttonML);
        ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if (xTurn) {
                    ml.setText("X");
                } else {
                    ml.setText("O");
                }
                ml.setEnabled(false);
                check();
                xTurn = !xTurn;
            }
        });
        mm = view.findViewById(R.id.buttonMM);
        mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if (xTurn) {
                    mm.setText("X");
                } else {
                    mm.setText("O");
                }
                mm.setEnabled(false);
                check();
                xTurn = !xTurn;
            }
        });
        mr = view.findViewById(R.id.buttonMR);
        mr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if (xTurn) {
                    mr.setText("X");
                } else {
                    mr.setText("O");
                }
                mr.setEnabled(false);
                check();
                xTurn = !xTurn;
            }
        });
        bl = view.findViewById(R.id.buttonBL);
        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if (xTurn) {
                    bl.setText("X");
                } else {
                    bl.setText("O");
                }
                bl.setEnabled(false);
                check();
                xTurn = !xTurn;
            }
        });
        bm = view.findViewById(R.id.buttonBM);
        bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if (xTurn) {
                    bm.setText("X");
                } else {
                    bm.setText("O");
                }
                bm.setEnabled(false);
                check();
                xTurn = !xTurn;
            }
        });
        br = view.findViewById(R.id.buttonBR);
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if (xTurn) {
                    br.setText("X");
                } else {
                    br.setText("O");
                }
                br.setEnabled(false);
                check();
                xTurn = !xTurn;
            }
        });
    }

    public void check(){
        boolean won = false;
        if (tl.getText().equals("-") == false) {
            if (tl.getText().equals(tm.getText()) && tl.getText().equals(tr.getText())) {
                won = true;
            } else if (tl.getText().equals(mm.getText()) && tl.getText().equals(br.getText())) {
                won = true;
            } else if (tl.getText().equals(ml.getText()) && tl.getText().equals(bl.getText())) {
                won = true;
            }
        }
        if (mm.getText().equals("-") == false) {
            if (mm.getText().equals(tm.getText()) && mm.getText().equals(bm.getText())) {
                won = true;
            } else if (mm.getText().equals(ml.getText()) && mm.getText().equals(mr.getText())) {
                won = true;
            } else if (mm.getText().equals(tr.getText()) && mm.getText().equals(bl.getText())) {
                won = true;
            }
        }
        if (br.getText().equals("-") == false) {
            if (br.getText().equals(tr.getText()) && br.getText().equals(mr.getText())) {
                won = true;
            } else if (br.getText().equals(bl.getText()) && br.getText().equals(bm.getText())) {
                won = true;
            }
        }

        if (won){
            for(Button button : buttons){
                button.setText("-");
                button.setEnabled(true);
            }
            if (xTurn){
                Xcount++;
                Xwins.setText("X wins:  " + Integer.toString(Xcount));
            } else {
                Ocount++;
                Owins.setText("O wins:  " + Integer.toString(Ocount));
            }
            if (xTurn) {
                textView.setText("X WINS!!! X goes first");
                xTurn = false;
            }
            else {
                textView.setText("O WINS!!! O goes first");
                xTurn = true;
            }
            count = 0;
        } else if (count == 9) {
            for(Button button : buttons){
                button.setText("-");
                button.setEnabled(true);
            }
            count = 0;
            if (xTurn) {
                textView.setText("Draw. X's turn");
            }
            else {
               textView.setText("Draw. O's turn");
            }
            xTurn = !xTurn;
        }
        else {
            if (xTurn) {
                textView.setText("Turn:  O");
            }
            else {
                textView.setText("Turn:  X");
            }
        }
    }
}