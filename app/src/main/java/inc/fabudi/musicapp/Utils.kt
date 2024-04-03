package inc.fabudi.musicapp

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import io.github.cdimascio.dotenv.dotenv

object Utils {
    val environmentVariables = dotenv {
        directory = "/assets"
        filename = "env"
    }
    fun String.toUrlWithUserAgent() = GlideUrl(
        this, LazyHeaders.Builder().setHeader(
            "User-Agent",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36"
        ).build()
    )

}