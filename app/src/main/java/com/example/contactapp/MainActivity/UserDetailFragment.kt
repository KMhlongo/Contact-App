package com.example.contactapp.MainActivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.contactapp.R
import com.example.contactapp.viewmodel.UserViewModel

class UserDetailFragment : Fragment() {

    private var parentLinearLayout: LinearLayoutCompat? = null
    private lateinit var inflater: LayoutInflater
    private var emailDetailRow: BaseDetailRow? = null
    private var phoneDetailRow: BaseDetailRow? = null
    private var companyDetailRow: BaseDetailRow? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.inflater = inflater
        return inflater.inflate(R.layout.user_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userViewModel: UserViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        parentLinearLayout = view.findViewById(R.id.display_user_linear_layout)



        emailDetailRow = BaseDetailRow(view, inflater, R.drawable.ic_baseline_email_24)

        phoneDetailRow = BaseDetailRow(view, inflater, R.drawable.ic_baseline_phone_24)

        companyDetailRow = BaseDetailRow(view, inflater, R.drawable.ic_baseline_business_24)

        userViewModel.getSelectedUser().observe(viewLifecycleOwner){
            Log.i("Observer", it.toString())

            phoneDetailRow!!.update(it.phone.toString())
            emailDetailRow!!.update(it.email.toString())
            companyDetailRow!!.update(it.company.toString())

            it.avatar.let { image ->
                Glide.with(requireActivity())
                    .load(image)
                    .into(view.findViewById(R.id.detail_profile_image))
            }
            view.findViewById<TextView>(R.id.full_name).text = view.context
                .getString(R.string.user_name, it.firstName, it.lastName)

        }
        editButton()
    }

    private fun editButton() {
        requireView().findViewById<Button>(R.id.btn_edit)
            .setOnClickListener{
                val activity = context as AppCompatActivity
                val editUserFragment = EditUserFragment()
                activity.supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                    .replace(R.id.user_detail_fragment, editUserFragment)
                    .addToBackStack(null)
                    .commit()
            }
    }
}