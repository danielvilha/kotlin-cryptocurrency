package com.danielvilha.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Daniel Ferreira de Lima Vilha 26/11/2023.
 */
data class CoinDetail(
    val description: String = "",
    @SerializedName("development_status")
    val developmentStatus: String = "",
    @SerializedName("first_data_at")
    val firstDataAt: String = "",
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean = false,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String = "",
    val id: String = "",
    @SerializedName("is_active")
    val isActive: Boolean = false,
    @SerializedName("is_new")
    val isNew: Boolean = false,
    @SerializedName("last_data_at")
    val lastDataAt: String = "",
    val links: Links,
    @SerializedName("links_extended")
    val linksExtended: List<LinksExtended>,
    val message: String = "",
    val name: String = "",
    @SerializedName("open_source")
    val openSource: Boolean = false,
    @SerializedName("org_structure")
    val orgStructure: String = "",
    @SerializedName("proof_type")
    val proofType: String = "",
    val rank: Int = 0,
    @SerializedName("started_at")
    val startedAt: String = "",
    val symbol: String = "",
    val tags: List<Tag>,
    val team: List<TeamMember>,
    val type: String = "",
    @SerializedName("whitepaper")
    val whitePaper: Whitepaper
)
