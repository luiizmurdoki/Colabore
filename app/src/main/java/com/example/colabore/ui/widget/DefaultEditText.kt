package com.example.colabore.ui.widget;

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.text.InputFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isNotEmpty
import com.example.colabore.R
import com.example.colabore.utils.CustomFilter
import com.example.colabore.utils.extension.appendFilter
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.default_edit_text.view.*
import org.jetbrains.anko.singleLine
import org.jetbrains.anko.textColor

class DefaultEditText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : ConstraintLayout(context, attrs, defStyle) {

    private var mAppTextInput: AppTextInputLayout
    private var mTextInputEditText: TextInputEditText

    init {
        val itemView = LayoutInflater.from(context).inflate(R.layout.default_edit_text, this, true)

        mAppTextInput = itemView.defaultAtil
        mTextInputEditText = itemView.defaultTiet

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.DefaultEditText, 0, 0)
            val inputType = typedArray.getInt(R.styleable.DefaultEditText_etInputTextType, 0)
            val minLength = typedArray.getInt(R.styleable.DefaultEditText_etMinLength, 0)
            val maxLength = typedArray.getInt(R.styleable.DefaultEditText_etMaxLength, -1)
            val singleLines = typedArray.getBoolean(R.styleable.DefaultEditText_etSingleLines, false)
            val allCaps = typedArray.getBoolean(R.styleable.DefaultEditText_etAllCaps, false)
            val blockSpace = typedArray.getBoolean(R.styleable.DefaultEditText_etBlockSpace, false)
            val maxLines = typedArray.getInt(R.styleable.DefaultEditText_etMaxLines, -1)
            val hint = typedArray.getString(R.styleable.DefaultEditText_hint)
            val emptinessValid = typedArray.getBoolean(R.styleable.DefaultEditText_etEmptinessIsValid, false)
            val needsValidation = typedArray.getBoolean(R.styleable.DefaultEditText_etNeedsValidation, true)
            val editable = typedArray.getBoolean(R.styleable.DefaultEditText_editable, true)
            val enabled = typedArray.getBoolean(R.styleable.DefaultEditText_etEnabled, true)
            val drawableRight = typedArray.getResourceId(R.styleable.DefaultEditText_drawableRight, -1)
            val drawableLeft = typedArray.getResourceId(R.styleable.DefaultEditText_drawableLeft, -1)
            val textColor = typedArray.getResourceId(R.styleable.DefaultEditText_textColor, -1)
            val hintColor = typedArray.getResourceId(R.styleable.DefaultEditText_hintColor, -1)
            val toggleColor = typedArray.getResourceId(R.styleable.DefaultEditText_toggleColor, -1)
            val verifyCpfWithSavedOne = typedArray.getBoolean(R.styleable.DefaultEditText_etVerifyCpfWithSavedOne, false)
            val regexToMatch = typedArray.getString(R.styleable.DefaultEditText_etMatchesRegex)
            val regexToNotMatch = typedArray.getString(R.styleable.DefaultEditText_etNotMatchesRegex)
            val regexUseDefaultErrorMessage = typedArray.getBoolean(R.styleable.DefaultEditText_etUseDefaultErrorForRegex, true)

            mAppTextInput.setParams(inputType, hint, emptinessValid, minLength, verifyCpfWithSavedOne)
//            mTextInputEditText.isEnabled = editable
            mTextInputEditText.isFocusableInTouchMode = editable

            if (drawableRight != -1) {
                mTextInputEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableRight, 0);
                mTextInputEditText.compoundDrawablePadding = 6
            }

            if (drawableLeft != -1) {
                mTextInputEditText.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, 0, 0, 0);
                mTextInputEditText.compoundDrawablePadding = 6
            }

            if (textColor != -1)
                mTextInputEditText.textColor = ContextCompat.getColor(context, textColor)

            if (hintColor != -1) {
                mAppTextInput.defaultHintTextColor = ColorStateList.valueOf(ContextCompat.getColor(context, hintColor))
                @SuppressLint("RestrictedApi")
                mTextInputEditText.supportBackgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, hintColor))
            }

            if (toggleColor != -1) {
                mAppTextInput.setPasswordVisibilityToggleTintList(ColorStateList.valueOf(ContextCompat.getColor(context, toggleColor)))
            }

            //IF PASSWORD SHOW THE TOGGLE
            if (inputType == 7 || inputType == 14) {
                mAppTextInput.isPasswordVisibilityToggleEnabled = true
            }

            if (maxLength != -1) {
                mTextInputEditText.appendFilter(InputFilter.LengthFilter(maxLength))
            }

            if (maxLines != -1) {
                mAppTextInput.editText?.maxLines = maxLines
            }

            if (singleLines) {
                mAppTextInput.editText?.singleLine = singleLines
            }

            if (allCaps) {
                mTextInputEditText.appendFilter(InputFilter.AllCaps())
            }

            if (blockSpace) {
                mTextInputEditText.appendFilter(CustomFilter.RemoveWhiteSpaces)
            }

            if (!regexToMatch.isNullOrBlank()) {
                mAppTextInput.setRegex(regexToMatch, true, regexUseDefaultErrorMessage)
            }

            if (!regexToNotMatch.isNullOrBlank()) {
                mAppTextInput.setRegex(regexToNotMatch, false, regexUseDefaultErrorMessage)
            }

            mAppTextInput.editText?.setOnEditorActionListener { _, i, _ ->
                if (i == EditorInfo.IME_ACTION_GO) {
                    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(windowToken, 0)
                    true
                } else {
                    false
                }
            }

            mTextInputEditText.isEnabled = enabled

            typedArray.recycle()
        }
    }

    val text: String
        get() = mAppTextInput.text

    fun setText(text: String?) {
        mAppTextInput.text = text
    }

    fun setHint(text: String?) {
        mAppTextInput.hint = text
    }

    fun requestEditTextFocus() {
        getEditText().requestFocus()
    }

    fun setEditTextEnabled(enabled: Boolean) {
        mAppTextInput.editText?.isEnabled = enabled
    }

    fun setRegex(regex: String, isRegexValid: Boolean) {
        mAppTextInput.setRegex(regex, isRegexValid)
    }

    fun validate() {
        mAppTextInput.validate()
    }

    fun hideError() {
        mAppTextInput.error = null
    }

    fun validateWithoutError() {
        mAppTextInput.validateWithoutError()
    }

    fun isFieldValid() = mAppTextInput.isFieldValid

    fun isNotEmpty() = mAppTextInput.isNotEmpty()

    fun getEditText(): TextInputEditText {
        return this.mTextInputEditText
    }

    fun setCustomError(errorMsg: String) {
        mTextInputEditText.error = if (errorMsg.isEmpty()) context.getString(R.string.apptextinputlayout_invalid_field, getEditText().hint.toString()) else errorMsg
    }

    fun setErrorListener(listener: AppTextInputLayout.EditTextListener) {
        mAppTextInput.setErrorListener(listener)
    }

    fun setHintEnabled(isEnabled: Boolean) {
        mAppTextInput.isHintEnabled = isEnabled
    }
}