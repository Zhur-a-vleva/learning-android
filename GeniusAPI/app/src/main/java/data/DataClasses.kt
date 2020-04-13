package data

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("meta") val meta: Any,
    @SerializedName("response") val response: Response
)

data class Response(
    @SerializedName("artist") val artist: Artist,
    @SerializedName("songs") val songs: List<Song>
)

data class Artist(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)

data class Song(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)