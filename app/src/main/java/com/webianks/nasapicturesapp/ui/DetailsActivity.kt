package com.webianks.nasapicturesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.webianks.nasapicturesapp.data.NasaPicture
import com.webianks.nasapicturesapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private var pictureData: NasaPicture? = null
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        pictureData = intent.getSerializableExtra("picture") as NasaPicture?
        pictureData?.let {
            showPictureDetails(it)
        }
    }

    private fun showPictureDetails(picture: NasaPicture) {

        binding.tvTitle.text = picture.title
        binding.tvDate.text = picture.date
        binding.tvDescription.text = picture.explanation

        Glide.with(this)
            .load(picture.hdUrl)
            .into(binding.ivPicture)
    }

}