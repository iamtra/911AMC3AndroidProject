package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.seggmentbutton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSingleChoiceSegmentedButton() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    var transactionFilter by remember { mutableStateOf<List<TransactionModel>>(transactionList) }

    val list = listOf<TransactionType>(
        TransactionType.TRANSFER,
        TransactionType.KHQR,
        TransactionType.MOBILE_TOPUP,
        TransactionType.BILL_PAYMENT,
    )

    fun onFilter() {
        val filteredList = transactionList.filter { it.type == list[selectedIndex] }
        transactionFilter = filteredList
    }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {},
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.background.copy(0.5f),
                            contentColor = colorResource(R.color.black)
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                title = {
                    Text(
                        text = "Single Choice Segmented Button"
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    transactionFilter = transactionList
                }
            ) {
                Text("Clear")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.padding(16.dp)
            ) {
                list.forEachIndexed { index, item ->
                    SegmentedButton(
                        selected = index == selectedIndex,
                        onClick = {
                            selectedIndex = index
                            onFilter()
                        },
                        shape = RoundedCornerShape(
                            topStart = if (index == 0) 16.dp else 0.dp,
                            topEnd = if (index == list.size - 1) 16.dp else 0.dp,
                            bottomStart = if (index == 0) 16.dp else 0.dp,
                            bottomEnd = if (index == list.size - 1) 16.dp else 0.dp
                        ),
                        label = {
                            Text(
                                text = item.type
                            )
                        }
                    )
                }
            }
            transactionFilter?.forEachIndexed { index, model ->
                Row(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "${model.id} - ${model.title}")
                }
                HorizontalDivider()
            }
        }
    }
}

enum class TransactionType(val id: String, val type: String) {
    TRANSFER("02", "Transfer"),
    KHQR("05", "KHQR"),
    MOBILE_TOPUP("07", "TopUp"),
    BILL_PAYMENT("08", "Payment"),
    BANKING("01", "Banking"),
    LOCAL_TRANSFER("03", "Local Transfer"),
    INTERNATIONAL_TRANSFER("04", "International Transfer"),
    QR_PAYMENT("06", "QR Payment"),
    DEBIT_CARD("10", "Debit Card"),
    CREDIT_CARD("11", "Credit Card"),
    ATM_WITHDRAW("12", "ATM Withdraw"),
    CASH_DEPOSIT("13", "Cash Deposit"),
    ABA_TRANSFER("14", "ABA Transfer"),
    ACLEDA_TRANSFER("15", "ACLEDA Transfer"),
    CHIPMONG_TRANSFER("16", "Chip Mong Transfer"),
    WING_TRANSFER("17", "Wing Transfer"),
    TRUE_MONEY("18", "TrueMoney"),
    MERCHANT_PAYMENT("19", "Merchant Payment"),
    POS_PAYMENT("20", "POS Payment"),
    FOOD_ORDER("21", "Food Order"),
    SHOPPING("22", "Shopping"),
    ELECTRICITY("23", "Electricity"),
    WATER("24", "Water Supply"),
    INTERNET("25", "Internet"),
    TV_SUBSCRIPTION("26", "TV Subscription"),
    SCHOOL_FEE("27", "School Fee"),
    TAX_PAYMENT("28", "Tax Payment"),
    GOVERNMENT_SERVICE("29", "Government Service"),
    BOOKING_RIDE("30", "Ride Booking")
}

data class TransactionModel(
    val id: String,
    val title: String,
    val amount: Double,
    val currency: String,
    val date: String,
    val receiver: String,
    val status: TransactionStatus,
    val type: TransactionType,
)

enum class TransactionStatus {
    SUCCESS,
    PENDING,
    FAILED
}

val transactionList = listOf(

    TransactionModel(
        id = "TRX001",
        title = "Transfer to Sok Dara",
        amount = 120.50,
        currency = "USD",
        date = "2026-05-24 08:30",
        receiver = "Sok Dara",
        status = TransactionStatus.SUCCESS,
        type = TransactionType.BILL_PAYMENT
    ),

    TransactionModel(
        id = "TRX002",
        title = "KHQR Coffee Payment",
        amount = 3.75,
        currency = "USD",
        date = "2026-05-24 09:10",
        receiver = "Brown Coffee",
        status = TransactionStatus.SUCCESS,
        type = TransactionType.KHQR
    ),

    TransactionModel(
        id = "TRX003",
        title = "Mobile Top Up",
        amount = 5.00,
        currency = "USD",
        date = "2026-05-24 10:00",
        receiver = "Smart Axiata",
        status = TransactionStatus.SUCCESS,
        type = TransactionType.MOBILE_TOPUP
    ),

    TransactionModel(
        id = "TRX004",
        title = "Electricity Bill",
        amount = 18.25,
        currency = "USD",
        date = "2026-05-23 18:45",
        receiver = "EDC Cambodia",
        status = TransactionStatus.PENDING,
        type = TransactionType.TRANSFER
    ),

    TransactionModel(
        id = "TRX005",
        title = "ABA Bank Transfer",
        amount = 250.00,
        currency = "USD",
        date = "2026-05-23 14:20",
        receiver = "Chan Makara",
        status = TransactionStatus.SUCCESS,
        type = TransactionType.MOBILE_TOPUP
    ),

    TransactionModel(
        id = "TRX006",
        title = "Food Delivery",
        amount = 12.40,
        currency = "USD",
        date = "2026-05-22 19:15",
        receiver = "Nham24",
        status = TransactionStatus.SUCCESS,
        type = TransactionType.BILL_PAYMENT
    ),

    TransactionModel(
        id = "TRX007",
        title = "ATM Cash Withdraw",
        amount = 100.00,
        currency = "USD",
        date = "2026-05-22 11:30",
        receiver = "Chip Mong ATM",
        status = TransactionStatus.SUCCESS,
        type = TransactionType.KHQR
    ),

    TransactionModel(
        id = "TRX008",
        title = "Internet Bill",
        amount = 15.99,
        currency = "USD",
        date = "2026-05-21 20:10",
        receiver = "Metfone",
        status = TransactionStatus.FAILED,
        type = TransactionType.INTERNET
    ),

    TransactionModel(
        id = "TRX009",
        title = "School Fee Payment",
        amount = 320.00,
        currency = "USD",
        date = "2026-05-20 07:50",
        receiver = "BELTEI School",
        status = TransactionStatus.SUCCESS,
        type = TransactionType.SCHOOL_FEE
    ),

    TransactionModel(
        id = "TRX010",
        title = "Wing Transfer",
        amount = 45.00,
        currency = "USD",
        date = "2026-05-19 16:25",
        receiver = "Mom Vanna",
        status = TransactionStatus.SUCCESS,
        type = TransactionType.WING_TRANSFER
    )
)

@Preview
@Composable
fun ScreenSingleChoiceSegmentedButtonPreview() {
    AppTheme() {
        ScreenSingleChoiceSegmentedButton()
    }
}