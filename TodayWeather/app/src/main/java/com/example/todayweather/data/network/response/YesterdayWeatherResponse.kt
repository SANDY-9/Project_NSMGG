package com.example.todayweather.data.network.response


import com.google.gson.annotations.SerializedName

data class YesterdayWeatherResponse(
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
                    @SerializedName("stnId")
                    val stnId: String,
                    @SerializedName("stnNm")
                    val stnNm: String,
                    @SerializedName("tm")
                    val tm: String,
                    @SerializedName("avgTa")
                    val avgTa: String,
                    @SerializedName("minTa")
                    val minTa: String,
                    @SerializedName("minTaHrmt")
                    val minTaHrmt: String,
                    @SerializedName("maxTa")
                    val maxTa: String,
                    @SerializedName("maxTaHrmt")
                    val maxTaHrmt: String,
                    @SerializedName("mi10MaxRnHrmt")
                    val mi10MaxRnHrmt: String,
                    @SerializedName("hr1MaxRn")
                    val hr1MaxRn: String,
                    @SerializedName("hr1MaxRnHrmt")
                    val hr1MaxRnHrmt: String,
                    @SerializedName("sumRn")
                    val sumRn: String,
                    @SerializedName("maxInsWs")
                    val maxInsWs: String,
                    @SerializedName("maxInsWsWd")
                    val maxInsWsWd: String,
                    @SerializedName("maxInsWsHrmt")
                    val maxInsWsHrmt: String,
                    @SerializedName("maxWs")
                    val maxWs: String,
                    @SerializedName("maxWsWd")
                    val maxWsWd: String,
                    @SerializedName("maxWsHrmt")
                    val maxWsHrmt: String,
                    @SerializedName("avgWs")
                    val avgWs: String,
                    @SerializedName("hr24SumRws")
                    val hr24SumRws: String,
                    @SerializedName("maxWd")
                    val maxWd: String,
                    @SerializedName("avgTd")
                    val avgTd: String,
                    @SerializedName("minRhm")
                    val minRhm: String,
                    @SerializedName("minRhmHrmt")
                    val minRhmHrmt: String,
                    @SerializedName("avgRhm")
                    val avgRhm: String,
                    @SerializedName("avgPv")
                    val avgPv: String,
                    @SerializedName("avgPa")
                    val avgPa: String,
                    @SerializedName("maxPs")
                    val maxPs: String,
                    @SerializedName("maxPsHrmt")
                    val maxPsHrmt: String,
                    @SerializedName("minPs")
                    val minPs: String,
                    @SerializedName("minPsHrmt")
                    val minPsHrmt: String,
                    @SerializedName("avgPs")
                    val avgPs: String,
                    @SerializedName("ssDur")
                    val ssDur: String,
                    @SerializedName("sumSsHr")
                    val sumSsHr: String,
                    @SerializedName("hr1MaxIcsrHrmt")
                    val hr1MaxIcsrHrmt: String,
                    @SerializedName("hr1MaxIcsr")
                    val hr1MaxIcsr: String,
                    @SerializedName("sumGsr")
                    val sumGsr: String,
                    @SerializedName("ddMefs")
                    val ddMefs: String,
                    @SerializedName("ddMefsHrmt")
                    val ddMefsHrmt: String,
                    @SerializedName("ddMes")
                    val ddMes: String,
                    @SerializedName("ddMesHrmt")
                    val ddMesHrmt: String,
                    @SerializedName("sumDpthFhsc")
                    val sumDpthFhsc: String,
                    @SerializedName("avgTca")
                    val avgTca: String,
                    @SerializedName("avgLmac")
                    val avgLmac: String,
                    @SerializedName("avgTs")
                    val avgTs: String,
                    @SerializedName("minTg")
                    val minTg: String,
                    @SerializedName("avgCm5Te")
                    val avgCm5Te: String,
                    @SerializedName("avgCm10Te")
                    val avgCm10Te: String,
                    @SerializedName("avgCm20Te")
                    val avgCm20Te: String,
                    @SerializedName("avgCm30Te")
                    val avgCm30Te: String,
                    @SerializedName("avgM05Te")
                    val avgM05Te: String,
                    @SerializedName("avgM10Te")
                    val avgM10Te: String,
                    @SerializedName("avgM15Te")
                    val avgM15Te: String,
                    @SerializedName("avgM30Te")
                    val avgM30Te: String,
                    @SerializedName("avgM50Te")
                    val avgM50Te: String,
                    @SerializedName("sumLrgEv")
                    val sumLrgEv: String,
                    @SerializedName("sumSmlEv")
                    val sumSmlEv: String,
                    @SerializedName("n99Rn")
                    val n99Rn: String,
                    @SerializedName("iscs")
                    val iscs: String,
                    @SerializedName("sumFogDur")
                    val sumFogDur: String
                )
            }
        }
    }
}