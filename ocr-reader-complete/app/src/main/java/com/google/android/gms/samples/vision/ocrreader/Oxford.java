package com.example.somalaya.spellcheck;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class Oxford {
    private static final String TAG = MyApplication.class.getName();
    final String app_id = "48c1b5e0";
    final String app_key = "611bb03541f7b5a688c2955e7e96e040";
    final String language = "en";


    public String requestDictionary(String word,final VolleyCallback callback){

        final String word_id = word.toLowerCase();
         String url = "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
        // Creating volley request obj
        String def ="";
        JsonObjectRequest Req = new JsonObjectRequest(Request.Method.GET,url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        callback.onSuccessResponse(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"errorrrrrr");
            }
        })
        {
            /**
             * Passing some request headers*
             */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("app_id", app_id);
                headers.put("app_key", app_key);
                return headers;
            }

        };

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(Req,"headerRequest");
        return def;
    }

    public interface VolleyCallback {
        void onSuccessResponse(JSONObject result);
    }
}
