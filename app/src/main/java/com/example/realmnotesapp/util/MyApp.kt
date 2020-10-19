package com.example.realmnotesapp.util

import android.app.Application
import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApp : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val configuration = RealmConfiguration.Builder()
            .name("Notes.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()

        Realm.setDefaultConfiguration(configuration)
        appContext = applicationContext
    }
}