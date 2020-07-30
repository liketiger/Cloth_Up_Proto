package com.liketiger.cloth_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.firebase.auth.FirebaseAuth
import com.liketiger.cloth_up.Auth.LoginActivity
import com.liketiger.cloth_up.Auth.MyCominActivity
import com.liketiger.cloth_up.Fragment.MarketInfo.MarketInfoActivity
import com.liketiger.cloth_up.Zzim.ZzimActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom.*
import kotlinx.android.synthetic.main.viewpager_activity.*

class MainActivity : AppCompatActivity() {



    internal lateinit var viewpager : ViewPager
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, LoadingActivity::class.java)
        startActivity(intent)

        auth = FirebaseAuth.getInstance()

        val img = arrayOf(
                R.drawable.man,
                R.drawable.woman,
                R.drawable.vintage,
                R.drawable.brand,
                R.drawable.sale,
                R.drawable.etc
        )



        val gridviewAdapter = GridviewAdapter(this, img)
        gridview.adapter = gridviewAdapter

        gridview.setOnItemClickListener{adapterView, view, i, l ->
            val intent = Intent ( this, LectureActivity::class.java)
            startActivity(intent)
        }

        viewpager = findViewById(R.id.viewPager) as ViewPager

        val adapter = ViewPagerAdapter(this)
        viewpager.adapter = adapter

        zzim_icon.setOnClickListener {
            val intent = Intent(this, ZzimActivity::class.java)
            startActivity(intent)
        }



        my_page.setOnClickListener {
            if(auth.currentUser == null){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else{
                val intent = Intent(this, MyCominActivity::class.java)
                startActivity(intent)
            }
        }


        loc_text.setOnClickListener {
            val intent = Intent(this, GMapActivity::class.java)
            startActivity(intent)
        }

        advertise.setOnClickListener {
            Toast.makeText(this, "기능적 한계 에러에러!", Toast.LENGTH_LONG).show()
        }
    }
}
