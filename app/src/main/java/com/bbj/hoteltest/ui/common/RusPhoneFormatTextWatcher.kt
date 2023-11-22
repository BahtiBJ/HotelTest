package com.bbj.hoteltest.ui.common

import android.text.Editable
import android.text.TextWatcher
import android.util.Log

class RusPhoneFormatTextWatcher : TextWatcher {

    private val mask = "+7 (9**) ***-**-**"
    private val maxInputChars = 9
    private val availableCharCount: Int
        get() {
            return if (input.length < maxInputChars)
                input.length
            else
                maxInputChars
        }
    private var input = ""

    private var isRunning = false
    private var isDeleting = false

    override fun beforeTextChanged(
        charSequence: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) {
        isDeleting = count > after
    }

    override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable) {
        if (isRunning) {
            return
        }
        isRunning = true

        if (isDeleting)
            input = input.dropLast(1)
        else {
            if (editable.toString().length <= maxInputChars)
                input += editable.toString().last()
        }

        var text = mask
        if (input.isNotBlank()) {
            for (i in 0 until availableCharCount) {
                text = text.replaceFirst('*', input[i])
            }
        }
        editable.clear()
        editable.append(text)
        Log.d(this.javaClass.simpleName, "input = $input  text = $text editable = $editable")
        isRunning = false
    }
}