package com.thenewboston.myapplicationvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    TextView t,bn,s,d,c,n;
    EditText e;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bn= (TextView) findViewById(R.id.b);
        s= (TextView) findViewById(R.id.s);
        d= (TextView) findViewById(R.id.d);
        c= (TextView) findViewById(R.id.c);
        n= (TextView) findViewById(R.id.n);
        e = (EditText ) findViewById(R.id.editText);
        b = (Button) findViewById(R.id.button);



    }
    public void but(View v){


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, "http://192.168.0.4/bmtc/json1.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray;
                try{
                    jsonArray = new JSONArray(response);
                    String bus = e.getText().toString();
                    /*String busno = jsonArray.getJSONObject(0).getString("bus_no");
                    t.setText(busno);*/
                    for(int i = 0; i < jsonArray.length(); i++)
                    {

                        String busno = jsonArray.getJSONObject(i).getString("bus_no");
                        if(busno.equals(bus)){
                            bn.setText(bus);
                            d.setText(jsonArray.getJSONObject(i).getString("dest"));
                            n.setText(jsonArray.getJSONObject(i).getString("now"));
                            s.setText(jsonArray.getJSONObject(i).getString("src"));
                            c.setText(jsonArray.getJSONObject(i).getString("crowd"));

                        }
                        //Iterate through the elements of the array i.
                        //Get thier value.
                        //Get the value for the first element and the value for the last element.
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }

                // Toast.makeText(getApplicationContext(),"Response:"+response,Toast.LENGTH_LONG).show();
                // t.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(request);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
