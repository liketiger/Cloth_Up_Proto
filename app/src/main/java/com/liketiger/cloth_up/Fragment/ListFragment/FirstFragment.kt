package com.liketiger.cloth_up.Fragment.ListFragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liketiger.cloth_up.Fragment.MarketInfo.MarketInfoActivity
import com.liketiger.cloth_up.R
import com.liketiger.cloth_up.Utils.FirebaseUtils
import kotlinx.android.synthetic.main.activity_lecture.view.*
import kotlinx.android.synthetic.main.fragment_first.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_first, container, false)

        val list_array = arrayListOf<ContentsListModel>(

            ContentsListModel(R.drawable.ceo1, "채원매장", 1, "보세"),
            ContentsListModel(R.drawable.ceo2, "보세랜드", 1, "보세"),
            ContentsListModel(R.drawable.ceo3, "서핑랜드", 1, "스포츠"),
            ContentsListModel(R.drawable.ceo4, "등산업", 1, "스포츠"),
            ContentsListModel(R.drawable.ceo5, "빈폴", 1, "브랜드"),
            ContentsListModel(R.drawable.ceo6, "황갤러리", 1, "잡화"),
            ContentsListModel(R.drawable.ceo7, "MyStyle", 1, "빈티지"),
            ContentsListModel(R.drawable.ceo8, "빈티지월드", 1, "빈티지"),
            ContentsListModel(R.drawable.ceo9, "브세", 1, "보랜드")
        )

        val list_adapter = FirstFragAdapter(requireContext(), list_array)
        view.listview_first_fragment.adapter = list_adapter

        FirebaseUtils.db
            .collection("zzim")
            .document(FirebaseUtils.getUid())
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if(documentSnapshot.exists() == true){

                }else{
                    val lecture = hashMapOf(
                        "채원매장" to "",
                        "보세랜드" to "",
                        "서핑랜드" to "",
                        "등산업" to "",
                        "빈폴" to "",
                        "황갤러리" to "",
                        "MyStyle" to "",
                        "빈티지월드" to "",
                        "브세" to ""
                    )
                    FirebaseUtils.db
                        .collection("zzim")
                        .document(FirebaseUtils.getUid())
                        .set(lecture)
                        .addOnSuccessListener {  }
                        .addOnFailureListener {  }
                }
            }

        view.listview_first_fragment.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(requireContext(), MarketInfoActivity::class.java)
            intent.putExtra("title", list_array.get(i).title)
            startActivity(intent)
        }

        return view

    }


}
