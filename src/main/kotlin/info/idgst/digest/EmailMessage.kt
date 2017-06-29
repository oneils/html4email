package info.idgst.digest

import org.springframework.core.io.FileSystemResource

/**
 * It's a wrapper for Digest to be send via email.
 */
data class EmailMessage(val content: String, val isContentHtml: Boolean = true,
                        val subject: String, val emailAttachment: EmailAttachment)

data class EmailAttachment(val fileResource: FileSystemResource, val inline: Boolean = true)
