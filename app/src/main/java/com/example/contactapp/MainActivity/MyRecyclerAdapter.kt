package com.example.contactapp.MainActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contactapp.R
import com.example.contactapp.model.User
import com.example.contactapp.viewmodel.UserViewModel
import java.util.*

class MyRecyclerAdapter(val context: MainActivity, var mList: ArrayList<User>) :
    RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>(), Filterable {

    var mFilterList = ArrayList<User>()
    private val userViewModel: UserViewModel = ViewModelProvider(context)[UserViewModel::class.java]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mFilterList[position]
        holder.fullName.text = context.getString(R.string.user_name, item.firstName, item.lastName)
        holder.loadImage(item.avatar)

        holder.itemView.setOnClickListener { v ->
            userViewModel.setSelectedUser(item)

            // Switch Fragment
            val activity = v!!.context as AppCompatActivity
            val detailFragment = UserDetailFragment()
            activity.supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                .replace(R.id.main_activity, detailFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return mFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                mFilterList = if (charSearch.isEmpty() || charSearch == "") {
                    mList
                } else {
                    val resultList = ArrayList<User>()
                    for (row in mList) {
                        val name = row.firstName + " " + row.lastName
                        if (
                            name.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = mFilterList
                return filterResults
            }
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                mFilterList = results?.values as ArrayList<User> /* = java.util.ArrayList<com.example.recyclerview.User> */
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val fullName : TextView = itemView.findViewById(R.id.rec_full_name)
        private val image : ImageView = itemView.findViewById(R.id.profile_image)

        fun loadImage(url: String) {
            url.let {
                Glide.with(context).load(url).into(image)
            }
        }
    }

}