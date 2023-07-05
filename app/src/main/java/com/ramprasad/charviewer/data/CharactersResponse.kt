package com.ramprasad.charviewer.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Ramprasad on 7/3/23.
 */
data class CharactersResponse(
    @SerializedName("Abstract")
    val `abstract`: String = "",
    @SerializedName("AbstractSource")
    val abstractSource: String = "",
    @SerializedName("AbstractText")
    val abstractText: String = "",
    @SerializedName("AbstractURL")
    val abstractURL: String = "",
    @SerializedName("Answer")
    val answer: String = "",
    @SerializedName("AnswerType")
    val answerType: String = "",
    @SerializedName("Definition")
    val definition: String = "",
    @SerializedName("DefinitionSource")
    val definitionSource: String = "",
    @SerializedName("DefinitionURL")
    val definitionURL: String = "",
    @SerializedName("Entity")
    val entity: String = "",
    @SerializedName("Heading")
    val heading: String = "",
    @SerializedName("Image")
    val image: String = "",
    @SerializedName("ImageHeight")
    val imageHeight: Int = 0,
    @SerializedName("ImageIsLogo")
    val imageIsLogo: Int = 0,
    @SerializedName("ImageWidth")
    val imageWidth: Int = 0,
    @SerializedName("Infobox")
    val infobox: String = "",
    @SerializedName("meta")
    val meta: Meta = Meta(),
    @SerializedName("Redirect")
    val redirect: String = "",
    @SerializedName("RelatedTopics")
    val relatedTopics: List<RelatedTopic> = listOf(),
    @SerializedName("Results")
    val results: List<Any> = listOf(),
    @SerializedName("Type")
    val type: String = ""
)

data class Meta(
    @SerializedName("attribution")
    val attribution: Any? = Any(),
    @SerializedName("blockgroup")
    val blockgroup: Any? = Any(),
    @SerializedName("created_date")
    val createdDate: Any? = Any(),
    @SerializedName("description")
    val description: String = "",
    @SerializedName("designer")
    val designer: Any? = Any(),
    @SerializedName("dev_date")
    val devDate: Any? = Any(),
    @SerializedName("dev_milestone")
    val devMilestone: String = "",
    @SerializedName("developer")
    val developer: List<Developer> = listOf(),
    @SerializedName("example_query")
    val exampleQuery: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("is_stackexchange")
    val isStackexchange: Any? = Any(),
    @SerializedName("js_callback_name")
    val jsCallbackName: String = "",
    @SerializedName("live_date")
    val liveDate: Any? = Any(),
    @SerializedName("maintainer")
    val maintainer: Maintainer = Maintainer(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("perl_module")
    val perlModule: String = "",
    @SerializedName("producer")
    val producer: Any? = Any(),
    @SerializedName("production_state")
    val productionState: String = "",
    @SerializedName("repo")
    val repo: String = "",
    @SerializedName("signal_from")
    val signalFrom: String = "",
    @SerializedName("src_domain")
    val srcDomain: String = "",
    @SerializedName("src_id")
    val srcId: Int = 0,
    @SerializedName("src_name")
    val srcName: String = "",
    @SerializedName("src_options")
    val srcOptions: SrcOptions = SrcOptions(),
    @SerializedName("src_url")
    val srcUrl: Any? = Any(),
    @SerializedName("status")
    val status: String = "",
    @SerializedName("tab")
    val tab: String = "",
    @SerializedName("topic")
    val topic: List<String> = listOf(),
    @SerializedName("unsafe")
    val unsafe: Int = 0
)

data class Developer(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("url")
    val url: String = ""
)

data class Maintainer(
    @SerializedName("github")
    val github: String = ""
)

data class SrcOptions(
    @SerializedName("directory")
    val directory: String = "",
    @SerializedName("is_fanon")
    val isFanon: Int = 0,
    @SerializedName("is_mediawiki")
    val isMediawiki: Int = 0,
    @SerializedName("is_wikipedia")
    val isWikipedia: Int = 0,
    @SerializedName("language")
    val language: String = "",
    @SerializedName("min_abstract_length")
    val minAbstractLength: String = "",
    @SerializedName("skip_abstract")
    val skipAbstract: Int = 0,
    @SerializedName("skip_abstract_paren")
    val skipAbstractParen: Int = 0,
    @SerializedName("skip_end")
    val skipEnd: String = "",
    @SerializedName("skip_icon")
    val skipIcon: Int = 0,
    @SerializedName("skip_image_name")
    val skipImageName: Int = 0,
    @SerializedName("skip_qr")
    val skipQr: String = "",
    @SerializedName("source_skip")
    val sourceSkip: String = "",
    @SerializedName("src_info")
    val srcInfo: String = ""
)

data class RelatedTopic(
    @SerializedName("FirstURL")
    val firstURL: String = "",
    @SerializedName("Icon")
    val icon: Icon = Icon(),
    @SerializedName("Result")
    val result: String = "",
    @SerializedName("Text")
    val text: String = ""
)

data class Icon(
    @SerializedName("Height")
    val height: String = "",
    @SerializedName("URL")
    val uRL: String = "",
    @SerializedName("Width")
    val width: String = ""
)
