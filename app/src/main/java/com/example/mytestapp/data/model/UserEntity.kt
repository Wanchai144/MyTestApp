package com.example.mytestapp.data.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserEntity (
    @PrimaryKey
    var id: Int = 1,
    var name: String? = ""
): RealmObject()