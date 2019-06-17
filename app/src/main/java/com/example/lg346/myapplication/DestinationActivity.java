

package com.example.lg346.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DestinationActivity extends AppCompatActivity {
    // Will show the string "data" that holds the results
    ListView results;
    // URL of object to be parsed
    String JsonURL = "https://jsonbin.org/ludovicgrosse/blog";

    // This string will hold the results
    String data;

    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;

    List<DestinationClass> listDest = new ArrayList<DestinationClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);


        // Casts results into the ListView found within the main layout XML with id jsonData
        results = (ListView) findViewById(R.id.jsonData);
        JSONObject jsonObj = new JSONObject();

        // Creating the JsonArrayRequest class called arrayreq, passing the required parameters
        // JsonURL is the URL to be fetched from
        JsonObjectRequest arrayobj = new JsonObjectRequest(0, JsonURL,jsonObj,

                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("Volley", "12345");
                        String room;
                        String etat;

                        try {

                            JSONArray tab = response.getJSONArray("data");

                            for (int i = 0; i < tab.length(); i++) {

                                //gets each JSON object within the JSON array
                                JSONObject jsonObject = tab.getJSONObject(i);

                                room = !jsonObject.isNull("room") ? jsonObject.getString("room") : "";
                                etat = !jsonObject.isNull("etat") ? jsonObject.getString("etat") : "";

                                DestinationClass dest = new DestinationClass(room, etat);
                                listDest.add(dest);
                                Log.d("ROOM :",": "+ room);
                                Log.d("ETAT :",": "+ etat);
                            }

                            // Adds the data string to the TextView "results"
                            DestinationAdaptater adapter = new DestinationAdaptater(getApplicationContext(), listDest);
                            results.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log

                            Log.d("JSONException :", e.toString());
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener()
                {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                })
        {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("authorization", "token a76c3bbc-c413-4085-8902-5ecff2e7d775");
                return headers;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        arrayobj.setTag("headerRequest");;
        requestQueue.add(arrayobj);}}
