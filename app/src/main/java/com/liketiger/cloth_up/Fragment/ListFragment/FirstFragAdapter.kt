package com.liketiger.cloth_up.Fragment.ListFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.liketiger.cloth_up.R

class FirstFragAdapter(val context : Context, val list : ArrayList<ContentsListModel>) : BaseAdapter() {
    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {

        val view : View
        val holder : ViewHolder

        if(converView == null){
            view = LayoutInflater.from(context).inflate(R.layout.listview_item, null)
            holder = ViewHolder()

            holder.view_image1 = view.findViewById(R.id.lv_image_area)
            holder.view_text1 = view.findViewById(R.id.title)
            holder.view_text2 = view.findViewById(R.id.detail1)
            holder.view_text3 = view.findViewById(R.id.detail2)

            view.tag = holder

        }else{
            holder = converView.tag as ViewHolder
            view = converView
        }
        val item = list[position]

        holder.view_image1?.setImageResource(item.image)
        holder.view_text1?.text = item.title
        holder.view_text3?.text = item.category

        return view
    }

    override fun getItem(p0: Int): Any {
        return list.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }

    private class ViewHolder{
        var view_image1 : ImageView? = null
        var view_text1 : TextView? = null
        var view_text2 : TextView? = null
        var view_text3 : TextView? = null
    }
}