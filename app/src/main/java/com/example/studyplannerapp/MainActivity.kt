package com.example.studyplannerapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvProgress = findViewById<TextView>(R.id.tvProgress)
        val rvTasks = findViewById<RecyclerView>(R.id.rvTasks)
        val fabAddTask = findViewById<FloatingActionButton>(R.id.fabAddTask)

        // Dummy task data
        val dummyTasks = mutableListOf(
            TaskItem("Calculus Problem Set 4", "Maths", "Due: Today, 5:00 PM", isCompleted = true),
            TaskItem("Physics Lab Report - Optics", "Science", "Due: Tomorrow, 11:59 PM", isCompleted = false),
            TaskItem("Organic Chemistry Revision", "Science", "Due: Nov 16, 2:00 PM", isCompleted = false),
            TaskItem("Linear Algebra Matrices", "Maths", "Due: Nov 18, 10:00 AM", isCompleted = false),
            TaskItem("Literature Essay Review", "English", "Due: Nov 20, 6:00 PM", isCompleted = false)
        )

        fun updateProgress() {
            val completed = dummyTasks.count { it.isCompleted }
            tvProgress.text = "Progress: $completed/${dummyTasks.size} Completed"
        }

        updateProgress()

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = TaskAdapter(dummyTasks) {
            updateProgress()
        }

        fabAddTask.setOnClickListener {
            Toast.makeText(this, "Add Task Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
