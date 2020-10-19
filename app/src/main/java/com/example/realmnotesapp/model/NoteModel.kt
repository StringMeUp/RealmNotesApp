package com.example.realmnotesapp.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class NoteModel(
    @PrimaryKey
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null
) : RealmObject()