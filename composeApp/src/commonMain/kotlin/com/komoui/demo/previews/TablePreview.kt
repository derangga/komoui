package com.komoui.demo.previews

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.komoui.components.Table
import com.komoui.components.TableBody
import com.komoui.components.TableCell
import com.komoui.components.TableHead
import com.komoui.components.TableHeader
import com.komoui.components.TableRow

private data class Invoice(val id: String, val status: String, val method: String, val amount: String)

private val invoices = listOf(
    Invoice("INV001", "Paid", "Credit Card", "$250.00"),
    Invoice("INV002", "Pending", "PayPal", "$150.00"),
    Invoice("INV003", "Unpaid", "Bank Transfer", "$350.00"),
    Invoice("INV004", "Paid", "Credit Card", "$450.00"),
)

@Preview
@Composable
internal fun TablePreview() {
    PreviewSurface {
        Table {
            TableHeader {
                TableRow {
                    TableHead(modifier = Modifier.width(100.dp)) { Text("Invoice") }
                    TableHead(modifier = Modifier.width(120.dp)) { Text("Status") }
                    TableHead(modifier = Modifier.width(140.dp)) { Text("Method") }
                    TableHead(modifier = Modifier.width(100.dp)) { Text("Amount") }
                }
            }
            TableBody {
                invoices.forEachIndexed { index, invoice ->
                    TableRow(showBottomBorder = index != invoices.lastIndex) {
                        TableCell(modifier = Modifier.width(100.dp)) { Text(invoice.id) }
                        TableCell(modifier = Modifier.width(120.dp)) { Text(invoice.status) }
                        TableCell(modifier = Modifier.width(140.dp)) { Text(invoice.method) }
                        TableCell(modifier = Modifier.width(100.dp)) { Text(invoice.amount) }
                    }
                }
            }
        }
    }
}
