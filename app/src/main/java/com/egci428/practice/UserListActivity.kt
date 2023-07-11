package com.egci428.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import com.egci428.practice.model.User
import com.egci428.practice.model.UserAdapter
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

class UserListActivity : AppCompatActivity() {

    lateinit var userList: MutableList<User>
    private val file = "users.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        val listView = findViewById<ListView>(R.id.listView)

        userList = mutableListOf()

        try{
            val fIn = openFileInput(file)
            val mfile = InputStreamReader(fIn)
            val br = BufferedReader(mfile)
            var line = br.readLine()
            var user = User()
            while(line != null) {
                var userItem = line.split(",")
                user = User(userItem[0], userItem[1],userItem[2].toDouble(), userItem[3].toDouble())
                userList.add(user)
                line = br.readLine()
            }
            br.close()
            mfile.close()
            fIn.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

        val adapter = UserAdapter(this,R.layout.userslist,userList)
        listView.adapter = adapter


        listView.setOnItemClickListener { parent, view, position, id ->

            val auser = userList!!.get(position)
            displayMap(auser)
        }
    }

    private fun displayMap(ausr: User) {
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("selUser", ausr.username)
        intent.putExtra("selLat", ausr.latitude)
        intent.putExtra("selLon", ausr.longitude)
        startActivity(intent)
    }
}
