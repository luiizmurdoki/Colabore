package com.example.colabore.ui.utils.validations;

/**
 * Created by azul on 11/04/17.
 */

public class IsNotEmpty {

    public static boolean isValid(String text) {
        return !text.isEmpty();
    }
}
