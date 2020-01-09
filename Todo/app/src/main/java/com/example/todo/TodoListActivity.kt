package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.model.DataManager
import com.example.todo.model.ITEM_POSITION
import kotlinx.android.synthetic.main.activity_todo_list.*
import kotlinx.android.synthetic.main.content_todo_list.*

class TodoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        listTodo.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, DataManager.detailItems)

        listTodo.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(ITEM_POSITION, position)
            startActivity(intent)
        }

    }

    override fun onResume() {
        listTodo.invalidateViews()
        super.onResume()
    }

}
