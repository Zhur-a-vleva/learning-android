package com.example.geniusapi

class ArtistRepository(private val api: ApiInterface) {

    private val token = "phUkgtxnmHoINTFc04K0tKIC5exdUn0cZh1k3oQnUqL75HmIeEQSNlZOPPyco9iC"

    fun getArtist(id: Int): Artist {
        val response =
            api.getArtist(id,
                token)
                .execute()
        return response.body()?.response?.artist?: Artist("Artist", 0)
    }

    fun getSongsFromArtist(id : Int): Array<Song>? {
        val response = api.getSongsFromArtist(id,
            token)
            .execute()
        return response.body()?.response?.songs
    }
}