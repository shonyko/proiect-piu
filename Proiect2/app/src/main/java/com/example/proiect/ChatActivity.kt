package com.example.proiect

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }
    fun goBack(item: MenuItem) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
            .setTitle("Parasire chat")
            .setMessage("Sunteti sigur ca situatia s-a rezolvat?")
            .setPositiveButton("Da") { _, _ ->
                startActivity(Intent(this, MainActivity::class.java))
            }
            .setNegativeButton("Nu", null)
            .create()
            .show()
    }
}
