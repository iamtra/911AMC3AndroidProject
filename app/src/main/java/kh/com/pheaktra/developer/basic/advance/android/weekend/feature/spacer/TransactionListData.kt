package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.spacer

val transactionList = listOf<TransactionModel>(
    TransactionModel(
        transactionId = "TXN0001",
        senderName = "Buon Pheaktra",
        senderAccountNo = "001234567890",
        receiverAccountName = "Sok Dara",
        receiverAccountNo = "009876543210",
        transactionDate = "20260201",
        transactionAmount = 150.00,
        transactionCurrency = "USD",
        transactionType = "Fund Transfer",
        debitAmount = 150.00,
        creditAmount = 0.0
    ),
    TransactionModel(
        transactionId = "TXN0002",
        senderName = "Buon Pheaktra",
        senderAccountNo = "001234567890",
        receiverAccountName = "Electricity Cambodia",
        receiverAccountNo = "EAC000112233",
        transactionDate = "20260202",
        transactionAmount = 45.50,
        transactionCurrency = "USD",
        transactionType = "Banking",
        debitAmount = 45.50,
        creditAmount = 0.0
    ),
    TransactionModel(
        transactionId = "TXN0003",
        senderName = "Buon Pheaktra",
        senderAccountNo = "001234567890",
        receiverAccountName = "Smart Axiata",
        receiverAccountNo = "SMART098765",
        transactionDate = "20260202",
        transactionAmount = 10.00,
        transactionCurrency = "USD",
        transactionType = "TopUp",
        debitAmount = 10.00,
        creditAmount = 0.0
    ),
    TransactionModel(
        transactionId = "TXN0004",
        senderName = "Sok Dara",
        senderAccountNo = "009876543210",
        receiverAccountName = "Buon Pheaktra",
        receiverAccountNo = "001234567890",
        transactionDate = "20260203",
        transactionAmount = 500.00,
        transactionCurrency = "USD",
        transactionType = "Fund Transfer",
        debitAmount = 0.0,
        creditAmount = 500.00
    ),
    TransactionModel(
        transactionId = "TXN0005",
        senderName = "Buon Pheaktra",
        senderAccountNo = "001234567890",
        receiverAccountName = "AEON Mall Phnom Penh",
        receiverAccountNo = "AEON778899",
        transactionDate = "20260204",
        transactionAmount = 120.75,
        transactionCurrency = "USD",
        transactionType = "Banking",
        debitAmount = 120.75,
        creditAmount = 0.0
    ),
    TransactionModel(
        transactionId = "TXN0006",
        senderName = "Buon Pheaktra",
        senderAccountNo = "001234567890",
        receiverAccountName = "Global Trading Ltd",
        receiverAccountNo = "INTL445566",
        transactionDate = "20260205",
        transactionAmount = 1000.00,
        transactionCurrency = "USD",
        transactionType = "International Fund Transfer",
        debitAmount = 1000.00,
        creditAmount = 0.0
    ),
    TransactionModel(
        transactionId = "TXN0007",
        senderName = "Payroll System",
        senderAccountNo = "PAYROLL001",
        receiverAccountName = "Buon Pheaktra",
        receiverAccountNo = "001234567890",
        transactionDate = "20260206",
        transactionAmount = 800.00,
        transactionCurrency = "USD",
        transactionType = "Bulk Transfer",
        debitAmount = 0.0,
        creditAmount = 800.00
    ),
    TransactionModel(
        transactionId = "TXN0008",
        senderName = "Buon Pheaktra",
        senderAccountNo = "001234567890",
        receiverAccountName = "Water Supply Phnom Penh",
        receiverAccountNo = "WATER334455",
        transactionDate = "20260206",
        transactionAmount = 25.30,
        transactionCurrency = "USD",
        transactionType = "Banking",
        debitAmount = 25.30,
        creditAmount = 0.0
    ),
    TransactionModel(
        transactionId = "TXN0009",
        senderName = "Buon Pheaktra",
        senderAccountNo = "001234567890",
        receiverAccountName = "Online Shop KH",
        receiverAccountNo = "SHOP112233",
        transactionDate = "20260207",
        transactionAmount = 65.99,
        transactionCurrency = "USD",
        transactionType = "Fund Transfer",
        debitAmount = 65.99,
        creditAmount = 0.0
    ),
    TransactionModel(
        transactionId = "TXN0010",
        senderName = "ABA Bank Saving",
        senderAccountNo = "SAV998877",
        receiverAccountName = "Buon Pheaktra",
        receiverAccountNo = "001234567890",
        transactionDate = "20260208",
        transactionAmount = 300.00,
        transactionCurrency = "USD",
        transactionType = "Banking",
        debitAmount = 0.0,
        creditAmount = 300.00
    )
)

val baseTransaction = TransactionModel(
    transactionId = "TXN0000",
    senderName = "Buon Pheaktra",
    senderAccountNo = "001234567890",
    receiverAccountName = "Demo Receiver",
    receiverAccountNo = "000000000000",
    transactionDate = "20260201",
    transactionAmount = 100.0,
    transactionCurrency = "USD",
    transactionType = "Fund Transfer",
    debitAmount = 100.0,
    creditAmount = 0.0
)