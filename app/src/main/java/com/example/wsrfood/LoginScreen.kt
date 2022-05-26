package com.example.wsrfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.wsrfood.server.Login
import com.example.wsrfood.server.MyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginScreen : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var pass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        email = findViewById(R.id.email_et)
        pass = findViewById(R.id.pass_et)
    }

    fun LoginClick(view: View) {
        if (email.text.isNotEmpty() && pass.text.isNotBlank()){
            if (Patterns.EMAIL_ADDRESS.matcher(email.text).matches()){
                val call = MyRetrofit.getRetrofit().login(email.text.toString(), pass.text.toString(), "5FA1B987-3890-4A87-9712-ACDEAD0173AE")
                call.enqueue(object : Callback<Login>{
                    override fun onResponse(call: Call<Login>, response: Response<Login>) {
                        if (response.body()?.token != null){
                            AlertDialog.Builder(this@LoginScreen).setTitle("Success").setMessage(response.body()?.token.toString()).show()
                        }
                        else AlertDialog.Builder(this@LoginScreen).setMessage("pass invalid").show()
                    }

                    override fun onFailure(call: Call<Login>, t: Throwable) {
                        AlertDialog.Builder(this@LoginScreen).setMessage(t.message).show()
                    }
                })
            }
            else AlertDialog.Builder(this).setMessage("email invalid").show()
        }
        else AlertDialog.Builder(this).setMessage("empty").show()
    }
}