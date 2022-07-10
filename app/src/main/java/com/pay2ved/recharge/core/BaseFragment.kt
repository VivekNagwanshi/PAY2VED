package com.jmcpapertech.jmcapp.core

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jmcpapertech.jmcapp.MainApplication
import com.jmcpapertech.jmcapp.R
import com.jmcpapertech.jmcapp.data.preference.AppPreference
import com.jmcpapertech.jmcapp.ui.clients.CountryListDataItem
import com.jmcpapertech.jmcapp.ui.home.MainActivity
import com.jmcpapertech.jmcapp.ui.login.verification.CountryListAdaptor
import com.jmcpapertech.jmcapp.utility.dialog.DialogUtil
import com.jmcpapertech.jmcapp.utility.dialog.ProgressDialogFragment
import com.jmcpapertech.jmcapp.viewModel.ViewModel

/**
 * This class is parent of all fragment class
 *
 * This class contains all common method of fragment(s).
 */
open class BaseFragment : androidx.fragment.app.Fragment() {
    private var progressDialogFragment: ProgressDialogFragment? = null
    //private var mFragmentNavigation: FragmentNavigation? = null
    lateinit var baseActivity: BaseActivity
    lateinit var viewModel: ViewModel


    /**
     * Used when we don't want to create/inflate view if same fragment instance is used.
     */
    var isFirstTimeLoad: Boolean = true
    var prevViewDataBinding: ViewDataBinding? = null
    fun <T : ViewDataBinding> createOrReloadView(
        inflater: LayoutInflater,
        resLayout: Int,
        container: ViewGroup?
    ): T {
        isFirstTimeLoad = false
        if (prevViewDataBinding == null) {
            prevViewDataBinding = DataBindingUtil.inflate(inflater, resLayout, container, false)
            isFirstTimeLoad = true
        } else if (prevViewDataBinding?.root?.parent != null) {
            container?.removeView(prevViewDataBinding?.root)
            if (prevViewDataBinding?.root?.parent != null) {
                return createOrReloadView(inflater, resLayout, container)
            }
        }
        return prevViewDataBinding as T
    }

    open fun attachObserver() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewModel::class.java]
        attachObserver()
        Log.i("currentFragment", this.toString())
        progressDialogFragment = ProgressDialogFragment.newInstance()
    }
    override fun onResume() {
        super.onResume()
        /*if (baseActivity is HomeActivity) {
            (baseActivity as HomeActivity).setBottomNavigationVisibility(bottomNavigationViewVisibility)
        }*/
    }

/*    interface FragmentNavigation {
        fun pushFragment(fragment: Fragment, transactionOptions: FragNavTransactionOptions? = null)

        fun popFragment(depth: Int, transactionOptions: FragNavTransactionOptions? = null)
    }*/

    interface RefreshApiListener {
        fun refreshApi(model: Any?)
    }

    var mRefreshApiListener: RefreshApiListener? = null
    fun setRefreshApiListener(refreshApiListener: RefreshApiListener) {
        mRefreshApiListener = refreshApiListener
    }

    fun showProgressDialog(isShow: Boolean) {
        try {
            if (isShow) {
                if (progressDialogFragment?.dialog == null || progressDialogFragment?.dialog?.isShowing == false)
                    progressDialogFragment?.show(
                        activity?.supportFragmentManager!!,
                        javaClass.simpleName
                    )
            } else {
                if (progressDialogFragment?.dialog != null && progressDialogFragment?.dialog?.isShowing == true) {
                    progressDialogFragment?.dismissAllowingStateLoss()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    val myUserId: String
        get() {
            return MainApplication.sharedPreference?.userId.toString()
        }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        /*if (context is FragmentNavigation) {
            mFragmentNavigation = context
        }*/
        baseActivity = context as BaseActivity
    }

/*    fun pushFragment(fragment: Fragment, transactionOptions: FragNavTransactionOptions? = null) {
        if (mFragmentNavigation != null) {
            mFragmentNavigation?.pushFragment(fragment, transactionOptions)
        } else {
            // todo logic when home Activity not implemented fragmentNavigation
        }
    }*/

/*    fun popFragment(depth: Int = 1, transactionOptions: FragNavTransactionOptions? = null) {
//        hideKeyboard(activity)
        if (mFragmentNavigation != null) {
            mFragmentNavigation?.popFragment(depth, transactionOptions)
        }
    }*/

    private fun View.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    open fun onBackPressed(): Boolean {
        return false
    }

    open fun onNetworkAvailable(isAvailable: Boolean) {}

    fun showCountryList(countryFlagImg: ImageView, countryCodeNumber: TextView) {
        val dialog = BottomSheetDialog(requireContext())
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

}