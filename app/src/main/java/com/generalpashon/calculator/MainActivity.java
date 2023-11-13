package com.generalpashon.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int[] buttonIdFromNum = new int[]{
                          R.id.button0,
            R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6,
            R.id.button7, R.id.button8, R.id.button9,
    };

    private final Calculator calc;

    public MainActivity(){
        this.calc = new Calculator();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 0 - 9
        for(int i = 0; i <= 9; i++) {
            final int number = i;
            findViewById(buttonIdFromNum[i]).setOnClickListener( (view) -> {
                calc.inputString(String.valueOf(number));
                updateInput();
            });
        }
        // Calculate result
        findViewById(R.id.calcResultButton).setOnClickListener( (view) -> {
            calcAndUpdateResult();
        });
        // AC
        findViewById(R.id.acButton).setOnClickListener( (view) -> {
            calc.setInput("");
            updateInput();
        });
        // +
        findViewById(R.id.buttonAdd).setOnClickListener( (view) -> {
            calc.inputString("+");
            updateInput();
        });
        // -
        findViewById(R.id.buttonSub).setOnClickListener( (view) -> {
            calc.inputString("-");
            updateInput();
        });
        // *
        findViewById(R.id.buttonMul).setOnClickListener( (view) -> {
            calc.inputString("*");
            updateInput();
        });
        // /
        findViewById(R.id.buttonDiv).setOnClickListener( (view) -> {
            calc.inputString("/");
            updateInput();
        });
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // Key down
        if(event.getAction() == KeyEvent.ACTION_DOWN) {
            calc.inputChar((char) event.getUnicodeChar());
            updateInput();
        }

        return super.dispatchKeyEvent(event);
    }

    @SuppressLint("SetTextI18n")
    public void updateInput() {
        final TextView resultView = findViewById(R.id.inputView);
        resultView.setText(calc.getInput());
    }

    @SuppressLint("SetTextI18n")
    public void calcAndUpdateResult() {
        final TextView resultView = findViewById(R.id.resultView);
        resultView.setText(calc.getResult());
    }

}