package com.tenmm.tilserver.crawler.adapter.outbound

import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.crawler.application.outbound.DoURLConnectionCrawlingPort
import com.tenmm.tilserver.crawler.domain.CssSelectorInfo
import com.tenmm.tilserver.crawler.domain.ParsedPost
import java.sql.Timestamp
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Component
import java.net.HttpURLConnection
import java.net.URL

@Component
class DoURLConnectionCrawlingAdapter : DoURLConnectionCrawlingPort {
    override fun crawling(url: Url, cssSelectorInfo: CssSelectorInfo): ParsedPost {
        val connection = URL(url.value).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val pageSource = connection.inputStream.bufferedReader().use { it.readText() }

        val document = Jsoup.parse(pageSource)

        val title = document.getAttribute(cssSelectorInfo.titleCssSelector)

        val description = try {
            document.getAttribute(cssSelectorInfo.descriptionCssSelector)
        } catch (e: Exception) {
            ""
        }

        val createdAt = try {
            val dateInfo = document.getAttribute(cssSelectorInfo.createdAtCssSelector)
            Timestamp(cssSelectorInfo.dateFormat.parse(dateInfo).time)
        } catch (e: Exception) {
            try {
                val dateInfo = document.select(cssSelectorInfo.createdAtCssSelector).text()
                Timestamp(cssSelectorInfo.dateFormat.parse(dateInfo).time)
            } catch (e: Exception) {
                null
            }
        }

        connection.disconnect()

        return ParsedPost(
            title = title,
            description = description,
            createdAt = createdAt,
            url = url
        )
    }

    private fun Document.getAttribute(cssSelector: String): String {
        return this.select(cssSelector).attr("content")
    }
}
