package com.example.ecoroad

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.graphics.Typeface

class EntradaResultadosActivity : AppCompatActivity() {

    // Definindo elementos:
    lateinit var caixaEntradaConsumo    : TextInputLayout
    lateinit var caixaEntradaDistancia  : TextInputLayout
    lateinit var caixaEntradaPreco      : TextInputLayout
    lateinit var editEntradaConsumo     : TextInputEditText
    lateinit var editEntradaDistancia   : TextInputEditText
    lateinit var editEntradaPreco       : TextInputEditText

    lateinit var textoResultadoValor       : TextView
    lateinit var textoResultadoCombustivel : TextView
    lateinit var textoTipoCombustivel      : TextView


    lateinit var botaoTipoCombustivel : AppCompatButton
    lateinit var botaoCalcular        : AppCompatButton

    // Função onCreate:
    override fun onCreate( savedInstanceState: Bundle? ) {

        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_entrada_resultados )
        WindowCompat.setDecorFitsSystemWindows( window, false )
        window.navigationBarColor = getColor( R.color.dark_gray )
        WindowInsetsControllerCompat( window, window.decorView ).isAppearanceLightNavigationBars = false

        // Chamada da função que vincula os elementos :
        inicializarComponentes()

        // Recenbendo informações do intent :
        val escolhaCombustivel = intent.extras

        // Checagem :
        if ( escolhaCombustivel != null ) {

            val combustivel = escolhaCombustivel.getString( "Combustivel" )

            // Mostrar o tipo do combustível com base na escolha :
            when ( combustivel ) {

                "Gasolina" -> textoTipoCombustivel.text = getString(R.string.entrada_tipo_combustivel_gasolina)
                "Diesel"   -> textoTipoCombustivel.text = getString(R.string.entrada_tipo_combustivel_diesel)
                "Alcool"   -> textoTipoCombustivel.text = getString(R.string.entrada_tipo_combustivel_alcool)

            }
        }

        // Botao que volta a escolha do combustível :
        botaoTipoCombustivel.setOnClickListener { finish() }

        // Botao que calcula os valores :
        botaoCalcular.setOnClickListener {

            // Armazenando elementos dentros das variaveis :
            var consumoInicial   = editEntradaConsumo.text.toString().toFloat()
            var distanciaInicial = editEntradaDistancia.text.toString().toFloat()
            var precoInicial     = editEntradaPreco.text.toString().toFloat()

            calcularExibir( consumoInicial , distanciaInicial , precoInicial )

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Função que inicializa os elementos :
    private fun inicializarComponentes() {

        // Caixas entrada:
        caixaEntradaConsumo    = findViewById(R.id.caixaEntradaConsumo)
        caixaEntradaDistancia  = findViewById(R.id.caixaEntradaDistancia)
        caixaEntradaPreco      = findViewById(R.id.caixaEntradaPreco)

        // Caixas de edição :
        editEntradaConsumo    = findViewById(R.id.editEntradaConsumo)
        editEntradaDistancia  = findViewById(R.id.editEntradaDistancia)
        editEntradaPreco      = findViewById(R.id.editEntradaPreco)

        // Textos manipulaveis :
        textoResultadoValor        = findViewById(R.id.textoResultadoValor)
        textoResultadoCombustivel  = findViewById(R.id.textoResultadoCombustivel)
        textoTipoCombustivel       = findViewById(R.id.textoTipoCombustivel)

        // Botões :
        botaoTipoCombustivel  = findViewById(R.id.botaoTipoCombustivel)
        botaoCalcular         = findViewById(R.id.botaoCalcular)

    }

    // Função que verfica se as caixas estão vazias :
    private fun checagemCaixasEntradas () : Boolean {

        // Armazenando elementos dentro das variaveis :
        var checagemConsumo    = editEntradaConsumo.text.toString()
        var checagemDistancia  = editEntradaDistancia.text.toString()
        var checagemPreco      = editEntradaPreco.text.toString()

        caixaEntradaConsumo.error    = null
        caixaEntradaDistancia.error  = null
        caixaEntradaPreco.error      = null

        if ( checagemConsumo.isEmpty() ) {

            caixaEntradaConsumo.error = "Digite o consumo por litro do veículo!"
            return false

        }

        else if ( checagemDistancia.isEmpty() ) {

            caixaEntradaDistancia.error = "Digite a distância do trajeto!"
            return false

        }

        else if ( checagemPreco.isEmpty() ) {

            caixaEntradaPreco.error = "Digite o preço do combustível!"
            return false

        }

        // Se a checagem foi aprovada retorna um true :
        return true

    }

    // Função que calcula e exibe os resultados :
    private fun calcularExibir ( consumoP : Float , distanciaP : Float , precoP : Float ) {

        // Se a checagem for aprovada :
        if ( checagemCaixasEntradas() ) {

            // Armazenados os parametros dentro das variaveis :
            var consumo = consumoP
            var distancia = distanciaP
            var preco = precoP

            // Calcula e armazena os resultados :
            var combustivelNecessario = distancia / consumo
            var valorNecessario = combustivelNecessario * preco

            // Exibe os resultados na tela :
            textoResultadoValor.text = "%.2f".format(valorNecessario)
            textoResultadoCombustivel.text = "%.2f".format(combustivelNecessario)

        }
    }
}