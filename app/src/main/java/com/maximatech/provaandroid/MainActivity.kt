package com.maximatech.provaandroid

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.maximatech.provaandroid.app.navigation.AppNavigation
import com.maximatech.provaandroid.app.theme.ProvaAndroidTheme
import com.maximatech.provaandroid.data.SyncWorker
import java.util.concurrent.TimeUnit


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startSyncWorker(application)
        setContent {
            ProvaAndroidTheme {
                AppNavigation()
            }
        }
    }
}

private fun startSyncWorker(context: Context) {
    val periodicRequest = PeriodicWorkRequestBuilder<SyncWorker>(
        15, TimeUnit.MINUTES
    ).build()

    WorkManager.getInstance(context)
        .enqueueUniquePeriodicWork(
            "SyncWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicRequest
        )
}