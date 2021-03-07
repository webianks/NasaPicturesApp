package com.webianks.nasapicturesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.webianks.nasapicturesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object{
        const val SINGLE_PICTURE = "picture"
        const val GRID_SPAN_COUNT = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupPicturesList()
        setupObservers()
    }

    private fun setupObservers() {

    }

    private fun setupPicturesList() {

        binding.rvPicturesList.layoutManager = GridLayoutManager(this, GRID_SPAN_COUNT)
        binding.rvPicturesList.adapter = PicturesListAdapter(emptyList()){

            //val item = picturesList[it]
            //Intent(this,DetailsActivity::class.java).run {
                //putExtra(SINGLE_PICTURE,item)
                //startActivity(this)
            //}
        }
    }
}