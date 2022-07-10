/*
package com.jmcpapertech.jmcapp.core

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent

import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jmcpapertech.jmcapp.data.preference.AppPreference
import com.pay2ved.recharge.R


open class BaseActivity : AppCompatActivity() {
    private var progressDialogFragment: ProgressDialogFragment? = null
    private var noInternetDialogFragment: NoInternetDialogFragment? = null
    lateinit var appPreference: AppPreference
    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        appPreference = AppPreference(this)
        progressDialogFragment = ProgressDialogFragment.newInstance()
        noInternetDialogFragment = NoInternetDialogFragment.newInstance()
        attachObserver()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    fun showProgressDialog(isShow: Boolean) {
        try {
            if (isShow) {
                if (progressDialogFragment?.dialog == null || progressDialogFragment?.dialog?.isShowing == false)
                    progressDialogFragment?.show(supportFragmentManager, javaClass.simpleName)
            } else {
                if (progressDialogFragment?.dialog != null && progressDialogFragment?.dialog?.isShowing == true) {
                    progressDialogFragment?.dismissAllowingStateLoss()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showNoInternetDialog(isShow: Boolean) {
        try {
            if (isShow) {
                if (noInternetDialogFragment?.dialog == null || noInternetDialogFragment?.dialog?.isShowing == false)
                    noInternetDialogFragment?.show(supportFragmentManager, javaClass.simpleName)
            } else {
                if (noInternetDialogFragment?.dialog != null && noInternetDialogFragment?.dialog?.isShowing == true) {
                    noInternetDialogFragment?.dismissAllowingStateLoss()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    open fun attachObserver() {

    }

    val myUserId: String
        get() {
            return MainApplication.sharedPreference?.userId.toString()
        }

    fun logOut() {
        DialogUtil.alert(
            this, msg = getString(R.string.app_name),
            il = object : DialogUtil.IL {
                override fun onSuccess() {
                    doLogout()
                }

                override fun onCancel(isNeutral: Boolean) {}
            }, isCancelable = false
        )
    }

    fun doLogout() {
        appPreference.clearUserPreference()
        val intent = Intent(this@BaseActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    fun showCountryList(countryFlagImg: ImageView, countryCodeNumber: TextView) {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.country_bottom_sheet)
        var mList: List<CountryListDataItem> = ArrayList()
        if (mList.isEmpty()) {
            viewModel.countryListPost()
        }
        viewModel.countryServicesLiveData.observe(this) {
            mList = it.data as List<CountryListDataItem>
            val countryListAdaptor = CountryListAdaptor(mList, object : CountryListAdaptor.OnCountryClick {
                override fun onClick(countryCode: String, countryFlag: String) {
                    countryCodeNumber.text = countryCode
                    Glide.with(countryFlagImg).load(countryFlag).centerCrop().into(countryFlagImg)
                    dialog.dismiss()
                }
            })
            val recycle = dialog.findViewById<RecyclerView>(R.id.rvCountryList)
            recycle?.adapter = countryListAdaptor
        }
        dialog.show()
    }

}*/
