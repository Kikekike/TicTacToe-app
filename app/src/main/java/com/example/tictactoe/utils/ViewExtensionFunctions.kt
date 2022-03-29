package com.example.tictactoe.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.Editable
import android.widget.EditText
import com.example.tictactoe.R

fun Context.showDialog(
    title: String?,
    message: String,
    positiveButtonText: String? = getString(R.string.ok),
    positiveButtonAction: DialogInterface.OnClickListener? = null,
    negativeButtonText: String? = null,
    negativeButtonAction: DialogInterface.OnClickListener? = null,
) = AlertDialog.Builder(this)
    .apply {
        setTitle(title)
        setMessage(message)
        setPositiveButton(positiveButtonText, positiveButtonAction)
        setNegativeButton(negativeButtonText, negativeButtonAction)
    }
    .create()
    .show()

fun EditText.inputText() = this.text.inputText()

fun Editable?.inputText() = this.toString().trim()
