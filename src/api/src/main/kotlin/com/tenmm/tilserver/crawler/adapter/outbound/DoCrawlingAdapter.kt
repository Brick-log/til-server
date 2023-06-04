package com.tenmm.tilserver.crawler.adapter.outbound

import com.tenmm.tilserver.common.domain.Url
import com.tenmm.tilserver.crawler.application.outbound.DoCrawlingPort
import com.tenmm.tilserver.crawler.domain.CssSelectorInfo
import com.tenmm.tilserver.crawler.domain.Post
import java.net.URL
import java.sql.Timestamp
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Component

@Component
class DoCrawlingAdapter : DoCrawlingPort {
    override fun crawling(url: Url, cssSelectorInfo: CssSelectorInfo): Post {
        val document = Jsoup.parse(URL(url.value), 1000 * 10)

        val title = try {
            document.getAttribute(cssSelectorInfo.titleCssSelector)
        } catch (e: Exception) {
            ""
        }

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

        return Post(
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
