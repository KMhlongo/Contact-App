package com.example.contactapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp.model.User
import com.example.contactapp.repository.ApiInterface
import com.example.contactapp.repository.RetrofitClient
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.collections.ArrayList

class UserViewModel : ViewModel() {

    private val users = MutableLiveData<ArrayList<User>>()
    private lateinit var selectedUser: User

    fun getUsers():LiveData<ArrayList<User>>{
        return users
    }

    fun getSelectedUser(): User {
        return selectedUser
    }

    fun setSelectedUser(mUser: User) {
        selectedUser = mUser
    }

    init {
        loadUsers()
    }

    private fun loadUsers() {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        viewModelScope.launch {
            try {

                val response = apiInterface.getAllUsers()
                if (response.isSuccessful) {
                    users.value = sorted(response.body()!!.data)
                } else {
                    Log.i("ViewModel", response.errorBody().toString())
                }
            } catch (Ex:Exception) {
                Log.e("Error/ViewModel", Ex.localizedMessage)
            }
        }
    }

    private fun sorted(data: ArrayList<User>): ArrayList<User> {
        val comparator2 = compareBy<User> { it.firstName }.thenBy { it.lastName }
        data.sortWith(comparator2)
        return data
    }
    
}

