package com.example.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
/*
*context є параметром конструктора ListAdapter. Він представляє собою контекст Android додатка, який передається для доступу до ресурсів та інших системних служб.
* dataArrayList є параметром конструктора і представляє список об'єктів типу ListData, який може містити null значення (ListData?). Знак питання (?) позначає, що цей тип може бути null. В даному випадку ArrayList<ListData?>? означає, що dataArrayList може бути або null, або масивом, який містить ListData об'єкти, включаючи null.
*ArrayAdapter є базовим класом, який використовується для зв'язування даних з ListView (або іншими списковими віджетами) в Android. У випадку ListAdapter, він успадковує ArrayAdapter<ListData?>, що означає, що адаптер призначений для роботи зі списком об'єктів типу ListData, які можуть бути null.
* R.layout.list_item є ресурсом макету, який використовується для відображення кожного елемента у списку. У цьому випадку, list_item.xml визначає вигляд кожного елемента списку, який включає ImageView і два TextView.
* !! (double bang) в Kotlin вказує на те, що змінна або вираз не буде null на цей момент. У випадку dataArrayList!!, це оператор унулярного приведення, який припускає, що dataArrayList не є null. Якщо dataArrayList все ж є null, то виникне NullPointerException.
*/
class ListAdapter(context: Context, dataArrayList: ArrayList<ListData?>?) :
    ArrayAdapter<ListData?>(context, R.layout.list_item, dataArrayList!!) {
    /*
    * Функція getView перевизначена для відображення кожного елемента списку (ListView або GridView). Основні дії цієї функції:
        Перевіряє, чи існує вже view для поточної позиції. Якщо view є null, вона інфлюється з макету R.layout.list_item.
        Отримує зображення, ім'я та час з listData (об'єкт типу ListData) за допомогою методів findViewById.
        Встановлює зображення (image) за допомогою setImageResource.
        Встановлює текст ім'я та часу (name і time) за допомогою setText.
    */
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        // View є базовим класом для всіх елементів інтерфейсу користувача в Android, таких як кнопки, тексти, зображення тощо. В цьому контексті view представляє один елемент списку (або перегляду), який відтворюється для позиції position.
        // ViewGroup також є класом в Android, який наслідується від View і використовується для організації і керування групою View об'єктів. parent в даному випадку представляє контейнер, в який вставляється елемент view.
        var view = view
        val listData = getItem(position) // є методом у класі ArrayAdapter, який повертає об'єкт даних зі списку за вказаною позицією position. У випадку ListAdapter, який успадковує ArrayAdapter<ListData?>, цей метод повертає об'єкт типу ListData?, який може бути null.

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val listImage = view!!.findViewById<ImageView>(R.id.listImage)
        val listName = view.findViewById<TextView>(R.id.listName)
        val listTime = view.findViewById<TextView>(R.id.listTime)

        listImage.setImageResource(listData!!.image)
        listName.text = listData.name
        listTime.text = listData.time

        return view
    }
}