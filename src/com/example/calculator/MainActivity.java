package com.example.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	// 定义按钮和编辑框
	private Button btn_1;
	private Button btn_2;
	private Button btn_3;
	private Button btn_4;
	private Button btn_5;
	private Button btn_6;
	private Button btn_7;
	private Button btn_8;
	private Button btn_9;
	private Button btn_0;
	private Button btn_clear;
	private Button btn_del;
	private Button btn_add;
	private Button btn_minus;
	private Button btn_multiply;
	private Button btn_divide;
	private Button btn_point;
	private Button btn_equal;
	private Button btn_to2;
	private EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 初始化
		btn_1 = (Button) findViewById(R.id.btn_1);
		btn_2 = (Button) findViewById(R.id.btn_2);
		btn_3 = (Button) findViewById(R.id.btn_3);
		btn_4 = (Button) findViewById(R.id.btn_4);
		btn_5 = (Button) findViewById(R.id.btn_5);
		btn_6 = (Button) findViewById(R.id.btn_6);
		btn_7 = (Button) findViewById(R.id.btn_7);
		btn_8 = (Button) findViewById(R.id.btn_8);
		btn_9 = (Button) findViewById(R.id.btn_9);
		btn_0 = (Button) findViewById(R.id.btn_0);
		btn_add = (Button) findViewById(R.id.btn_add);
		btn_minus = (Button) findViewById(R.id.btn_minus);
		btn_multiply = (Button) findViewById(R.id.btn_multiply);
		btn_divide = (Button) findViewById(R.id.btn_divide);
		btn_point = (Button) findViewById(R.id.btn_point);
		btn_equal = (Button) findViewById(R.id.btn_equal);
		btn_clear = (Button) findViewById(R.id.btn_clear);
		btn_del = (Button) findViewById(R.id.btn_del);
		btn_to2 = (Button) findViewById(R.id.btn_to2);
		et = (EditText) findViewById(R.id.et_input);

		// 绑定点击事件
		btn_1.setOnClickListener(this);
		btn_2.setOnClickListener(this);
		btn_3.setOnClickListener(this);
		btn_4.setOnClickListener(this);
		btn_5.setOnClickListener(this);
		btn_6.setOnClickListener(this);
		btn_7.setOnClickListener(this);
		btn_8.setOnClickListener(this);
		btn_9.setOnClickListener(this);
		btn_0.setOnClickListener(this);
		btn_add.setOnClickListener(this);
		btn_minus.setOnClickListener(this);
		btn_multiply.setOnClickListener(this);
		btn_divide.setOnClickListener(this);
		btn_point.setOnClickListener(this);
		btn_equal.setOnClickListener(this);
		btn_clear.setOnClickListener(this);
		btn_del.setOnClickListener(this);
		btn_to2.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		String exp = et.getText().toString();
		switch (((Button) v).getId()) {
		case R.id.btn_0:
		case R.id.btn_1:
		case R.id.btn_2:
		case R.id.btn_3:
		case R.id.btn_4:
		case R.id.btn_5:
		case R.id.btn_6:
		case R.id.btn_7:
		case R.id.btn_8:
		case R.id.btn_9:
			et.setText(exp + ((Button) v).getText());
			break;
		case R.id.btn_point:
			if (exp.equals("")) {
				et.setText("0.");
				break;
			}
			if (exp.charAt(exp.length()) == '.') {
				break;
			}
			et.setText(exp + ".");
			break;
		case R.id.btn_add:
		case R.id.btn_minus:
		case R.id.btn_multiply:
		case R.id.btn_divide:
			if (exp.charAt(exp.length()) == '+'
					|| exp.charAt(exp.length()) == '-'
					|| exp.charAt(exp.length()) == '*'
					|| exp.charAt(exp.length()) == '/') {
				break;
			}
			et.setText(exp + ((Button) v).getText());
			break;

		case R.id.btn_clear:
			et.setText("");
			break;

		case R.id.btn_del:
			if (exp.equals("")) {
				break;
			}
			String str = et.getText().toString();
			str = str.substring(0, str.length() - 1);
			et.setText(str);
			break;
		case R.id.btn_equal:
			getResult();
			break;
		case R.id.btn_to2:
			toBinary();
			break;
		default:
			break;
		}

	}

	/*
	 * 后缀表达式计算结果
	 */
	public void getResult() {
		String exp = et.getText().toString();
		Test t = new Test(exp);
		double res = t.run();
		String str = new String();
		str = Double.toString(res);
		et.setText(str);

	}

	/*
	 * from 10 to binary
	 */

	public void toBinary() {
		String exp = et.getText().toString();
		int i = Integer.parseInt(exp);
		String res = new String();
		while (i != 0) {
			res = i % 2 + res;
			i = i / 2;
		}
		et.setText(res);
	}

}