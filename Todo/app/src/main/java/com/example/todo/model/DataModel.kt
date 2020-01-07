package com.example.todo.model

const val ITEM_POSITION = "ITEM_POSITION"

data class Todo(val todoId: String, val title: String) {
    override fun toString(): String {
        return title
    }
}

data class Detail(val todo: Todo, val title: String, val text: String)

object DataManager {

    val todoItems = HashMap<String, Todo>()

    val detailItems = ArrayList<Detail>()

    init {
        addTodo("alpha", "Do Alpha")
        addTodo("beta", "Do Beta")
        addDetail("Title 1", "Text 1")
        addDetail("Title 2", "Text 2")
    }

    private fun addTodo(todoId: String, title: String) {
        todoItems[todoId] = Todo(todoId, title)
    }

    private fun addDetail(title: String, text: String) {
        detailItems.add(Detail(todoItems.values.random(), title, text))
    }

}
