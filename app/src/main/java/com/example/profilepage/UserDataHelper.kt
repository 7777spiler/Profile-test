package com.example.profilepage

import android.app.Activity
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class UserDataHelper(var context:Activity) {
    val API_URL = "https://randomuser.me/api/?results=10"


    fun createRequest(listener: Response.Listener<JSONObject>) {
        var requestQueue = Volley.newRequestQueue(context)

        CoroutineScope(Dispatchers.IO).launch {
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                API_URL,
                null,
                listener,
                {
                    Log.d("API Request Error", "${it.printStackTrace()}")
                })
            requestQueue.add(jsonObjectRequest)
        }
    }

    fun getUser(jsonObject: JSONObject): User {
        return User(
            jsonObject.getJSONObject("name").getString("first"),
            jsonObject.getString("gender"),
            jsonObject.getString("email"),
            jsonObject.getJSONObject("picture").getString("large"),
            jsonObject.getString("phone"),
            jsonObject.getJSONObject("location").getString("country")
        )
    }

    fun getUserList(array: JSONArray): MutableList<User> {
        var users: MutableList<User> = mutableListOf()

        for (i in 0..array.length() - 1) {
            users.add(getUser(array.getJSONObject(i)))
        }
        return users
    }
}