package com.example.listview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listview.databinding.OthermainBinding

//class MainActivity2 : AppCompatActivity() {
//    private lateinit var binding: OthermainBinding
//    private lateinit var adapter: PlantAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = OthermainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        init() // Викликаємо init() після встановлення binding
//    }
//
//    private fun init() {
//        binding.apply {
//            adapter = PlantAdapter(this@MainActivity2, getPlantList()) { plant ->
//                val intent = Intent(this@MainActivity2, DetailedActivity::class.java).apply {
//                    putExtra("name", plant.title)
//                    // Додайте інші необхідні дані в intent
//                }
//                startActivity(intent)
//            }
//            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity2)
//            recyclerView.adapter = adapter
//        }
//    }
//
//    private fun getPlantList(): ArrayList<Plant> {
//        val plantList = ArrayList<Plant>()
//        plantList.add(Plant(R.drawable.cake, "One"))
//        plantList.add(Plant(R.drawable.pancake, "Two"))
//        plantList.add(Plant(R.drawable.burger, "Three"))
//        return plantList
//    }
//}
class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: OthermainBinding
    private lateinit var adapter: PlantAdapter_ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OthermainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init() // Викликаємо init() після встановлення binding
    }

    private fun init() {
        binding.apply {
            adapter = PlantAdapter_ListAdapter(this@MainActivity2) { plant ->
                val intent = Intent(this@MainActivity2, DetailedActivity::class.java).apply {
                    putExtra("name", plant.title)
                    // Додайте інші необхідні дані в intent
                }
                startActivity(intent)
            }
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity2)
            recyclerView.adapter = adapter

            // Оновлюємо список даними
            adapter.submitList(getPlantList())
            //к працює submitList()?
            //Новий список передається в адаптер: Коли ти викликаєш submitList(getPlantList()), ти передаєш новий список (у цьому випадку список рослин) в адаптер.
            //
            //Порівняння старого і нового списків: ListAdapter використовує DiffUtil.ItemCallback (у твоєму випадку це клас PlantDiffCallback), щоб визначити, які елементи списку були змінені, додані або видалені.
            //
            //Оптимізоване оновлення елементів: Після порівняння старого і нового списків, адаптер автоматично оновлює тільки ті елементи, які змінилися. Завдяки цьому, не потрібно перевантажувати весь список і оновлювати кожен елемент, що робить процес значно ефективнішим.
        }
    }

    private fun getPlantList(): List<Plant> {
        return listOf(
            Plant(R.drawable.cake, "One"),
            Plant(R.drawable.pancake, "Two"),
            Plant(R.drawable.burger, "Three")
        )
    }
}