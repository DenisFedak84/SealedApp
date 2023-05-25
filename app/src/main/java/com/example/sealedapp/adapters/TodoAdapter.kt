package com.example.sealedapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sealedapp.data.TodoModel
import com.example.sealedapp.databinding.ItemTodoBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<TodoModel>() {
        override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var todos: List<TodoModel>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            tvTitle.text = todo.title
            cbDone.isChecked = todo.completed
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)
}