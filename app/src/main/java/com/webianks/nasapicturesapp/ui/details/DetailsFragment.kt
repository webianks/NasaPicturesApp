package com.webianks.nasapicturesapp.ui.details

import android.graphics.Bitmap
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.webianks.nasapicturesapp.data.NasaPicture
import com.webianks.nasapicturesapp.ui.main.MainActivity.Companion.SINGLE_PICTURE
import com.webianks.nasapicturesapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var binding: FragmentDetailsBinding? = null

    companion object {
        fun newInstance(nasaPicture: NasaPicture): DetailsFragment {
            val args = Bundle()
            args.putSerializable(SINGLE_PICTURE, nasaPicture)
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val picture = arguments?.getSerializable(SINGLE_PICTURE) as NasaPicture?
        picture?.let {
            showPictureDetails(it)
        }
    }

    private fun showPictureDetails(picture: NasaPicture) {

        binding?.tvTitle?.text = picture.title
        binding?.tvDate?.text = picture.date

        picture.copyright?.let {
            binding?.tvCopyright?.text = "By - $it"
        } ?: kotlin.run {
            binding?.tvCopyright?.visibility = View.GONE
        }

        binding?.tvDescription?.text = Html.fromHtml(picture.explanation)

        Glide.with(this)
            .asBitmap()
            .load(picture.hdUrl)
            .into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    binding?.ivPicture?.setImageBitmap(resource)
                    setColors(resource)
                }
            })
    }

    private fun setColors(bitmap: Bitmap) {
        val builder = Palette.Builder(bitmap)
        builder.generate { palette: Palette? ->
            val vibrantSwatch = palette?.vibrantSwatch
            binding?.let {
                binding ->
                vibrantSwatch?.titleTextColor?.let { binding.tvTitle.setTextColor(it) }
                vibrantSwatch?.bodyTextColor?.let { binding.tvDate.setTextColor(it) }
                vibrantSwatch?.titleTextColor?.let { binding.tvCopyright.setTextColor(it) }
                vibrantSwatch?.bodyTextColor?.let { binding.tvDescription.setTextColor(it) }
                vibrantSwatch?.rgb?.let { binding.root.setBackgroundColor(it) }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}