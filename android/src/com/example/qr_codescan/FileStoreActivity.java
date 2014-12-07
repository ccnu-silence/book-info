package com.example.qr_codescan;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * SharedPreferences 主要用于保存一些简单的数据格式
 * 可通过Context,Activity,SharedPreference.Manager三种方式来得到SharedPreferences对象
 * Created by phnix on 2014/12/7.
 */
public class FileStoreActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_db);
        Button button1 = (Button) findViewById(R.id.btnPref);
        Button button2 = (Button) findViewById(R.id.btnWritePref);
        final EditText editText = (EditText) findViewById(R.id.editText);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editText.getText())) {
                    SharedPreferences preferences =
                            PreferenceManager.getDefaultSharedPreferences(FileStoreActivity.this);
                    String name = preferences.getString("name", "");
                    editText.setText(name);
                    editText.setSelection(name.length());
                    Toast.makeText(FileStoreActivity.this,
                            "读取成功", Toast.LENGTH_LONG).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences =
                        PreferenceManager.getDefaultSharedPreferences(FileStoreActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", editText.getText().toString());
                editor.commit();
                Toast.makeText(FileStoreActivity.this,
                        " 保存成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
