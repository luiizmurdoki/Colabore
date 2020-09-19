package com.example.colabore.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by mobile2you on 19/07/16.
 */
public class TextMask {
    public static final String CEP_MASK = "#####-###";
    public static final String CPF_MASK = "###.###.###-##";
    public static final String RG_MASK = "##.###.###-#";
    public static final String CPF_OR_CNPJ_MASK = "###.###.###-###";
    public static final String CNAE_MASK = "####-#/##";
    public static final String PHONE_MASK = "(##) ####-#####";
    public static final String CEL_PHONE_MASK = "(##) # ####-####";
    public static final String CEL_PHONE_MASK_FULL = "+## (##) # ####-####";
    public static final String CREDIT_CARD_MASK = "#### #### #### ####";
    public static final String DATE_MASK = "##/##/####";
    public static final String CNPJ_MASK = "##.###.###/####-##";
    public static final String HOUR_MASK = "##:##";
    public static final String CREDIT_CARD_DATE_MASK = "##/##";
    public static final String COMMON_BILL = "#####.##### #####.###### #####.###### # ##############";
    public static final String DEALERSHIP_MANUAL_BILL = "############ ############ ############ ############";
    public static final String DEALERSHIP_SCANNER_BILL = "########### ########### ########### ###########";

    public static String unmask(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "").replaceAll(" ", "")
                .replaceAll("[:]", "").replaceAll("[+]", "")
                .replaceAll("[,]", "");
    }

    public static TextWatcher insert(final String mask, final EditText ediTxt) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";
            String maskAux = mask;

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (mask.equals(PHONE_MASK)) {
                    if (unmask(s.toString()).length() > 10) {
                        maskAux = CEL_PHONE_MASK;
                    } else {
                        maskAux = PHONE_MASK;
                    }
                }
                if (mask.equals(CPF_OR_CNPJ_MASK)) {
                    if (unmask(s.toString()).length() > 11) {
                        maskAux = CNPJ_MASK;
                    } else {
                        maskAux = CPF_MASK;
                    }
                }
                if(mask.equals(COMMON_BILL)) {
                    if(s.length() > 1 && s.charAt(0) == '8') {
                        maskAux = DEALERSHIP_MANUAL_BILL;
                    } else {
                        maskAux = COMMON_BILL;
                    }
                }
                String str = TextMask.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
//                if (str.length() > old.length()) {
                for (char m : maskAux.toCharArray()) {
                    try {
                        char cha = str.charAt(i);
                        if (m == '#') {
                            mascara += cha;
                            i++;
                        } else {
                            mascara += m;
                        }
                    } catch (Exception e) {
                        break;
                    }

                }
//                } else {
//                    mascara = s.toString();
//                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

}
