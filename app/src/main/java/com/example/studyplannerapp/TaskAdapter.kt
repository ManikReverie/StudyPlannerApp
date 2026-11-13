package com.example.studyplannerapp

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class TaskItem(
    val title: String,
    val subject: String,
    val deadline: String,
    var isCompleted: Boolean = false
)

class TaskAdapter(
    private val tasks: List<TaskItem>,
    private val onTaskToggled: (() -> Unit)? = null
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cbCompleted: CheckBox = itemView.findViewById(R.id.cbCompleted)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvSubject: TextView = itemView.findViewById(R.id.tvSubject)
        val tvDeadline: TextView = itemView.findViewById(R.id.tvDeadline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.tvTitle.text = task.title
        holder.tvSubject.text = task.subject
        holder.tvDeadline.text = task.deadline

        holder.cbCompleted.setOnCheckedChangeListener(null)
        holder.cbCompleted.isChecked = task.isCompleted
        updateStrikeThrough(holder.tvTitle, task.isCompleted)

        holder.cbCompleted.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
            updateStrikeThrough(holder.tvTitle, isChecked)
            onTaskToggled?.invoke()
        }
    }

    override fun getItemCount(): Int = tasks.size

    private fun updateStrikeThrough(textView: TextView, isChecked: Boolean) {
        if (isChecked) {
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}

