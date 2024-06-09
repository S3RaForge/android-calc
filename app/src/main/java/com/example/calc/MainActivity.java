package com.example.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView tvAnswer;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    private Button btnZero;
    private Button btnDot;
    private Button btnClear;
    private Button btnPlusMinus;
    private Button btnPercent;
    private Button btnDivision;
    private Button btnMultiply;
    private Button btnPlus;
    private Button btnMinus;
    private Button btnEquals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeComponents();
        SetComponentListeners();
    }

    private void InitializeComponents() {
        // Buttons
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnZero = findViewById(R.id.btnZero);
        btnDot = findViewById(R.id.btnDot);
        btnClear = findViewById(R.id.btnClear);
        btnPlusMinus = findViewById(R.id.btnPlusMinus);
        btnPercent = findViewById(R.id.btnPercent);
        btnDivision = findViewById(R.id.btnDivision);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnEquals = findViewById(R.id.btnEquals);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        // TextViews
        tvAnswer = findViewById(R.id.tvAnswer);
    }

    private void SetComponentListeners() {
        btnOne.setOnClickListener(this::BtnNumberOnClick);
        btnTwo.setOnClickListener(this::BtnNumberOnClick);
        btnThree.setOnClickListener(this::BtnNumberOnClick);
        btnFour.setOnClickListener(this::BtnNumberOnClick);
        btnFive.setOnClickListener(this::BtnNumberOnClick);
        btnSix.setOnClickListener(this::BtnNumberOnClick);
        btnSeven.setOnClickListener(this::BtnNumberOnClick);
        btnEight.setOnClickListener(this::BtnNumberOnClick);
        btnNine.setOnClickListener(this::BtnNumberOnClick);
        btnZero.setOnClickListener(this::BtnNumberOnClick);

        btnClear.setOnClickListener(this::BtnClearOnClick);
        btnPlusMinus.setOnClickListener(this::BtnNotWork);
        btnPercent.setOnClickListener(this::BtnNotWork);
        btnDot.setOnClickListener(this::BtnNotWork);

        btnDivision.setOnClickListener(this::BtnSymbolOnClick);
        btnMultiply.setOnClickListener(this::BtnSymbolOnClick);
        btnPlus.setOnClickListener(this::BtnSymbolOnClick);
        btnMinus.setOnClickListener(this::BtnSymbolOnClick);

        btnEquals.setOnClickListener(this::BtnEqualsOnClick);
    }

    private void BtnNumberOnClick(View v) {
        String currentText = tvAnswer.getText().toString();
        String buttonText = ((Button) v).getText().toString();

        if (currentText.equals("0") || currentText.equals("Error")) {
            tvAnswer.setText(buttonText);
        } else {
            tvAnswer.setText(currentText + buttonText);
        }
    }
    private void BtnSymbolOnClick(View v) {
        String currentText = tvAnswer.getText().toString();
        String buttonText = ((Button) v).getText().toString();

        if (!currentText.equals("0") &&
            !currentText.equals("Error") &&
            currentText.charAt(currentText.length() - 1) != '-' &&
            currentText.charAt(currentText.length() - 1) != '+' &&
            currentText.charAt(currentText.length() - 1) != '÷' &&
            currentText.charAt(currentText.length() - 1) != 'x') {
            tvAnswer.setText(currentText + buttonText);
        }
        if (currentText.equals("0") && buttonText.equals("-")) {
            tvAnswer.setText(buttonText);
        }
    }

    private void BtnClearOnClick(View v) {
        tvAnswer.setText("0");
    }

    private void BtnNotWork(View v) {
        // Code to handle button percent click
        Toast.makeText(this, "Sorry but its doesn't work", Toast.LENGTH_SHORT).show();
    }
    private void BtnEqualsOnClick(View v) {
        String text = tvAnswer.getText().toString();
        if (text.equals("Error")) {
            tvAnswer.setText("0");
        }
        if (text.endsWith("-") || text.endsWith("+") || text.endsWith("÷") || text.endsWith("x")) {
            tvAnswer.setText(R.string.error);
            return;
        }
        int currentNumber = 0;
        char currentOperator = '+';
        int result = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0');
            }
            if (!Character.isDigit(c) || i == text.length() - 1) {
                switch (currentOperator) {
                    case '+':
                        result += currentNumber;
                        break;
                    case '-':
                        result -= currentNumber;
                        break;
                    case 'x':
                        result *= currentNumber;
                        break;
                    case '÷':
                        try {
                            result /= currentNumber;
                        } catch (ArithmeticException e) {
                            tvAnswer.setText(R.string.error);
                            return;
                        }
                        break;
                }
                currentNumber = 0;
                currentOperator = c;
            }
        }
        tvAnswer.setText(String.valueOf(result));
    }
}