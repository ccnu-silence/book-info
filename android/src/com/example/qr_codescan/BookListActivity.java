package com.example.qr_codescan;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pera.model.Book;
import com.pera.model.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phnix on 2014/12/4.
 */
public class BookListActivity extends Activity implements ReFlashListView.IReflashListener {
    //    private List<Fruit> fruitList = new ArrayList<Fruit>();
//    private List<Book> bookList = new ArrayList<Book>();
    private ReFlashListView listView;
    private final static int BOOK_JSON_OK = 1;
    BookListActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_list);

        initFruits();
        activity = this;

    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BOOK_JSON_OK:
                    List<Book> bookList = (List<Book>) msg.obj;
                    BookAdapter adapter = new BookAdapter(BookListActivity.this,
                            R.layout.list_item, bookList);
                    listView = (ReFlashListView) activity.findViewById(R.id.listView);
                    listView.setInterface(BookListActivity.this);
                    listView.setAdapter(adapter);
            }
        }
    };

    private void initFruits() {
        HttpUtil.sendHttpRequest("http://182.92.186.171:8080/book/all",
                new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        Gson gson = new Gson();
                        List<Book> bookList = gson.fromJson(response,
                                new TypeToken<List<Book>>() {
                                }.getType());

                        Message message = new Message();
                        message.what = BOOK_JSON_OK;
                        message.obj = bookList;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    @Override
    public void onReflash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BookListActivity.this, "已刷新", Toast.LENGTH_SHORT).show();
                initFruits();
                listView.reflashComplete();
            }
        }, 2000);

    }
}
