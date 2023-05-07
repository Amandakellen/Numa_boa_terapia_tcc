package com.example.numaboaterapia.appNavigation.psychologist.data

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class MyActivityResultContract : ActivityResultContract<Intent, MyResult>() {

    override fun createIntent(context: Context, input: Intent): Intent {
        return input
    }

    override fun parseResult(resultCode: Int, intent: Intent?): MyResult {
        return when (resultCode) {
            Activity.RESULT_OK -> MyResult.Success
            Activity.RESULT_CANCELED -> MyResult.Canceled
            else -> MyResult.Failure
        }
    }
}

sealed class MyResult {
    object Success : MyResult()
    object Canceled : MyResult()
    object Failure : MyResult()
}
