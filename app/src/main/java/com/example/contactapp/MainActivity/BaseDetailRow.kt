package com.example.contactapp.MainActivity

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.contactapp.R

class BaseDetailRow(view: View, inflater: LayoutInflater, imageResource: Int) {

    private var row : View? = null
    private var myInflater = inflater
    private var image = imageResource
    private var parentLinearLayout: LinearLayoutCompat? = null
    private var isAdded = false

    init {
        parentLinearLayout = view.findViewById(R.id.display_user_linear_layout)
    }

    fun update(text: String?) {
        Log.i("Update", text!!)
        if (text!="null") {
            if (row === null || !isAdded) {
                row = myInflater.inflate(R.layout.user_detail_row, null)
                row?.findViewById<ImageView>(R.id.detail_icon)!!.setImageResource(image)
                parentLinearLayout!!.addView(row)
                isAdded = true
            }
            row?.findViewById<TextView>(R.id.text)?.text = text
        } else {
            if (isAdded) {
                parentLinearLayout!!.removeView(row)
                isAdded = false
            }
        }
    }

}