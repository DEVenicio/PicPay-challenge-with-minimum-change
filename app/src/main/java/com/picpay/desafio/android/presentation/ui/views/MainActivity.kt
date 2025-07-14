package com.picpay.desafio.android.presentation.ui.views

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.presentation.ui.UiState
import com.picpay.desafio.android.presentation.ui.adapters.UserListAdapter
import com.picpay.desafio.android.presentation.viewmodels.UserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by viewModel()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressBar = binding.userListProgressBar
        recyclerView = binding.recyclerView

        setupRecyclerView()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }

                        is UiState.Success -> {
                            val users = uiState.data
                            progressBar.visibility = View.GONE
                            recyclerView.visibility = View.VISIBLE
                            adapter.users = users
                        }

                        is UiState.Error -> {
                            val message = uiState.message
                            progressBar.visibility = View.GONE
                            recyclerView.visibility = View.GONE

                            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}