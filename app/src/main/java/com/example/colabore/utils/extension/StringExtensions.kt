package com.example.colabore.utils.extension

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import androidx.annotation.StringRes
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import com.example.colabore.utils.Constants
import com.example.colabore.utils.TextMask
import java.text.NumberFormat
import java.util.*


/**
 * Created by azul on 11/10/17.
 */
/**
 * Returns string of float value to percentage
 * @param maximumFractionDigits length of fraction digits
 * @return percentage value of @value
 */
fun Float.toPercentage(maximumFractionDigits: Int? = null, minimumFractionDigits: Int? = null): String {
    val number = NumberFormat.getPercentInstance()
    maximumFractionDigits?.let { number.maximumFractionDigits = it }
    minimumFractionDigits?.let { number.minimumFractionDigits = minimumFractionDigits }
    return number.format(this.toDouble())
}

/**
 * @param locale locale of the currency to format
 * @return the value with the locale currency
 */
fun Float.formatToCurrency(locale: Locale): String {
    val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
    return currencyFormatter.format(this.toDouble())
}

fun Double.formatToCurrency(locale: Locale): String {
    val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
    return currencyFormatter.format(this)
}

fun String.formatCurrencyBRL(trimRs: Boolean = false) : String {
    return if (trimRs) this.replace(".", ",") else "R$ ".plus(this.replace(".", ","))
}

/**
 * @return the value with the BRL currency
 */
fun Float.formatCurrencyBRL(trimRs: Boolean = false): String {
    val locale = Locale(Constants.LANGUAGE_PT, Constants.COUNTRY_BR)
    return if (trimRs) this.formatToCurrency(locale).replace("R$", "") else this.formatToCurrency(locale)
}

fun Long.formatCurrencyBRL(trimRs: Boolean = false): String {
    val locale = Locale(Constants.LANGUAGE_PT, Constants.COUNTRY_BR)
    val string = (this.toDouble() / 100.0).formatToCurrency(locale)
    return if (trimRs) string.replace("R$", "") else string
}

/**
 * @param message
 * @return message with the first letter capitalized
 */
fun String.capitalizeFirstLetter(): String {
    return this.substring(0, 1).capitalize() + this.substring(1)
}

fun String.getFloatValue(): Float {
    return try {
        this.replace(Regex("[A-z., $]"), "").trim().toFloat() / 100f
    } catch (e: NumberFormatException) {
        return 0f
    }
}

fun Context.str(@StringRes id: Int): String {
    return getString(id)
}

