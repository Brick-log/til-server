package com.tenmm.crawler.application.service.crawler

import com.tenmm.crawler.domain.Crawling
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class TistoryCrawlingService {
    companion object {
        fun crawling(url: String): Crawling {
            var title = ""
            var createdAt = ""
            var description = ""

            try {
                val conn: Connection = Jsoup.connect(url)
                val document: Document = conn.get()

                title = document.select("meta[property=og:title]").attr("content")
                createdAt = document.select("meta[property=article:published_time]").attr("content")
                description = document.select("meta[property=og:description]").attr("content")
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return Crawling(
                title = title,
                createdAt = createdAt,
                description = description
            )
        }
    }
}
