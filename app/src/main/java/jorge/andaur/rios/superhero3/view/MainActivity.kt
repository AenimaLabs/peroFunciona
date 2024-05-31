package jorge.andaur.rios.superhero3.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import jorge.andaur.rios.superhero3.R
import jorge.andaur.rios.superhero3.databinding.ActivityMainBinding
import jorge.andaur.rios.superhero3.network.SuperheroApiService
import jorge.andaur.rios.superhero3.repo.SuperheroRepository
import jorge.andaur.rios.superhero3.view.adapter.SuperheroAdapter
import jorge.andaur.rios.superhero3.viewmodel.SuperheroViewModel
import jorge.andaur.rios.superhero3.viewmodel.SuperheroViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Actividad principal que muestra una lista de superhéroes usando un RecyclerView
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding // Binding para acceder a las vistas en el layout
    private val viewModel: SuperheroViewModel by viewModels {// Inicializa el ViewModel usando una factory
        SuperheroViewModelFactory(
            SuperheroRepository(

                // Configura Retrofit para hacer llamadas a la API de superhéroes
                //esto se podría hacer como función en un object (es lo ideal)
                //pero de esta manera ... también funciona...
                Retrofit.Builder()
                    .baseUrl("https://www.superheroapi.com/")// URL base de la API
                    .addConverterFactory(GsonConverterFactory.create())// Conversor para JSON
                    .build()
                    .create(SuperheroApiService::class.java)// Crea una implementación de la interfaz de la API
            )
        )
    }
    private lateinit var adapter: SuperheroAdapter// Adaptador para el RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el layout usando View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura el RecyclerView
        setupRecyclerView()

        // Observa los cambios en el ViewModel
        observeViewModel()

        // Inicia la obtención de superhéroes
        viewModel.fetchAllSuperheroes()
    }

    private fun setupRecyclerView() {
        // Establece un LinearLayoutManager para el RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun observeViewModel() {
        // Observa el LiveData de superhéroes en el ViewModel
        viewModel.superheroes.observe(this, Observer { superheroes ->
            // Configura el adaptador del RecyclerView con la lista de superhéroes y un listener de clics
            adapter = SuperheroAdapter(superheroes) { superhero ->
                // Crea un Intent para iniciar SuperheroDetailActivity al hacer clic en un superhéroe
                val intent = Intent(this, SuperheroDetailActivity::class.java)
                // Pasa el objeto Superhero seleccionado a la nueva actividad
                intent.putExtra("superhero", superhero)
                // Inicia la actividad de detalles
                startActivity(intent)
                // Opcional: finalizar la actividad actual
//                finish()
            }
            // Establece el adaptador del RecyclerView
            binding.recyclerView.adapter = adapter
            // Registro de información de un superhéroe (opcional para depuración)
            Log.i("SuperHero", superheroes[1].toString())
        })
    }
}