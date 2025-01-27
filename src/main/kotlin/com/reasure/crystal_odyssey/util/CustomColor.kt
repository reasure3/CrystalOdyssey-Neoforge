package com.reasure.crystal_odyssey.util

import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import io.netty.buffer.ByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import kotlin.jvm.optionals.getOrDefault

/**
 * @param color ARGB32
 */
data class CustomColor(val color: UInt) {
    /**
     * Formats the color as a hexadecimal string.
     * It always returns an 8-character string in the format "#AARRGGBB".
     */
    private fun format(): String = "#${color.toString(16).uppercase().padStart(8, 'F')}"

    fun toInt(): Int = color.toInt()

    override fun toString(): String = format()

    companion object {
        val DEFAULT = CustomColor(0xFFFFFFFF.toUInt())

        val CODEC: Codec<CustomColor> = Codec.STRING.comapFlatMap(CustomColor::parse, CustomColor::format)

        val STREAM_CODEC: StreamCodec<ByteBuf, CustomColor> =
            ByteBufCodecs.STRING_UTF8.map(CustomColor::parseOrDefault, CustomColor::format)

        fun of(color: Long) = CustomColor(color.toUInt())

        fun parseOrDefault(colorCode: String): CustomColor {
            return parse(colorCode).result().getOrDefault(DEFAULT)
        }

        /**
         * @param colorCode The color code string (e.g., "#FF537425")
         * @return A DataResult of CustomColor object representing the parsed color
         */
        private fun parse(colorCode: String): DataResult<CustomColor> {
            return try {
                // Remove the "#" prefix if it exists
                val hexString = colorCode.removePrefix("#")

                // If the string is 6 characters, append "FF" for full opacity
                val normalizedHexString = when (hexString.length) {
                    6 -> hexString + "FF" // Add FF for alpha value
                    8 -> hexString
                    else -> {
                        // Invalid color code length, return default color
                        return DataResult.error { "Invalid color code length: $colorCode" }
                    }
                }

                // Parse the 16-bit hexadecimal value as UInt
                val parsedColor = normalizedHexString.toUInt(16)
                DataResult.success(CustomColor(parsedColor))
            } catch (e: Exception) {
                DataResult.error { "Failed to parse color code: $colorCode" }
            }
        }
    }
}