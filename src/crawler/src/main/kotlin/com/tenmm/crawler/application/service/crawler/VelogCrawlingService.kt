package com.tenmm.crawler.application.service.crawler

import com.tenmm.crawler.domain.Crawling
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.sql.Timestamp
import java.text.SimpleDateFormat

class VelogCrawlingService {
    companion object {
        fun crawling(url: String): Crawling {
            var title = ""
            var createdAt: Timestamp? = null
            var description = ""

            try {
                val conn: Connection = Jsoup.connect(url)
                val document: Document = conn.get()
                var published_time: String = document.select("div.information > span:nth-child(3)").text()

                title = document.select("meta[property=og:title]").attr("content")
                description = document.select("meta[property=og:description]").attr("content")
                createdAt = Timestamp(SimpleDateFormat("yyyy년 MM월 dd일").parse(published_time).time)
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
