package com.liketiger.cloth_up.Zzim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.liketiger.cloth_up.R
import com.liketiger.cloth_up.Utils.FirebaseUtils
import kotlinx.android.synthetic.main.activity_zzim.*

class ZzimActivity : AppCompatActivity() {

    val array_list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zzim)

        FirebaseUtils.db
            .collection("zzim")
            .document(FirebaseUtils.getUid())
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if(documentSnapshot.get("채원매장")!=""){
                    array_list.add("채원매장")
                }
                if(documentSnapshot.get("보세랜드")!=""){
                    array_list.add("보세랜드")
                }
                if(documentSnapshot.get("서핑랜드")!=""){
                    array_list.add("서핑랜드")
                }
                if(documentSnapshot.get("등산업")!=""){
                    array_list.add("등산업")
                }
                if(documentSnapshot.get("빈폴")!=""){
                    array_list.add("빈폴")
                }
                if(documentSnapshot.get("황갤러리")!=""){
                    array_list.add("황갤러리")
                }
                if(documentSnapshot.get("MyStyle")!=""){
                    array_list.add("MyStyle")
                }
                if(documentSnapshot.get("빈티지월드")!=""){
                    array_list.add("빈티지월드")
                }
                if(documentSnapshot.get("브세")!="") {
                    array_list.add("브세")
                }

                val zzimAdapter = ZzimAdapter(this, array_list)
                zzim_listview.adapter = zzimAdapter
            }
            .addOnFailureListener {  }

    }
}