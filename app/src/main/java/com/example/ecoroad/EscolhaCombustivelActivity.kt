package com.example.ecoroad

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class EscolhaCombustivelActivity : AppCompatActivity() {

    lateinit var botaoGasolina : AppCompatButton
    lateinit var botaoDiesel   : AppCompatButton
    lateinit var botaoAlccol   : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escolha_combustivel)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.navigationBarColor = getColor(R.color.dark_gray)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightNavigationBars = false

        inicializarComponentes()

        botaoGasolina.setOnClickListener  { abrirEntradaComGasolina() }
        botaoDiesel.setOnClickListener    { abrirEntradaComDiesel() }
        botaoAlccol.setOnClickListener { abrirEntradaComAlcool() }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private fun inicializarComponentes() {

        botaoGasolina = findViewById(R.id.botaoGasolina)
        botaoDiesel   = findViewById(R.id.botaoDiesel)
        botaoAlccol   = findViewById(R.id.botaoAlcool)

    }

    private fun abrirEntradaComGasolina()
    {
        var caminhoEntradaGasolina = Intent (this, EntradaResultadosActivity :: class.java )
        caminhoEntradaGasolina.putExtra("Combustivel","Gasolina")
        startActivity(caminhoEntradaGasolina)
    }

    // Função que abre a activity com escolha diesel :
    private fun abrirEntradaComDiesel()
    {
        var caminhoEntradaDiesel = Intent(this,EntradaResultadosActivity :: class.java)
        caminhoEntradaDiesel.putExtra("Combustivel","Diesel")
        startActivity(caminhoEntradaDiesel)
    }

    // Função que abre a activity com escolha alcool :
    private fun abrirEntradaComAlcool()
    {
        var caminhoEntradaAlcool = Intent(this,EntradaResultadosActivity :: class.java)
        caminhoEntradaAlcool.putExtra("Combustivel","Alcool")
        startActivity(caminhoEntradaAlcool)

    }

}