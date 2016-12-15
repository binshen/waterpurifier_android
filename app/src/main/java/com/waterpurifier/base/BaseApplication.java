package com.waterpurifier.base;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by bin.shen on 03/12/2016.
 */

public class BaseApplication extends Application {

    private RequestQueue requestQueue;

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public JSONObject loginUser;
}
