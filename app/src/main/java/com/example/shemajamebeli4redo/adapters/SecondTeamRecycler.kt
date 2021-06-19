package com.example.shemajamebeli4redo.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli4redo.R
import com.example.shemajamebeli4redo.databinding.ActionItemBinding
import com.example.shemajamebeli4redo.databinding.SubstitutionItemBinding
import com.example.shemajamebeli4redo.extensions.loadImg
import com.example.shemajamebeli4redo.extensions.mirrorView
import com.example.shemajamebeli4redo.models.TeamAction

class SecondTeamRecycler(
    private val summaries: MutableList<TeamAction>,
    private val actionTime: String
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class SubstitutionViewHolder(private val binding: SubstitutionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind () {
            binding.root.mirrorView()
            val model = summaries[adapterPosition]

            binding.firstPlayer.text = model.action.player1?.playerName
            binding.secondPlayer.text = model.action.player2?.playerName

            binding.firstImage.loadImg(model.action.player1?.playerImage.toString())
            binding.SecondImage.loadImg(model.action.player2?.playerImage.toString())

            binding.action.text = "$actionTime' Substitution"

        }
    }

    inner class ViewHolder(private val binding: ActionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind() {
            binding.root.mirrorView()
            val model = summaries[adapterPosition]
            binding.playerImage.loadImg(model.action.player?.playerImage.toString())
            binding.name.text = model.action.player?.playerName

            when(model.actionType) {
                1 -> {
                    if (model.action.goalType == 1) {
                        binding.actionType.text = "$actionTime' Goals by"
                        binding.event3.setImageResource(R.drawable.ic_goal)
                    } else {
                        binding.actionType.text = "$actionTime' Own Goal"
                        binding.actionType.setTextColor(Color.RED)
                        binding.event3.setImageResource(R.drawable.ic_selfgoal)
                    }
                }
                2 -> {
                    binding.actionType.text = "$actionTime' Tripping"
                    binding.event3.setImageResource(R.drawable.ic_yellow)

                }
                3 -> {
                    binding.actionType.text = "$actionTime' Red Card"
                    binding.actionType.setTextColor(Color.RED)
                    binding.event3.setImageResource(R.drawable.ic_red)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1)
            ViewHolder(
                ActionItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        else SubstitutionViewHolder(
            SubstitutionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SecondTeamRecycler.ViewHolder -> holder.bind()
            is SecondTeamRecycler.SubstitutionViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int = summaries.size

    override fun getItemViewType(position: Int): Int {
        val model = summaries[position]
        return if (model.action.player == null) {
            2
        } else {
            1
        }
    }

}