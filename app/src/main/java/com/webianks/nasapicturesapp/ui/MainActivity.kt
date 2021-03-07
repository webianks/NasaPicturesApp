package com.webianks.nasapicturesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.webianks.nasapicturesapp.data.NasaPicture
import com.webianks.nasapicturesapp.databinding.ActivityMainBinding
import com.webianks.nasapicturesapp.utils.Error
import com.webianks.nasapicturesapp.utils.Loading
import com.webianks.nasapicturesapp.utils.Success
import com.webianks.nasapicturesapp.utils.ViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PicturesListAdapter


    companion object {
        const val SINGLE_PICTURE = "picture"
        const val GRID_SPAN_COUNT = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        viewModel = ViewModelProviders.of(this, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(application, Gson())
        }).get(MainViewModel::class.java)

        setContentView(view)
        setupPicturesList()
        setupObservers()

        viewModel.getPicturesList()
    }

    private fun setupObservers() {

        viewModel.picturesListState.observe(this) {
            when (it) {
                is Error -> {
                }
                Loading -> {
                }
                is Success<*> -> {
                    if (it.data is List<*>) {
                        val nasaPicturesList: List<NasaPicture> = it.data as List<NasaPicture>
                        adapter.refreshList(nasaPicturesList)
                    }
                }
            }
        }

    }

    private fun setupPicturesList() {

        binding.rvPicturesList.layoutManager = GridLayoutManager(this, GRID_SPAN_COUNT)
        adapter = PicturesListAdapter(emptyList()) {
            //val item = picturesList[it]
            //Intent(this,DetailsActivity::class.java).run {
            //putExtra(SINGLE_PICTURE,item)
            //startActivity(this)
            //}
        }
        binding.rvPicturesList.adapter = adapter
    }
}