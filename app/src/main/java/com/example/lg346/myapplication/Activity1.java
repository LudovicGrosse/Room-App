package com.example.lg346.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Activity1 extends AppCompatActivity {
    // Will show the string "data" that holds the results
    TextView results;
    // URL of object to be parsed
    String JsonURL = "http://voyage2.corellis.eu/api/v2/homev2?lat=43.14554197717751&lon=6.00246207789145&offset=0";
    // This string will hold the results
    String data = "";
    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        // Creates the Volley request queue
        requestQueue = Volley.newRequestQueue(this);

        // Casts results into the TextView found within the main layout XML with id jsonData
        results = (TextView) findViewById(R.id.jsonData);
        JSONObject jsonObj = new JSONObject();

        // Creating the JsonArrayRequest class called arrayreq, passing the required parameters
        //JsonURL is the URL to be fetched from
        JsonObjectRequest arrayobj = new JsonObjectRequest(JsonURL,jsonObj,
                // The second parameter Listener overrides the method onResponse() and passes
                //JSONArray as a parameter
                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Retrieves first JSON object in outer array
                            // Retrieves "colorArray" from the JSON object
                            JSONArray tab = response.getJSONArray("data");
                            // Iterates through the JSON Array getting objects and adding them
                            //to the list view until there are no more objects in colorArray
                            for (int i = 0; i < tab.length(); i++) {
                                //gets each JSON object within the JSON array
                                JSONObject jsonObject = tab.getJSONObject(i);

                                // Retrieves the string labeled "colorName" and "hexValue",
                                // and converts them into javascript objects
                                String type = !jsonObject.isNull("type") ? jsonObject.getString("type"): "";
                                String id = !jsonObject.isNull("id") ? jsonObject.getString("id"): "";

                                // Adds strings from the current object to the data string
                                //spacing is included at the end to separate the results from
                                //one another
                                data += "Data " + (i + 1) + ": [ Type : " + type +
                                        ", ID : " + id + "] --- "; }
                            // Adds the data string to the TextView "results"
                            results.setText(data);
                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log

                            Log.d("TEST :", "aaaa"+e.toString());
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON array request "arrayreq" to the request queue
        requestQueue.add(arrayobj);
    }
}