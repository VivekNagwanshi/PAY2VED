package com.jmcpapertech.jmcapp.utility.dialog

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.jmcpapertech.jmcapp.R
import com.jmcpapertech.jmcapp.databinding.DialogNoInternetBinding

class NoInternetDialogFragment : DialogFragment() {
    var binding: DialogNoInternetBinding? = null

    companion object {
        var FRAGMENT_TAG = "dialog"
        fun newInstance(): NoInternetDialogFragment {
            val dialogFragment = NoInternetDialogFragment()
            dialogFragment.isCancelable = false
            return dialogFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogNoInternetBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun dismiss() {
        if (dialog != null)
            super.dismiss()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog!!.window!!.setLayout(width, height)
        }
        val window = dialog?.window
        val windowParams = window!!.attributes
        window.decorView.setBackgroundResource(R.color.white)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            windowParams.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
        window.attributes = windowParams
    }
}