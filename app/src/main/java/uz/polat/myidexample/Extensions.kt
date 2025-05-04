package uz.polat.myidexample

import androidx.compose.runtime.key
import com.google.firebase.crashlytics.FirebaseCrashlytics


fun log(message: String){
    FirebaseCrashlytics.getInstance().log(message)
}

fun logException(exception: Exception){
    FirebaseCrashlytics.getInstance().recordException(exception)
}

fun setUserIdForCrashlytics(id: String){
    FirebaseCrashlytics.getInstance().setUserId(id)
}


fun setCustomIdForCrashlytics(key: String, value: String){
    FirebaseCrashlytics.getInstance().setCustomKey(key,value)
}

