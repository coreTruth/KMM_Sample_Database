package com.example.kmmapplication.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kmmapplication.KMMStorage
import com.example.kmmapplication.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserListAdapter
    private val kmmStorage = KMMStorage(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        setListeners()
    }

    private fun initialize() {
        val factory = MainViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        adapter = UserListAdapter()
        binding.recyclerViewUser.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewUser.adapter = adapter
        viewModel.appUsers.observe(this) {
            adapter.submitList(it)
        }
        viewModel.updateUsers()

        binding.editTextName.setText(kmmStorage.getLastUser())
    }

    private fun setListeners() = with(binding) {
        buttonAdd.setOnClickListener {
            viewModel.addUser(editTextName.text.toString())
            kmmStorage.putLastUser(editTextName.text.toString())
            binding.editTextName.text.clear()
        }

        buttonAddMultiple.setOnClickListener {
            viewModel.addMultipleUser(editTextName.text.toString())
            kmmStorage.putLastUser(editTextName.text.toString())
            binding.editTextName.text.clear()
        }

        buttonClear.setOnClickListener {
            viewModel.deleteAllUsers()
            binding.editTextName.text.clear()
        }
    }
}
