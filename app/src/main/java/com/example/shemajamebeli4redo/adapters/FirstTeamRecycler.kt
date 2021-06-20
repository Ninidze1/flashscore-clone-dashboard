package com.example.shemajamebeli4redo.adapters

import com.example.shemajamebeli4redo.base.BaseTeamAdapter
import com.example.shemajamebeli4redo.models.TeamAction

class FirstTeamRecycler(
    summaries: MutableList<TeamAction>,
    actionTime: String,
    team: Int
) : BaseTeamAdapter(summaries, actionTime, team)