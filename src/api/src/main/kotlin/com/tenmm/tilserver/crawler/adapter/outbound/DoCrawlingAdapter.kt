package com.tenmm.tilserver.crawler.adapter.outbound

import com.tenmm.tilserver.crawler.application.outbound.DoCrawlingPort
import com.tenmm.tilserver.crawler.domain.CssSelectorInfo
import com.tenmm.tilserver.crawler.domain.Post
import com.tenmm.tilserver.crawler.domain.Url
import java.net.URL
import java.sql.Timestamp
import java.text.SimpleDateFormat
import org.jsoup.Jsoup
import org.springframework.stereotype.Component

@Component
class DoCrawlingAdapter : DoCrawlingPort {
    override fun crawling(url: Url, cssSelectorInfo: CssSelectorInfo): Post {
        val document = Jsoup.parse(URL(url.value), 1000 * 10)

        val dateFormat = SimpleDateFormat(cssSelectorInfo.dateFormat)
        val createdAt = try {
            Timestamp(dateFormat.parse(document.select(cssSelectorInfo.createdAtCssSelector).attr("content")).time)
        } catch (e: Exception) {
            Timestamp(System.currentTimeMillis())
        }

        return Post(
            title = document.select(cssSelectorInfo.titleCssSelector).attr("content"),
            description = document.select(cssSelectorInfo.descriptionCssSelector).attr("content"),
            createdAt = createdAt,
            url = url
        )
    }
}
