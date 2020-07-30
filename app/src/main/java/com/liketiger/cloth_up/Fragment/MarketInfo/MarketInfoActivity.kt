package com.liketiger.cloth_up.Fragment.MarketInfo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.liketiger.cloth_up.MainActivity
import com.liketiger.cloth_up.R
import com.liketiger.cloth_up.Utils.FirebaseUtils
import kotlinx.android.synthetic.main.activity_lecture.*
import kotlinx.android.synthetic.main.activity_market_info.*


class MarketInfoActivity : AppCompatActivity() {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val db : FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_info)

        lecture_text.text = intent.getStringExtra("title")

        FirebaseUtils.db
            .collection("zzim")
            .document(FirebaseUtils.getUid())
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if(documentSnapshot.get(intent.getStringExtra("title").toString()) == true){
                    header_zzim.text="즐찾취소"
                    header_zzim.setTextColor(Color.BLUE)
                }
            }
            .addOnFailureListener{
            }

        FirebaseUtils.getUid()
        FirebaseUtils.db

        zzim.setOnClickListener {

            if(header_zzim.text.equals("즐찾취소")){

                header_zzim.text="즐찾하기"
                header_zzim.setTextColor(Color.RED)

                FirebaseUtils.db
                    .collection("zzim")
                    .document(FirebaseUtils.getUid())
                    .update(intent.getStringExtra("title").toString(), "")
                    .addOnSuccessListener {
                        Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                    }

            }else{
                header_zzim.text="즐찾취소"
                header_zzim.setTextColor(Color.BLUE)

                FirebaseUtils.db
                    .collection("zzim")
                    .document(FirebaseUtils.getUid())
                    .update(intent.getStringExtra("title").toString(), true)
                    .addOnSuccessListener {
                        Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener{
                        Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                    }
            }


        }

        figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20F)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_area, ContentFragment()).commit()

        figure_1.setOnClickListener {
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15F)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_area, ContentFragment()).commit()
        }
        figure_2.setOnClickListener {
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15F)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_area, InfoFragment()).commit()
        }
        figure_3.setOnClickListener {
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20F)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_area, ReviewFragment()).commit()
        }

    }
}