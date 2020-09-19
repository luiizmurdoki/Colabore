package com.example.colabore.utils

import com.example.colabore.BuildConfig

object Constants {
    const val PACKAGE_NAME = BuildConfig.APPLICATION_ID

    const val GOOGLE_PLAY_URL = "https://play.google.com/store/apps/details?id=$PACKAGE_NAME"
    const val GOOGLE_MAPS_API = "https://maps.googleapis.com/maps/api/"

    const val AUTHORITY = Constants.PACKAGE_NAME + ".fileprovider"

    //GENERAL
    const val LANGUAGE_PT = "pt"
    const val COUNTRY_BR = "BR"
    const val INCOME_VALUE = 1F
    const val EMPTY = ""
    const val RESQUEST ="@colaboreapp.com"

    //ARG
    const val ARG_EXTRACT_TYPE = "$PACKAGE_NAME.ARG_EXTRACT_TYPE"
    const val ARG_END_DATE = "$PACKAGE_NAME.ARG_END_DATE"
    const val ARG_START_DATE = "$PACKAGE_NAME.ARG_START_DATE"

    //DATE FORMATS
    const val VOUCHER_FORMAT = "dd 'de' MMMM 'de' yyyy"
    const val CARD_FORMAT = "MM/yy"
    const val EXTRACT_FORMAT = "dd/MM/yyyy - HH:mm"
    const val COMMON_DATE_FORMAT = "dd/MM/yyyy"
    const val TICKET_EXPIRE_DATE_FORMAT = "dd MMM yyyy"
    const val COMMON_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm"
    const val COMMON_TIME_FORMAT = "HH:mm"
    const val NOTIFICATION_TIME_FORMAT = "dd/MM/yyyy '\n' HH:mm"
    const val TICKET_DATE_FORMAT = "yyyy-MM-dd"
    const val COMMON_REGEX_DATE_FORMAT = "ddMMyy"
    const val COMMON_REGEX_DAY_MONTH_FORMAT = "ddMM"
    const val EXTRACT_DATE_FORMAT = "dd 'de' MMMM 'de' yyyy"
    const val COMMON_REGEX_DAY_MONTH_EXTENSIVE_FORMAT = "dd/MMM"
    const val DATE_AT_TIME_DATE_FORMAT = "dd/MM/yyyy 'às' HH:mm"
    const val CREATED_AT_DATE_FORMAT = "dd/MM/yyyy HH:mm"
    const val SYS_DATA_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val FILTER_MONTHS_DATE_FORMAT = "MMMM/yyyy"
    const val CDT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    const val RECHARGE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'+'SSS" //2018-12-20T16:06:16.355+0000
    const val UNIQUE_TICKET__FORMAT = "yyyy-MM-dd'T'HH:mm:ss" //2018-12-20T16:06:16.355+0000
    const val PLACEHOLDER_FORMAT = "yyyyMMddHHmmss"
    const val EXTRAC_REQUEST_DATE_FORMAT = "yyyy-MM-dd"
    const val RECEIPT_DATE_FORMAT = "dd/MM/yyyy HH:mm"
    const val TICKET_RECEIPT_DATE_FORMAT = "dd 'de' MMMM 'de' yyyy"
    const val CDT_TICKET_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS"
    const val CDT_TICKET_RESALES_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    const val PAYMENT_DUEDATE = "dd/mm/yyyy'T'HH:mm:ss.SSS"
    //REGEX
    const val APP_PASSWORD_REGEX = "(?:^012345|^123456|^234567|^345678|^456789|^567890|^987654|^876543|^765432|^654321|^543210|^098765|^000000|^111111|^222222|^333333|^444444|^555555|^666666|^777777|^888888|^999999)"
    const val CARD_PASSWORD_REGEX = "(?:.*(.)\\\\^0123|^1234|^2345|^3456|^4567|^5678|^6789|^7890|^9876|^8765|^7654|^6543|^5432|^4321|^3210|^0000|^1111|^2222|^3333|^4444|^5555|^6666|^7777|^8888|^9999\\(birth)"


    const val EXTRA_FAVORED_RECHARGE = "$PACKAGE_NAME.EXTRA_FAVORED_RECHARGE"
    const val EXTRA_FAVORED_UNIQUE = "$PACKAGE_NAME.EXTRA_FAVORED_UNIQUE"
    const val EXTRA_FAVORITE = "$PACKAGE_NAME.EXTRA_FAVORITE"

    //Tickets
    const val BARCODE_MIN_LENGTH = 35
    const val BARCODE_MAX_LENGTH = 60
    const val TICKET_ADD_MONEY_TYPE = 9
    const val TICKET_SELLING_TYPE = 1
    const val TRANSFER_VALUE_MINIMAL = "Verifique o valor da transferência, serão realizadas apenas transferências superiores a R$0,10"

    //BANK ACCOUNT
    const val BANK_NAME = "<Banco>"
    const val BANK_AGENCY = "<Agência>"
    const val BANK_ACCOUNT = "<Conta>"
    const val BANK_DESC = "<Descrição>"
    const val BANK_CPF = "<CPF>"
    const val BANK_PERSON_NAME = "<Nome>"

    //ANIMATIONS CONSTANTS
    const val ANIMATION_DURATION_DEFAULT = 1000L

    //TOKEN
    val SECONDS_REMAINING_ID = "com.resocoder.timer.seconds_remaining"
    val EXTRA_REQUEST = "$PACKAGE_NAME.EXTRA_REQUEST"
    val ENABLE_TRUE = true
    val ENABLE_FALSE = false
    const val STATION_MAP_REQUEST = 987
    const val PLACE_FILTER_REQUEST = 654
    const val PLACE_PRODUCT_REQUEST = 321

    //To debug purposes
    const val VALIDATE_FIELDS = true
}