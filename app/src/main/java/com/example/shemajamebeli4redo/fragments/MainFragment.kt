package com.example.shemajamebeli4redo.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shemajamebeli4redo.App
import com.example.shemajamebeli4redo.R
import com.example.shemajamebeli4redo.adapters.MainRecyclerAdapter
import com.example.shemajamebeli4redo.databinding.MainFragmentBinding
import com.example.shemajamebeli4redo.extensions.loadImg
import com.example.shemajamebeli4redo.models.Match
import com.example.shemajamebeli4redo.models.ResultHandle
import com.example.shemajamebeli5.fragments.MainViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: MainRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        viewModel.init()
        setInfo()
        mainRecyclerInit()

    }

    private fun setInfo() {
        viewModel._matchInfo.observe(viewLifecycleOwner, {
            when (it.status) {
                ResultHandle.Companion.Status.SUCCESS -> {
                    val model = it.data!!.match

                    binding.teamFirstImage.loadImg(model.team1.teamImage)
                    binding.teamSecondImage.loadImg(model.team2.teamImage)

                    binding.fieldTextView.text = model.stadiumAdress
                    binding.dateTextView.text = getDataTime(model.matchDate)
                    binding.firstTeamName.text = model.team1.teamName
                    binding.secondTeamName.text = model.team2.teamName
                    binding.duration.text = App.context.getString(R.string.time_format, model.matchTime.roundToInt().toString())

                    binding.scoreTextView.text =
                        App.context.getString(R.string.result, model.team1.score, model.team2.score)

                    binding.firstPossesion.text = App.context.getString(R.string.possession, model.team1.ballPosition)
                    binding.secondPossesion.text = App.context.getString(R.string.possession, model.team2.ballPosition)
                    binding.progressBar.progress = model.team1.ballPosition!!
                    adapter.addActions(model.matchSummary.summaries.toMutableList())
                    firstHalfScores(binding.score, it)

                }
                ResultHandle.Companion.Status.ERROR -> {

                }
            }
        })
    }

    private fun firstHalfScores(dashboard: TextView, matchInfo: ResultHandle<Match>) {
            val model = matchInfo.data!!.match
            var firstScores = 0
            var secondScores = 0

            // scored to opponents (first team)
            model.matchSummary.summaries.forEach { summaries ->
                if (summaries.actionTime.toInt() < 45) {
                    summaries.team1Action?.forEach { actions ->
                        if (actions.actionType == 1 && actions.action.goalType == 1) {
                            firstScores++
                        }
                    }
                }
            }

            // auto goals (by second team)
            model.matchSummary.summaries.forEach { summaries ->
                if (summaries.actionTime.toInt() < 45) {
                    summaries.team2Action?.forEach { actions ->
                        if (actions.actionType == 1 && actions.action.goalType != 1)
                            firstScores++
                    }
                }
            }

            // scored to opponents (second team)
            model.matchSummary.summaries.forEach { summaries ->
                if (summaries.actionTime.toInt() < 45) {
                    summaries.team2Action?.forEach { actions ->
                        if (actions.actionType == 1 && actions.action.goalType == 1) {
                            secondScores++
                        }
                    }
                }
            }

            // auto goals (by first team)
            model.matchSummary.summaries.forEach { summaries ->
                if (summaries.actionTime.toInt() < 45) {
                    summaries.team1Action?.forEach { actions ->
                        if (actions.actionType == 1 && actions.action.goalType != 1)
                            secondScores++
                    }
                }
            }
            dashboard.text = App.context.getString(R.string.first_half, firstScores, secondScores)
    }


    @SuppressLint("SimpleDateFormat")
    private fun getDataTime(date: Long): String {
        val form = SimpleDateFormat("dd MMMM yyyy")
        return form.format(Date(date))
    }

    private fun mainRecyclerInit() {
        adapter = MainRecyclerAdapter()
        binding.recylcer.layoutManager = LinearLayoutManager(requireContext())
        binding.recylcer.adapter = adapter
    }


}