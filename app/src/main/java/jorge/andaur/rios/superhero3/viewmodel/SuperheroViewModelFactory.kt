package jorge.andaur.rios.superhero3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jorge.andaur.rios.superhero3.repo.SuperheroRepository

class SuperheroViewModelFactory(private val repository: SuperheroRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuperheroViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SuperheroViewModel(repository) as T // Crea una instancia de SuperheroViewModel
        }
        throw IllegalArgumentException("Unknown ViewModel class") // Lanza una excepción si la clase del ViewModel es desconocida
    }
}
