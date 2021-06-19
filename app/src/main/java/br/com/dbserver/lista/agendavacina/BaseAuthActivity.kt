package br.com.dbserver.lista.agendavacina

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

abstract class BaseAuthActivity: AppCompatActivity() {
    val auth = Firebase.auth

    override fun onResume() {
        super.onResume()
        auth.addAuthStateListener {
            if (auth.currentUser == null){
                Intent(this, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(this)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_logout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_item_logout -> auth.signOut()
        }
        return super.onOptionsItemSelected(item)
    }


}