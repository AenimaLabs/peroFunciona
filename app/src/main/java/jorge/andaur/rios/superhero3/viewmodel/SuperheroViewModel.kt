package jorge.andaur.rios.superhero3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jorge.andaur.rios.superhero3.model.Superhero
import jorge.andaur.rios.superhero3.repo.SuperheroRepository
import kotlinx.coroutines.launch

// ViewModel para gestionar la lógica de negocio y los datos relacionados con los superhéroes
class SuperheroViewModel(private val repository: SuperheroRepository) : ViewModel() {

    // LiveData privada que almacena la lista de superhéroes
    private val _superheroes = MutableLiveData<List<Superhero>>()
    // LiveData pública que permite la observación de la lista de superhéroes desde el exterior
    val superheroes: LiveData<List<Superhero>> get() = _superheroes

    // Función para obtener todos los superhéroes desde el repositorio
    fun fetchAllSuperheroes() {
        // Inicia una nueva coroutine en el alcance del ViewModel
        viewModelScope.launch {
            try {
                // Llama al repositorio para obtener la lista de superhéroes
                val result = repository.getAllSuperheroes()
                // Actualiza el valor de _superheroes con la lista obtenida
                _superheroes.postValue(result)
            } catch (e: Exception) {
                // Maneja cualquier excepción que ocurra durante la llamada al repositorio
            }
        }
    }
}