package com.example.ecoroad

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isEmpty
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {


    // Email e senha para acesso:
    val emailAcesso = "ecoroadacesso@gmail.com"
    val senhaAcesso = "ecoroadacesso123"


    // Definindo tipos de elementos:

    // caixas de entradas
    lateinit var caixaEntradaEmail : TextInputLayout
    lateinit var caixaEntradaSenha : TextInputLayout
    lateinit var  editEntradaEmail : TextInputEditText
    lateinit var  editEntradaSenha : TextInputEditText

    // texto clicaveis:
    lateinit var textoClicavelCriarConta : TextView
    lateinit var textoClicavelEsqueciSenha : TextView

    // botões
    lateinit var botaoEntrar : AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        window.navigationBarColor = getColor(R.color.dark_gray)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightNavigationBars = false

        inicializarComponentes()
        botaoEntrar.setOnClickListener { abrirActivity() }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    // Funcão de inicializar os componentes
    private fun inicializarComponentes () {

        // caixas entrada e edit
        caixaEntradaEmail = findViewById(R.id.caixaEntradaEmail)
        caixaEntradaSenha = findViewById(R.id.caixaEntradaSenha)
        editEntradaEmail = findViewById(R.id.editEntradaEmail)
        editEntradaSenha = findViewById(R.id.editEntradaSenha)

        // textos clicaveis
        textoClicavelCriarConta = findViewById(R.id.textoClicavelCriarConta)
        textoClicavelEsqueciSenha = findViewById(R.id.textoClicavelEsqueciSenha)

        // botões
        botaoEntrar = findViewById(R.id.botaoEntrar)

    }


    // Função de entrada para outra activity :
    private fun abrirActivity () {

        // definindo que o erro começa nulo
        caixaEntradaEmail.error = null
        caixaEntradaSenha.error = null

        // caso a caixa de entrada estiver vazia
        if ( caixaEntradaEmail.isEmpty() ){

            caixaEntradaEmail.error = "Digite o email para a verificação!"

        }

        // caso a caixa de entrada estiver vazia
        else if ( caixaEntradaSenha.isEmpty() ){

            caixaEntradaSenha.error = "Digite a senha para a verificação!"

        }

            // Se o email e senha estiverem corretos :
            if ( editEntradaEmail.text.toString() == emailAcesso || editEntradaSenha.text.toString() == senhaAcesso ) {

                var caminhoEscolhaCombustivel = Intent( this,EscolhaCombustivelActivity :: class.java )
                startActivity(caminhoEscolhaCombustivel)
                finish()

            }

            // Se o email e senha estiverem errados
            else {

                AlertDialog.Builder(this)
                    .setTitle("Erro no login")
                    .setMessage("Por favor, preencha todos os campos obrigatórios para continuar. Verifique se você digitou corretamente o e-mail e a senha.")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()

            }
    }
}