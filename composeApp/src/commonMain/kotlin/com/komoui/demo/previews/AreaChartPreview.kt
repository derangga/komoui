package com.komoui.demo.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.komoui.components.charts.AreaChart
import com.komoui.components.charts.AreaPoint
import com.komoui.components.charts.AreaSeries
import com.komoui.themes.styles

private val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")

@Preview
@Composable
internal fun AreaChartPreview() {
    PreviewSurface {
        Text("Gradient fill")
        AreaChart(
            modifier = Modifier.fillMaxWidth(),
            chartHeight = 240.dp,
            series = listOf(
                AreaSeries(
                    key = "visitors",
                    label = "Visitors",
                    color = MaterialTheme.styles.chart1,
                    points = months.zip(listOf(186f, 305f, 237f, 209f, 273f, 214f))
                        .map { (m, v) -> AreaPoint(m, v) },
                ),
            ),
        )

        Text("Multiple series")
        AreaChart(
            modifier = Modifier.fillMaxWidth(),
            chartHeight = 240.dp,
            series = listOf(
                AreaSeries(
                    key = "desktop",
                    label = "Desktop",
                    color = MaterialTheme.styles.chart1,
                    points = months.zip(listOf(186f, 305f, 237f, 273f, 209f, 214f))
                        .map { (m, v) -> AreaPoint(m, v) },
                ),
                AreaSeries(
                    key = "mobile",
                    label = "Mobile",
                    color = MaterialTheme.styles.chart2,
                    points = months.zip(listOf(80f, 200f, 120f, 190f, 130f, 140f))
                        .map { (m, v) -> AreaPoint(m, v) },
                ),
            ),
            showLegend = true,
        )
    }
}
