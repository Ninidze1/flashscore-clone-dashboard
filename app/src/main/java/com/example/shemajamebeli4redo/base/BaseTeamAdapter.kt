package com.example.shemajamebeli4redo.base

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli4redo.App
import com.example.shemajamebeli4redo.R
import com.example.shemajamebeli4redo.databinding.ActionItemBinding
import com.example.shemajamebeli4redo.databinding.SubstitutionItemBinding
import com.example.shemajamebeli4redo.extensions.getInitials
import com.example.shemajamebeli4redo.extensions.loadImg
import com.example.shemajamebeli4redo.extensions.mirrorView
import com.example.shemajamebeli4redo.models.TeamAction
import com.example.shemajamebeli4redo.utils.Constants.NORMAL_EVENT
import com.example.shemajamebeli4redo.utils.Constants.TEAM_SECOND
import com.example.shemajamebeli4redo.utils.Constants.TRANSITION

abstract class BaseTeamAdapter(
    private val summaries: MutableList<TeamAction>,
    private val actionTime: String,
    private val team: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class SubstitutionViewHolder(private val binding: SubstitutionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            if (team == TEAM_SECOND)
                binding.root.mirrorView()

            val model = summaries[adapterPosition]
            binding.firstPlayer.text = App.context.getString(
                R.string.initals,
                model.action.player1?.playerName?.getInitials()
            )
            binding.secondPlayer.text = App.context.getString(
                R.string.initals,
                model.action.player2?.playerName?.getInitials()
            )

            binding.firstImage.loadImg(model.action.player1?.playerImage.toString())
            binding.SecondImage.loadImg(model.action.player2?.playerImage.toString())
            binding.action.text = App.context.getString(R.string.substitution, actionTime)
        }
    }

    inner class ViewHolder(private val binding: ActionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            if (team == TEAM_SECOND)
                binding.root.mirrorView()

            val model = summaries[adapterPosition]
            binding.playerImage.loadImg(model.action.player?.playerImage.toString())
            binding.name.text = App.context.getString(
                R.string.initals,
                model.action.player?.playerName?.getInitials()
            )

            when (model.actionType) {
                1 -> {
                    if (model.action.goalType == 1) {
                        binding.actionType.text = App.context.getString(R.string.goal, actionTime)
                        binding.event3.setImageResource(R.drawable.ic_goal)
                    } else {
                        binding.actionType.text =
                            App.context.getString(R.string.own_goal, actionTime)
                        binding.actionType.setTextColor(Color.RED)
                        binding.event3.setImageResource(R.drawable.ic_selfgoal)
                    }
                }
                2 -> {
                    binding.actionType.text =
                        App.context.getString(R.string.yellow_card, actionTime)
                    binding.event3.setImageResource(R.drawable.ic_yellow)
                }
                3 -> {
                    binding.actionType.text = App.context.getString(R.string.red_card, actionTime)
                    binding.actionType.setTextColor(Color.RED)
                    binding.event3.setImageResource(R.drawable.ic_red)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == NORMAL_EVENT)
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
            is ViewHolder -> holder.bind()
            is SubstitutionViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int = summaries.size

    override fun getItemViewType(position: Int): Int {
        val model = summaries[position]
        return if (model.action.player == null) {
            TRANSITION
        } else {
            NORMAL_EVENT
        }
    }
}