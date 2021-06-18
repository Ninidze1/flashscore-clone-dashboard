package com.example.shemajamebeli4redo.models

import com.google.gson.annotations.SerializedName


data class Match(
    val match: Game,
    val resultCode: Int
)

data class Game(
    val matchDate: Long,
    val matchSummary: MatchSummary,
    val matchTime: Double,
    val stadiumAdress: String,
    val team1: Team,
    val team2: Team
)

data class Team(
    val ballPosition: Int?,
    val score: Int?,
    val teamImage: String,
    val teamName: String
)

data class MatchSummary(
    val summaries: List<Summaries>
)

data class Summaries(
    val actionTime: String,
    val team1Action: List<TeamAction>? = null,
    val team2Action: List<TeamAction>? = null
)

data class TeamAction(
    val action: Action,
    val actionType: Int,
    val teamType: Int,
)

data class Action(
    val goalType: Int? = null,
    val player: Player? = null,
    val player1: Player? = null,
    val player2: Player? = null
)

data class Player(
    val playerImage: String? =null,
    val playerName: String
)