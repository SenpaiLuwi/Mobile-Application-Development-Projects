package com.example.act6gridview


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class GridAdapter(private val textList: ArrayList<String>, private val imageList: ArrayList<Int>) : BaseAdapter() {

    override fun getCount(): Int {
        return textList.size
    }

    override fun getItem(position: Int): Any {
        return textList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.gridview_layout, parent, false)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val textView = view.findViewById<TextView>(R.id.textView)

        textView.text = textList[position]
        imageView.setImageResource(imageList[position])

        return view
    }
}

