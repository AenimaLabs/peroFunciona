package jorge.andaur.rios.superhero3.network

import jorge.andaur.rios.superhero3.model.Superhero
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroApiService {

    // Define una función suspendida para hacer una llamada GET a la API
    @GET("api.php/1117243916369294/{id}")

    // Anota la función con @GET y especifica la ruta de la API. La ruta incluye un parámetro de ruta {id}
    // que será reemplazado por el valor del argumento id cuando se llame a la función.
    suspend fun getSuperhero(@Path("id") id: String // Anota el parámetro id con @Path para indicar que su valor debe sustituir {id} en la ruta
    ): Response<Superhero>// La función retorna un objeto Response que contiene un objeto Superhero
}