package com.example.listview

import android.os.Bundle
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity

class OtherActivity : AppCompatActivity() {
    lateinit var expandableListView: ExpandableListView
    var header: MutableList<String> = ArrayList()
    var childItem: MutableList<MutableList<String>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.otheractivity)

        expandableListView = findViewById(R.id.expanded_menu_List)

        val header1: MutableList<String> = ArrayList()
        header1.add("childItem 1")
        header1.add("childItem 2")
        header1.add("childItem 3")

        val header2: MutableList<String> = ArrayList()
        header2.add("childItem 1")
        header2.add("childItem 2")
        header2.add("childItem 3")

        header.add("header 1")
        header.add("header 2")

        childItem.add(header1)
        childItem.add(header2)

        expandableListView.setAdapter(ExpandableAdapter(this, header, childItem))
    }
}
