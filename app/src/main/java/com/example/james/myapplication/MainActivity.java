package com.example.james.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

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
        new Myclass().execute();


    }


//    Bitmap bitmap;
    private  class Myclass extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... params) {

        jsonStr = Downloadjson("http://10.0.2.2:50018/WebService1.asmx/HelloWorld");
        //   bitmap = DownloadImage(
        //           "https://www.streetcar.org/wp-content/uploads/IMG_1327-e1511941500211.jpg");
        //Downloadjson("https://www.streetcar.org/wp-content/uploads/IMG_1327-e1511941500211.jpg");
        return null;
    }


    String jsonStr = null;

    @Override
    protected void onPostExecute(Void result) {


        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("contacts");

                // looping through All Contacts
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);

                    String name = c.getString("name");
                    String phone = c.getString("phone");


                    Mycontact contact = new Mycontact(name, phone);


                    // adding contact to contact list
                    internalList.add(contact);
                }
            } catch (final JSONException e) {
                //   Log.e(TAG, "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }


            internalList.add(new Mycontact("James", "12345678"));
            internalList.add(new Mycontact("Joyce", "12345678"));

            internalList.add(new Mycontact("John", "12345678"));

            internalList.add(new Mycontact("Marry", "12345678"));

            MyListAdater adapter = new MyListAdater(getApplicationContext(), internalList);
            ListView myview = (ListView) findViewById(R.id.mylist);
            String[] items = {"item1", "item2", "James", "Joyce", "John"};
            // ArrayAdapter<String> adapter= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, items);


            myview.setAdapter(adapter);

            myview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String str = ((TextView) view).getText().toString();
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

                }
            });


        }

    }
}

    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception ex) {
            throw new IOException("Error connecting");
        }
        return in;
    }



    private String  Downloadjson(String URL) {

        InputStream in = null;

        String ret="";
        try {
            in = OpenHttpConnection(URL);

            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }

            ret= total.toString();
            in.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return  ret;
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
