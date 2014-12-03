package com.example.qr_codescan;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.pera.model.Book;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends Activity {
    private final static int SCANNIN_GREQUEST_CODE = 1;
    private final static int BOOK_JSON_OK = 1;
    /**
     * 显示扫描结果
     */
    private TextView mTextView;
    /**
     * 显示扫描拍的图片
     */
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.result);
        mImageView = (ImageView) findViewById(R.id.qrcode_bitmap);

        //点击按钮跳转到二维码扫描界面，这里用的是startActivityForResult跳转
        //扫描完了之后调到该界面
        Button mButton = (Button) findViewById(R.id.button1);
        mButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
            }
        });
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case BOOK_JSON_OK:
                    Book book = (Book)msg.obj;
                    mTextView.setText(book.getTitle());
                    URL picUrl;
                    try {
                        picUrl = new URL("http://www.baidu.com/img/bd_logo1.png");
                        Bitmap pngBM = BitmapFactory.decodeStream(picUrl.openStream());
//                        mImageView.setImageBitmap(pngBM);
                        mImageView.setImageURI(Uri.parse("http://www.baidu.com/img/bd_logo1.png"));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    String isbn = data.getStringExtra("book"); // json
                    // 处理网络
                    HttpUtil.sendHttpRequest(
                            "http://182.92.186.171:8080/book-info/search/" + isbn,
                            new HttpCallbackListener() {
                                @Override
                                public void onFinish(String response) {
                                    Intent resultIntent = new Intent();
                                    resultIntent.putExtra("book", response);
                                    Gson gson = new Gson();
                                    Book book = gson.fromJson(response, Book.class);
                                    Message message = new Message();
                                    message.what = BOOK_JSON_OK;
                                    message.obj=book;
                                    handler.sendMessage(message);
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                }
                break;
        }
    }

}
