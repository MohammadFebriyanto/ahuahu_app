package com.cap0097.ahuahuapp.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cap0097.ahuahuapp.data.local.HistoryEntity
import com.cap0097.ahuahuapp.databinding.ListHistoryBinding

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.MainViewHolder>() {

    private var historyData = mutableListOf<HistoryEntity>()

    inner class MainViewHolder(val binding: ListHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    fun setHistoryData(listHistory: List<HistoryEntity>){
        historyData.clear()
        historyData = listHistory.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ListHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val historyData = historyData[position]
        holder.binding.let {
            it.tvRecommendation.text = "Kualitas : ${historyData.airQuality}"
            it.tvLocation.text = historyData.address
            it.tvDate.text = historyData.date
        }
    }

    override fun getItemCount(): Int = historyData.size
}