package com.example.fitnesskit.presenter.tabs.lessons

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnesskit.BaseApplication
import com.example.fitnesskit.LessonDate
import com.example.fitnesskit.R
import com.example.fitnesskit.adapter.FitnessAdapter
import com.example.fitnesskit.common.FitnessClub
import com.example.fitnesskit.common.Lesson
import com.example.fitnesskit.common.data.ItemList
import com.example.fitnesskit.common.data.TrainingDataItemList
import com.example.fitnesskit.common.data.TrainingDateItemList
import com.example.fitnesskit.databinding.FragmentLessonsBinding
import com.example.fitnesskit.di.LessonsViewModelFactory
import com.example.fitnesskit.utils.BaseDateFormat
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class LessonsFragment : Fragment(R.layout.fragment_lessons) {

    private var _binding: FragmentLessonsBinding? = null
    private val binding: FragmentLessonsBinding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: LessonsViewModelFactory
    lateinit var viewModel: LessonsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as BaseApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[LessonsViewModel::class.java]
        viewModel.fetchFitnessClub(2)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLessonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFetchFitnessKitEvent()
        observeShowMessageEvent(view)
    }

    private fun observeFetchFitnessKitEvent() = viewModel.fetchFitnessClubEvent
        .observe(viewLifecycleOwner) { fitnessClub ->

            val adapter = FitnessAdapter(getItemsList(fitnessClub))

            binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.adapter = adapter
        }

    private fun observeShowMessageEvent(view: View) = viewModel.showMessageEvent
        .observe(viewLifecycleOwner) { message ->
            Log.e("logs", message)
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
        }

    private fun getItemsList(fitnessClub: FitnessClub): List<ItemList> {
        val lessonDateMap = mutableMapOf<LessonDate, Lesson>()

        fitnessClub.lessons.let { lessons ->
            for (item in lessons) {
                lessonDateMap[LessonDate(item.date)] = item
            }
        }

        val result = lessonDateMap.toList().sortedBy {
                (value, _) -> value
        }.toMap()

        val sortedDate = lessonDateMap.toSortedMap().keys.toList()

        val newLessonList = result.values.toList()

        val itemsList = mutableListOf<ItemList>()
        val elementsNumber = newLessonList.size + sortedDate.size
        val newSortedDate = mutableListOf<String>()
        var temp = ""
        var i = 0
        var j = 0

        for (e in sortedDate) {

            val newDate = BaseDateFormat("yyyy-MM-dd", "EEEE, dd MMMM")
                .getNewDateFormat(e.oldDate)
            newSortedDate.add(newDate)
        }

        for (e in 0..elementsNumber) {
            if (temp != newLessonList[j].date) {
                temp = sortedDate[i].oldDate
                itemsList.add(
                    TrainingDateItemList(date = newSortedDate[i])
                )
                i++
            } else {
                itemsList.add(
                    TrainingDataItemList(
                        startTime = newLessonList[j].startTime,
                        endTime = newLessonList[j].endTime,
                        textTitle = newLessonList[j].name,
                        color = newLessonList[j].color,
                        name = newLessonList[j].name,
                        available_slot = newLessonList[j].available_slots,
                        place = newLessonList[j].place,
                        coach_id = newLessonList[j].coach_id
                    )
                )
            }

            if (j < newLessonList.size - 1) { j++ }
        }

        return itemsList
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}