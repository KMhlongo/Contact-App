package com.example.contactapp.MainActivity

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.contactapp.R
import com.example.contactapp.model.User
import com.example.contactapp.viewmodel.UserViewModel
import com.google.android.material.textfield.TextInputLayout

class EditUserFragment : Fragment() {

    private lateinit var mUser: User
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {
        return inflater.inflate(R.layout.edit_user_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        mUser = userViewModel.getSelectedUser().value!!
        loadInfo(view)
    }

    private fun loadInfo(view: View) {
        val image : ImageView = view.findViewById(R.id.edit_profile_image)
        mUser.let {
            Glide.with(requireActivity())
                .load(mUser.avatar)
                .into(image)
        }

        val firstName : TextInputLayout = view.findViewById(R.id.input_first_name)
        firstName.editText?.setText(mUser.firstName)

        val lastName : TextInputLayout = view.findViewById(R.id.input_last_name)
        lastName.editText?.setText(mUser.lastName)

        val email : TextInputLayout = view.findViewById(R.id.input_email)
        email.editText?.setText(mUser.email)

        val phone : TextInputLayout = view.findViewById(R.id.input_phone)
        phone.editText?.setText(mUser.phone)

        val company : TextInputLayout = view.findViewById(R.id.input_company)
        company.editText?.setText(mUser.company)

        val save : Button = view.findViewById(R.id.btn_save)
        save.setOnClickListener{
            this.mUser = User(mUser.id,
                if (email.editText?.text.toString().trim()=="") null else email.editText?.text.toString(),
                firstName.editText?.text.toString().trim(),
                lastName.editText?.text.toString().trim(),
                mUser.avatar,
                if (phone.editText?.text.toString().trim()=="") null else phone.editText?.text.toString(),
                if (company.editText?.text.toString().trim()=="") null else company.editText?.text.toString())
            userViewModel.editUser(this.mUser)
        }
    }
}