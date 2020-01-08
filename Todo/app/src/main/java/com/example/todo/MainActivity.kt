package com.example.todo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.model.DataManager
import com.example.todo.model.ITEM_POSITION
import com.example.todo.model.Todo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var notePosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        spinner.adapter = ArrayAdapter<Todo>(
            this,
            android.R.layout.simple_spinner_item,
            DataManager.todoItems.values.toList()
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        notePosition = intent.getIntExtra(ITEM_POSITION, -1)
        displayTodo(notePosition)
    }

    private fun displayTodo(position: Int) {
        if (position < 0) return

        val (todo, title, text) = DataManager.detailItems[position]
        textTitle.setText(title)
        textText.setText(text)

        spinner.setSelection(DataManager.todoItems.values.indexOf(todo))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                moveNext()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        displayTodo(++notePosition)
        invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (notePosition >= DataManager.detailItems.lastIndex) {
            val item = menu?.findItem(R.id.action_next)
            item?.setIcon(R.drawable.ic_block_white_24dp)
            item?.isEnabled = false
        }

        return super.onPrepareOptionsMenu(menu)
    }
}
