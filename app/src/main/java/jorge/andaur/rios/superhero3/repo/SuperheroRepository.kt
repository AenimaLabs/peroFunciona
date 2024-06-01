package jorge.andaur.rios.superhero3.repo

import jorge.andaur.rios.superhero3.model.Superhero
import jorge.andaur.rios.superhero3.network.SuperheroApiService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class SuperheroRepository (private val apiService: SuperheroApiService) {

    // Función suspendida que obtiene todos los superhéroes
    suspend fun getAllSuperheroes(): List<Superhero> = coroutineScope {
        val superheroes = mutableListOf<Superhero>()// Lista mutable para almacenar los superhéroes
        val batchSize = 5 // Tamaño del lote para concurrencia(número de superhéroes por lote)
        val totalSuperheroes = 731// Número total de superhéroes a obtener

        // Lista de coroutines diferidas que se ejecutarán en paralelo
        val deferredResults = (1..totalSuperheroes step batchSize).map { startId ->
            async {
                // Mapeo de IDs de superhéroes dentro del rango del lote actual
                (startId until (startId + batchSize)).mapNotNull { id ->
                    try {
                        // Llamada a la API para obtener el superhéroe con el ID actual
                        val response = apiService.getSuperhero(id.toString())
                        if (response.isSuccessful) {
                            response.body()// Devuelve el cuerpo de la respuesta si es exitosa
                        } else {
                            null// Devuelve null si la respuesta no es exitosa
                        }
                    } catch (e: Exception) {
                        null // Maneja cualquier excepción devolviendo null
                    }
                }
            }
        }
        // Espera a que todas las coroutines completen y obtén sus resultados
        val results = deferredResults.awaitAll()
        // Añade todos los resultados obtenidos a la lista de superhéroes
        results.forEach { superheroes.addAll(it) }

        // Retorna la lista de superhéroes obtenidos de la API
        return@coroutineScope superheroes
    }


//    suspend fun getAllSuperheroes(): List<Superhero> {
//        val superheroes = mutableListOf<Superhero>()
//
//        //la línea 15 no es la manera de hacerlo, trae todos los superheroes
//        //sin embargo tarda mucho en cargarlos...
//        //pero funciona...
//        for (id in 1..731) { // Assuming there are 731 superheroes
//            //una opción para corregir sería
//            // pedir que baje los superheroes por lotes
//            //para eso debemos paginar los datos y es algo a implementar
//
//            try {
//                // Llama a la API para obtener los datos del superhéroe con el ID actual
//                val response = apiService.getSuperhero(id.toString())
//
//                // Verifica si la respuesta de la API fue exitosa (código de respuesta 200-299)
//                if (response.isSuccessful) {
//
//                    // Obtiene el cuerpo de la respuesta (el objeto Superhero) y lo añade a la lista
//                    response.body()?.let { superheroes.add(it) }
//                }
//            } catch (e: Exception) {
//                // Maneja cualquier excepción que ocurra durante la llamada a la API.
//                // Esto puede incluir problemas de red, errores de conversión, etc.
//                // En este ejemplo, simplemente se ignora la excepción y continúa con el siguiente ID.
//            }
//            }
//
//    // Retorna la lista de superhéroes obtenidos de la API
//        return superheroes
//    }


}