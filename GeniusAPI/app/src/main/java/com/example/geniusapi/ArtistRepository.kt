package com.example.geniusapi

class ArtistRepository(private val api: ApiInterface) {

    fun getArtist(id: Int): Artist {
        val response =
            api.getArtist(id,
                "phUkgtxnmHoINTFc04K0tKIC5exdUn0cZh1k3oQnUqL75HmIeEQSNlZOPPyco9iC")
                .execute()
        return response.body()?.response?.artist?: Artist("Artist", 0)
    }
}