fun String.colored(textToColor: String, color: Int, bold: Boolean = false): SpannableString {
    val spannable = SpannableString(this)

    val startIndex = this.indexOf(textToColor)
    if (startIndex >= 0) {
        val endIndex = startIndex + textToColor.length
        spannable.setSpan(ForegroundColorSpan(color), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        if (bold) {
            val style = StyleSpan(Typeface.BOLD)
            spannable.setSpan(style, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    return spannable
}

fun String.getCreditCardType(): CreditCardType {
    val regVisa = Regex("^4[0-9]{12}(?:[0-9]{3})?\$")
    val regMaster = Regex("^5[1-5][0-9]{14}\$")
    val regExpress = Regex("^3[47][0-9]{13}\$")
    val regDiners = Regex("^3(?:0[0-5]|[68][0-9])[0-9]{11}\$")
    val regDiscover = Regex("^6(?:011|5[0-9]{2})[0-9]{12}\$")
    val regJCB = Regex("^(?:2131|1800|35\\d{3})\\d{11}\$")


    return when {
        regVisa.matches(this) -> CreditCardType.Visa
        regMaster.matches(this) -> CreditCardType.Mastercard
        regExpress.matches(this) -> CreditCardType.AmericanExpress
        regDiners.matches(this) -> CreditCardType.Diners
        regDiscover.matches(this) -> CreditCardType.Discovers
        regJCB.matches(this) -> CreditCardType.JCB
        else -> CreditCardType.ELO
    }
}

fun String.fromHtml() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
} else {
    Html.fromHtml(this)
}


fun TextView.setHtmlText(@StringRes stringRes: Int) {
    setHtmlText(context.getString(stringRes))
}

fun TextView.setHtmlText(text: String) {
    val html = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(text)
    }

    this.text = html
}

fun String.unmask() = TextMask.unmask(this)

enum class CreditCardType {
    Visa, Mastercard, AmericanExpress, Diners, Discovers, JCB, ELO
}

fun <T> List<T>.concatList(): String {
    var result = ""
    this.forEach { result += it.toString() }
    return result
}

fun String.toCamelCase(): String {
    return split(" ").filter {
        it.isNotBlank()
    }.mapIndexed { stringIndex, string ->
        string.mapIndexed { charIndex, char ->
            if (charIndex == 0 && stringIndex != 0) char.toUpperCase() else char.toLowerCase()
        }.joinToString("")
    }.joinToString("")
}

fun String.fillWithChars(minLenght: Int, char: Char, position: CustomString): String {
    if (this.length >= minLenght)
        return this

    val diff = minLenght - this.length
    var insert = (0 until diff).map { char }.concatList()

    return when (position) {
        CustomString.BEFORE -> {
            "$insert$this"
        }
        CustomString.AFTER -> {
            "$this$insert"
        }
        else -> {
            ""
        }
    }

}

fun String.isBarcode(): Boolean {
    val unmasked = TextMask.unmask(this)
    //check contains only number and (
    return unmasked.replace(" ", "").matches("-?\\d+(\\.\\d+)?".toRegex())
            && (unmasked.length == 48 || unmasked.length == 47)
}

enum class CustomString {
    BEFORE, AFTER, MID
}

fun String.isToday(): Boolean {
    return this.getDate("dd/MM/yyyy")?.isToday() == true
}

fun String.isSameDay(date: String): Boolean {
    val stringDate = this.getDate("dd/MM/yyyy")
    return stringDate != null && date.getDate("dd/MM/yyyy")?.isSameDay(stringDate) == true
}

fun String.addMask(inputMask: String, substitute: Char? = '#'): String {
    var mask = inputMask
    var unmaskedString = this.unmask()
    var index = 0
    var result = ""
    if (mask == TextMask.CPF_OR_CNPJ_MASK) {
        mask = if (unmaskedString.length > 11) TextMask.CNPJ_MASK else TextMask.CPF_MASK
    }
    if (mask == TextMask.CNPJ_MASK) {
        unmaskedString.addZerosUntilLength(14)
    } else if (mask == TextMask.CPF_MASK) {
        unmaskedString.addZerosUntilLength(11)
    }
    mask.forEach { item ->
        if (index < unmaskedString.length) {
            if (item == substitute) {
                result += unmaskedString[index]
                index++
            } else {
                result += item
            }
        }
    }
    return result
}

fun String.getInitials(size: Int): String {
    val splitted = this.split(" ")
    val initials = splitted.map { it.first().toUpperCase() }.joinToString("")
    return if (initials.length > size) {
        initials.take(size)
    } else {
        (initials + splitted.last().drop(1)).toUpperCase().take(size)
    }
}

fun String.addZerosUntilLength(quantity: Int): String {
    var result = this
    if (this.length < quantity) {
        for (i in 1..(quantity - this.length)) {
            result = "0$result"
        }
    }
    return result
}

fun String.normalizeSpaces() = this.trim().replace("\\s{2,}".toRegex(), " ")

fun String.takeLastDigitsPhone(): String {
    val placeholder = "*******"
    var formattedPhone = this.takeLast(4)
    formattedPhone = if(formattedPhone.isEmpty()) "****" else placeholder + formattedPhone
    return formattedPhone.addMask(TextMask.CEL_PHONE_MASK)
}

fun String.maskPhone(): String {
    val phoneSize = this.unmask().length
    val startIndex = -1
    val endIndex = phoneSize - 4

    return this.unmask().mapIndexed { index, char -> if (index <= startIndex || index >= endIndex) char else '*' }.joinToString("")
}

fun String.maskBirthday(): String {
    val phoneSize = this.unmask().length
    val startIndex = 1
    val endIndex = phoneSize - 2

    return this.unmask().mapIndexed { index, char -> if (index <= startIndex || index >= endIndex) char else '*' }.joinToString("")
}

fun String.maskEmail(): String {
    val prefixSplit = this.split("@")
    val suffixSplit = prefixSplit.last().split(".")
    val startIndex = 0

    var suffix = ""
    suffixSplit.forEach {
        suffix += it.mapIndexed {
            index, char -> if (index <= startIndex) char else '*'
        }.joinToString("") + "."
    }

    return prefixSplit.first() + "@" + suffix
}

fun String.maskText(): String {
    val words = this.split(" ")
    val startIndex = 0

    var resultString = ""
    words.forEach {
        resultString += it.mapIndexed {
            index, char -> if (index <= startIndex) char else '*'
        }.joinToString("") + " "
    }

    return resultString
}
