package com.adpth.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.adpth.bmicalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    float height,weight;
    int count_weight = 50,count_age = 19;
    boolean male_clk = true, female_clk = true, check1 = true, check2 = true;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.cardViewMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check1) {

                    if (male_clk) {

                        binding.male.setTextColor(Color.parseColor("#FFFFFF"));
                        binding.male.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.male_white,0,0);
                        male_clk = false;
                        check2 = false;

                    } else {

                        binding.male.setTextColor(Color.parseColor("#8D8E99"));
                        binding.male.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.male,0,0);
                        male_clk = true;
                        check2 = true;
                    }
                }
            }
        });

        binding.cardViewFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check2) {
                    if (female_clk) {
                        binding.female.setTextColor(Color.parseColor("#FFFFFF"));
                        binding.female.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.female_white,0,0);
                        female_clk = false;
                        check1 = false;
                    }
                    else  {

                        binding.female.setTextColor(Color.parseColor("#8D8E99"));
                        binding.female.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.female,0,0);
                        female_clk = true;
                        check1 = true;
                    }
                }
            }
        });

        CheckSeekbarStatus();

        CheckWeight();

        CheckAge();

        binding.calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculateBMI();
            }
        });

    }


    private void CheckAge() {

        binding.agePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_age++;
                binding.age.setText(String.valueOf(count_age));
            }
        });

        binding.ageMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_age--;
                binding.age.setText(String.valueOf(count_age));
            }
        });
    }

    private void CheckWeight() {

        binding.weightPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_weight++;
                binding.weight.setText(String.valueOf(count_weight));
            }
        });

        binding.weightMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_weight--;
                binding.weight.setText(String.valueOf(count_weight));
            }
        });

        weight = Float.parseFloat(binding.weight.getText().toString());

    }

    private void CheckSeekbarStatus() {

        binding.Seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String ht = progress + getResources().getString(R.string.cm);
                binding.heightTxt.setText(ht);
                height = (float)(progress)/100;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void CalculateBMI() {

        float BMI = weight / (height * height);
        Intent intent = new Intent(MainActivity.this,ResultActivity.class);
        intent.putExtra("BMI",BMI);
        intent.putExtra("age",binding.age.getText().toString());
        startActivity(intent);
    }
}