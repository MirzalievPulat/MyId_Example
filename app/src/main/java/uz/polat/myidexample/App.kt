package uz.polat.myidexample

import android.app.Application
import android.provider.Settings
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.crashlytics
import com.google.firebase.initialize
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


class App : Application(){
    override fun onCreate() {
        super.onCreate()
        Firebase.crashlytics.isCrashlyticsCollectionEnabled = true

        FirebaseCrashlytics.getInstance().setUserId("user2")
        FirebaseCrashlytics.getInstance().setCustomKey("customKey2","value2")
    }
}


//        FirebaseCrashlytics.getInstance().isCrashlyticsCollectionEnabled = false //meta data works perfect


//            android.util.Log.wtf("FirebaseTag", "Handler was set")
//            val fb = Thread.getDefaultUncaughtExceptionHandler()
//            android.util.Log.wtf("FirebaseTag", fb.toString())
//            Thread.setDefaultUncaughtExceptionHandler { thread, ex ->
//                android.util.Log.wtf("FirebaseTag", "thread $thread, ex: ${ex.message}")
//                FirebaseCrashlytics.getInstance().recordException(ex)
//            }