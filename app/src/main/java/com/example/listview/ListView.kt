package com.example.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listview.databinding.SomethingBinding

// Клас для адаптера, тепер це ListAdapter з DiffUtil
class PlantAdapter_ListAdapter(
    private val context: Context,
    private val onItemClick: (Plant) -> Unit
) : ListAdapter<Plant, PlantAdapter_ListAdapter.PlantHolder>(PlantDiffCallback()) {

    // ViewHolder як і раніше зберігає посилання на Views та прив'язує дані
    class PlantHolder(item: View, val context: Context, val onItemClick: (Plant) -> Unit) : RecyclerView.ViewHolder(item) {
        val binding = SomethingBinding.bind(item)

        fun bind(plant: Plant) = with(binding) {
            im.setImageResource(plant.imageId)
            tvTitle.text = plant.title
            itemView.setOnClickListener {
                onItemClick(plant)
                Toast.makeText(context, "Clicked on ${plant.title}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Створюємо ViewHolder з макету
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.something, parent, false)
        return PlantHolder(view, context, onItemClick)
    }

    // Прив'язуємо дані до ViewHolder
    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        val plant = getItem(position)
        holder.bind(plant)
    }
}

// Клас DiffUtil для ефективного порівняння елементів списку
class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
    // Перевіряємо, чи є два елементи ідентичними
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.imageId == newItem.imageId
    }

    // Перевіряємо, чи однаковий вміст елементів
    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
}
