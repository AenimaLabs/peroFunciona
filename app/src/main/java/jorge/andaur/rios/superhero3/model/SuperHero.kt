package jorge.andaur.rios.superhero3.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//a esta api nos conectaremos
// https://www.superheroapi.com/api.php/1117243916369294/500

@Parcelize
data class Superhero(
    val id: String,
    val name: String,
    val powerstats: PowerStats,
    //por qué comento biography y los demás campos?
    //tarea para la casa...
//    val biography: Biography,
//    val appearance: Appearance,
//    val work: Work,
//    val connections: Connections,
    val image: Image
): Parcelable // Implementa Parcelable para permitir que los objetos Superhero se puedan pasar entre componentes Android

@Parcelize
data class PowerStats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
): Parcelable

@Parcelize
data class Biography(
    val fullName: String,
    val alterEgos: String,
    val aliases: List<String>,
    val placeOfBirth: String,
    val firstAppearance: String,
    val publisher: String,
    val alignment: String
): Parcelable
@Parcelize
data class Appearance(
    val gender: String,
    val race: String,
    val height: List<String>,
    val weight: List<String>,
    val eyeColor: String,
    val hairColor: String
): Parcelable
@Parcelize
data class Work(val occupation: String, val base: String): Parcelable
@Parcelize
data class Connections(val groupAffiliation: String, val relatives: String): Parcelable
@Parcelize
data class Image(val url: String): Parcelable


