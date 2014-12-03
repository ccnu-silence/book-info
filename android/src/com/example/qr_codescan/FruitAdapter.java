package com.example.qr_codescan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.pera.model.Fruit;

import java.util.List;

/**
 * Created by phnix on 2014/12/4.
 */
public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
//    public FruitAdapter()

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.fruit_image);
        TextView textView = (TextView)view.findViewById(R.id.fruit_name);
        imageView.setImageResource(fruit.getImageId());
        textView.setText(fruit.getName());
        return view;
    }
}
