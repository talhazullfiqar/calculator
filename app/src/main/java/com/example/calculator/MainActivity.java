package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private StringBuilder currentNumber;
    private double operand1, operand2;
    private String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        currentNumber = new StringBuilder();
    }

    // Method to handle digit button clicks
    public void onDigitClick(View view) {
        Button button = (Button) view;
        currentNumber.append(button.getText().toString());
        display.setText(currentNumber.toString());
    }

    // Method to handle operator button clicks (+, -, *, /)
    public void onOperatorClick(View view) {
        operator = ((Button) view).getText().toString();
        operand1 = Double.parseDouble(currentNumber.toString());
        currentNumber.setLength(0); // Clear currentNumber for next input
    }

    // Method to handle decimal button click
    public void onDecimalClick(View view) {
        if (!currentNumber.toString().contains(".")) {
            currentNumber.append(".");
            display.setText(currentNumber.toString());
        }
    }

    // Method to handle equals button click
    public void onEqualsClick(View view) {
        operand2 = Double.parseDouble(currentNumber.toString());
        double result = performOperation(operand1, operand2, operator);
        display.setText(String.valueOf(result));
        currentNumber.setLength(0); // Clear currentNumber for next calculation
    }

    // Method to reset the calculator
    public void onResetClick(View view) {
        currentNumber.setLength(0);
        display.setText("0");
    }

    // Helper method to perform arithmetic operations
    private double performOperation(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    return Double.NaN; // Handle division by zero
                }
                return operand1 / operand2;
            default:
                return Double.NaN; // Invalid operation
        }
    }
}
