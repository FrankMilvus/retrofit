package com.example.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.retrofit.model.JsonPlaceHolderApi
import com.example.retrofit.model.Post
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var textViewResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        textViewResult = findViewById(R.id.text_view_result)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        val call = jsonPlaceHolderApi.getPosts()

        call.enqueue(object : Callback<MutableList<Post>> {
            override fun onResponse(
                call: Call<MutableList<Post>>,
                response: Response<MutableList<Post>>
            ) {
                if (response.isSuccessful) {

                    val posts = response.body()!!
                    for (post in posts) {
                        var content = ""
                        content += "ID: " + post.id + "\n"
                        content += "User ID: " + post.userId + "\n"
                        content += "Title: " + post.title + "\n"
                        content += "Text: " + post.text + "\n\n"
                        textViewResult.append(content)
                    }
                } else {
                    textViewResult.text = response.code().toString()

                }
            }


            override fun onFailure(
                call: Call<MutableList<Post>>,
                t: Throwable
            ) {
                Toast.makeText(
                    this@MainActivity,
                    "Something went wrong ${t.toString()}",
                    Toast.LENGTH_SHORT
                ).show()
                textViewResult.text="Some error"
            }

        })
    }
}
