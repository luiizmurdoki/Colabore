package com.example.colabore.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.colabore.R;
import com.example.colabore.utils.TextMask;
import com.example.colabore.utils.validations.IsCnpj;
import com.example.colabore.utils.validations.IsCpf;
import com.example.colabore.utils.validations.IsEmail;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class AppTextInputLayout extends TextInputLayout {
    private final int TYPE_CUSTOM = 0;
    private final int TYPE_NAME = 1;
    private final int TYPE_MAIL = 2;
    private final int TYPE_DATE = 3;
    private final int TYPE_PHONE = 4;
    private final int TYPE_CEP = 5;
    private final int TYPE_CREDIT_CARD = 6;
    private final int TYPE_PASSWORD = 7;
    private final int TYPE_MATCHING = 8;
    private final int TYPE_CPF = 9;
    private final int TYPE_CNPJ = 10;
    private final int TYPE_CREDIT_CARD_DATE = 11;
    private final int TYPE_NUMBER = 12;
    private final int TYPE_CELLPHONE = 13;
    private final int TYPE_NUMBER_PASSWORD = 14;
    private final int TYPE_CELLPHONE_FULL = 15;
    private final int TYPE_CPF_OR_CNPJ = 16;
    private final int TYPE_CNAE = 17;
    private final int TYPE_RG = 18;

    private int mInputType;
    private EditTextListener mEditTextListener = null;
    private boolean mFieldNeedsValidation;
    private boolean mUseDefaultError;
    private int mErrorBackground;
    private int mDefaultBackground;
    private boolean mEmptinessIsValid;
    private String mEmptyErrorText;
    private String mRegexErrorText = "";
    private String mInvalidErrorText;
    private int mMinLength;
    private EditText mMatchingReference;
    private String mMask = "";
    private boolean isRegexValid = false;
    private TypedArray mValues;
    private boolean mMustVerifyCPfWithSaved = false;

    private String mRegex;

    private EditText mEditText;

    public AppTextInputLayout(Context context) {
        super(context);
    }

    public AppTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mValues = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AppTextInputLayout, 0, 0);
        initialize(mValues);
    }

    public AppTextInputLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mValues = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AppTextInputLayout, 0, 0);
        initialize(mValues);
    }

    public void setErrorListener(EditTextListener listener) {
        mEditTextListener = listener;
    }

    public void setRegex(String regex, boolean isRegexValid) {
        mRegex = regex;
        this.isRegexValid = isRegexValid;
    }

    public void setRegex(String regex, boolean isRegexValid, boolean useDefaultError) {
        mRegex = regex;
        mUseDefaultError = useDefaultError;
        this.isRegexValid = isRegexValid;
    }

    public interface EditTextListener {
        void onError();

        void onValid();

        void onFocusChange(Boolean isFocused);
    }

    private void initialize(TypedArray values) {
        mFieldNeedsValidation = values.getBoolean(R.styleable.AppTextInputLayout_validation, true);
        mEmptyErrorText = values.getString(R.styleable.AppTextInputLayout_emptyErrorText) == null ? "" : values.getString(R.styleable.AppTextInputLayout_emptyErrorText);
        mInvalidErrorText = values.getString(R.styleable.AppTextInputLayout_invalidErrorText) == null ? "" : values.getString(R.styleable.AppTextInputLayout_invalidErrorText);
        mInputType = values.getInt(R.styleable.AppTextInputLayout_inputTextType, TYPE_CUSTOM);
        mMinLength = values.getInt(R.styleable.AppTextInputLayout_minLength, 0);
        mMask = values.getString(R.styleable.AppTextInputLayout_customMask) == null ? "" : values.getString(R.styleable.AppTextInputLayout_customMask);
        mErrorBackground = values.getResourceId(R.styleable.AppTextInputLayout_errorBackground, -1);
        mDefaultBackground = values.getResourceId(R.styleable.AppTextInputLayout_defaultBackground, -1);
        mEmptinessIsValid = values.getBoolean(R.styleable.AppTextInputLayout_emptinessIsValid, false);
        mRegex = values.getString(R.styleable.AppTextInputLayout_pattern) == null ? "" : values.getString(R.styleable.AppTextInputLayout_pattern);
        values.recycle();
    }


    public void setParams(int inputTextType, String hint, boolean emptinessValid, int minLength) {
        mInputType = inputTextType;
        setInputType();

        if (hint != null) {
            setHint(hint);
        }

        mEmptinessIsValid = emptinessValid;
        mMinLength = minLength;

        if (mEmptyErrorText.isEmpty())
            mEmptyErrorText = getContext().getString(R.string.apptextinputlayout_empty_field, getHint().toString());
    }

    public void setParams(int inputTextType, String hint, boolean emptinessValid, int minLength, boolean mustVerifyCPfWithSaved) {
        setParams(inputTextType, hint, emptinessValid, minLength);
        mMustVerifyCPfWithSaved = mustVerifyCPfWithSaved;
    }

    private boolean mIsInitialized = false;

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (getEditText() != null && !mIsInitialized) {
            mIsInitialized = true;
            setInputType();
        }
    }

    private void setInputType() {
        mEditText = getEditText();
        setEditTextListener(mEditText);
        switch (mInputType) {
            case TYPE_NAME:
                mEditText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                break;
            case TYPE_MATCHING:
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case TYPE_CUSTOM:
                break;
            case TYPE_PASSWORD:
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;
            case TYPE_MAIL:
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case TYPE_DATE:
                mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                mMask = TextMask.DATE_MASK;
                break;
            case TYPE_PHONE:
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_PHONE);
                mMask = TextMask.PHONE_MASK;
                break;
            case TYPE_CELLPHONE:
                mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                mMask = TextMask.CEL_PHONE_MASK;
                break;
            case TYPE_CELLPHONE_FULL:
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_PHONE);
                mMask = TextMask.CEL_PHONE_MASK_FULL;
                break;
            case TYPE_CEP:
                mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                mMask = TextMask.CEP_MASK;
                break;
            case TYPE_CREDIT_CARD:
                mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                mMask = TextMask.CREDIT_CARD_MASK;
                break;
            case TYPE_CPF:
                mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                mMask = TextMask.CPF_MASK;
                break;
            case TYPE_CNPJ:
                mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                mMask = TextMask.CNPJ_MASK;
                break;
            case TYPE_CREDIT_CARD_DATE:
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_DATETIME_VARIATION_DATE);
                mMask = TextMask.CREDIT_CARD_DATE_MASK;
                break;
            case TYPE_NUMBER:
                mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case TYPE_NUMBER_PASSWORD:
                mEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;
            case TYPE_CPF_OR_CNPJ:
                mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                mMask = TextMask.CPF_OR_CNPJ_MASK;
                break;
            case TYPE_CNAE:
                mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                mMask = TextMask.CNAE_MASK;
                break;
            case TYPE_RG:
                mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                mMask = TextMask.RG_MASK;
                break;

        }
        if (!mMask.isEmpty()) {
            addMask(mEditText, mMask);
        }
    }

    private void addMask(EditText editText, String mask) {
        editText.addTextChangedListener(TextMask.insert(mask, editText));
    }

    private void setEditTextListener(final EditText editTextListener) {
        editTextListener.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (mFieldNeedsValidation) {
                    if (mEditTextListener != null)
                        mEditTextListener.onFocusChange(b);

                    if (b) {
                        if (mDefaultBackground >= 0)
                            (AppTextInputLayout.this).setBackgroundResource(mDefaultBackground);

                        setErrorEnabled(false);
                    } else {
                        validate();
                    }
                }
            }
        });
    }

    public void validate() {
        validateText(true);
    }

    public void validateWithoutError() {
        validateText(false);
    }

    private void validateText(Boolean showError) {
        if (mFieldNeedsValidation) {
            //Type validations
            boolean isFieldValid = true;
            switch (mInputType) {
                case TYPE_CUSTOM:
                case TYPE_NAME:
                case TYPE_PHONE:
                case TYPE_CNAE:
                case TYPE_RG:
                case TYPE_CELLPHONE:
                case TYPE_CELLPHONE_FULL:
                case TYPE_CREDIT_CARD:
                case TYPE_PASSWORD:
                    break;
                case TYPE_DATE:
                    isFieldValid = isDateValid();
                    break;
                case TYPE_CREDIT_CARD_DATE:
                    isFieldValid = isCardDateValid();
                    break;
                case TYPE_CNPJ:
                    isFieldValid = isCnpjValid();
                    break;
                case TYPE_MAIL:
                    isFieldValid = isMailValid();
                    break;
                case TYPE_MATCHING:
                    isFieldValid = isMatchingValid();
                    break;
                case TYPE_CPF:
                    isFieldValid = isCpfValid();
                    break;
                case TYPE_CEP:
                    isFieldValid = isCepValid();
                    break;
                case TYPE_CPF_OR_CNPJ:
                    isFieldValid = isCpfValid() || isCnpjValid();
                    break;
            }

            if (mDefaultBackground >= 0)
                this.setBackgroundResource(mDefaultBackground);

            //Emptiness validation
            if (showError) {
                if (!isNotEmpty()) {
                    setEmptyErrorText();
                } else if (!isFieldValid) {
                    setInvalidErrorText();
                } else if (!isPatternValid()) {
                    if (mUseDefaultError) {
                        setInvalidErrorText();
                    } else {
                        setRegexErrorText();
                    }
                } else if (!isLengthValid()) {
                    if (mMask.isEmpty()) {
                        showMinLengthErrorText();
                    } else {
                        setInvalidErrorText();
                    }
                } else {
                    //if got here it's because field is valid
                    if (mEditTextListener != null)
                        mEditTextListener.onValid();


                    setErrorEnabled(false);
                }
            }

        }
    }

    private boolean isCepValid() {
        if (mEditText.length() < 8 && mEditText.length() != 0) {
            return false;
        } else {

            return true;
        }
    }

    private boolean isDateValid() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.setLenient(false);
            format.parse(mEditText.getText().toString());
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isCardDateValid() {
        SimpleDateFormat format = new SimpleDateFormat("MM/yy");
        try {
            format.setLenient(false);
            format.parse(mEditText.getText().toString());
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isPatternValid() {
        //Returns true only if there' a regex and the Pattern matches
        return mRegex.isEmpty() || Pattern.matches(mRegex, mEditText.getText()) == isRegexValid;
    }

    private boolean isLengthValid() {
        if (!mMask.isEmpty()) {

            if (mMask.equals(TextMask.PHONE_MASK)) {
                return hasMinLengthOrMore(TextMask.PHONE_MASK.length() - 1) || hasMinLengthOrMore(TextMask.CEL_PHONE_MASK.length());
            }
            if (mMask.equals(TextMask.CPF_OR_CNPJ_MASK)) {
                return true;
            }

            mMinLength = mMask.length();
        }

        return hasMinLengthOrMore(mMinLength);
    }

    private boolean hasMinLengthOrMore(int minLength) {
        return minLength == 0 || mEditText.getText().length() >= minLength;
    }

    private boolean isCpfValid() {
        return IsCpf.isValid(mEditText.getText().toString());
    }

    private boolean isCnpjValid() {
        return IsCnpj.isValid(mEditText.getText().toString());
    }

    private boolean isNotEmpty() {
        return mEmptinessIsValid || !mEditText.getText().toString().trim().isEmpty();
    }

    public boolean isFieldValid() {
        return !isErrorEnabled();
    }

    private boolean isMailValid() {
        return IsEmail.isValid(mEditText.getText().toString());
    }

    public void setCustomError(String errorMsg) {
        if (mErrorBackground >= 0)
            this.setBackgroundResource(mErrorBackground);

        if (mEditTextListener != null)
            mEditTextListener.onError();

        setError(errorMsg.isEmpty() ? getContext().getString(R.string.apptextinputlayout_invalid_field, getHint().toString()) : errorMsg);
    }

    private void setEmptyErrorText() {
        if (mErrorBackground >= 0)
            this.setBackgroundResource(mErrorBackground);

        if (mEditTextListener != null)
            mEditTextListener.onError();

        setError(mEmptyErrorText.isEmpty() ? getContext().getString(R.string.apptextinputlayout_empty_field, getHint().toString()) : mEmptyErrorText);
    }

    private void setRegexErrorText() {
        if (mErrorBackground >= 0)
            this.setBackgroundResource(R.drawable.bg_rounded_corners_green);

        if (mEditTextListener != null)
            mEditTextListener.onError();

        setError(mRegexErrorText.isEmpty() ? getContext().getString(R.string.apptextinputlayout_regex_field, getHint().toString()) : mRegexErrorText);
    }

    private void setInvalidErrorText() {
        if (mErrorBackground >= 0)
            this.setBackgroundResource(mErrorBackground);

        if (mEditTextListener != null)
            mEditTextListener.onError();

        setError(mInvalidErrorText.isEmpty() ? getContext().getString(R.string.apptextinputlayout_invalid_field, getHint().toString()) : mInvalidErrorText);
    }

    private void showMinLengthErrorText() {
        if (mErrorBackground >= 0)
            this.setBackgroundResource(mErrorBackground);

        if (mEditTextListener != null)
            mEditTextListener.onError();

        setError(mInvalidErrorText.isEmpty() ? getContext().getString(R.string.apptextinputlaoyut_password_field, getHint().toString(), mMinLength) : mInvalidErrorText);
    }

    private boolean isMatchingValid() {
        try {
            return mMatchingReference.getText().toString().equals(mEditText.getText().toString());
        } catch (NullPointerException e) {
            return true;
        }
    }

    public void setMatchingReference(EditText matchingReference) {
        mMatchingReference = matchingReference;
    }

    public void setText(String text) {
        if (getEditText() != null) {
            getEditText().setText(text);
        }
    }

    public String getText() {
        return getEditText().getText().toString();
    }

    public String getUnmaskedText() {
        return TextMask.unmask(getText());
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        mEditText.setOnClickListener(l);
        mEditText.setFocusable(false);
        mEditText.setClickable(true);
        setClickable(true);
        setFocusable(true);
        mEditText.setEnabled(true);
        super.setOnClickListener(l);
    }

}
