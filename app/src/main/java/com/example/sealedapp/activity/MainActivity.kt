package com.example.sealedapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sealedapp.adapters.TodoAdapter
import com.example.sealedapp.data.TodoModel
import com.example.sealedapp.databinding.ActivityMainBinding
import com.example.sealedapp.events.MainActivityEvents
import com.example.sealedapp.extansions.visibleOrGone
import com.example.sealedapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        subscribeToLiveData()
        initRecyclerView()
        viewModel.getTodos()
    }

    private fun initRecyclerView() {
        binding.rvTodos.apply {
            todoAdapter = TodoAdapter()
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun initBinding() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun subscribeToLiveData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mainActivityEvents.collect {
                    when (it) {
                        is MainActivityEvents.DefaultStateEvent -> Unit
                        is MainActivityEvents.ErrorEvent -> showError(it.message)
                        is MainActivityEvents.LoadingEvent -> handleLoading(it.loading)
                        is MainActivityEvents.ShowDataEvent -> showTodoList(it.todos)
                    }
                }
            }
        }
    }

    private fun showTodoList(todos: List<TodoModel>) {
        todoAdapter.todos = todos
    }

    private fun handleLoading(loading: Boolean) {
        with(binding) {
            rvTodos.visibleOrGone(!loading)
            pbProgress.visibleOrGone(loading)
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, "Error: $message", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}