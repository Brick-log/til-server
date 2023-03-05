package com.tenmm.crawler

import com.tenmm.tilserver.protocol.CrawlerServiceGrpcKt
import com.tenmm.tilserver.protocol.CrawlingResponse
import com.tenmm.tilserver.protocol.CrawlingRequest
import org.springframework.stereotype.Component
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

@Component
class CrawlerService : CrawlerServiceGrpcKt.CrawlerServiceCoroutineImplBase() {
    override suspend fun doCrawling(request: CrawlingRequest): CrawlingResponse {
        /**
         * TODO: CrawlingRequest에 type에 따른 CrawlingResponse 반환
         */
        return CrawlingResponse.newBuilder()
            .setIdentifier("")
            .build()
        // when (request.type) {
        //     "tistory" -> return getTistoryInfo(request.url)
        //     else -> return CrawlingResponse.newBuilder()
        //         .setIdentifier("")
        //         .build()
        // }
    }
    fun getTistoryInfo(url: String): CrawlingResponse {
        var title: String = ""
        var dttm: String = ""
        try {
            val conn: Connection = Jsoup.connect(url)
            val document: Document = conn.get()

            title = document.select("meta[property=og:title]").attr("content")
            dttm = document.select("meta[property=article:published_time]").attr("content")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        /**
         * TODO: Redis에 title, dttm, Desription(?) 저장 후 CrawlingResponse에 identifier(key)로 반환
         */
        return CrawlingResponse.newBuilder()
            .setIdentifier("")
            .build()
    }
}
