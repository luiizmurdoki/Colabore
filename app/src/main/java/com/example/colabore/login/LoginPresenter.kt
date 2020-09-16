package com.example.colabore.login

class LoginPresenter : LoginContract.Presenter {

    private var view: LoginContract.View? = null


    override fun attachView(mvpView: LoginContract.View?) {
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

}