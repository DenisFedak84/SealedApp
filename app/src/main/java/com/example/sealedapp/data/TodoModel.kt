package com.example.sealedapp.data

/**
 * Immutable model class for a Task.
 *
 * @param completed whether or not this task is completed
 * @param id id of the task
 * @param title title of the task
 * @param userId user id which belong to that task
 */
data class TodoModel(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)