package com.example.james.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class Mycontact{
   public  String name;
    public String phone;
    public Mycontact(String a, String b)
    {
        name=a;
        phone =b;

    }
};

public class MainActivity extends AppCompatActivity {

    ArrayList<Mycontact> internalList = new ArrayList<Mycontact>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        internalList.add(new Mycontact("James","12345678"));
        internalList.add(new Mycontact("Joyce","12345678"));

        internalList.add(new Mycontact("John","12345678"));

        internalList.add(new Mycontact("Marry","12345678"));

        MyListAdater adapter= new MyListAdater(getApplicationContext(),internalList);
        ListView myview =(ListView) findViewById(R.id.mylist);
        String [] items={"item1","item2","James","Joyce","John"};
       // ArrayAdapter<String> adapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, items);



        myview.setAdapter(adapter);

        myview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = ((TextView)view).getText().toString();
                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();

            }
        });

    }



    class MyListAdater extends ArrayAdapter<Mycontact> {

        public MyListAdater(Context con, ArrayList<Mycontact> users)
        {
            super(con,0,users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater =getLayoutInflater();

           View myview= inflater.inflate(R.layout.myview, null);

           Mycontact user= internalList.get(position);
            TextView tv=(TextView) myview.findViewById(R.id.textView);
            TextView tv2=(TextView) myview.findViewById(R.id.textView2);
            tv.setText(user.name);
            tv2.setText(user.phone);


            return myview;
        }
    }


}
