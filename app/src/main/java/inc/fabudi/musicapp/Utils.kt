package inc.fabudi.musicapp

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import inc.fabudi.musicapp.model.User
import io.github.cdimascio.dotenv.dotenv

object Utils {
    val environmentVariables = dotenv {
        directory = "/assets"
        filename = "env"
    }
    val userAgent: String = environmentVariables["USER-AGENT"]
    fun String.toUrlWithUserAgent() = GlideUrl(
        this, LazyHeaders.Builder().setHeader(
            "User-Agent",
            userAgent
        ).build()
    )

    fun List<User>.toCommaString() = this.joinToString(", ") { it.nickname }

    fun Number.toMmSs() =
        "${(this.toInt() / 60).toString().padStart(2, '0')}:${
            (this.toInt() % 60).toString().padStart(2, '0')
        }"

}