package com.webianks.nasapicturesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.webianks.nasapicturesapp.data.NasaPicture
import com.webianks.nasapicturesapp.databinding.ActivityDetailsBinding
import com.webianks.nasapicturesapp.ui.MainActivity.Companion.SINGLE_PICTURE

class DetailsActivity : AppCompatActivity() {

    private var pictureData: NasaPicture? = null
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        pictureData = intent.getSerializableExtra(SINGLE_PICTURE) as NasaPicture?
        pictureData?.let {
            showPictureDetails(it)
        }
    }

    private fun showPictureDetails(picture: NasaPicture) {

        binding.tvTitle.text = picture.title
        binding.tvDate.text = picture.date

        picture.copyright?.let {
            binding.tvCopyright.text = "By - $it"
        } ?: kotlin.run {
            binding.tvCopyright.visibility = View.GONE
        }

        binding.tvDescription.text = picture.explanation

        Glide.with(this)
            .load(picture.hdUrl)
            .into(binding.ivPicture)
    }

}