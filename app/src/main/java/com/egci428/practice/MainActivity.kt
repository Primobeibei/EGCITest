package com.egci428.practice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.egci428.practice.model.User
import java.io.BufferedReader
import java.io.InputStreamReader



class MainActivity : AppCompatActivity() {

    var uname: String? = null
    var pname: String? = null
    var userprofile: User? = null

    private val file = "users.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signInBtn = findViewById<Button>(R.id.signInBtn)
        val signUpBtn = findViewById<Button>(R.id.signUpBtn)
        val cancelBtn = findViewById<Button>(R.id.cancelBtn)
        val userText = findViewById<EditText>(R.id.userText)
        val passText = findViewById<EditText>(R.id.passText)

        signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        signInBtn.setOnClickListener {
            uname = userText.text.toString()
            pname = passText.text.toString()
            if (!uname.isNullOrEmpty() && !pname.isNullOrEmpty()) {
                try {
                    val fIn = openFileInput(file)
                    val mfile = InputStreamReader(fIn)
                    val br = BufferedReader(mfile)
                    var line = br.readLine()
                    while(line != null){
                        var userItem = line.split(",")
                        if(userItem[0]==uname && userItem[1]==pname) {
                            Toast.makeText(applicationContext, "Login success", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, UserListActivity::class.java)
                            startActivity(intent)
                        }
                        line = br.readLine()
                    }
                    br.close()
                    mfile.close()
                    fIn.close()

                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        cancelBtn.setOnClickListener {
            userText.text = null
            passText.text = null


        }
    }

}
