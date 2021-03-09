package com.webianks.nasapicturesapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.webianks.nasapicturesapp.R
import com.webianks.nasapicturesapp.data.NasaPicture
import com.webianks.nasapicturesapp.databinding.ActivityMainBinding
import com.webianks.nasapicturesapp.di.component.DaggerActivityComponent
import com.webianks.nasapicturesapp.di.module.ActivityModule
import com.webianks.nasapicturesapp.ui.PicturesListAdapter
import com.webianks.nasapicturesapp.ui.details.DetailsActivity
import com.webianks.nasapicturesapp.utils.EqualSpacingItemDecoration
import com.webianks.nasapicturesapp.utils.Error
import com.webianks.nasapicturesapp.utils.Loading
import com.webianks.nasapicturesapp.utils.Success
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var adapter: PicturesListAdapter

    @Inject
    lateinit var glm: GridLayoutManager

    companion object {
        const val SINGLE_PICTURE = "picture"
        const val CLICKED_POSITION = "clicked_position"
        const val GRID_SPAN_COUNT = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDependencies()

        val view = binding.root

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

        binding.rvPicturesList.layoutManager = glm
        binding.rvPicturesList.addItemDecoration(
            EqualSpacingItemDecoration(
                resources.getDimension(R.dimen.default_margin_grid).toInt()
            )
        )
        adapter.openDetailsClickLister = {
            Intent(this, DetailsActivity::class.java).run {
                putExtra(CLICKED_POSITION, it)
                startActivity(this)
            }
        }
        binding.rvPicturesList.adapter = adapter
    }

    private fun getDependencies() {
        DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

}