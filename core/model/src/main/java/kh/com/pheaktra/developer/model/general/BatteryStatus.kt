package kh.com.pheaktra.developer.model.general

data class BatteryStatus(
    val percentage: Int = 0,
    val isCharging: Boolean = false,
)