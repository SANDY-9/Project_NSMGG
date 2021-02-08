package com.example.todayweather.data.network.response


import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

data class CurrentWeatherResponse(
    @SerializedName("response")
    val response: Response
) {
    data class Response(
        @SerializedName("header")
        val header: Header,
        @SerializedName("body")
        val body: Body
    ) {
        data class Header(
            @SerializedName("resultCode")
            val resultCode: String,
            @SerializedName("resultMsg")
            val resultMsg: String
        )

        data class Body(
            @SerializedName("dataType")
            val dataType: String,
            @SerializedName("items")
            val items: Items,
            @SerializedName("pageNo")
            val pageNo: Int,
            @SerializedName("numOfRows")
            val numOfRows: Int,
            @SerializedName("totalCount")
            val totalCount: Int
        ) {
            data class Items(
                @SerializedName("item")
                val item: List<Item>
            ) {
                data class Item(
                    @SerializedName("baseDate")
                    var baseDate: String,
                    @SerializedName("baseTime")
                    var baseTime: String,
                    @SerializedName("category")
                    var category: String,
                    @SerializedName("nx")
                    var nx: Int,
                    @SerializedName("ny")
                    var ny: Int,
                    @SerializedName("obsrValue")
                    var obsrValue: String
                )
            }
        }
    }
}

//미세먼지
@Root(name = "response", strict = false)
class FineDustResponse {
    @field:Element(name = "header")
    var header: Header? = null
        get() = field
        set(value) {
            field = value
        }
    @field:Element(name = "body")
    var body: Body? = null
        get() = field
        set(value) {
            field = value
        }

    @Root(name = "header", strict = false)
    class Header {
        @field:Element(name = "resultCode", required = false)
        var resultCode: String? = null
            get() = field
            set(value) {
                field = value
            }
        @field:Element(name = "resultMsg", required = false)
        var resultMsg: String? = null
            get() = field
            set(value) {
                field = value
            }
    }

    @Root(name = "body", strict = false)
    class Body {
        @field:Element(name = "items", required = false)
        var items: Items? = null
            get() = field
            set(value) {
                field = value
            }

        @field:Element(name = "numOfRows", required = false)
        var numOfRows: String? = null
            get() = field
            set(value) {
                field = value
            }

        @field:Element(name = "pageNo", required = false)
        var pageNo: String? = null
            get() = field
            set(value) {
                field = value
            }

        @field:Element(name = "totalCount", required = false)
        var totalCount: String? = null
            get() = field
            set(value) {
                field = value
            }

        override fun toString(): String {
            return "Body(items=$items, numOfRows=$numOfRows, pageNo=$pageNo, totalCount=$totalCount)"
        }
    }

    @Root(name = "items", strict = false)
    class Items {
        @field:ElementList(name = "items", required = false, entry = "item", inline = true)
        var item: MutableList<Item>? = arrayListOf()
            get() = field
            set(value) {
                field = value
            }
    }
    @Root(name = "item", strict = false)
    class Item {
        @field:Element(name = "dataTime", required = false)
        var dataTime: String? = null
            get() = field
            set(value) {
                field = value
            }

        @field:Element(name = "cityName", required = false)
        var cityName: String? = null
            get() = field
            set(value) {
                field = value
            }

        @field:Element(name = "so2Value", required = false)
        var so2Value: String? = null
            get() = field
            set(value) {
                field = value
            }

        @field:Element(name = "coValue", required = false)
        var coValue: String? = null
            get() = field
            set(value) {
                field = value
            }

        @field:Element(name = "o3Value", required = false)
        var o3Value: String? = null
            get() = field
            set(value) {
                field = value
            }

        @field:Element(name = "no2Value", required = false)
        var no2Value: String? = null
            get() = field
            set(value) {
                field = value
            }

        @field:Element(name = "pm10Value", required = false)
        var pm10Value: String? = null
            get() = field
            set(value) {
                field = value
            }

        @field:Element(name = "pm25Value", required = false)
        var pm25Value: String? = null
            get() = field
            set(value) {
                field = value
            }

        override fun toString(): String {
            return "Item(dataTime=$dataTime, cityName=$cityName, so2Value=$so2Value, coValue=$coValue, o3Value=$o3Value, no2Value=$no2Value, pm10Value=$pm10Value, pm25Value=$pm25Value)"
        }
    }
}

