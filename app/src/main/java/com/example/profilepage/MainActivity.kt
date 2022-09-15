package com.example.profilepage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.android.volley.Response
import org.json.JSONObject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userDataHelper = UserDataHelper(this)

        var listener: Response.Listener<JSONObject> = Response.Listener<JSONObject> {
            var userList = userDataHelper.getUserList(it.getJSONArray("results"))

            setContent {
                CustomNavHost(userList)
            }
        }
        userDataHelper.createRequest(listener)
    }
}




