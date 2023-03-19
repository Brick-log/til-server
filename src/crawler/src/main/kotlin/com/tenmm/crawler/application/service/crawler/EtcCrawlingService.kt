package com.tenmm.crawler.application.service.crawler

import com.tenmm.crawler.domain.Crawling
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.sql.Timestamp
import java.text.SimpleDateFormat

class EtcCrawlingService {
    companion object {
        fun crawling(url: String): Crawling {
            var title = ""
            var createdAt: Timestamp? = null
            var description = ""

            try {
                val conn: Connection = Jsoup.connect(url)
                val document: Document = conn.get()
                val published_time: String = document.select("meta[property=article:published_time]").attr("content")

                title = document.select("meta[property=og:title]").attr("content")
                description = document.select("meta[property=og:description]").attr("content")
                createdAt = Timestamp(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(published_time).time)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return Crawling(
                title = title,
                createdAt = createdAt,
                description = description,
                url = url
            )
        }
    }
}
