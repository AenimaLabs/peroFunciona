package jorge.andaur.rios.superhero3.repo

import jorge.andaur.rios.superhero3.model.Superhero
import jorge.andaur.rios.superhero3.network.SuperheroApiService

class SuperheroRepository (private val apiService: SuperheroApiService) {


    suspend fun getAllSuperheroes(): List<Superhero> {
        val superheroes = mutableListOf<Superhero>()

        //la línea 15 no es la manera de hacerlo, trae todos los superheroes
        //sin embargo tarda mucho en cargarlos...
        //pero funciona...
        for (id in 1..731) { // Assuming there are 731 superheroes
            //una opción para corregir sería
            // pedir que baje los superheroes por lotes
            //para eso debemos paginar los datos y es algo a implementar

            try {
                // Llama a la API para obtener los datos del superhéroe con el ID actual
                val response = apiService.getSuperhero(id.toString())

                // Verifica si la respuesta de la API fue exitosa (código de respuesta 200-299)
                if (response.isSuccessful) {

                    // Obtiene el cuerpo de la respuesta (el objeto Superhero) y lo añade a la lista
                    response.body()?.let { superheroes.add(it) }
                }
            } catch (e: Exception) {
                // Maneja cualquier excepción que ocurra durante la llamada a la API.
                // Esto puede incluir problemas de red, errores de conversión, etc.
                // En este ejemplo, simplemente se ignora la excepción y continúa con el siguiente ID.
            }
            }

    // Retorna la lista de superhéroes obtenidos de la API
        return superheroes
    }


}