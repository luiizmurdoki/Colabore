package com.example.colabore.model

import com.example.colabore.utils.Constants
import android.content.Context
import android.content.SharedPreferences

private const val SHARED_PREFERENCES_NAME = Constants.PACKAGE_NAME + ".SHARED_PREFERENCES"

private const val PREF_SESSION_COOKIE = "$SHARED_PREFERENCES_NAME.PREF_SESSION_COOKIE"
private const val PREF_ADD_MONEY = "$SHARED_PREFERENCES_NAME.PREF_ADD_MONEY"
private const val PREF_DEV_SESSION_COOKIE = "$SHARED_PREFERENCES_NAME.PREF_DEV_SESSION_COOKIE"
private const val PREF_USER_FINGERPRINT = "$SHARED_PREFERENCES_NAME.PREF_USER_FINGERPRINT"
private const val PREF_USER_FINGERPRINT_AVAILABLE =
    "$SHARED_PREFERENCES_NAME.PREF_USER_FINGERPRINT_AVAILABLE"
private const val PREF_USER_PINPAD = "$SHARED_PREFERENCES_NAME.PREF_USER_PINPAD"
private const val PREF_PINPAD_STORE = "$SHARED_PREFERENCES_NAME.PREF_PINPAD_STORE"
private const val PREF_USER_CARD_ID = "$SHARED_PREFERENCES_NAME.PREF_USER_CARD_ID"
private const val PREF_CDT_TOKEN = "$SHARED_PREFERENCES_NAME.PREF_CDT_TOKEN"
private const val PREF_CDT_TOKEN_SYSTEM_TIME = "$SHARED_PREFERENCES_NAME.PREF_CDT_TOKEN_SYSTEM_TIME"
private const val PREF_CDT_TOKEN_EXPIRATION_TIME =
    "$SHARED_PREFERENCES_NAME.PREF_CDT_TOKEN_EXPIRATION_TIME"
private const val PREF_DEV_CDT_TOKEN = "$SHARED_PREFERENCES_NAME.PREF_DEV_CDT_TOKEN"
private const val PREF_BASIC_AUTH = "$SHARED_PREFERENCES_NAME.PREF_BASIC_AUTH"
private const val PREF_LAST_CLIPBOARD = "$SHARED_PREFERENCES_NAME.PREF_LAST_CLIPBOARD"
private const val PREF_PINPAD_SITEF_IP = "$SHARED_PREFERENCES_NAME.PREF_PINPAD_SITEF_IP"
private const val PREF_PINPAD_TERMINAL_ID = "$SHARED_PREFERENCES_NAME.PREF_PINPAD_TERMINAL_ID"
private const val PREF_URL_ABOUT = "$SHARED_PREFERENCES_NAME.PREF_URL_ABOUT"
private const val PREF_URL_TERMS = "$SHARED_PREFERENCES_NAME.PREF_URL_TERMS"
private const val PREF_URL_POLICY = "$SHARED_PREFERENCES_NAME.PREF_URL_POLICY"
private const val PREF_ACCOUNT_ID = "$SHARED_PREFERENCES_NAME.PREF_ACCOUNT_ID"
private const val PREF_USER_CPF = "$SHARED_PREFERENCES_NAME.PREF_USER_CPF"
private const val PREF_ONG_ID = "$SHARED_PREFERENCES_NAME.PREF_ONG_ID"
private const val PREF_ADRESS_ID = "$SHARED_PREFERENCES_NAME.PREF_ADRESS_ID"
private const val PREF_CATEGORY_ID = "$SHARED_PREFERENCES_NAME.PREF_CATEGORY_ID"
private const val PREF_INFO_ID = "$SHARED_PREFERENCES_NAME.PREF_INFO_ID"
private const val PREF_URL_ID = "$SHARED_PREFERENCES_NAME.PREF_URL_ID"
private const val PREF_USER_NAME = "$SHARED_PREFERENCES_NAME.PREF_USER_NAME"
private const val PREF_USER_CNPJ = "$SHARED_PREFERENCES_NAME.PREF_USER_CNPJ"
private const val PREF_USER_PASSWORD = "$SHARED_PREFERENCES_NAME.PREF_USER_PASSWORD"
private const val PREF_DEVICE_ID = "$SHARED_PREFERENCES_NAME.PREF_DEVICE_ID"
private const val PREF_SHOW_ALERT = "$SHARED_PREFERENCES_NAME.PREF_SHOW_ALERT"

object PreferencesHelper {

    private lateinit var sharedPreferencesInstance: SharedPreferences

    private val sharedPreferences: SharedPreferences
        get() = try {
            sharedPreferencesInstance
        } catch (e: Exception) {
            throw NullPointerException("M2YCDTPreferencesHelper not initialized")
        }

    fun init(context: Context) {
        sharedPreferencesInstance = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        sessionCookie = null
    }

    var sessionCookie: String?
        get() = sharedPreferences.getString(PREF_SESSION_COOKIE, null)
        set(value) = sharedPreferences.edit().putString(PREF_SESSION_COOKIE, value).apply()

    var addMoneyId: String?
        get() = sharedPreferences.getString(PREF_ADD_MONEY, null)
        set(value) = sharedPreferences.edit().putString(PREF_ADD_MONEY, value).apply()

