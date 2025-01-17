package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main); // tells activity what to look like

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton", "New York", "Orlando", "Los Angeles"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*
                The following code was inspired by https://www.homeandlearn.co.uk/android/android_simple_list_items.html#:~:text=To%20get%20which%20item%20was,getItemAtPosition(%20position%20)%3B
                Authored by: Home and Learn
                Accessed Jan 17 by Luke Yaremko
                 */

                cityList.setSelector(android.R.color.darker_gray);
                selected = (String)cityList.getItemAtPosition(position);
            }
        });


        TextView cityField = findViewById(R.id.cityText);

        cityField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityField.setText("");
            }
        });

        Button createButton = findViewById(R.id.create_button);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = cityField.getText().toString().trim();
                if (!(city.isEmpty()) && !dataList.contains(city)){ // no empty or duplicates
                    dataList.add(cityField.getText().toString());
                    cityAdapter.notifyDataSetChanged();
                    cityField.setText("Enter City Name");
                }


            }
        });

        Button deleteButton = findViewById(R.id.delete_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataList.remove(selected);
                cityAdapter.notifyDataSetChanged();
                cityList.setSelector(android.R.color.white);
            }
        });




    }
}