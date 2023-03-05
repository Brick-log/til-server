package com.tenmm.crawler

import io.grpc.tenm.crawler.CrawlerGrpcKt
import io.grpc.tenm.crawler.CrawlReply
import io.grpc.tenm.crawler.CrawlRequest
import org.springframework.stereotype.Component
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

@Component
class CrawlerService : CrawlerGrpcKt.CrawlerCoroutineImplBase() {
    override suspend fun doCrawl(request: CrawlRequest): CrawlReply {
        when (request.type) {
            "tistory" -> return getTistoryInfo(request.url)
            else -> return CrawlReply.newBuilder()
                .setTitle("")
                .setCreatedAt("")
                .build()
        }
    }
    fun getTistoryInfo(url: String): CrawlReply {
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
        return CrawlReply.newBuilder()
            .setTitle(title)
            .setCreatedAt(dttm)
            .build()
    }
}
