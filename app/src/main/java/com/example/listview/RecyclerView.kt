package com.example.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.listview.databinding.SomethingBinding

class PlantAdapter(
    private val context: Context, //private val context: Context: Це параметр, який передає контекст з активності або фрагменту до адаптера. Контекст використовується для отримання ресурсів, створення LayoutInflater і показу Toast повідомлень.
    private val plantList: ArrayList<Plant>,
    private val onItemClick: (Plant) -> Unit
) : RecyclerView.Adapter<PlantAdapter.PlantHolder>() {
    // RecyclerView.ViewHolder є базовим класом для створення нових типів ViewHolder у RecyclerView. Його основна мета - зберігати посилання на Views, які відображають дані одного елемента списку. Це дозволяє уникнути повторного пошуку Views за допомогою findViewById, що робить прокручування списку ефективнішим
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.something, parent, false) // Цей рядок створює новий View з XML-ресурсу R.layout.something. parent.context забезпечує доступ до контексту, а inflate створює новий екземпляр View.
        return PlantHolder(view, context, onItemClick)
    }

    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        holder.bind(plantList[position])
    }

    override fun getItemCount(): Int {
        return plantList.size
    }
}
