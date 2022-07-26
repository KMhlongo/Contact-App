package com.example.contactapp.model

import com.google.gson.annotations.SerializedName

data class User  (
    @SerializedName("id") var id: Int,
    @SerializedName("email") var email: String? = null,
    @SerializedName("first_name") var firstName: String,
    @SerializedName("last_name") var lastName: String,
    @SerializedName("avatar") var avatar: String,
    @SerializedName("phone") var phone : String? = null,
    @SerializedName("company") var company : String? = null
    )

