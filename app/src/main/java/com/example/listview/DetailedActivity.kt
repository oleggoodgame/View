package com.example.listview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.listview.R
// AppCompatActivity() - це спеціальний тип активності в Android, який дозволяє використовувати сучасні можливості платформи і забезпечує сумісність з різними версіями Android.
class DetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delayed)

        val detailName: TextView = findViewById(R.id.detailName)
        val detailTime: TextView = findViewById(R.id.detailTime)
        val detailDesc: TextView = findViewById(R.id.detailDesc)
        val detailIngredients: TextView = findViewById(R.id.detailIngredients)
        val detailImage: ImageView = findViewById(R.id.detailImage)

        val intent = this.intent // Intent: У контексті Android інтент є об'єктом, який представляє намір виконати певну дію. Він може використовуватись для запуску інших активностей, служб, передачі даних між компонентами тощо.
        // this.intent: Цей вираз отримує інтент, який був використаний для запуску поточної активності DetailedActivity. Інтент містить додаткові дані, які були передані з попередньої активності чи компонента. Ці дані можуть бути передані за допомогою методу putExtra() з іншої активності.
        if (intent != null) {
            val name = intent.getStringExtra("name")
            val time = intent.getStringExtra("time")
            val ingredients = intent.getIntExtra("ingredients", R.string.maggiIngredients)
            val desc = intent.getIntExtra("desc", R.string.maggieDesc)
            val image = intent.getIntExtra("image", R.drawable.maggi)

            detailName.text = name
            detailTime.text = time
            detailDesc.setText(desc)
            detailIngredients.setText(ingredients)
            detailImage.setImageResource(image)
        }
    }
}
