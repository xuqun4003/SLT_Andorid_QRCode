package com.example.slt_andorid_qrcode;

import com.google.zxing.MipcaActivityCapture;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@ViewInject(R.id.textView1)
	TextView tv1;
	static final private int GET_CODE = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
	}
	
	@OnClick(R.id.button1)
	private void Button1(View v){
		startActivity(new Intent(MainActivity.this,MakeQRCodeActivity.class));
	}
	
	@OnClick(R.id.button2)
	private void Button2(View v){
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, MipcaActivityCapture.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivityForResult(intent, GET_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 当前只有一个返回值
		if (resultCode == RESULT_OK && requestCode == GET_CODE) {
			String result = data.getExtras().getString("result");
			tv1.setText(result);
			if (TextUtils.isEmpty(result)) {
				Toast.makeText(MainActivity.this, R.string.scan_retry,
						Toast.LENGTH_SHORT).show();
				return;
			} else {
				Toast.makeText(MainActivity.this, result,
						Toast.LENGTH_SHORT).show();
				return;
			}
		}
		
	}
}
