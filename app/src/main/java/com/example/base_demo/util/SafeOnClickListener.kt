package com.example.base_demo.util

import android.os.SystemClock
import android.view.View

class SafeOnClickListener(
    private val safeTime: Long,
    private val onSafeClickListener: (View?) -> Unit
) : View.OnClickListener {

    companion object {
        private var lastTimeClicked = 0L
        fun newInstance(safeTime: Long, onSafeClickListener: (View?) -> Unit) : SafeOnClickListener {
            return SafeOnClickListener(safeTime, onSafeClickListener)
        }
    }

    override fun onClick(v: View?) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < safeTime) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()

        onSafeClickListener(v)
    }
}