package com.example.listview

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//class gridView : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.other)
//
//        val gridView: GridView = findViewById(R.id.gridView)
//
//        val buttonLabels = listOf("Button 1", "Button 2", "Button 3", "Button 4", "Button 5", "Button 6")
//
//        // Адаптер для створення кнопок у GridView
//        val adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, buttonLabels) {
//            override fun getView(position: Int, convertView: View?, parent: ViewGroup): Vie
//
//
//
//
//             {
//                val button = Button(this)
//                button.text = getItem(position)
//                button.setOnClickListener {
//                    Toast.makeText(this, "${button.text} clicked", Toast.LENGTH_SHORT).show()
//                }
//                return button
//            }
//        }
//
//        gridView.adapter = adapter
//    }
//}
//<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
//android:layout_width="match_parent"
//android:layout_height="match_parent"
//android:orientation="vertical"
//android:padding="16dp">
//
//<GridView
//android:id="@+id/gridView"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:numColumns="3"                <!-- Кількість колонок -->
//android:verticalSpacing="16dp"        <!-- Вертикальний відступ між елементами -->
//android:horizontalSpacing="16dp"      <!-- Горизонтальний відступ між елементами -->
//android:stretchMode="columnWidth"
//android:gravity="center"/>
//
//</LinearLayout>