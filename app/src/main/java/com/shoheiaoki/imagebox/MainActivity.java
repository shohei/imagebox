package com.shoheiaoki.imagebox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView algoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        algoListView = (ListView) findViewById(R.id.algoListView);
        String[] algoList = getResources().getStringArray(R.array.image_processing_algorithms);
        ArrayList<String> algoNames = new ArrayList<>();
        for(String a : algoList){
            int algoId = this.getResources().getIdentifier(a,"string",this.getPackageName());
            String algoName = getResources().getString(algoId);
            algoNames.add(algoName);
        }

        ArrayAdapter<String> algoAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,algoNames);
        algoListView.setAdapter(algoAdapter);
    }
}
