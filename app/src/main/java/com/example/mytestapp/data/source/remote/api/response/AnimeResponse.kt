package com.example.mytestapp.data.source.remote.api.response

data class AnimeResponse(
    val `data`: List<Data>,
    val links: Links,
    val meta: Meta,
    val pagination: Pagination
)

data class Data(
    val aired: Aired,
    val airing: Boolean,
    val approved: Boolean,
    val background: String,
    val broadcast: Broadcast,
    val demographics: List<Demographic>,
    val duration: String,
    val episodes: Int,
    val explicit_genres: List<Any>,
    val favorites: Int,
    val genres: List<Genre>,
    val images: Images,
    val licensors: List<Licensor>,
    val mal_id: Int,
    val members: Int,
    val popularity: Int,
    val producers: List<Producer>,
    val rank: Int,
    val rating: String,
    val score: Double,
    val scored_by: Int,
    val season: String,
    val source: String,
    val status: String,
    val studios: List<Studio>,
    val synopsis: String,
    val themes: List<Theme>,
    val title: String,
    val title_english: String,
    val title_japanese: String,
    val title_synonyms: List<String>,
    val titles: List<Title>,
    val trailer: Trailer,
    val type: String,
    val url: String,
    val year: Int
)

data class Links(
    val first: String,
    val last: String,
    val next: String,
    val prev: Any
)

data class Meta(
    val current_page: Int,
    val from: Int,
    val last_page: Int,
    val links: List<Link>,
    val path: String,
    val per_page: Int,
    val to: Int,
    val total: Int
)

data class Pagination(
    val current_page: Int,
    val has_next_page: Boolean,
    val items: Items,
    val last_visible_page: Int
)

data class Aired(
    val from: String,
    val prop: Prop,
    val string: String,
    val to: String
)

data class Broadcast(
    val day: String,
    val string: String,
    val time: String,
    val timezone: String
)

data class Demographic(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class Genre(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class Images(
    val jpg: Jpg,
    val webp: Webp
)

data class Licensor(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class Producer(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class Studio(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class Theme(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
)

data class Title(
    val title: String,
    val type: String
)

data class Trailer(
    val embed_url: String,
    val images: ImagesX,
    val url: String,
    val youtube_id: String
)

data class Prop(
    val from: From,
    val to: To
)

data class From(
    val day: Int,
    val month: Int,
    val year: Int
)

data class To(
    val day: Int,
    val month: Int,
    val year: Int
)

data class Jpg(
    val image_url: String,
    val large_image_url: String,
    val small_image_url: String
)

data class Webp(
    val image_url: String,
    val large_image_url: String,
    val small_image_url: String
)

data class ImagesX(
    val image_url: String,
    val large_image_url: String,
    val maximum_image_url: String,
    val medium_image_url: String,
    val small_image_url: String
)

data class Link(
    val active: Boolean,
    val label: String,
    val url: String
)

data class Items(
    val count: Int,
    val per_page: Int,
    val total: Int
)