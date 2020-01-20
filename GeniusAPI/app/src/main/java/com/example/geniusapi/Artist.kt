package com.example.geniusapi

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("meta") val meta: Any,
    @SerializedName("response") val response: ArtistResponse
)

data class ArtistResponse(
    @SerializedName("artist") val artist: Artist
)

data class Artist(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)