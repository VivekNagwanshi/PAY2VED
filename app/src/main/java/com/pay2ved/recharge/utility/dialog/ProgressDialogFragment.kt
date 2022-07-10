package com.jmcpapertech.jmcapp.utility.dialog

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.jmcpapertech.jmcapp.databinding.InflateProgressViewBinding

class ProgressDialogFragment : DialogFragment() {
    var binding: InflateProgressViewBinding? = null

    companion object {
        var FRAGMENT_TAG = "dialog"
        fun newInstance(): ProgressDialogFragment {
            val dialogFragment = ProgressDialogFragment()
            dialogFragment.isCancelable = false
            return dialogFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = InflateProgressViewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun dismiss() {
        if (dialog != null)
            super.dismiss()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        val window = dialog?.window
        val windowParams = window!!.attributes

        window.decorView.setBackgroundResource(android.R.color.transparent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            windowParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
        window.attributes = windowParams
    }
}