package com.example.retrofit

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.retrofit.model.Comment
import com.example.retrofit.model.JsonPlaceHolderApi
import com.example.retrofit.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var jsonPlaceHolderApi: JsonPlaceHolderApi

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
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        getPost(listOf(6,3), "id", "desc")
//        getComments(4)


    }

    private fun getPost(
        userId: List<Int>,
//        userId2: Int,
        id: String,
        order: String
    ) {

        val call = jsonPlaceHolderApi.getPosts(
            userId,
//            userId2,
            id,
            order
        )

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
                textViewResult.text = "Some error"
            }

        })
    }

    private fun getComments(postId: Int) {
        val call = jsonPlaceHolderApi.getComments(postId)

        call.enqueue(object : Callback<MutableList<Comment>> {
            override fun onResponse(
                call: Call<MutableList<Comment>>,
                response: Response<MutableList<Comment>>
            ) {
                if (response.isSuccessful) {
                    val comments = response.body()

                    for (comment in comments!!) {
                        var content = ""
                        content += "ID: " + comment.id + "\n"
                        content += "Post ID: " + comment.postId + "\n"
                        content += "Name: " + comment.name + "\n"
                        content += "Email: " + comment.email + "\n"
                        content += "Text: " + comment.text + "\n\n"

                        textViewResult.append(content)
                    }
                }
            }

            override fun onFailure(
                call: Call<MutableList<Comment>?>,
                t: Throwable
            ) {
                Toast.makeText(
                    this@MainActivity,
                    "Something went wrong ${t.toString()}",
                    Toast.LENGTH_SHORT
                ).show()
                textViewResult.text = "Some error"
            }


        })


    }
}


