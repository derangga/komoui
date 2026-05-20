package com.komoui.demo.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.komoui.components.charts.BarChart
import com.komoui.components.charts.BarPoint
import com.komoui.components.charts.BarSeries
import com.komoui.themes.styles

private val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")

@Preview
@Composable
internal fun BarChartPreview() {
    PreviewSurface {
        Text("Multiple series")
        BarChart(
            modifier = Modifier.fillMaxWidth(),
            chartHeight = 240.dp,
            series = listOf(
                BarSeries(
                    key = "desktop",
                    label = "Desktop",
                    color = MaterialTheme.styles.chart1,
                    points = months.zip(listOf(186f, 305f, 237f, 273f, 209f, 214f))
                        .map { (m, v) -> BarPoint(m, v) },
                ),
                BarSeries(
                    key = "mobile",
                    label = "Mobile",
                    color = MaterialTheme.styles.chart2,
                    points = months.zip(listOf(80f, 200f, 120f, 190f, 130f, 140f))
                        .map { (m, v) -> BarPoint(m, v) },
                ),
            ),
            showLegend = true,
        )

        Text("Single series with value labels")
        BarChart(
            modifier = Modifier.fillMaxWidth(),
            chartHeight = 240.dp,
            series = listOf(
                BarSeries(
                    key = "visitors",
                    label = "Visitors",
                    color = MaterialTheme.styles.chart3,
                    points = months.zip(listOf(186f, 305f, 237f, 273f, 209f, 214f))
                        .map { (m, v) -> BarPoint(m, v) },
                ),
            ),
            showValueLabels = true,
        )
    }
}
