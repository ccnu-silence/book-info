package com.example.qr_codescan;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;
import com.pera.model.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phnix on 2014/12/4.
 */
public class BookListActivity extends Activity implements ReFlashListView.IReflashListener {

    //    private String[] data = {"apple", "hello", "phnix", "apple", "hello", "phnix", "apple", "hello", "phnix"};
    private List<Fruit> fruitList = new ArrayList<Fruit>();
    private ReFlashListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_list);

        initFruits();
        FruitAdapter adapter = new FruitAdapter(BookListActivity.this,
                R.layout.list_item, fruitList);

        listView = (ReFlashListView) this.findViewById(R.id.listView);
        listView.setInterface(this);
        listView.setAdapter(adapter);

    }

    private void initFruits() {
        Fruit apple = new Fruit("apple", R.drawable.ic_launcher);
        Fruit apple2 = new Fruit("apple", R.drawable.ic_launcher);
        Fruit apple3 = new Fruit("apple", R.drawable.ic_launcher);
        Fruit apple4 = new Fruit("apple", R.drawable.ic_launcher);
        Fruit apple5 = new Fruit("apple", R.drawable.ic_launcher);
        fruitList.add(apple);
        fruitList.add(apple2);
        fruitList.add(apple3);
        fruitList.add(apple4);
        fruitList.add(apple5);

    }

    @Override
    public void onReflash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BookListActivity.this, "已刷新", Toast.LENGTH_SHORT).show();
                listView.reflashComplete();
            }
        }, 2000);

    }
}
