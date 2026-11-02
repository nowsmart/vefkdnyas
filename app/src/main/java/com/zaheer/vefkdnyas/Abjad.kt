package com.zaheer.vefkdnyas

private object Abjad : Map<Char, UShort> by mapOf(
    'ا' to 1u,
    'آ' to 1u,
    'ء' to 1u,
    'ئ' to 1u,
    'ب' to 2u,
    'پ' to 2u,
    'ج' to 3u,
    'چ' to 3u,
    'د' to 4u,
    'ڈ' to 4u,
    'ہ' to 5u,
    'ھ' to 5u,
    'و' to 6u,
    'ز' to 7u,
    'ژ' to 7u,
    'ح' to 8u,
    'ط' to 9u,
    'ی' to 10u,
    'ے' to 10u,
    'ک' to 20u,
    'گ' to 20u,
    'ل' to 30u,
    'م' to 40u,
    'ن' to 50u,
    'ں' to 50u,
    'س' to 60u,
    'ع' to 70u,
    'ف' to 80u,
    'ص' to 90u,
    'ق' to 100u,
    'ر' to 200u,
    'ڑ' to 200u,
    'ش' to 300u,
    'ت' to 400u,
    'ٹ' to 400u,
    'ۃ' to 400u,
    'ة' to 400u,
    'ث' to 500u,
    'خ' to 600u,
    'ذ' to 700u,
    'ض' to 800u,
    'ظ' to 900u,
    'غ' to 1000u,

    // Lowercase letters
    'a' to 1u,
    'b' to 2u,
    'c' to 3u,
    'ç' to 4u,
    'd' to 5u,
    'e' to 6u,
    'f' to 7u,
    'g' to 8u,
    'ğ' to 9u,
    'h' to 10u,
    'ı' to 20u,
    'i' to 30u,
    'j' to 40u,
    'k' to 50u,
    'l' to 60u,
    'm' to 70u,
    'n' to 80u,
    'o' to 90u,
    'ö' to 100u,
    'p' to 200u,
    'r' to 300u,
    's' to 400u,
    'ş' to 500u,
    't' to 600u,
    'u' to 700u,
    'ü' to 800u,
    'v' to 900u,
    'y' to 1000u,
    'z' to 2000u,

    // Uppercase letters
    'A' to 1u,
    'B' to 2u,
    'C' to 3u,
    'Ç' to 4u,
    'D' to 5u,
    'E' to 6u,
    'F' to 7u,
    'G' to 8u,
    'Ğ' to 9u,
    'H' to 10u,
    'I' to 20u,
    'İ' to 30u,
    'J' to 40u,
    'K' to 50u,
    'L' to 60u,
    'M' to 70u,
    'N' to 80u,
    'O' to 90u,
    'Ö' to 100u,
    'P' to 200u,
    'R' to 300u,
    'S' to 400u,
    'Ş' to 500u,
    'T' to 600u,
    'U' to 700u,
    'Ü' to 800u,
    'V' to 900u,
    'Y' to 1000u,
    'Z' to 2000u
)
val TURKISH_ALPHABET = "aAbBcCçÇdDeEfFgGğĞhHıIiİjJkKlLmMnNoOöÖpPrRsSşŞtTuUüÜvVyYzZ"
val URDU_ALPHABET="اآءئبپجچدڈہھوزژحطیےکگلمنںسعفصقرڑشتۃةثخذضظغ"
val ALPHABET = TURKISH_ALPHABET + URDU_ALPHABET
fun String.calculateAbjadSum(): Int {
    this.toIntOrNull()?.let { return it }
    return this.sumOf { char ->
        Abjad[char]?.toInt() ?: 0
    }
}
fun String.toArabicDigits(): String {
    val arabicDigits = charArrayOf('٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩')
    return this.map { char ->
        if (char.isDigit()) {
            arabicDigits[char.digitToInt()]
        } else {
            char
        }
    }.joinToString("")
}