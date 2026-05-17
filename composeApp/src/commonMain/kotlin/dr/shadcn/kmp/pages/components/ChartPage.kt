package dr.shadcn.kmp.pages.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shadcn.ui.components.charts.AreaChart
import com.shadcn.ui.components.charts.AreaPoint
import com.shadcn.ui.components.charts.AreaSeries
import com.shadcn.ui.components.charts.BarChart
import com.shadcn.ui.components.charts.BarPoint
import com.shadcn.ui.components.charts.BarSeries
import com.shadcn.ui.components.charts.LineChart
import com.shadcn.ui.components.charts.LinePoint
import com.shadcn.ui.components.charts.LineSeries
import com.shadcn.ui.themes.styles
import dr.shadcn.kmp.components.ContentPageWithTitle
import dr.shadcn.kmp.components.Layout

private val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")

@Composable
fun ChartPage() {
    Layout {
        Text(
            "Chart",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
        )

        Text(
            text = "Bar, line, and area charts with axis labels, animation, and a press-and-drag tooltip.",
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(4.dp))

        ContentPageWithTitle("1. Bar chart — multiple series") {
            BarChart(
                modifier = Modifier.fillMaxWidth(),
                chartHeight = 240.dp,
                series = listOf(
                    BarSeries(
                        key = "desktop",
                        label = "Desktop",
                        color = MaterialTheme.styles.chart1,
                        points = listOf(
                            BarPoint("Jan", 186f),
                            BarPoint("Feb", 305f),
                            BarPoint("Mar", 237f),
                            BarPoint("Apr", 273f),
                            BarPoint("May", 209f),
                            BarPoint("Jun", 214f),
                        ),
                    ),
                    BarSeries(
                        key = "mobile",
                        label = "Mobile",
                        color = MaterialTheme.styles.chart2,
                        points = listOf(
                            BarPoint("Jan", 80f),
                            BarPoint("Feb", 200f),
                            BarPoint("Mar", 120f),
                            BarPoint("Apr", 190f),
                            BarPoint("May", 130f),
                            BarPoint("Jun", 140f),
                        ),
                    ),
                ),
                showLegend = true,
            )
        }

        ContentPageWithTitle("2. Bar chart with value labels") {
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

        ContentPageWithTitle("3. Line chart — single series, smooth") {
            LineChart(
                modifier = Modifier.fillMaxWidth(),
                chartHeight = 240.dp,
                series = listOf(
                    LineSeries(
                        key = "visitors",
                        label = "Visitors",
                        color = MaterialTheme.styles.chart1,
                        points = months.zip(listOf(186f, 305f, 237f, 273f, 209f, 214f))
                            .map { (m, v) -> LinePoint(m, v) },
                    ),
                ),
            )
        }

        ContentPageWithTitle("4. Line chart — multiple series") {
            LineChart(
                modifier = Modifier.fillMaxWidth(),
                chartHeight = 240.dp,
                series = listOf(
                    LineSeries(
                        key = "desktop",
                        label = "Desktop",
                        color = MaterialTheme.styles.chart1,
                        points = months.zip(listOf(186f, 305f, 237f, 273f, 209f, 214f))
                            .map { (m, v) -> LinePoint(m, v) },
                    ),
                    LineSeries(
                        key = "mobile",
                        label = "Mobile",
                        color = MaterialTheme.styles.chart2,
                        points = months.zip(listOf(80f, 200f, 120f, 190f, 130f, 140f))
                            .map { (m, v) -> LinePoint(m, v) },
                    ),
                ),
                showLegend = true,
            )
        }

        ContentPageWithTitle("5. Area chart with gradient") {
            AreaChart(
                modifier = Modifier.fillMaxWidth(),
                chartHeight = 240.dp,
                series = listOf(
                    AreaSeries(
                        key = "visitors",
                        label = "Visitors",
                        color = MaterialTheme.styles.chart1,
                        points = months.zip(listOf(186f, 305f, 237f, 273f, 209f, 214f))
                            .map { (m, v) -> AreaPoint(m, v) },
                    ),
                ),
            )
        }

        ContentPageWithTitle("6. Area chart — multiple series") {
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
}
