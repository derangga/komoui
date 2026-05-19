package com.komoui.demo.pages.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.komoui.components.Calendar
import com.komoui.components.CalendarDefaults
import com.komoui.components.CalendarSelectionMode
import com.komoui.components.DateRange
import com.komoui.components.DateRangePicker
import com.komoui.components.DateSelectionMode
import com.komoui.kmp.format
import com.komoui.themes.styles
import com.komoui.utils.now
import com.komoui.demo.components.ContentPageWithTitle
import com.komoui.demo.components.Layout
import kotlinx.datetime.LocalDate

@Composable
fun CalendarPage() {
    var selectedDateAll by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
    var customDate by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
    var selectedDatePast by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
    var selectedDateFuture by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
    var selectedRange by remember { mutableStateOf<DateRange?>(null) }
    var selectedRangePicker by remember { mutableStateOf<DateRange?>(null) }
    var selectedRangeFuture by remember { mutableStateOf<DateRange?>(null) }
    Layout {
        Text(
            "Calendar",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
        )

        Text(
            text = "A date field component that allows users to enter and edit date.",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        ContentPageWithTitle("1. Basic calendar usage") {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Calendar(
                    selectedDate = selectedDateAll,
                    onDateSelected = { date ->
                        selectedDateAll = date
                    },
                )

                Text(
                    text = "Selected Date (All): ${
                        selectedDateAll?.format("MMM dd, yyyy") ?: "None"
                    }",
                    color = MaterialTheme.styles.foreground,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        ContentPageWithTitle("2. Calendar with date past or today") {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Calendar(
                    selectedDate = selectedDatePast,
                    onDateSelected = { date ->
                        selectedDatePast = date
                    },
                    dateSelectionMode = DateSelectionMode.PastOrToday
                )

                Text(
                    text = "Selected Date (Past): ${
                        selectedDatePast?.format("MMM dd, yyyy") ?: "None"
                    }",
                    color = MaterialTheme.styles.foreground,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        ContentPageWithTitle("3. Calendar with date future or today") {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Calendar(
                    selectedDate = selectedDateFuture,
                    onDateSelected = { date ->
                        selectedDateFuture = date
                    },
                    dateSelectionMode = DateSelectionMode.FutureOrToday
                )

                Text(
                    text = "Selected Date (Future): ${
                        selectedDateFuture?.format("MMM dd, yyyy") ?: "None"
                    }",
                    color = MaterialTheme.styles.foreground,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        ContentPageWithTitle("4. Range calendar") {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Calendar(
                    selectionMode = CalendarSelectionMode.Range(
                        selectedRange = selectedRange,
                        onRangeSelected = { range ->
                            selectedRange = range
                        }
                    )
                )

                Text(
                    text = if (selectedRange != null) {
                        "Range: ${selectedRange!!.start.format("MMM dd, yyyy")} - ${selectedRange!!.end.format("MMM dd, yyyy")}"
                    } else {
                        "Range: None"
                    },
                    color = MaterialTheme.styles.foreground,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        ContentPageWithTitle("5. Date Range Picker") {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DateRangePicker(
                    selectedRange = selectedRangePicker,
                    onRangeSelected = { range ->
                        selectedRangePicker = range
                    }
                )
            }
        }

        ContentPageWithTitle("6. Range calendar (future or today)") {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Calendar(
                    selectionMode = CalendarSelectionMode.Range(
                        selectedRange = selectedRangeFuture,
                        onRangeSelected = { range ->
                            selectedRangeFuture = range
                        }
                    ),
                    dateSelectionMode = DateSelectionMode.FutureOrToday
                )

                Text(
                    text = if (selectedRangeFuture != null) {
                        "Booking: ${selectedRangeFuture!!.start.format("MMM dd, yyyy")} - ${selectedRangeFuture!!.end.format("MMM dd, yyyy")}"
                    } else {
                        "Booking: None"
                    },
                    color = MaterialTheme.styles.foreground,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        ContentPageWithTitle("7. Custom calendar color") {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val chart3 = MaterialTheme.styles.chart3
                val chart4 = MaterialTheme.styles.chart4
                Calendar(
                    selectedDate = customDate,
                    onDateSelected = { date ->
                        customDate = date
                    },
                    colors = CalendarDefaults.colors {
                        copy(
                            rightIconTint = chart4,
                            leftIconTint = chart4,
                            monthText = chart4,
                            yearText = chart4,
                            weekDaysText = chart4,
                            dateCellBgStyle = dateCellBgStyle.copy(
                                selectedDate = chart3,
                                todayUnselectedBg = chart3.copy(alpha = 0.1f),
                            ),
                            dateCellTextStyle = dateCellTextStyle.copy(
                                todayUnselected = chart3,
                                currentMonthUnselected = chart3,
                                previousAndNextDateMonth = chart3.copy(alpha = 0.3f),
                            )
                        )
                    }
                )
            }
        }
    }
}