    var devSessionCookie: String?
        get() = sharedPreferences.getString(PREF_DEV_SESSION_COOKIE, null)
        set(value) = sharedPreferences.edit().putString(PREF_DEV_SESSION_COOKIE, value).apply()

    var userCardId: String?
        get() = sharedPreferences.getString(PREF_USER_CARD_ID, null)
        set(value) = sharedPreferences.edit().putString(PREF_USER_CARD_ID, value).apply()

    var basicAuth: String?
        get() = sharedPreferences.getString(PREF_BASIC_AUTH, null)
        set(value) = sharedPreferences.edit().putString(PREF_BASIC_AUTH, value).apply()

    var lastClipboard: String?
        get() = sharedPreferences.getString(PREF_LAST_CLIPBOARD, null)
        set(value) = sharedPreferences.edit().putString(PREF_LAST_CLIPBOARD, value).apply()

    var userFingerprint: Boolean
        get() = sharedPreferences.getBoolean(PREF_USER_FINGERPRINT, false)
        set(value) = sharedPreferences.edit().putBoolean(PREF_USER_FINGERPRINT, value).apply()

    var deviceId: String?
        get() = sharedPreferences.getString(PREF_DEVICE_ID, null)
        set(value) = sharedPreferences.edit().putString(PREF_DEVICE_ID, value).apply()

    var devCDTTokenCreationTime: String?
        get() = sharedPreferences.getString(PREF_DEV_CDT_TOKEN, null)
        set(value) = sharedPreferences.edit().putString(PREF_DEV_CDT_TOKEN, value).apply()

    var pinPadAddress: String?
        get() = sharedPreferences.getString(PREF_USER_PINPAD, null)
        set(value) = sharedPreferences.edit().putString(PREF_USER_PINPAD, value).apply()

    var urlAbout: String?
        get() = sharedPreferences.getString(PREF_URL_ABOUT, null)
        set(value) = sharedPreferences.edit().putString(PREF_URL_ABOUT, value).apply()

    var urlPolicy: String?
        get() = sharedPreferences.getString(PREF_URL_POLICY, null)
        set(value) = sharedPreferences.edit().putString(PREF_URL_POLICY, value).apply()

    var accountId: String?
        get() = sharedPreferences.getString(PREF_ACCOUNT_ID, null)
        set(value) = sharedPreferences.edit().putString(PREF_ACCOUNT_ID, value).apply()

    var urlTerms: String?
        get() = sharedPreferences.getString(PREF_URL_TERMS, null)
        set(value) = sharedPreferences.edit().putString(PREF_URL_TERMS, value).apply()

    var userCpf: String?
        get() = sharedPreferences.getString(PREF_USER_CPF, null)
        set(value) = sharedPreferences.edit().putString(PREF_USER_CPF, value).apply()

    var idOng: String?
        get() = sharedPreferences.getString(PREF_ONG_ID, null)
        set(value) = sharedPreferences.edit().putString(PREF_ONG_ID, value).apply()

    var cnpj: String?
        get() = sharedPreferences.getString(PREF_USER_CNPJ, null)
        set(value) = sharedPreferences.edit().putString(PREF_USER_CNPJ, value).apply()

    var categoria: String?
        get() = sharedPreferences.getString(PREF_CATEGORY_ID, null)
        set(value) = sharedPreferences.edit().putString(PREF_CATEGORY_ID, value).apply()

    var endereco: String?
        get() = sharedPreferences.getString(PREF_ADRESS_ID, null)
        set(value) = sharedPreferences.edit().putString(PREF_ADRESS_ID, value).apply()

    var imageUrl: String?
        get() = sharedPreferences.getString(PREF_URL_ID, null)
        set(value) = sharedPreferences.edit().putString(PREF_URL_ID, value).apply()

    var info: String?
        get() = sharedPreferences.getString(PREF_INFO_ID, null)
        set(value) = sharedPreferences.edit().putString(PREF_INFO_ID, value).apply()

    var userName: String?
        get() = sharedPreferences.getString(PREF_USER_NAME, null)
        set(value) = sharedPreferences.edit().putString(PREF_USER_NAME, value).apply()
    var userCnpj: String?
        get() = sharedPreferences.getString(PREF_USER_CNPJ, null)
        set(value) = sharedPreferences.edit().putString(PREF_USER_CNPJ, value).apply()

    var pinPadSitefIP: String?
        get() = sharedPreferences.getString(PREF_PINPAD_SITEF_IP, null)
        set(value) = sharedPreferences.edit().putString(PREF_PINPAD_SITEF_IP, value).apply()

    var pinPagTerminalId: String?
        get() = sharedPreferences.getString(PREF_PINPAD_TERMINAL_ID, null)
        set(value) = sharedPreferences.edit().putString(PREF_PINPAD_TERMINAL_ID, value).apply()

    var showAlert: Boolean
        get() = sharedPreferences.getBoolean(PREF_SHOW_ALERT, true)
        set(value) = sharedPreferences.edit().putBoolean(PREF_SHOW_ALERT, value).apply()

    //PINPAD

    fun isLogged(): Boolean {
        return !sessionCookie.isNullOrBlank()
    }

    fun clearSharedPref() {
        sharedPreferences.edit().clear().apply()
    }
}