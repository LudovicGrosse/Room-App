

package com.example.lg346.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.time.LocalDateTime;

public class DestinationActivity extends AppCompatActivity {
    // Will show the string "data" that holds the results
    ListView results;
    // URL of object to be parsed
    String JsonURL = "https://galerie-elise.com:8080/api/devices?limit=99";

    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;

    List<DestinationClass> listDest = new ArrayList<DestinationClass>();

    public static class SSLCerts {

        public static void nuke() {
            try {
                TrustManager[] trustAllCerts = new TrustManager[] {
                        new X509TrustManager() {
                            public X509Certificate[] getAcceptedIssuers() {
                                X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                                return myTrustedAnchors;
                            }

                            @Override
                            public void checkClientTrusted(X509Certificate[] certs, String authType) {}

                            @Override
                            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                        }
                };

                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String arg0, SSLSession arg1) {
                        return true;
                    }
                });
            } catch (Exception e) {
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SSLCerts.nuke();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);


        // Casts results into the ListView found within the main layout XML with id jsonData
        results = findViewById(R.id.jsonData);
        JSONObject jsonObj = new JSONObject();

        // Creating the JsonArrayRequest class called arrayreq, passing the required parameters
        // JsonURL is the URL to be fetched from
        JsonObjectRequest arrayobj = new JsonObjectRequest(0,JsonURL,jsonObj,

                new Response.Listener<JSONObject>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("Volley", "12345");
                        String room;
                        String etat;

                        try {

                            JSONArray tab = response.getJSONArray("result");

                            // Affichage des données JSON
                            Log.d("Tableau JSON : ", tab.toString());

                            for (int i = 0; i < tab.length(); i++) {

                                //gets each JSON object within the JSON array
                                JSONObject jsonObject = tab.getJSONObject(i);

                                room = !jsonObject.isNull("name") ? jsonObject.getString("name") : "";

                                if(room.contains("Room"))
                                {
                                    Long period = -1l;

                                    if(!jsonObject.isNull("lastSeenAt"))
                                    {
                                        etat = jsonObject.getString("lastSeenAt").substring(0,23);

                                        LocalDateTime now = java.time.LocalDateTime.now();
                                        LocalDateTime lastview = LocalDateTime.parse(etat);
                                        period = Duration.between(lastview, now).getSeconds();

                                        // System.out.println("Room " + room);
                                        // System.out.println("Dernière vue: " + date);
                                        // System.out.println("Temps courant: " + now);
                                        // System.out.println("Période: " + d.getSeconds());
                                    }

                                    DestinationClass dest = new DestinationClass(room, period);
                                    listDest.add(dest);
                                }
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
                headers.put("Grpc-Metadata-Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJsb3JhLWFwcC1zZXJ2ZXIiLCJleHAiOjE5OTUzMTc4NzgsImlzcyI6ImxvcmEtYXBwLXNlcnZlciIsIm5iZiI6MTU1NTIzMTQ3OCwic3ViIjoidXNlciIsInVzZXJuYW1lIjoidG90byJ9.Ytis78Tj39hPXWAYB9h2Lol5IBbOAFAT-UXyKNWKR-E");
                return headers;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        arrayobj.setTag("headerRequest");
        requestQueue.add(arrayobj);}}