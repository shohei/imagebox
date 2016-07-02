package com.shoheiaoki.imagebox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String MAIN_TO_IMAGE_PROCESS = "MainActivity_ImageProcessActivity";

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

        ArrayAdapter<String> algoAdapter = new ArrayAdapter<String>(this,R.layout.rowdata,algoNames);
        algoListView.setAdapter(algoAdapter);

        algoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String val = (String)adapterView.getItemAtPosition(i);
//                Toast.makeText(MainActivity.this,val,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,ImageProcessActivity.class);
                intent.putExtra(MAIN_TO_IMAGE_PROCESS,val);
                startActivity(intent);
            }
        });
    }
}
