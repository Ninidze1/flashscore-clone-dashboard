package com.example.shemajamebeli4redo.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.shemajamebeli4redo.R
import com.example.shemajamebeli4redo.adapters.MainRecyclerAdapter
import com.example.shemajamebeli4redo.databinding.MainFragmentBinding
import com.example.shemajamebeli4redo.extensions.loadImg
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

    @SuppressLint("SetTextI18n")
    private fun setInfo() {
        viewModel._matchInfo.observe(viewLifecycleOwner, {

            binding.teamFirstImage.loadImg(it.match.team1.teamImage)
            binding.teamSecondImage.loadImg(it.match.team2.teamImage)

            binding.fieldTextView.text = it.match.stadiumAdress
            binding.dateTextView.text = getDataTime(it.match.matchDate)
            binding.firstTeamName.text = it.match.team1.teamName
            binding.secondTeamName.text = it.match.team2.teamName
            binding.duration.text = it.match.matchTime.roundToInt().toString() + "'"

            binding.scoreTextView.text =
                it.match.team1.score.toString() + " : " + it.match.team2.score.toString()

            binding.firstPossesion.text = it.match.team1.ballPosition.toString() + "%"
            binding.secondPossesion.text = it.match.team2.ballPosition.toString() + "%"
            binding.progressBar.progress = it.match.team1.ballPosition!!
            adapter.addActions(it.match.matchSummary.summaries.toMutableList())

            it.match.matchSummary.summaries.forEach {
                d("loglog", "${it.team1Action}")
            }



        })
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDataTime(date: Long): String {
        val form = SimpleDateFormat("dd MM yyyy")
        return form.format(Date(date))
    }

    private fun mainRecyclerInit() {
        adapter = MainRecyclerAdapter()
        binding.recylcer.layoutManager = LinearLayoutManager(requireContext())
        binding.recylcer.adapter = adapter
    }


}