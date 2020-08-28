package com.example.colabore.login

import android.content.Intent
import android.os.Bundle
import com.example.colabore.main.MainActivity
import com.example.colabore.R
import com.example.colabore.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity :  BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        setListeners()
    }

    private fun setListeners(){
        loginSigninBtn.setOnClickListener { openHome() }
    }

    private fun openHome(){
        val intent = Intent(this, MainActivity::class.java).apply{}
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
/*    private fun setUnderlineTexts() {
        loginForgotPasswordTv.paintFlags = loginForgotPasswordTv.paintFlags or UNDERLINE_TEXT_FLAG
        loginLogAnotherAccountBtn.paintFlags = loginForgotPasswordTv.paintFlags or UNDERLINE_TEXT_FLAG
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLocationUpdates()
        presenter.detachView()
    }

    override fun updateUI(state: LoginState) {
        BBCAnalytics.logNewScreen(this, if (state is LoginState.CheckCpf) BBCScreenName.LOGIN_CPF_SCREEN
        else BBCScreenName.LOGIN_AUTH_SCREEN)

        loginRegisterBtn.setVisible(state is LoginState.CheckCpf)
        loginCpfEt.setEditTextEnabled(state is LoginState.CheckCpf)
        loginPasswordEt.setVisible(state is LoginState.EnterPassword)
        loginForgotPasswordTv.setVisible(state is LoginState.EnterPassword)
        loginLogAnotherAccountBtn.setVisible(state is LoginState.EnterPassword)
        loginSigninBtn.setText(getString(state.btnTextRes))
        loginMessageTv.text = getString(state.loginMessageRes, if (state is LoginState.EnterPassword) state.username else "")
        loginSigninBtn.setOnClickListener { setLoginBtnClick(state) }
        if (state is LoginState.EnterPassword) {
            loginCpfEt.setText(state.cpf)
            if (state.isFingerprintAuthEnable) enablePasswordField(false)
        }
    }

    private fun setLoginBtnClick(state: LoginState) {
        if (state is LoginState.EnterPassword) {
            if (allFieldsValid()) {
                //presenter.onClickSignIn(loginCpfEt.text, loginPasswordEt.text)
                presenter.checkCpf(loginCpfEt.text, loginPasswordEt.text)
                BBCAnalytics.logEvent(BBCEvent.LOGIN, BBCLoginParam.LOGIN_METHOD_PASSWORD)
            }
        } else {
            loginCpfEt.validate()
            if (loginCpfEt.isFieldValid()) {
//                presenter.checkCpf(loginCpfEt.text)
                updateUI(LoginState.EnterPassword(cpf = loginCpfEt.text , username = ""))
                BBCAnalytics.logEvent(BBCEvent.CHECK_CPF)
            }
        }
    }

    private fun setListeners() {
        loginForgotPasswordTv.setOnClickListener { presenter.onClickForgotPassword() }
        loginLogAnotherAccountBtn.setOnClickListener { presenter.onAnotherAccountClick() }
        loginRegisterBtn.setOnClickListener { presenter.onClickSignUp() }
        loginPasswordEt.setOnClickListener { presenter.openFingerprintDialog() }
    }

    private fun checkExtras() {
        if (intent.getBooleanExtra(Constants.EXTRA_EXPIRED, false)) {
            intent.putExtra(Constants.EXTRA_EXPIRED, false)
            intent.removeExtra(Constants.EXTRA_EXPIRED)
            displayExpiredSessionDialog()
        } else presenter.onCreateActivity()
    }

    private fun displayExpiredSessionDialog() {
        MessageBottomDialog(
                this,
                getString(R.string.generic_dialog_error_title),
                getString(R.string.network_error_session),
                getString(R.string.ok),
                positiveAction = { presenter.onCreateActivity() },
                isCancelable = false
        ).show()
    }

    private fun allFieldsValid(): Boolean {
        loginCpfEt.validate()
        loginPasswordEt.validate()
        return loginCpfEt.isFieldValid() && loginPasswordEt.isFieldValid()
    }

    override fun displayError(msg: String?) {
        MessageBottomDialog(this, getString(R.string.generic_dialog_error_title), msg
                ?: getString(R.string.placeholder_error_label), getString(R.string.action_ok), positiveAction = null, isCancelable = true).show()
    }

    override fun displayError(res: Int) {
        MessageBottomDialog(this, getString(R.string.generic_dialog_error_title), getString(res), getString(R.string.action_ok), positiveAction = null, isCancelable = true).show()
    }

    override fun displayLoading(loading: Boolean) {
        loginSigninBtn.setLoading(loading)
    }

    override fun restartLogin() {
        BBCAnalytics.logEvent(BBCEvent.ENTER_ANOTHER_ACCOUNT)
        startActivitySlideTransition(createLoginIntent(true))
    }

    override fun showPasswordField(show: Boolean) {
        loginPasswordEt.setVisible(show)
    }

    override fun openHome() {
        startActivitySlideTransition(createHomeIntent())
    }

    override fun openForgotPassword() {
        BBCAnalytics.logEvent(BBCEvent.OPEN_FORGOT_PASSWORD)
        startActivitySlideTransition(createForgotPasswordIntent())
    }

    override fun openResetPassword() {
        startActivitySlideTransition(createResetPasswordIntent())
    }

    override fun openSignUp(user: SignUpRequest?) {
        BBCAnalytics.logEvent(BBCEvent.OPEN_SIGN_UP)
        startActivitySlideTransition(createSignUpActivityIntent(user))
    }

    override fun openChangeCardPasswordNoName() {
        startActivitySlideTransition(createChangePasswordCardIntent(isForgotPassword = true, cardId = null))
    }

    override fun displaySmsDialog() {
        createSmsDialog({ presenter.onCodeValid() })
    }

    override fun displaySmsDialogDevice() {
        createSmsDialog({ presenter.onCodeValidDevice() })
    }


    private fun enablePasswordField(enable: Boolean) {
        loginPasswordEt.getEditText().run {
            if (enable) {
                keyListener = EditText(this@LoginActivity).keyListener
                inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
                transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                keyListener = null
                movementMethod = null
            }
        }
    }

    override fun removeCache() {
        cacheDir.deleteRecursively()
        externalCacheDir?.deleteRecursively()
    }

    override fun openSendDocuments(document: String?) {
        startActivitySlideTransition(createSendDocumentIntent(document))
    }*/
}

