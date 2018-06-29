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

    private static final String ADDITION = "+";
    private static final String SUBTRACTION = "-";
    private static final String MULTIPLICATION = "*";
    private static final String DIVISION = "/";
    private static final String SIN = "sin ";
    private static final String COS = "cos ";
    private static final String TAN = "tan ";
    private static final String DEG = "DEG";
    private static final String RAD = "RAD";

    private String CURRENT_ACTION; //Represents the current math operation being handled

    private double valueOne = Double.NaN; //The first value the user enters
    private double valueTwo; //The second value the user enters
    private double trigAnswer;
    private String mode = RAD;

    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Context context = this;

        decimalFormat = new DecimalFormat("#.##########");

        binding = DataBindingUtil.setContentView(this, com.example.app.calculator.R.layout.activity_main);

        binding.buttonDot.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                editTextSet(".");
            }
        });

        binding.buttonZero.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
               editTextSet("0");
            }
        });

        binding.buttonOne.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                editTextSet("1");
            }
        });

        binding.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                editTextSet("2");
            }
        });

        binding.buttonThree.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                editTextSet("3");
            }
        });

        binding.buttonFour.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                editTextSet("4");
            }
        });

        binding.buttonFive.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                editTextSet("5");
            }
        });

        binding.buttonSix.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                editTextSet("6");
            }
        });

        binding.buttonSeven.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                editTextSet("7");
            }
        });

        binding.buttonEight.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                editTextSet("8");
            }
        });

        binding.buttonNine.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                editTextSet("9");
            }
        });

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                CURRENT_ACTION = ADDITION;
                computeCalculation();
                if (!Double.isNaN(valueOne)) {
                    infoTextSet(" + ");
                }
                binding.editText.setText(null);
            }
        });

        binding.buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                CURRENT_ACTION = SUBTRACTION;
                computeCalculation();
                if (!Double.isNaN(valueOne)) {
                    infoTextSet(" - ");
                }
                binding.editText.setText(null);
            }
        });

        binding.buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                CURRENT_ACTION = MULTIPLICATION;
                computeCalculation();
                if (!Double.isNaN(valueOne)) {
                    infoTextSet(" * ");
                }
                binding.editText.setText(null);
            }
        });

        binding.buttonDivide.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                CURRENT_ACTION = DIVISION;
                computeCalculation();
                if (!Double.isNaN(valueOne)) {
                    infoTextSet(" / ");
                }
                binding.editText.setText(null);
            }
        });

        binding.buttonSin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                CURRENT_ACTION = SIN;
                computeCalculation();
                binding.infoTextView.setText("sin ");
            }
        });

        binding.buttonCos.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                CURRENT_ACTION = COS;
                computeCalculation();
                binding.infoTextView.setText("cos ");
            }
        });

        binding.buttonTan.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                CURRENT_ACTION = TAN;
                computeCalculation();
                binding.infoTextView.setText("tan ");
            }
        });

        binding.buttonDeg.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (mode.equals("RAD")) {
                    mode = DEG;
                    binding.buttonDeg.setText(DEG);
                } else {
                    mode = RAD;
                    binding.buttonDeg.setText(RAD);
                }
            }
        });

        binding.buttonEqual.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (!Double.isNaN(valueOne) || binding.editText.getText().length() > 0) {
                    computeCalculation();
                    if (CURRENT_ACTION.equals(DIVISION) && valueTwo == 0) {
                        binding.infoTextView.setText("Cannot divide by 0");
                    } else {
                        if (!Double.isNaN(valueTwo) && binding.editText.getText().length() > 0) {
                            valueTwo = Double.parseDouble(binding.editText.getText().toString());
                        }
                        if (!Double.isNaN(valueTwo)) {
                            binding.infoTextView.setText(binding.infoTextView.getText().toString() +
                                    decimalFormat.format(valueTwo) + " = " + decimalFormat.format(valueOne));
                        }
                    }
                    valueOne = Double.NaN;
                    CURRENT_ACTION = "0";
                }
                binding.editText.setText(null);
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
                startActivity(new Intent(context, BrowserActivity.class));
            }
        });
    }

    private void computeCalculation() {
        if (binding.editText.getText().length() <= 0) return;

        if (!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(binding.editText.getText().toString());
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
            valueOne = Double.parseDouble(binding.editText.getText().toString());
            if (CURRENT_ACTION.equals(SIN) || CURRENT_ACTION.equals(COS) || CURRENT_ACTION.equals(TAN)) {
                switch (CURRENT_ACTION) {
                    case SIN:
                        if (mode.equals(DEG)) {
                            if (valueOne % 90 == 0) {
                                valueOne = Math.round(Math.sin(Math.toRadians(valueOne)));
                            } else {
                                valueOne = Math.sin(Math.toRadians(valueOne));
                            }
                        } else {
                            valueOne = Math.sin(valueOne);
                        }
                        break;
                    case COS:
                        if (mode.equals(DEG)) {
                            if (valueOne % 180 == 0) {
                                valueOne = Math.round(Math.cos(Math.toRadians(valueOne)));
                            } else {
                                valueOne = Math.cos(Math.toRadians(valueOne));
                            }
                        } else {
                            valueOne = Math.cos(valueOne);
                        }
                        break;
                    case TAN:
                        if (mode.equals(DEG)) {
                            if (valueOne % 180 == 0) {
                                valueOne = Math.round(Math.tan(Math.toRadians(valueOne)));
                            } else {
                                valueOne = Math.tan(Math.toRadians(valueOne));
                            }
                        } else {
                            valueOne = Math.tan(valueOne);
                        }
                        break;
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void editTextSet(String s) {
        binding.editText.setText(binding.editText.getText() + s);
    }

    @SuppressLint("SetTextI18n")
    private void infoTextSet(String s) {
        binding.infoTextView.setText(decimalFormat.format(valueOne) + s);
    }
}