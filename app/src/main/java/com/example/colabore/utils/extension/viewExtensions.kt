package com.example.colabore.utils.extension

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.example.colabore.ui.widget.DefaultEditText
import com.example.colabore.utils.Constants
import com.example.colabore.utils.CurrencyMask
import com.example.colabore.utils.CustomLinearLayoutManager
import java.util.*

fun View.setVisible(visible: Boolean, useInvisible: Boolean = false) {
    visibility = when {
        visible -> View.VISIBLE
        useInvisible -> View.INVISIBLE
        else -> View.GONE
    }
}

fun EditText.appendFilter(inputFilter: InputFilter) {
    filters += inputFilter
}

fun List<DefaultEditText>.validateFields(thenInvalidate: Boolean? = false): Boolean {
    if (!Constants.VALIDATE_FIELDS) return true
    else {
        var isFormValid = true
        this.forEach {
            it.validate()
            if (isFormValid) isFormValid = it.isFieldValid()
            if (thenInvalidate == true) it.hideError()
        }
        return isFormValid
    }
}


fun EditText.afterTextChanged(onTextChanged: ((String) -> Unit)) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            onTextChanged(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(query: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}

fun androidx.recyclerview.widget.RecyclerView.setup(adapter: androidx.recyclerview.widget.RecyclerView.Adapter<in androidx.recyclerview.widget.RecyclerView.ViewHolder>,
                                                    layoutManager: androidx.recyclerview.widget.RecyclerView.LayoutManager? = CustomLinearLayoutManager(this.context),
                                                    decoration: androidx.recyclerview.widget.RecyclerView.ItemDecoration? = null,
                                                    hasFixedSize: Boolean = true) {

    this.adapter = adapter
    this.layoutManager = layoutManager
    this.setHasFixedSize(hasFixedSize)
    decoration?.let { this.addItemDecoration(it) }
}


fun EditText.addCurrencyMask(displayCurrency: Boolean = false) {
    addTextChangedListener(CurrencyMask.insert(Locale("pt", "BR"), this, displayCurrency))
}

fun EditText.onImeActionDone(onAction: () -> Unit) {
    this.setOnEditorActionListener{ v, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            onAction()
            return@setOnEditorActionListener true
        }
        false
    }
}

