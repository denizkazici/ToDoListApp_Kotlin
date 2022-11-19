package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: ToDoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = ToDoAdapter(mutableListOf())

        val rvToDoItems = findViewById<RecyclerView>(R.id.rvToDoItems)
        rvToDoItems.adapter = todoAdapter
        rvToDoItems.layoutManager = LinearLayoutManager (this)

        val btnAddToDo = findViewById<Button>(R.id.btnAddToDo)
        btnAddToDo.setOnClickListener{
            val etToDoTitle = findViewById<EditText>(R.id.etToDoTitle)
            val todoTitle  = etToDoTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = ToDo(todoTitle)
                todoAdapter.addTodo(todo)
                etToDoTitle.text.clear()
            }
        }
        val btnDelete = findViewById<Button>(R.id.btnDelete)
       btnDelete.setOnClickListener{
           todoAdapter.deleteDone()
       }
    }
}