package com.liketiger.cloth_up.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.liketiger.cloth_up.MainActivity
import com.liketiger.cloth_up.R
import kotlinx.android.synthetic.main.activity_join.*

class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        auth = FirebaseAuth.getInstance()

        join_login_button.setOnClickListener {

            auth.createUserWithEmailAndPassword(
                join_email_area.text.toString(),
                join_password_area.text.toString()
            )
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {

                        val intent = Intent(this, JoinInfoActivity::class.java)
                        startActivity(intent)

                    } else {

                        Toast.makeText(this, "이미 가입된 아이디 혹은 형식에 맞지 않는 아이디 입니다", Toast.LENGTH_LONG).show()

                    }

                }
        }

    }
}