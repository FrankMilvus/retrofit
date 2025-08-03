package com.example.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val gson = Gson()

        val employee = Employee("John Doe", 30, "johnDoe@example.com")

        val json = gson.toJson(employee)




//        {
//            "age": 30,
//            "mail": "johnDoe@example.com",
//            "name": "John Doe"
//        }

        val json2="{\"firstName\":\"John\",\"age\":30,\"mail\":\"johnDoe@example.com\",\"name\":\"John Doe\"}"

        val employee2 = gson.fromJson(json2, Employee::class.java)
        Log.d("json2", json2)
        println(json2)
    }
}