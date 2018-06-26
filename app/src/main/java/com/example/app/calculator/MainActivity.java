/*
 * Calculator app
 */

package com.example.app.calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.app.calculator.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    private char CURRENT_ACTION; //Represents the current math operation being handled

    private double valueOne = Double.NaN; //The first value the user enters
    private double valueTwo; //The second value the user enters
    private String mode = "RAD";

    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Context self = this;

        decimalFormat = new DecimalFormat("#.##########");

        binding = DataBindingUtil.setContentView(this, com.example.app.calculator.R.layout.activity_main);

        binding.buttonDot.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + ".");
            }
        });

        binding.buttonZero.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "0");
            }
        });

        binding.buttonOne.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "1");
            }
        });

        binding.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "2");
            }
        });

        binding.buttonThree.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "3");
            }
        });

        binding.buttonFour.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "4");
            }
        });

        binding.buttonFive.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "5");
            }
        });

        binding.buttonSix.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "6");
            }
        });

        binding.buttonSeven.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "7");
            }
        });

        binding.buttonEight.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "8");
            }
        });

        binding.buttonNine.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                binding.editText.setText(binding.editText.getText() + "9");
            }
        });

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = ADDITION;
                if (!Double.isNaN(valueOne)) {
                    binding.infoTextView.setText(decimalFormat.format(valueOne) + " + ");
                }
                binding.editText.setText(null);
            }
        });

        binding.buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = SUBTRACTION;
                if (!Double.isNaN(valueOne)) {
                    binding.infoTextView.setText(decimalFormat.format(valueOne) + " - ");
                }
                binding.editText.setText(null);
            }
        });

        binding.buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = MULTIPLICATION;
                if (!Double.isNaN(valueOne)) {
                    binding.infoTextView.setText(decimalFormat.format(valueOne) + " * ");
                }
                binding.editText.setText(null);
            }
        });

        binding.buttonDivide.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                computeCalculation();
                CURRENT_ACTION = DIVISION;
                if (!Double.isNaN(valueOne)) {
                    binding.infoTextView.setText(decimalFormat.format(valueOne) + " / ");
                }
                binding.editText.setText(null);
            }
        });

        binding.buttonSin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                computeCalculation();
                binding.infoTextView.setText("sin(" + decimalFormat.format(valueOne));
                if (mode.equals("DEG")) {
                    if (valueOne % 90 == 0) {
                        binding.infoTextView.setText(binding.infoTextView.getText().toString() + ") = " +
                                Math.round(Math.sin(Math.toRadians(valueOne))));
                    } else {
                        binding.infoTextView.setText(binding.infoTextView.getText().toString() + ") = " +
                                Math.sin(Math.toRadians(valueOne)));
                    }
                } else {
                    binding.infoTextView.setText(binding.infoTextView.getText().toString() + ") = " + Math.sin(valueOne));
                }
                binding.editText.setText(null);
                valueOne = Double.NaN;
                CURRENT_ACTION = '0';
            }
        });

        binding.buttonCos.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                computeCalculation();
                binding.infoTextView.setText("cos(" + decimalFormat.format(valueOne));
                if (mode.equals("DEG")) {
                    if (valueOne % 90 == 0) {
                        binding.infoTextView.setText(binding.infoTextView.getText().toString() + ") = " +
                                Math.round(Math.cos(Math.toRadians(valueOne))));
                    } else {
                        binding.infoTextView.setText(binding.infoTextView.getText().toString() + ") = " +
                                Math.cos(Math.toRadians(valueOne)));
                    }
                } else {
                    binding.infoTextView.setText(binding.infoTextView.getText().toString() + ") = " + Math.cos(valueOne));
                }
                binding.editText.setText(null);
                valueOne = Double.NaN;
                CURRENT_ACTION = '0';
            }
        });

        binding.buttonTan.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                computeCalculation();
                binding.infoTextView.setText("tan(" + decimalFormat.format(valueOne));
                if (mode.equals("DEG")) {
                    if (valueOne % 180 == 0) {
                        binding.infoTextView.setText(binding.infoTextView.getText().toString() + ") = " +
                                Math.round(Math.tan(Math.toRadians(valueOne))));
                    } else {
                        binding.infoTextView.setText(binding.infoTextView.getText().toString() + ") = " +
                                Math.tan(Math.toRadians(valueOne)));
                    }
                } else {
                    binding.infoTextView.setText(binding.infoTextView.getText().toString() + ") = " + Math.tan(valueOne));
                }
                binding.editText.setText(null);
                valueOne = Double.NaN;
                CURRENT_ACTION = '0';
            }
        });

        binding.buttonDeg.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (mode.equals("RAD")) {
                    mode = "DEG";
                    binding.buttonDeg.setText("DEG");
                } else {
                    mode = "RAD";
                    binding.buttonDeg.setText("RAD");
                }
            }
        });

        binding.buttonEqual.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                computeCalculation();
                if (CURRENT_ACTION == DIVISION && valueTwo == 0) {
                    binding.infoTextView.setText("Error: Cannot divide by 0");
                } else {
                    binding.infoTextView.setText(binding.infoTextView.getText().toString() +
                            decimalFormat.format(valueTwo) + " = " + decimalFormat.format(valueOne));
                }
                valueOne = Double.NaN;
                CURRENT_ACTION = '0';
            }
        });

        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editText.getText().length() > 0) {
                    CharSequence currentText = binding.editText.getText();
                    binding.editText.setText(currentText.subSequence(0, currentText.length() - 1));
                } else {
                    valueOne = Double.NaN;
                    valueTwo = Double.NaN;
                    binding.editText.setText("");
                    binding.infoTextView.setText("");
                }
            }
        });

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(self, BrowserActivity.class));
            }
        });
    }

    private void computeCalculation() {
        if (!Double.isNaN(valueOne)) {
            try {
                valueTwo = Double.parseDouble(binding.editText.getText().toString());
            } catch (Exception e) {
                valueTwo = valueOne;
            }
            binding.editText.setText(null);

            switch (CURRENT_ACTION) {
                case ADDITION:
                    valueOne = this.valueOne + valueTwo;
                    break;
                case SUBTRACTION:
                    valueOne = this.valueOne - valueTwo;
                    break;
                case MULTIPLICATION:
                    valueOne = this.valueOne * valueTwo;
                    break;
                case DIVISION:
                    if (valueTwo != 0) {
                        valueOne = this.valueOne / valueTwo;
                    }
                    break;

            }
        } else {
            try {
                valueOne = Double.parseDouble(binding.editText.getText().toString());
            } catch (Exception e) {
                valueOne = Double.NaN;
            }
        }
    }
}