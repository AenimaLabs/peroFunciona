package jorge.andaur.rios.superhero3.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import jorge.andaur.rios.superhero3.R
import jorge.andaur.rios.superhero3.databinding.ActivitySuperheroDetailBinding
import jorge.andaur.rios.superhero3.model.Superhero

class SuperheroDetailActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySuperheroDetailBinding
    private lateinit var superhero: Superhero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperheroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el superhéroe seleccionado desde la intención
        superhero = intent.getParcelableExtra("superhero")!!

        // Mostrar la información del superhéroe
        binding.superheroName.text = superhero.name
        Glide.with(this).load(superhero.image.url).into(binding.superheroImage)
        binding.powerStats.text = """
            Inteligencia: ${superhero.powerstats.intelligence}
            Fuerza: ${superhero.powerstats.strength}
            Velocidad: ${superhero.powerstats.speed}
            Durabilidad: ${superhero.powerstats.durability}
            Poder: ${superhero.powerstats.power}
            Combate: ${superhero.powerstats.combat}
        """.trimIndent()
    }
}