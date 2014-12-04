package com.example.qr_codescan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.pera.model.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phnix on 2014/12/4.
 */
public class BookListActivity extends Activity {

//    private String[] data = {"apple", "hello", "phnix", "apple", "hello", "phnix", "apple", "hello", "phnix"};
    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_list);

        initFruits();
        FruitAdapter adapter = new FruitAdapter(BookListActivity.this,
                R.layout.fruit_item, fruitList);

        ListView listView = (ListView) this.findViewById(R.id.listView);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookListActivity.this,
//                android.R.layout.simple_list_item_1, data);
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
}
