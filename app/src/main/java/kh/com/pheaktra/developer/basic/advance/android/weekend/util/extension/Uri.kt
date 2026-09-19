package kh.com.pheaktra.developer.basic.advance.android.weekend.util.extension

import android.content.Context
import android.net.Uri

enum class MediaType {
    IMAGE,
    VIDEO,
    UNKNOWN,
}

fun Uri.getMediaType(context: Context): MediaType {
    val mimeType = context.contentResolver.getType(this)

    return when {
        mimeType?.startsWith("image/") == true -> MediaType.IMAGE
        mimeType?.startsWith("video/") == true -> MediaType.VIDEO
        else -> MediaType.UNKNOWN
    }
}