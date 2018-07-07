package com.example.app.calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.app.calculator.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

enum Action {
    NONE, ADDITION, SUBTRACTION, MULTIPLICATION,
    DIVISION, SIN, COS, TAN, DEG, RAD
}

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private Action currentAction;
    private double valueOne = Double.NaN;
    private double valueTwo = Double.NaN;
    private Action mode = Action.RAD;

    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        decimalFormat = new DecimalFormat("#.##########");
        binding = DataBindingUtil.setContentView(this, com.example.app.calculator.R.layout.activity_main);

        binding.buttonDot.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (!binding.editText.getText().toString().contains(".")) {
                    editTextSet(".");
                }
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
                computeCalculation();
                currentAction = Action.ADDITION;
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
                computeCalculation();
                currentAction = Action.SUBTRACTION;
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
                computeCalculation();
                currentAction = Action.MULTIPLICATION;
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
                computeCalculation();
                currentAction = Action.DIVISION;
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
                currentAction = Action.SIN;
                binding.infoTextView.setText("sin(");
            }
        });

        binding.buttonCos.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                currentAction = Action.COS;
                binding.infoTextView.setText("cos(");
            }
        });

        binding.buttonTan.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                currentAction = Action.TAN;
                binding.infoTextView.setText("tan(");
            }
        });

        binding.buttonDeg.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                binding.buttonDeg.setText(mode == Action.RAD ? Action.DEG.toString() : Action.RAD.toString());
                mode = mode == Action.RAD ? Action.DEG : Action.RAD;
            }
        });

        binding.buttonEqual.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (!Double.isNaN(valueOne) || binding.editText.getText().length() > 0) {
                    computeCalculation();
                    if (currentAction == Action.DIVISION && valueTwo == 0) {
                        Toast.makeText(getContext(), "Cannot divide by 0", Toast.LENGTH_SHORT).show();
                        binding.infoTextView.setText("");
                        valueTwo = Double.NaN;
                    } else if (!Double.isNaN(valueTwo)){
                        binding.infoTextView.setText(binding.infoTextView.getText().toString() +
                                decimalFormat.format(valueTwo) + " = " + decimalFormat.format(valueOne));
                    } else if (actionIsTrig()) {
                        binding.infoTextView.setText(binding.infoTextView.getText().toString() +
                                decimalFormat.format(Double.parseDouble(binding.editText.getText().toString()))
                                + ") = " + decimalFormat.format(valueOne));
                    }
                    valueOne = Double.NaN;
                    currentAction = Action.NONE;
                }
                binding.editText.setText("");
            }
        });

        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editText.getText().length() > 0) {
                    CharSequence currentText = binding.editText.getText();
                    binding.editText.setText(currentText.subSequence(0, currentText.length() - 1));
                } else {
                    clearAll();
                }
            }
        });

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), BrowserActivity.class));
            }
        });
    }

    private void computeCalculation() {
        if (binding.editText.getText().length() == 0) return;
        if (binding.editText.getText().toString().contains(".")) {
            int indexOfDecimalPoint = binding.editText.getText().toString().indexOf(".");
            try {
                //Is there a number after the decimal point?
                char c = binding.editText.getText().toString().charAt(indexOfDecimalPoint + 1);
            } catch (StringIndexOutOfBoundsException e) {
                /*
                There wasn't any number after the decimal point, so tell the user what went wrong
                and stop the execution of this method.
                 */
                Toast.makeText(getContext(), "Bad expression", Toast.LENGTH_SHORT).show();
                clearAll();
                return;
            }
        }

        if (!Double.isNaN(valueOne)) {
            if (!actionIsTrig()) valueTwo = Double.parseDouble(binding.editText.getText().toString());

            switch (currentAction) {
                case ADDITION:
                    valueOne += valueTwo;
                    break;
                case SUBTRACTION:
                    valueOne -= valueTwo;
                    break;
                case MULTIPLICATION:
                    valueOne *= valueTwo;
                    break;
                case DIVISION:
                    if (valueTwo != 0) {
                        valueOne /= valueTwo;
                    }
                    break;
            }
        } else {
            valueOne = Double.parseDouble(binding.editText.getText().toString());
            if (actionIsTrig()) {
                switch (currentAction) {
                    case SIN:
                        if (mode == Action.DEG) {
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
                        if (mode == Action.DEG) {
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
                        if (mode == Action.DEG) {
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

    private void clearAll() {
        valueOne = Double.NaN;
        valueTwo = Double.NaN;
        binding.editText.setText("");
        binding.infoTextView.setText("");
    }

    private Context getContext() {
        return this;
    }

    private boolean actionIsTrig() {
        return currentAction == Action.SIN || currentAction == Action.COS || currentAction == Action.TAN;
    }
}