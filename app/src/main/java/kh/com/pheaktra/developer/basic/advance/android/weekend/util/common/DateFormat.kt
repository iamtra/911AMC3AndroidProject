package kh.com.pheaktra.developer.basic.advance.android.weekend.util.common

enum class DateFormat(val pattern: String) {
    ISO_DATE("yyyy-MM-dd"),

    DAY_MONTH_YEAR_SLASH("dd/MM/yyyy"),
    MONTH_DAY_YEAR_SLASH("MM/dd/yyyy"),

    DAY_MONTH_YEAR_DASH("dd-MM-yyyy"),
    YEAR_MONTH_DAY_SLASH("yyyy/MM/dd"),

    DAY_SHORT_MONTH_YEAR("dd MMM yyyy"),
    FULL_MONTH_DAY_YEAR("MMMM dd, yyyy")
}