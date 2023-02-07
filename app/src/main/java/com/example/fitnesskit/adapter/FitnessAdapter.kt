package com.example.fitnesskit.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesskit.R
import com.example.fitnesskit.databinding.ListItemTrainingDataBinding
import com.example.fitnesskit.databinding.ListItemTrainingDateBinding
import com.example.fitnesskit.common.data.ItemList
import com.example.fitnesskit.common.data.TrainingDataItemList
import com.example.fitnesskit.common.data.TrainingDateItemList

class FitnessAdapter(
    private val list: List<ItemList>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class FitnessTrainingDataHolder(val binding: ListItemTrainingDataBinding) : RecyclerView.ViewHolder(binding.root)
    class FitnessTrainingDateHolder(val binding: ListItemTrainingDateBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TRAINING_DATA_VIEW_TYPE -> {
                FitnessTrainingDataHolder(
                    ListItemTrainingDataBinding.bind(
                        inflater.inflate(R.layout.list_item_training_data, parent, false)
                    )
                )
            }
            TRAINING_DATE_VIEW_TYPE -> {
                FitnessTrainingDateHolder(
                    ListItemTrainingDateBinding.bind(
                        inflater.inflate(R.layout.list_item_training_date, parent, false)
                    )
                )
            }
            else -> {
                throw IllegalArgumentException("Can't create view holder from view type $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TRAINING_DATA_VIEW_TYPE -> {
                val fitnessTrainingDataHolder = holder as FitnessTrainingDataHolder

                val item = list[position] as TrainingDataItemList
                fitnessTrainingDataHolder.binding.endTimeTextView.text = item.endTime

                fitnessTrainingDataHolder.binding.fullNameTextView.text = item.name
                fitnessTrainingDataHolder.binding.nameTextView.text = item.name
                fitnessTrainingDataHolder.binding.placeTextView.text = item.place
                fitnessTrainingDataHolder.binding.startTimeTextView.text = item.startTime
                fitnessTrainingDataHolder.binding.colorLinearLayout.setBackgroundColor(Color.parseColor(item.color))
            }
            TRAINING_DATE_VIEW_TYPE -> {
                val fitnessTrainingDataHolder = holder as FitnessTrainingDateHolder

                val item = list[position] as TrainingDateItemList
                fitnessTrainingDataHolder.binding.dateTextView.text = item.date
            }
            else -> {
                throw IllegalArgumentException("Can't create bind holder fro position $position")
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        val item = list[position]

        if (item is TrainingDateItemList) {
            return TRAINING_DATE_VIEW_TYPE
        }
        if (item is TrainingDataItemList) {
            return TRAINING_DATA_VIEW_TYPE
        }
        throw IllegalArgumentException("Can't find view type for position $position")
    }

    companion object {
        private const val TRAINING_DATA_VIEW_TYPE = 0
        private const val TRAINING_DATE_VIEW_TYPE = 1
    }
}