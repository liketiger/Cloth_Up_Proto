package com.liketiger.cloth_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.tabs.TabLayout
import com.liketiger.cloth_up.Fragment.ListFragment.FragmentAdapter
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.activity_lecture.*
import kotlinx.android.synthetic.main.activity_lecture.home_button
import kotlinx.android.synthetic.main.custom_tab.view.*

class LectureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecture)

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        list_viewpager.adapter = fragmentAdapter

        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("남성")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("여성")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("브랜드")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("빈티지")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("악세사리")))
        tab_layout.addTab(tab_layout.newTab().setCustomView(createTabView("신상")))

        list_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null) {
                    list_viewpager.currentItem = p0.position
                }
            }
        })

        home_button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun createTabView(tabName : String) : View {
        val tabView = LayoutInflater.from(baseContext).inflate(R.layout.custom_tab, null)
        tabView.txt_name.text = tabName

        return tabView
    }
}
