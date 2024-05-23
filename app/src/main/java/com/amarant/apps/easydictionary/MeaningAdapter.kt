package com.amarant.apps.easydictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amarant.apps.easydictionary.databinding.ListItemMeaningBinding

class MeaningAdapter(
    private var meaningList: List<Meaning>
) : RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val binding = ListItemMeaningBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeaningViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        val item = meaningList[position]
        with(holder.binding) {
            tvPartOfSpeech.text = item.partOfSpeech
            tvDefinition.text = item.definitions.joinToString("\n\n") {
                var currentIndex = item.definitions.indexOf(it)
                "${currentIndex + 1}. ${it.definition}"
            }
            if (item.synonyms.isEmpty()) {
                lblSynonyms.visibility = View.GONE
                tvSynonym.visibility = View.GONE
            } else {
                lblSynonyms.visibility = View.VISIBLE
                tvSynonym.visibility = View.VISIBLE
                tvSynonym.text = item.synonyms.joinToString(", ")
            }
            if (item.antonyms.isEmpty()) {
                lblAntonyms.visibility = View.GONE
                tvAntonym.visibility = View.GONE
            } else {
                lblAntonyms.visibility = View.VISIBLE
                tvAntonym.visibility = View.VISIBLE
                tvAntonym.text = item.antonyms.joinToString(", ")
            }
        }
    }

    override fun getItemCount() = meaningList.size

    fun updateNewData(newMeaningList: List<Meaning>) {
        meaningList = newMeaningList
        notifyDataSetChanged()
    }

    class MeaningViewHolder(val binding: ListItemMeaningBinding) : RecyclerView.ViewHolder(binding.root)
}