package com.example.colabore.utils.validations;

public class IsLengthValid {

    public static boolean isValid(String text, int size, boolean exactSameSize) {
        return exactSameSize ? text.length() == size : text.length() >= size;
    }

}
