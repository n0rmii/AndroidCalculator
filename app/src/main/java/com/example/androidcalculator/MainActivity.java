package com.example.androidcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	double num1 = 0, num2 = 0, memory = 0;
	char oper = 0;
	boolean isDot = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView display = findViewById(R.id.display);

		AppCompatButton[] num_btns = {
				findViewById(R.id.btn_0),
				findViewById(R.id.btn_1),
				findViewById(R.id.btn_2),
				findViewById(R.id.btn_3),
				findViewById(R.id.btn_4),
				findViewById(R.id.btn_5),
				findViewById(R.id.btn_6),
				findViewById(R.id.btn_7),
				findViewById(R.id.btn_8),
				findViewById(R.id.btn_9),
				findViewById(R.id.btn_00)
		};

		AppCompatButton[] fun_btns = {
				findViewById(R.id.btn_add),
				findViewById(R.id.btn_sub),
				findViewById(R.id.btn_mul),
				findViewById(R.id.btn_div)
		};
		AppCompatButton btn_clr = findViewById(R.id.btn_clr);
		AppCompatButton btn_cle = findViewById(R.id.btn_cle);
		AppCompatButton btn_equ = findViewById(R.id.btn_equ);
		AppCompatButton btn_dot = findViewById(R.id.btn_dot);
		AppCompatButton btn_sqrt = findViewById(R.id.btn_sqrt);

		AppCompatButton memBtn_clear = findViewById(R.id.memBtn_clear);
		AppCompatButton memBtn_recall = findViewById(R.id.memBtn_recall);
		AppCompatButton memBtn_add = findViewById(R.id.memBtn_add);
		AppCompatButton memBtn_sub = findViewById(R.id.memBtn_sub);

		for (AppCompatButton btn: num_btns) {
			btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					display.append(btn.getText());
					num1 = Double.parseDouble(display.getText().toString());
				}
			});
		}

		for (AppCompatButton btn: fun_btns) {
			btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if(oper == 0 && display.getText().toString() != ""){
						num2 = num1;
						num1 = 0;
						display.setText("");
						oper = btn.getText().toString().charAt(0);
						isDot = false;
					}
				}
			});
		}

		btn_clr.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				num1 = 0;
				display.setText("");
				isDot = false;
			}
		});

		btn_cle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				display.setText("");
				num1 = 0;
				num2 = 0;
				oper = 0;
				isDot = false;
			}
		});

		btn_dot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!isDot){
					display.append(".");
					isDot = true;
				}
			}
		});

		btn_equ.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(oper != 0 && display.getText().toString() != ""){
					double result = 0;
					switch (oper){
						case '+':
							result = num2 + num1;
							break;
						case '-':
							result = num2 - num1;
							break;
						case '*':
							result = num2 * num1;
							break;
						case '/':
							result = num2 / num1;
							break;
						case 'r':
							result = Math.pow(num1, 1/num2);
							break;
						default:
							Toast.makeText(MainActivity.this, "Incorrect operation: " + oper, Toast.LENGTH_LONG).show();
							break;
					}
					oper = 0;
					num1 = result;
					if(Math.round(result) == result){
						isDot = false;
						display.setText(String.valueOf(Math.round(result)));
					}
					else{
						isDot = true;
						display.setText(String.valueOf(result));
					}
				}
			}
		});

		btn_sqrt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(oper == 0 && display.getText().toString() != ""){
					num2 = num1;
					num1 = 0;
					display.setText("");
					oper = 'r';
					isDot = false;
				}
			}
		});

		memBtn_clear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				memory = 0;
			}
		});

		memBtn_recall.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				num1 = memory;
				if(Math.round(memory) == memory){
					display.setText(String.valueOf(Math.round(memory)));
				}
				else{
					display.setText(String.valueOf(memory));
				}
			}
		});

		memBtn_add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(display.getText().toString() != ""){
					memory += num1;
				}
			}
		});

		memBtn_sub.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(display.getText().toString() != ""){
					memory -= num1;
				}
			}
		});
	}
}