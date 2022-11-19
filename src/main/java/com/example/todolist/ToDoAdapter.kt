package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class ToDoAdapter (
    private val todos: MutableList<ToDo>
    ) : RecyclerView.Adapter<ToDoAdapter.TodoViewHolder>() {
    class TodoViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
         return TodoViewHolder(
             LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
         )
    }
    //eleman ekleme
    fun addTodo (todo: ToDo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }
    fun deleteDone (){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }
    //üstü çizili olması için fonksiyon
    private fun toggleStrikeThrough(tvToDoTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var curTodo = todos[position]
        holder.itemView.apply {

            val tvToDoTitle  = findViewById<TextView>(R.id.tvToDoTitle)
            val cbDone = findViewById<CheckBox>(R.id.cbDone)
            tvToDoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked

            toggleStrikeThrough(tvToDoTitle, curTodo.isChecked)

            cbDone.setOnCheckedChangeListener{ _, isChecked ->
                toggleStrikeThrough(tvToDoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }



        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}