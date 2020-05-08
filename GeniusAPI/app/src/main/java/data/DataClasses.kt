package data

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("response") val response: Response
)

data class Response(
    @SerializedName("artist") val artist: Artist,
    @SerializedName("songs") val songs: List<Song>
)

data class Artist(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("description") val description: Description
)

data class Song(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)

data class Description(
    @SerializedName("plain") val text: String
)
