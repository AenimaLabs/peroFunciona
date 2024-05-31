package jorge.andaur.rios.superhero3.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jorge.andaur.rios.superhero3.databinding.ItemSuperheroBinding
import jorge.andaur.rios.superhero3.model.Superhero

// Adaptador para RecyclerView que maneja una lista de objetos Superhero
// y gestiona los eventos de clic en los elementos
class SuperheroAdapter(
    private val superheroList: List<Superhero>, // Lista de superhéroes a mostrar en el RecyclerView
    private val onItemClick: (Superhero) -> Unit// Función de callback que se ejecuta cuando se hace clic en un superhéroe
) : RecyclerView.Adapter<SuperheroAdapter.SuperheroViewHolder>() {

    // ViewHolder que contiene el binding para un elemento de superhéroe
    class SuperheroViewHolder(val binding: ItemSuperheroBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Infla el layout para cada elemento del RecyclerView y crea un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val binding =
            ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuperheroViewHolder(binding)
    }

    // Asocia los datos de un superhéroe con el ViewHolder correspondiente
    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        val superhero = superheroList[position] // Obtiene el superhéroe en la posición actual
        holder.binding.superheroName.text =
            superhero.name// Establece el nombre del superhéroe en el TextView correspondiente

        // Carga la imagen del superhéroe usando Glide y la establece en el ImageView correspondiente
        Glide.with(holder.binding.root.context).load(superhero.image.url)
            .into(holder.binding.superheroImage)

        // Agregar un listener para cuando se selecciona un superhéroe
        holder.binding.root.setOnClickListener {
            onItemClick(superhero)// Llama a la función de callback pasando el superhéroe seleccionado
        }
    }

    // Devuelve el número total de elementos en la lista de superhéroes
    override fun getItemCount(): Int {
        return superheroList.size
    }
}
