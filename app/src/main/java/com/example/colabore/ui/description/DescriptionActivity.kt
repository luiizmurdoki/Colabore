package com.example.colabore.ui.description

import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.colabore.R
import com.example.colabore.model.PersistUserInformation.cpf
import com.example.colabore.ui.base.BaseActivity
import com.example.colabore.ui.dialogmessage.MessageBottomDialog
import com.example.colabore.ui.main.MainContract
import com.example.colabore.ui.main.MainPresenter
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_ngo_description.*
import kotlinx.android.synthetic.main.item_card.view.*

class DescriptionActivity :  BaseActivity(), DescriptionContract.View {
    private lateinit var auth: FirebaseAuth
    private val url = "https://scontent.fcgh37-1.fna.fbcdn.net/v/t1.0-9/25498233_304732076683737_2571184881809077165_n.png?_nc_cat=109&_nc_sid=85a577&_nc_ohc=bT8dJjmbih8AX8wkFrv&_nc_ht=scontent.fcgh37-1.fna&oh=c5a1a6b488dcb53a61dfbb0a52be54fa&oe=5F7EE42F"
    private val title = "Centro de Inclusão Digital"
    private val desc = "Objetivo Promover a Inclusão Digital de Jovens no Mercado de Informática, conscientização da Preservação do Meio Ambiente e Cidadania, através da Reciclagem de Equipamentos de Informática.  O projeto esta dividido em três fases, cidadania, meio ambiente e técnicas de informática:  Fase 1 – Cidadania Nesta primeira fase o aluno obterá conhecimentos sobre cidadania, direitos e deveres do cidadão, Ética profissional, governabilidade, viver em comunidade, com duração de 02horas/aula.  Fase 2 – Meio ambiente Nesta fase o aluno aprendera os conceitos de Preservação do Meio Ambiente, Causas e Efeitos à Natureza, Legislação e Reciclagem de Equipamentos de Informática, com duração de 02 horas.  Fase 3 – Curso Técnico em Informática Nesta fase o aluno terá aulas praticas com acompanhamento apostilado de Noções básicas de Informática, Montagem e Manutenção de Microcomputadores."

    private val presenter: DescriptionContract.Presenter by lazy {
        DescriptionPresenter().apply { attachView(this@DescriptionActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_description)
        auth = FirebaseAuth.getInstance()
        displayDescription()
        FirebaseApp.initializeApp(this)

    }


    private fun displayDescription(){
        Glide.with(this).load(url).apply(RequestOptions().error(R.drawable.ic_default_empty).placeholder(R.drawable.ic_default_empty)).into(imageNgoIv)
        ngoName.text = title
        drescripitionTv.text = desc
    }

    override fun displayError(msg: String?){
        MessageBottomDialog(this, getString(R.string.placeholder_error_title),
            if (msg.isNullOrEmpty()) getString(R.string.login_error) else msg,
            getString(R.string.action_ok), {}, null, {}, isCancelable = true
        ).show()
    }
}
