package com.example.contactapp.model

import com.example.contactapp.model.Support
import com.example.contactapp.model.User
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class ResponseUsers(
    @SerializedName("data") var data: ArrayList<User>,
    @SerializedName("page") var page: Int,
    @SerializedName("per_page") var perPage: Int,
    @SerializedName("support") var support: Support,
    @SerializedName("total") var total: Int,
    @SerializedName("total_pages") var totalPages: Int)
