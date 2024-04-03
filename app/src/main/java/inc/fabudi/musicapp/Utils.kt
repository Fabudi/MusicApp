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
            Utils.environmentVariables["USER-AGENT"]
        ).build()
    )

}