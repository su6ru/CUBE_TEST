package com.ci.v1_ci_view.ui.alert

import android.content.Context
import android.util.AttributeSet
import com.ci.v1_ci_view.R

class CILoadingAlert constructor(context: Context) : CIBaseAlert(context) {

    init {
        inflate(context, R.layout.alert_loading,null)
    }


}