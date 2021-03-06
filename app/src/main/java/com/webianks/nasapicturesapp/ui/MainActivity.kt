package com.webianks.nasapicturesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.webianks.nasapicturesapp.data.NasaPicture
import com.webianks.nasapicturesapp.databinding.ActivityMainBinding
import com.webianks.nasapicturesapp.utils.getPicturesList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupPicturesList()
    }

    private fun setupPicturesList() {
        val picturesList = getPicturesList(this)
        binding.rvPicturesList.layoutManager = GridLayoutManager(this,2)
        binding.rvPicturesList.adapter = PicturesListAdapter(picturesList)
    }
}