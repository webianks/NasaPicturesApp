package com.webianks.nasapicturesapp.ui.details

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.webianks.nasapicturesapp.data.NasaPicture
import com.webianks.nasapicturesapp.databinding.ActivityDetailsBinding
import com.webianks.nasapicturesapp.di.component.DaggerActivityComponent
import com.webianks.nasapicturesapp.di.module.ActivityModule
import com.webianks.nasapicturesapp.ui.main.MainActivity.Companion.CLICKED_POSITION
import com.webianks.nasapicturesapp.utils.Error
import com.webianks.nasapicturesapp.utils.Loading
import com.webianks.nasapicturesapp.utils.Success
import com.webianks.nasapicturesapp.utils.ZoomOutPageTransformer
import javax.inject.Inject
import kotlin.properties.Delegates

class DetailsActivity : AppCompatActivity() {

    private var clickedPosition by Delegates.notNull<Int>()

    @Inject
    lateinit var binding: ActivityDetailsBinding

    @Inject
    lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDependencies()

        val view = binding.root
        setContentView(view)

        setupViews()
        clickedPosition = intent.getIntExtra(CLICKED_POSITION, 0)
        setupObservers()

        viewModel.getPicturesList()
    }

    private fun setupViews() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
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
                        setupSliderAdapter(it.data as List<NasaPicture>)
                    }
                }
            }
        }
    }

    private fun setupSliderAdapter(picturesList: List<NasaPicture>) {
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager, picturesList)
        binding.pager.setPageTransformer(true, ZoomOutPageTransformer())
        binding.pager.adapter = pagerAdapter
        binding.pager.currentItem = clickedPosition
    }

    private fun getDependencies() {
        DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private inner class ScreenSlidePagerAdapter(
        fm: FragmentManager,
        private val picturesList: List<NasaPicture>
    ) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount(): Int = picturesList.size
        override fun getItem(position: Int): Fragment =
            DetailsFragment.newInstance(picturesList[position])
    }
}