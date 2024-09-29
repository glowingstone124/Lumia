package ind.glowingstone.lumia
import io.ktor.http.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.http.content.*
import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.respond
import io.ktor.server.response.respondFile
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import java.io.File
import java.util.*

fun main() {
    val workDir = File("/opt/server")
    if (!workDir.exists()) {
        workDir.mkdir()
    }

    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            jackson()
        }
        install(CallLogging)

        routing{
            get("/download/{filename}") {
                val fileName = call.parameters["filename"]

                if (fileName != null) {
                    val file = File(workDir, fileName)

                    if (file.exists()) {
                        call.respondFile(file)
                    } else {
                        call.respondText("File not found", status = HttpStatusCode.NotFound)
                    }
                } else {
                    call.respondText("Filename parameter is missing", status = HttpStatusCode.BadRequest)
                }

            }

            get("/files") {
                val files = workDir.listFiles()?.map { it.name } ?: emptyList()
                call.respond(files)
            }
        }
    }.start(wait = true)
}
