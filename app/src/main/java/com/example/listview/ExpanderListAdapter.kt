package com.example.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
//getChild(groupPosition, childPosition) використовується для визначення того, яка дитина до якої групи належить.
//Параметри groupPosition і childPosition допомагають знайти відповідний елемент у списку childItem, який структурований як вкладений список (MutableList<MutableList<String>>).
//Адаптер автоматично пов’язує кожного дочірнього елемента з відповідною групою під час відтворення ExpandableListView.
//Що стосується продуктивності, і getGroupView, і getChildView покладаються на LayoutInflater для збільшення переглядів,
// якщо convertView має значення null. Пошук у MutableList для дітей (getChild)
// може включати більший обхід вкладених даних порівняно з getGroup,
// але на практиці це зазвичай не є вузьким місцем, якщо структура даних не дуже велика або неефективно керована.
class ExpandableAdapter(
    var context: Context,
    var header: MutableList<String>,
    var childItem: MutableList<MutableList<String>>
) : BaseExpandableListAdapter() {
    //Повертає кількість груп у списку (тобто кількість заголовків).
    //Використовується для визначення загальної кількості груп, які має адаптер.
    override fun getGroupCount(): Int {
        return header.size
    }
    //Повертає кількість елементів у певній групі.
    //Використовується для визначення, скільки піделементів має конкретний заголовок (група
    override fun getChildrenCount(p0: Int): Int {
        return childItem[p0].size
    }
    //Повертає об'єкт заголовка на основі переданої позиції групи.
    //Використовується для отримання заголовка для відображення.
    override fun getGroup(groupPosition: Int): Any {
        // Повертає об'єкт, який представляє заголовок групи.
        // У вашому випадку він повертає header[groupPosition], тобто заголовок групи як рядок.
        // Ви можете змінити тип цього об'єкта,
        // якщо ваші заголовки містять більше інформації, наприклад, клас із додатковими полями.
        return header[groupPosition]
    }
    //Повертає об'єкт піделемента у певній групі на основі переданих позицій.
    //Використовується для отримання тексту або об'єкта піделемента.
    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return childItem[groupPosition][childPosition]
    }
    //Повертає унікальний ідентифікатор для групи.
    //Зазвичай використовується для забезпечення унікальності ідентифікаторів груп.
    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }
    //Це допомагає системі Android ефективно працювати з ExpandableListView, наприклад, для відстеження змін, анімацій і вибору елементів.
    //Повертає унікальний ідентифікатор для піделемента у певній групі.
    //Також використовується для унікальності піделементів.
    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        //Повертає унікальний ідентифікатор для групи. Він зазвичай використовується для того,
        // щоб система могла розпізнати, яка група була взаємодією, наприклад, при розгортанні чи згортанні.
        // У вашому випадку ідентифікатор — це просто groupPosition, що гарантує унікальність для кожної групи.
        return childPosition.toLong()
        //Навіщо це потрібно?
        //Оптимізація роботи списку: Ці методи допомагають системі швидше оновлювати представлення списку. Вона використовує ці ідентифікатори для кешування та перевірки унікальності елементів.
        //Анімації та відтворення станів: Ідентифікатори потрібні для коректного відтворення анімацій при розгортанні чи згортанні списків.
        //Збереження стану: Ідентифікатори допомагають при збереженні та відновленні стану списку після обертання екрану або інших змін конфігурації.
    }
    //Вказує, чи є ID елементів стабільними між змінами у наборі даних.
    //Повертає false, що означає, що ID можуть змінюватися.
    override fun hasStableIds(): Boolean {
return false    }
    //Відповідає за створення і повернення представлення для заголовка групи.
    //Використовує LayoutInflater для створення нового View, якщо його ще не створено.
    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        var convertView = p2
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            //context.getSystemService(Context.LAYOUT_INFLATER_SERVICE): Використовується для отримання LayoutInflater — об'єкта, який дозволяє "надувати" (створювати) View з файлу макета XML.
            convertView = inflater.inflate(R.layout.list_group, null)
        }
        //findViewById<TextView>(R.id.groupHeader): Знаходить TextView у макеті, який відповідає за відображення заголовка групи.
        //itemChild.text = getGroup(p0).toString(): Встановлює текст для цього TextView відповідно до значення, що повертається методом getGroup(p0) (назва групи).
        val itemChild = convertView!!.findViewById<TextView>(R.id.groupHeader)
        itemChild.text = getGroup(p0).toString()
        return convertView
    }
    //p0: Int (groupPosition) — позиція групи в адаптері. Використовується для отримання даних, пов'язаних із цією групою.
    //p1: Boolean (isExpanded) — вказує, чи розгорнута група (тобто чи показані її дочірні елементи).
    //p2: View? (convertView) — перероблений (повторно використаний) View для ефективності. Якщо він не null, адаптер може його повторно використовувати, що покращує продуктивність.
    //p3: ViewGroup? (parent) — батьківський ViewGroup, до якого буде додано View. Зазвичай це ExpandableListView.
    //Відповідає за створення і повернення представлення для піделемента у певній групі.
    //Подібно до getGroupView, використовує LayoutInflater, якщо View ще не існує.
    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        //Процесом зіставлення дочірніх елементів із відповідними заголовками груп керує структура адаптера,
        // зокрема заголовок списків і childItem у вашому коді
        var convertView = p3
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.list_items, null)
        }
        val childItem = convertView!!.findViewById<TextView>(R.id.childitem)
        childItem.text = getChild(p0, p1) as String
        return convertView
    }
    //Повертає true, що дозволяє піделементам бути вибраними або натиснутими.
    //Вказує, що піделементи у списку можна вибирати.
    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        //Ці параметри дозволяють вам визначати, чи конкретний дочірній елемент вибираний, залежно від його позиції.
        // Наприклад, ви можете зробити так, щоб деякі дочірні елементи були вибираними, а інші — ні, виходячи з їхніх позицій або значень.
        return true
    }
}