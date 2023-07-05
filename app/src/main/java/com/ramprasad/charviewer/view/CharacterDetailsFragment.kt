package com.ramprasad.charviewer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.ramprasad.charviewer.R
import com.ramprasad.charviewer.commons.getName
import com.ramprasad.charviewer.databinding.FragmentCharacterDetailsBinding
import com.ramprasad.charviewer.viewmodel.CharactersViewModel

/**
 * Created by Ramprasad on 7/4/23.
 */
class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private val viewModel: CharactersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.characterDetails.observe(viewLifecycleOwner) {
            binding.apply {
                tvTitle.text = getName(it.firstURL)
                tvDetail.text = it.text
                if (it.icon.uRL.isNotBlank()) {
                    val url = "https://i.duckduckgo.com${it.icon.uRL}"
                    charImage.load(url)
                } else {
                    charImage.load(R.drawable.no_image_icon)
                }
            }
        }
    }
}