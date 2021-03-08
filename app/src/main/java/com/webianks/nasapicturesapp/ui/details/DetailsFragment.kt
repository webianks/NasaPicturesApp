package com.webianks.nasapicturesapp.ui.details

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.webianks.nasapicturesapp.data.NasaPicture
import com.webianks.nasapicturesapp.ui.MainActivity.Companion.SINGLE_PICTURE
import com.webianks.nasapicturesapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

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
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val picture = arguments?.getSerializable(SINGLE_PICTURE) as NasaPicture?
        picture?.let {
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

        binding.tvDescription.text = Html.fromHtml(picture.explanation)

        Glide.with(this)
            .load(picture.hdUrl)
            .into(binding.ivPicture)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}