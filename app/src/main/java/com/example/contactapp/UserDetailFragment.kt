package com.example.contactapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.contactapp.model.User
import com.example.contactapp.viewmodel.UserViewModel

class UserDetailFragment : Fragment() {

    private lateinit var mUser: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userViewModel: UserViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        mUser = userViewModel.getSelectedUser()
        loadInfo(mUser, view)
    }

    private fun loadInfo(mUser: User, view: View) {
        val image : ImageView = view.findViewById(R.id.detail_profile_image)
        val name : TextView = view.findViewById(R.id.full_name)
        val email : TextView = view.findViewById(R.id.email)

        name.text = view.context.getString(R.string.user_name, mUser.firstName, mUser.lastName)
        email.text = mUser.email

        mUser.let {
            Glide.with(requireActivity()).load(mUser.avatar).into(image)
        }
    }

}