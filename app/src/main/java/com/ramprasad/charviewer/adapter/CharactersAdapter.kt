package com.ramprasad.charviewer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ramprasad.charviewer.commons.getName
import com.ramprasad.charviewer.data.RelatedTopic
import com.ramprasad.charviewer.databinding.CharacterItemBinding
import java.util.ArrayList
import java.util.Locale

/**
 * Created by Ramprasad on 7/4/23.
 */
class CharactersAdapter(
    private var itemClicked: (RelatedTopic) -> Unit
) :
    ListAdapter<RelatedTopic, CharactersAdapter.CharacterViewHolder>(
        object : DiffUtil.ItemCallback<RelatedTopic>() {
            override fun areItemsTheSame(oldItem: RelatedTopic, newItem: RelatedTopic): Boolean {
                return oldItem.firstURL == newItem.firstURL
            }

            override fun areContentsTheSame(oldItem: RelatedTopic, newItem: RelatedTopic): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    private val originalList = ArrayList<RelatedTopic>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        val biding = CharacterItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(biding) {
            itemClicked.invoke(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getName(getItem(position).firstURL)
        holder.bind(character)
    }

    inner class CharacterViewHolder(
        private val binding: CharacterItemBinding,
        private val itemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClicked.invoke(adapterPosition)
            }
        }

        fun bind(name: String) {
            binding.tvCharacterName.text = name
        }
    }

    fun filter(constraint: CharSequence?) {
        val charString = constraint?.toString()?.lowercase() ?: ""
        val filteredCharacters: MutableList<RelatedTopic> = if (charString.isEmpty())
            originalList
        else
            originalList
                .filter {
                    (getName(it.firstURL).lowercase(locale = Locale.getDefault())
                        .contains(charString))
                    it.text.lowercase().contains(charString)
                }.toMutableList()
        submitList(filteredCharacters)
    }

    fun modifyList(relatedTopics: List<RelatedTopic>) {
        if(originalList.isEmpty()) {
            originalList.clear()
            originalList.addAll(relatedTopics)
        }
        submitList(originalList)
    }

    fun renderList() {
        submitList(originalList)
    }
}