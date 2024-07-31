package com.example.listview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.listview.databinding.OtheronemainBinding

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: OtheronemainBinding
    private lateinit var viewPagerItemArrayList: ArrayList<Plant>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OtheronemainBinding.inflate(layoutInflater) //  перетворити XML-розмітку у відповідні View-елементи.
        setContentView(binding.root)

        viewPagerItemArrayList = ArrayList()

        val images = intArrayOf(
            R.drawable.pancake, R.drawable.pasta, R.drawable.pizza, R.drawable.fries, R.drawable.burger
        )
        val headings = arrayOf("Baked", "Grilled", "Dessert", "Italian", "Shakes")

        for (i in images.indices) {
            val viewPagerItem = Plant(images[i], headings[i])
            viewPagerItemArrayList.add(viewPagerItem)
        }

        val vpAdapter = PlantAdapter(this@MainActivity3, viewPagerItemArrayList) { plant ->
            Toast.makeText(this, "Clicked on ${plant.title}", Toast.LENGTH_SHORT).show()
        }

        binding.viewpager.adapter = vpAdapter

        binding.viewpager.clipToPadding = false // Цей параметр контролює, чи потрібно обрізати вміст, що виходить за межі відступів (padding) ViewPager2. Якщо clipToPadding встановлений у false, то вміст ViewPager2 може виходити за межі відступів. Це корисно, якщо ви хочете, щоб ваші елементи на краях могли бути частково видимі навіть за межами відступів.
            //Наприклад: Коли ви прокручуєте ViewPager2 і хочете, щоб частини наступної або попередньої сторінки були видимі з боку, потрібно встановити clipToPadding у false.
        binding.viewpager.clipChildren = false
        //Наприклад: Якщо ви використовуєте ViewPager2 для відображення горизонтального списку, де сторінки частково видимі з боків, потрібно встановити clipChildren у false, щоб сторінки могли частково виходити за межі видимого вікна.
        binding.viewpager.offscreenPageLimit = 2 // Якщо у вас є кілька сторінок у ViewPager2 і ви хочете, щоб користувач міг швидко прокручувати між ними, зберігання додаткових сторінок у пам’яті допоможе уникнути затримок при завантаженні нових сторінок.
        binding.viewpager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER //  контролює, чи дозволяється прокручування за межі видимого вікна.
    }
}
