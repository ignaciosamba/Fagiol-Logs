package com.sambas.fagiollogs.core.navigation

import android.net.Uri
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Base64
import androidx.navigation.NavType
import java.nio.charset.StandardCharsets

internal class BaseParcelableNavType<T : Parcelable> : NavType<T>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): T {
        return decodeParcelable(value)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }

    companion object {
        fun <T : Parcelable> encodeParcelable(value: T): String {
            val encodedArgs = value.toBase64()
            val encodedClassName = Uri.encode(value::class.java.name)
            return "$encodedClassName;$encodedArgs"
        }

        fun <T : Parcelable> decodeParcelable(value: String): T {
            val (encodedArgsClassName, data) = value.split(";")
            val argsClassName = Uri.decode(encodedArgsClassName)
            val argsClass = Class.forName(argsClassName)
            return base64ToParcelable(data, parcelableCreator(argsClass))
        }
    }
}

private fun String.base64ToByteArray(): ByteArray {
    return Base64.decode(toByteArray(StandardCharsets.UTF_8), Base64.URL_SAFE or Base64.NO_WRAP)
}

private fun ByteArray.toBase64String(): String {
    return Base64.encodeToString(this, Base64.URL_SAFE or Base64.NO_WRAP)
}

private fun Parcelable.toBase64(): String {
    val parcel = Parcel.obtain()
    writeToParcel(parcel, 0)
    val bytes = parcel.marshall()
    parcel.recycle()
    return bytes.toBase64String()
}

private fun <T> base64ToParcelable(base64: String, creator: Parcelable.Creator<T>): T {
    val parcel = Parcel.obtain()
    val bytes = base64.base64ToByteArray()
    parcel.unmarshall(bytes, 0, bytes.size)
    parcel.setDataPosition(0)
    val result = creator.createFromParcel(parcel)
    parcel.recycle()
    return result
}

@Suppress("UNCHECKED_CAST")
private fun <T : Parcelable> parcelableCreator(parcelable: Class<*>): Parcelable.Creator<T> =
    parcelable.getDeclaredField("CREATOR").get(null) as? Parcelable.Creator<T>
        ?: throw IllegalArgumentException("Could not access CREATOR field in class ${parcelable.simpleName}")