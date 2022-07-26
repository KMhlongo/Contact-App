package com.example.contactapp.repository

import com.example.contactapp.model.User

class Database {

    private val data = ArrayList<User>()

    fun getUsers() : ArrayList<User> {

        data.add(User(1,
            "george.bluth@reqres.in",
            "George",
            "Bluth",
            "https://reqres.in/img/faces/1-image.jpg",
            "4167969718"))
        data.add(User(2,
            "janet.weaver@reqres.in",
            "Janet",
            "Weaver",
            "https://reqres.in/img/faces/2-image.jpg",
            "3548127506"))
        data.add(User(3,
            "emma.wong@reqres.in",
            "Emma",
            "Wong",
            "https://reqres.in/img/faces/3-image.jpg",
            "6438548160"))
        data.add(User(4
            ,"eve.holt@reqres.in",
            "Eve",
            "Holt",
            "https://reqres.in/img/faces/4-image.jpg",
            "9079168180"))
        data.add(User(5,
            "charles.morris@reqres.in",
            "Charles",
            "Morris",
            "https://reqres.in/img/faces/5-image.jpg",
            "6084040588"))
        data.add((User(6,
            "tracey.ramos@reqres.in",
            "Tracey",
            "Ramos",
            "https://reqres.in/img/faces/6-image.jpg",
        "9139663771")))
        data.add((User(7,
            "michael.lawson@reqres.in",
            "Michael",
            "Lawson",
            "https://reqres.in/img/faces/7-image.jpg",
            "5405259335")))
        data.add((User( 8,
            "lindsay.ferguson@reqres.in",
            "Lindsay",
            "Ferguson",
            "https://reqres.in/img/faces/8-image.jpg",
        " 4278476169")))
        data.add((User(  9,
            "tobias.funke@reqres.in",
            "Tobias",
            "Funke",
            "https://reqres.in/img/faces/9-image.jpg",
            "9963484393")))
        data.add((User(   10,
            "byron.fields@reqres.in",
            "Byron",
            "Fields",
            "https://reqres.in/img/faces/10-image.jpg")))
        data.add((User(    11,
            "george.edwards@reqres.in",
            "George",
            "Edwards",
            "https://reqres.in/img/faces/11-image.jpg",
        "4256828167")))
        data.add(User( 12,
            "rachel.howell@reqres.in",
            "Rachel",
            "Howell",
            "https://reqres.in/img/faces/12-image.jpg",
            "3132419435"))

        return data
    }
}