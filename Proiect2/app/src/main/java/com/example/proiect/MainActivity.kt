package com.example.proiect

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.proiect.model.LoggedInUser
import com.example.proiect.model.Role
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar);
        toolbar.setNavigationOnClickListener {
            this.onBackPressedDispatcher.onBackPressed()
        }
        val helpButton = findViewById<Button>(R.id.needHelp)
        helpButton.visibility = if(LoggedInUser.role == Role.PACIENT) View.VISIBLE else View.GONE
    }
    fun signOut(item: MenuItem) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
            .setTitle("Delogare")
            .setMessage("Sunteti sigur ca doriti sa va delogati?")
            .setPositiveButton("Da") { _, _ ->
                startActivity(Intent(this, LoginActivity::class.java))
            }
            .setNegativeButton("Nu", null)
            .create()
            .show()
    }

    fun helpMe(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.are_you_lost_fragment, null)
        dialogBuilder
            .setTitle("Ajutor")
            .setMessage("Te simti dezorientat?")
            .setView(dialogView)
            .setNegativeButton("Sunt bine", null)
            .create()
            .show()
    }

    fun showWhatIHadToDo(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val window = dialogBuilder
            .setTitle("Ce trebuia sa fac")
            .setMessage("Mergi la spital")
            .setNegativeButton("M-am lamurit", null)
            .setPositiveButton("Cere ajutor") { dialog, _ ->
                dialog.dismiss()
                sendHelp(view)
            }
            .create()
        window.setOnShowListener {
            window.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED)
        }
        window.show()
    }
    fun sendHelp(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val window = dialogBuilder
            .setTitle("Contacteaza ajutor")
            .setMessage("Nu dispera, exista ajutor pe drum")
            .setPositiveButton("Continua") { dialog, _ ->
                startEmergencyChat()
            }
            .create()
        window.setOnShowListener {
            window.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED)
        }
        window.show()
    }
    private fun startEmergencyChat() {
        startActivity(Intent(this, ChatActivity::class.java))
    }
}