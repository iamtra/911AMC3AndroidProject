package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.spacer

data class TransactionModel(
    val transactionId: String,
    val senderName: String,
    val senderAccountNo: String,
    val receiverAccountName: String,
    val receiverAccountNo: String,
    val transactionDate: String, // yyyyMMdd -> Example 20260208,
    val transactionAmount: Double,
    val transactionCurrency: String,
    val transactionType: String,  // Bankong, Fund Transfer, Bulk Transafer, TopUp, International fun transfer
    val debitAmount: Double,
    val creditAmount: Double,
)
