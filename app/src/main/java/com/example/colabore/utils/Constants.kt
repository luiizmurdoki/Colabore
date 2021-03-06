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
    const val ONG_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS"
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

    //IMAGES
    const val IMAGE0 ="https://gist.githubusercontent.com/Lucasvor/4211c5ce4b599128360de7e7a16a944e/raw/da52a420b1883aedc0a8dab587e7e049b11ecd66/face0.svg"
    const val IMAGE1 ="https://gist.githubusercontent.com/Lucasvor/281215fa1f89c67fc48e6de32a81d453/raw/474fc97ee474966afb677f289d4167d5aed5ed9e/face1.svg"
    const val IMAGE2 ="https://gist.githubusercontent.com/Lucasvor/2b773d0ef4c968976c1f6f8cc642414c/raw/1c2542abe661f4bb65d57acf5a85a6aeeacfc549/face2.svg"
    const val IMAGE3 ="https://gist.githubusercontent.com/Lucasvor/28e91b69db5726d5030841bf158df8e3/raw/5c447a7ab598d1b3545e22a60f6ef2f9702580c2/face3.svg"
    const val IMAGE4 ="https://gist.githubusercontent.com/Lucasvor/20ee9281eb2cf5e7cd2f3247dbc87ce6/raw/7ef31426aeeb4a3f2a526e22a25f3b69f266be52/face4.svg"
    const val IMAGE5 ="https://gist.githubusercontent.com/Lucasvor/7bc572214f11db62c638441bb3f12fcf/raw/307a7cb2b14ca9440e5aee9bc9b143087028502a/face5.svg"
    const val IMAGE6 ="https://gist.githubusercontent.com/Lucasvor/720c1f20a65d2c665a4f976bbb1ce9ad/raw/ba2840792787149be8a264be37ab9cd30871c8a8/face6.svg"
    const val IMAGE7 ="https://gist.githubusercontent.com/Lucasvor/853e1517739877df5ef77b581658e30f/raw/df0c1b2a55d207f4777791ff7042b4a503400192/face7.svg"
    const val IMAGE8 ="https://gist.githubusercontent.com/Lucasvor/b3dd403d64971bb2d043d5f9957c5150/raw/7fdec5625d1ae84082c53a8c5ade4527103479b9/face8.svg"
    const val IMAGE9 ="https://gist.githubusercontent.com/Lucasvor/85b7cb9e241011f61b7ba1f36b7d12bf/raw/cc80ce258d56f4461d46e9b41cc1a14a40036a08/face9.svg"
    const val IMAGE10 ="https://gist.githubusercontent.com/Lucasvor/5cb52c33e4c0c733eb4073d9531c6f9c/raw/efc5ec3e4de19d246a8962847c4cceab6a6e4201/face10.svg"
    const val IMAGE11 ="https://gist.githubusercontent.com/Lucasvor/08c30e3fec0b1449c50f6d6dfe188fcd/raw/036faea885ae5edfed6a67cf5774902ef67cddc6/face11.svg"
    const val IMAGE12 ="https://gist.githubusercontent.com/Lucasvor/7463ff0f986191101ff8180430fd3fb9/raw/5ea2c74904b7821c13d8e1b37313460c9deafcae/face12.svg"
    const val IMAGE13 ="https://gist.githubusercontent.com/Lucasvor/ac80a1af41c2750f6736ab805d724c8b/raw/d2c116731e9890a92d605048a74e1dfe652b1ff7/face13.svg"
    const val IMAGE14 ="https://gist.githubusercontent.com/Lucasvor/521b4ef72f509d167014f950efe803ff/raw/36bca440fb608d2fb3446b7ad4186e6c095c547c/face14.svg"
    const val IMAGE15 ="https://gist.githubusercontent.com/Lucasvor/976e6166117000cc0ca84ed9ac8676b0/raw/70bb65877da23c88617ed84441f249f9e6fbaf1a/face15.svg"
    const val IMAGE16 ="https://gist.githubusercontent.com/Lucasvor/7f9424a2d847f4ac07105c97a83222bc/raw/909765b75962325188d4cf72878330da6217bfbd/face16.svg"
    const val IMAGE17 ="https://gist.githubusercontent.com/Lucasvor/c00b89bcc5be1ad078acb80f7b136448/raw/bc9e071dbf04bbad87e42f85d2077b1cfe273b93/face17.svg"
    const val IMAGE18 ="https://gist.githubusercontent.com/Lucasvor/220b4004318279decaf40f38f2a3861d/raw/5f5f9ca929e3a8b9614540080a38b0176676b9ee/face18.svg"
    const val IMAGE19 ="https://gist.githubusercontent.com/Lucasvor/e3ffc9dadbde46cd30d21daf7be8bac0/raw/188f24d8596569f8b48ce7dd505ac52f4a8887c0/face19.svg"
    const val IMAGE20 ="https://gist.githubusercontent.com/Lucasvor/bd995421e70d861deae8fb68b04bc178/raw/6072bb85860de2e761d6b9f96b4efac1bdd8ab39/face20.svg"
}
















