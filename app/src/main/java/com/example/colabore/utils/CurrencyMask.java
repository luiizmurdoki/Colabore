package com.example.colabore.ui.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by mobile2you on 06/07/16.
 */
public class CurrencyMask {

    public static String unmask(String s) {
        String newStr = s.replaceAll("[^0-9]", "");
        return newStr;
    }

    public static float parseValue(String s) throws NumberFormatException {
        return Float.parseFloat(unmask(s).replaceAll("\\s+", "")) / 100;
    }

    public static TextWatcher insert(final Locale locale, final EditText ediTxt, final boolean displayCurrency) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                if (!s.toString().equals(old)) {

                    isUpdating = true;

                    String cleanString = unmask(s.toString());

                    try {
                        double parsed = Double.parseDouble(cleanString.replaceAll("\\s+", ""));
                        String formated = NumberFormat.getCurrencyInstance(locale).format((parsed / 100));

                        if (!displayCurrency)
                            formated = formated.replace("R$", "").replaceAll("\\s+", "");

                        old = formated;
                        ediTxt.setText(formated);
                        ediTxt.setSelection(ediTxt.getText().length());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                if (old.equals("R$0,0")) {
                    ediTxt.setText("R$0,00");
                }

                // is erasing message
                if (old.length() > s.length() && s.length() > 0) {
                    old = s.toString();
                    return;
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }
}