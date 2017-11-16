package com.example.james.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myview =(ListView) findViewById(R.id.mylist);
        String [] items={"item1","item2","James","Joyce","John"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, items);
        myview.setAdapter(adapter);

        myview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = ((TextView)view).getText().toString();
                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();

            }
        });



    }
}
