package com.example.shemajamebeli4redo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli4redo.databinding.ItemLayoutBinding
import com.example.shemajamebeli4redo.models.Summaries

class MainRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val actions = mutableListOf<Summaries>()
    private lateinit var firstTeamAdapter: FirstTeamRecycler
    private lateinit var secondTeamAdapter: SecondTeamRecycler

    inner class ViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {

            firstTeamAdapterSet()
            secondTeamAdapterSet()

        }
        private fun firstTeamAdapterSet() {
            if (actions[adapterPosition].team1Action != null) {
                firstTeamAdapter = FirstTeamRecycler(actions[adapterPosition].team1Action!!.toMutableList(), actions[adapterPosition].actionTime)
                binding.firstTeamRec.layoutManager = LinearLayoutManager(binding.firstTeamRec.context)
                binding.firstTeamRec.adapter = firstTeamAdapter
            }

        }
        private fun secondTeamAdapterSet() {
            if (actions[adapterPosition].team2Action != null) {
                secondTeamAdapter = SecondTeamRecycler(actions[adapterPosition].team2Action!!.toMutableList(), actions[adapterPosition].actionTime)
                binding.secondTeamRec.layoutManager = LinearLayoutManager(binding.secondTeamRec.context)
                binding.secondTeamRec.adapter = secondTeamAdapter
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind()
    }

    override fun getItemCount(): Int = actions.size

    fun addActions(actions: MutableList<Summaries>) {
        this.actions.clear()
        this.actions.addAll(actions)
        notifyDataSetChanged()
    }

}