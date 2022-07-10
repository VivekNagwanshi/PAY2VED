package com.jmcpapertech.jmcapp.utility.dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.pay2ved.recharge.R

/**
 * This class contain alert message helper
 */
object DialogUtil {

    interface IL {
        fun onSuccess()

        fun onCancel(isNeutral: Boolean)
    }

    interface ILOption {
        fun onWhich(position: Int)
    }

    /**
     * This method is used to show alert dialog box for force close application
     *
     * @param context - Object of Context, context from where the activity is going
     * to start.
     * @param msg     - Message String that represents alert box message
     */
    fun alert(context: Context, msg: String) {
        try {
            val alertDialogBuilder = getBuilder(context)
            alertDialogBuilder.setMessage(fromHtml(msg))
            alertDialogBuilder.setPositiveButton(
                android.R.string.ok
            ) { dialog, id -> dialog.dismiss() }
            alertDialogBuilder
                .setOnKeyListener { dialog, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_BACK)
                        dialog.dismiss()
                    false
                }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
            val pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            pbutton.setTextColor(Color.BLACK)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    private fun fromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    /**
     * This method is used to show alert dialog box for force close application
     *
     * @param context - Object of Context, context from where the activity is going
     * to start.
     * @param title   - Title String that represents alert box title
     * @param msg     - Message String that represents alert box message
     */
    fun alert(context: Context, title: String, msg: String, isCancelable: Boolean = true) {
        try {
            val alertDialogBuilder = getBuilder(context)
            alertDialogBuilder.setMessage(fromHtml(msg))
            alertDialogBuilder.setTitle(title)
            alertDialogBuilder.setCancelable(isCancelable)
            alertDialogBuilder.setPositiveButton(
                android.R.string.ok
            ) { dialog, id -> dialog.dismiss() }
            alertDialogBuilder
                .setOnKeyListener { dialog, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_BACK)
                        dialog.dismiss()
                    false
                }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    /**
     * This method is used to show alert dialog box for force close application
     *
     * @param context - Object of Context, context from where the activity is going
     * to start.
     * @param msg     - Message String that represents alert box message
     */
    fun alert(context: Context, msg: String, il: IL, isCancelable: Boolean = true) {
        try {
            val alertDialogBuilder = getBuilder(context)
            alertDialogBuilder.setMessage(msg)
            alertDialogBuilder.setCancelable(isCancelable)
            alertDialogBuilder.setPositiveButton(
                android.R.string.ok
            ) { dialog, _ ->
                dialog.dismiss()
                il.onSuccess()
            }
            alertDialogBuilder.setNegativeButton(
                android.R.string.cancel
            ) { dialog, _ ->
                dialog.dismiss()
                il.onCancel(false)
            }

            alertDialogBuilder
                .setOnKeyListener { dialog, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        il.onCancel(false)
                        dialog.dismiss()
                    }
                    false
                }
            val alertDialog = alertDialogBuilder.create()

            alertDialog.show()
            val nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
            nbutton.setTextColor(Color.BLACK)
            val pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            pbutton.setTextColor(Color.BLACK)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    /**
     * This method is used to show alert dialog box for force close application
     *
     * @param context - Object of Context, context from where the activity is going
     * to start.
     * @param msg     - Message String that represents alert box message
     */
    fun confirmDialog(context: Context, msg: String, il: IL) {
        try {
            val alertDialogBuilder = getBuilder(context)
            alertDialogBuilder.setMessage(msg)
            alertDialogBuilder.setPositiveButton(
                android.R.string.ok
            ) { dialog, _ ->
                il.onSuccess()
                dialog.dismiss()
            }

            alertDialogBuilder.setNegativeButton(
                android.R.string.cancel
            ) { dialog, _ ->
                il.onCancel(false)
                dialog.dismiss()
            }

            alertDialogBuilder
                .setOnKeyListener { dialog, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        il.onCancel(false)
                        dialog.dismiss()
                    }
                    false
                }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    /**
     * This method is used to show alert dialog box for force close application
     *
     * @param context - Object of Context, context from where the activity is going
     * to start.
     * @param title   - Title String that represents alert box title
     * @param msg     - Message String that represents alert box message
     */
    fun confirmDialog(
        context: Context, title: String, msg: String,
        il: IL
    ) {
        try {
            val alertDialogBuilder = getBuilder(context)
            alertDialogBuilder.setMessage(msg)
            alertDialogBuilder.setTitle(title)
            alertDialogBuilder.setPositiveButton(
                android.R.string.ok
            ) { dialog, id ->
                il.onSuccess()
                dialog.dismiss()
            }

            alertDialogBuilder.setNegativeButton(
                android.R.string.cancel
            ) { dialog, which ->
                il.onCancel(false)
                dialog.dismiss()
            }

            alertDialogBuilder
                .setOnKeyListener { dialog, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        il.onCancel(false)
                        dialog.dismiss()
                    }
                    false
                }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    /**
     * This method is used to show alert dialog box for force close application
     *
     * @param context - Object of Context, context from where the activity is going
     * to start.
     * @param msg     - Message String that represents alert box message
     */
    @SuppressLint("ResourceAsColor")
    fun confirmDialog(
        context: Context, msg: String,
        positiveBtnText: String, negativeBtnText: String, il: IL
    ) {
        try {
            val alertDialogBuilder = getBuilder(context)
            alertDialogBuilder.setMessage(msg)
            alertDialogBuilder.setPositiveButton(
                positiveBtnText
            ) { dialog, id ->
                il.onSuccess()
                dialog.dismiss()
            }

            alertDialogBuilder.setNegativeButton(
                negativeBtnText
            ) { dialog, which ->
                il.onCancel(false)
                dialog.dismiss()
            }

            alertDialogBuilder
                .setOnKeyListener { dialog, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        il.onCancel(false)
                        dialog.dismiss()
                    }
                    false
                }
            val alertDialog = alertDialogBuilder.create()

            alertDialog.show()

            val nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
            nbutton.setTextColor(Color.BLACK)
            val pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            pbutton.setTextColor(Color.BLACK)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    /**
     * This method is used to show alert dialog box for force close application
     *
     * @param context - Object of Context, context from where the activity is going
     * to start.
     * @param title   - Title String that represents alert box title
     * @param msg     - Message String that represents alert box message
     */
    fun confirmDialog(
        context: Context, title: String, msg: String,
        positiveBtnText: String, negativeBtnText: String, il: IL
    ) {
        try {
            val alertDialogBuilder = getBuilder(context)
            alertDialogBuilder.setMessage(msg)
            alertDialogBuilder.setTitle(title)
            alertDialogBuilder.setPositiveButton(
                positiveBtnText
            ) { dialog, id ->
                il.onSuccess()
                dialog.dismiss()
            }

            alertDialogBuilder.setNegativeButton(
                negativeBtnText
            ) { dialog, which ->
                il.onCancel(false)
                dialog.dismiss()
            }

            alertDialogBuilder
                .setOnKeyListener { dialog, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        il.onCancel(false)
                        dialog.dismiss()
                    }
                    false
                }
            val alertDialog = alertDialogBuilder.create() // create
            // alert
            // dialog
            alertDialog.show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    /**
     * This method is used to show alert dialog box for force close application
     *
     * @param context - Object of Context, context from where the activity is going
     * to start.
     * @param title   - Title String that represents alert box title
     * @param msg     - Message String that represents alert box message
     */
    fun confirmDialog(
        context: Context, title: String, msg: String,
        positiveBtnText: String, negativeBtnText: String,
        neutralBtnText: String,
        il: IL
    ) {
        try {
            val alertDialogBuilder = getBuilder(context)
            alertDialogBuilder.setMessage(msg)
            alertDialogBuilder.setTitle(title)
            alertDialogBuilder.setPositiveButton(
                positiveBtnText
            ) { dialog, id ->
                il.onSuccess()
                dialog.dismiss()
            }

            alertDialogBuilder.setNegativeButton(
                negativeBtnText
            ) { dialog, which ->
                il.onCancel(false)
                dialog.dismiss()
            }
            if (!TextUtils.isEmpty(neutralBtnText)) {
                alertDialogBuilder.setNeutralButton(
                    neutralBtnText
                ) { dialog, which ->
                    il.onCancel(true)
                    dialog.dismiss()
                }
            }

            alertDialogBuilder
                .setOnKeyListener { dialog, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        il.onCancel(false)
                        dialog.dismiss()
                    }
                    false
                }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    /**
     * This method is used to show alert dialog box for force close application
     *
     * @param context - Object of Context, context from where the activity is going
     * to start.
     */
    fun showAlertDialogOKCancelWithView(
        context: Activity, view: View, il: IL,
        positiveBtnText: String, negativeBtnText: String
    ) {
        try {
            val alertDialogBuilder = getBuilder(context)
            alertDialogBuilder.setView(view)
            alertDialogBuilder.setPositiveButton(
                positiveBtnText
            ) { dialog, id ->
                il.onSuccess()
                dialog.dismiss()
            }
            alertDialogBuilder.setNegativeButton(
                negativeBtnText
            ) { dialog, which ->
                il.onCancel(false)
                dialog.dismiss()
            }
            alertDialogBuilder
                .setOnKeyListener { dialog, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        il.onCancel(false)
                        dialog.dismiss()
                    }
                    false
                }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     * This method is used to show alert dialog box for force close application
     *
     * @param context - Object of Context, context from where the activity is going
     * to start.
     * @param msg     - Message String that represents alert box message
     */
    fun showAlertDialogAction(
        context: Activity, msg: String,
        il: IL, positiveBtnText: String, negativeBtnText: String
    ) {
        try {
            val alertDialogBuilder = getBuilder(context)
            alertDialogBuilder.setMessage(msg)
            alertDialogBuilder.setPositiveButton(
                positiveBtnText
            ) { dialog, id ->
                il.onSuccess()
                dialog.dismiss()
            }

            alertDialogBuilder.setNegativeButton(
                negativeBtnText
            ) { dialog, which ->
                il.onCancel(false)
                dialog.dismiss()
            }

            alertDialogBuilder
                .setOnKeyListener { dialog, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        il.onCancel(false)
                        dialog.dismiss()
                    }
                    false
                }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    fun getBuilder(context: Context): AlertDialog.Builder {
        val alertDialogBuilder = AlertDialog.Builder(context, R.style.Theme_PAY2VED)
        alertDialogBuilder.setCancelable(false)
        return alertDialogBuilder
    }

    fun ok(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }


    fun showOptionDialog(
        context: Context, title: String,
        opsChars: Array<CharSequence>, il: ILOption
    ) {
        try {
            val alertDialogBuilder = AlertDialog.Builder(
                context
            )
            alertDialogBuilder.setTitle(title)
            alertDialogBuilder.setItems(
                opsChars
            ) { dialog, which ->
                il.onWhich(which)
                dialog.dismiss()
            }

            alertDialogBuilder
                .setOnKeyListener { dialog, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_BACK)
                        dialog.dismiss()
                    false
                }
            alertDialogBuilder.create().show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun confirmDownloadDialog(context: Context, msg: String, il: IL) {
        try {
            val alertDialogBuilder = getBuilder(context)
            alertDialogBuilder.setMessage(msg)
            alertDialogBuilder.setPositiveButton(
                android.R.string.ok
            ) { dialog, id ->
                il.onSuccess()
                dialog.dismiss()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
            val nbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            nbutton.setTextColor(Color.BLACK)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
