# Chart components

Hand-rolled Compose Multiplatform chart primitives in `commonMain` (Android + iOS). No external charting lib. All shared logic lives in `ChartCommon.kt` next to this file.

Public charts: `BarChart`, `LineChart`, `AreaChart`. Demo lives at `composeApp/src/commonMain/kotlin/dr/shadcn/kmp/pages/components/ChartPage.kt`.

## Pitfalls — read first

These cost real debug time. Don't repeat them.

### 1. `Canvas` collapses to 0 height unless you give it a FIXED height

`Canvas` is internally `Spacer(modifier.drawBehind(...))`. `Spacer`'s `MeasurePolicy` only takes a non-zero size when `Constraints.hasFixedHeight` (i.e. `minHeight == maxHeight`). `defaultMinSize(minHeight = X.dp)` only lifts the minimum — `maxHeight` stays whatever the parent gave it (often `Infinity` inside a scrolling Column), so Canvas measures to **0** and everything collapses against `plotRect.bottom = 0`. Bars look like flat pills near the top, y-axis labels stack on top of x-axis labels.

**Rule:** wrap the chart canvas in a `Box(Modifier.height(chartHeight))` and give the Canvas `Modifier.fillMaxSize()`. Never rely on `defaultMinSize` alone for chart sizing.

### 2. `MaterialTheme.*` only works at the @Composable level, not inside `DrawScope`

The `Canvas { ... }` lambda runs in `DrawScope`, not in a `@Composable` context. Reading `MaterialTheme.styles.popover` inside the draw lambda **does not compile / does not recompose correctly**. Always read theme values into local `val`s outside the Canvas:

```kotlin
val gridColor = MaterialTheme.styles.border
val labelColor = MaterialTheme.styles.mutedForeground
val dotInnerColor = MaterialTheme.styles.popover
Canvas(...) {
    drawCircle(color = dotInnerColor, ...)  // use the captured val
}
```

### 3. Text on Canvas needs `TextMeasurer`, not Compose `Text`

For axis labels and value labels we draw text into the canvas:
```kotlin
val textMeasurer = rememberTextMeasurer()       // composable scope
// inside Canvas:
val measured = textMeasurer.measure(label, labelStyle)
drawText(textLayoutResult = measured, color = labelColor, topLeft = Offset(x, y))
```
Don't try to position a `Text` composable at arbitrary pixel coordinates derived from data — that won't align with grid lines.

### 4. Drag-scrub tooltip conflicts with `Modifier.horizontalScroll` — gesture ownership

Both modifiers compete for the same pointer stream, but they use **incompatible claim strategies**:

- **`Modifier.horizontalScroll`** (`Modifier.scrollable` under the hood) is **claim-after-touch-slop**: it does NOT consume the down event, watches for movement, and only consumes once the drag distance exceeds touch slop (~3–8dp). That lets quick taps fall through to children — scroll only "wins" once it's clear the user is dragging.
- **`Modifier.chartScrub`** is **claim-on-down**: in `awaitPointerEventScope` it calls `change.consume()` on the very first down event. This is deliberate — the UX we want is "press anywhere, tooltip appears under the finger instantly and follows it." A touch-slop delay would make the tooltip feel sluggish.

Result: `chartScrub` claims the gesture before `scrollable` ever sees enough movement to decide. The chart never scrolls.

We resolve it by force-disabling the tooltip in scrollable mode:
```kotlin
val effectiveShowTooltip = showTooltip && !scrollable
```

If you want both to coexist, pick one of:
1. **Long-press-then-scrub** (Apple Stocks pattern): wait for `awaitLongPressOrCancellation` before claiming. Plain drag = scroll; long press + drag = scrub. Intuitive and common.
2. **Tap-only tooltip**: use `detectTapGestures` on tap-release — doesn't consume the down event, so scroll still works. Loses smooth scrubbing.
3. **Two-pointer scrub**: only claim on second finger. Works, but most users won't discover the gesture.

Don't just remove the `&& !scrollable` guard — without changing the claim strategy you'll silently break scroll.

### 5. `LaunchedEffect(series)` restarts the animation on **content-equal** lists

`series: List<XSeries>` is freshly allocated on every parent recomposition. `BarSeries` is a data class with `points: List<BarPoint>` (also data classes), so `list1.equals(list2)` is `true` when content matches and `LaunchedEffect` does **not** re-run. Good. But if you key on a `List<...>` of non-data-class wrappers, equality falls back to reference and the animation restarts forever. Keep series and point types as `@Immutable data class`.

### 6. `BoxWithConstraints` viewport math is in `Dp`, not `Int`

Inside `BoxWithConstraints`, `maxWidth` is already a `Dp` (NOT `constraints.maxWidth` which is `Int` pixels). Multiplying by `Int` columnCount and `Dp` minColumnWidth gives Dp directly — no `with(density)` needed:
```kotlin
val viewportDp = maxWidth                       // Dp
val rawContentDp = minColumnWidth * columnCount // Dp
```

### 7. The y-domain must include 0 for bars/areas; use `niceCeil` for the top

`ChartCommon.computeDomain` already handles this. Don't hand-roll min/max in a new chart — call `computeDomain(min, max, override)` and `yTicks(domain, count)`.

## Architecture in 60 seconds

```
ChartCommon.kt
  ├─ data classes:  BarSeries/BarPoint, LineSeries/LinePoint, AreaSeries/AreaPoint
  ├─ ChartAxisOptions, ChartScrubState
  ├─ niceCeil, computeDomain, yTicks
  ├─ DrawScope helpers:  drawAxesAndGrid, drawYAxisOnly, drawScrubIndicator
  ├─ Modifier.chartScrub  (gesture)
  └─ @Composable ChartTooltip, ChartLegend, chartLabelStyle, rememberChartScrubState

BarChart.kt / LineChart.kt / AreaChart.kt
  Each is a single public @Composable. Same outer layout:

    Column(modifier) {
      Row(.fillMaxWidth().height(chartHeight)) {
        Canvas(yAxisWidthDp, fillMaxHeight)         // pinned y-axis (drawYAxisOnly)
        BoxWithConstraints(weight(1f), fillMaxHeight) {
          Box(fillMaxSize + optional horizontalScroll) {
            Canvas(width(contentWidthDp), fillMaxHeight, chartScrub?) {
              drawAxesAndGrid (with showY = false)
              draw bars / lines / area
              drawScrubIndicator if scrubbing
            }
            tooltip overlay (positioned by Modifier.padding)
          }
        }
      }
      if (showLegend) ChartLegend(...)
    }
```

## Recipe — adding a new chart family

Worked example: `ScatterChart`. Same shape applies to `PieChart`, `RadarChart`, `RadialChart` (those need polar-coordinate primitives instead of `drawAxesAndGrid`, so add them to ChartCommon first).

1. **Add data types to `ChartCommon.kt`** next to the existing ones:
   ```kotlin
   @Immutable
   data class ScatterSeries(
       val key: String,
       val label: String,
       val color: Color,
       val points: List<ScatterPoint>,
   )
   @Immutable
   data class ScatterPoint(val x: String, val y: Float)
   ```

2. **Create `ScatterChart.kt`** in `components/charts/`. Copy `LineChart.kt` as the starting template — it has the closest skeleton (single canvas, scrub, scrollable). Replace the path-building section with whatever your chart draws (e.g. `drawCircle` per point).

3. **Pull theme colors at composable scope, not inside Canvas:**
   ```kotlin
   val gridColor = MaterialTheme.styles.border
   val labelColor = MaterialTheme.styles.mutedForeground
   val indicatorColor = MaterialTheme.styles.mutedForeground
   ```

4. **Compute domain and ticks via the shared helpers** — do NOT roll your own:
   ```kotlin
   val domain = computeDomain(minValue, maxValue, axisOptions.yDomain)
   val ticks = yTicks(domain, axisOptions.yTickCount)
   ```

5. **Use the standard layout** (Row with pinned y-axis + BoxWithConstraints plot). Copy verbatim from `LineChart.kt` — the structure is identical for every Cartesian chart.

6. **For the plot canvas, pass `plotAxisOptions = axisOptions.copy(showY = false)`** to `drawAxesAndGrid`. The y-axis labels are drawn separately in the pinned canvas via `drawYAxisOnly`.

7. **Add a `chartHeight: Dp = 220.dp` parameter** (NOT just a `Modifier.height(...)` from the caller — see pitfall #1).

8. **Support `scrollable: Boolean = false` + `minColumnWidth: Dp = 56.dp`** by copying the `BoxWithConstraints { ... }` block. Force-disable tooltip in scrollable mode (pitfall #4).

9. **Wire the demo:**
   - Add a section to `ChartPage.kt` with `ContentPageWithTitle("N. Scatter chart") { ScatterChart(...) }`.
   - Pull colors from `MaterialTheme.styles.chart1..chart5` (already defined in `ShadcnStyles.kt`).

10. **Verify on both targets:**
    ```bash
    ./gradlew :shadcn-ui-kmp:compileCommonMainKotlinMetadata
    ./gradlew :composeApp:compileDebugKotlinAndroid
    ./gradlew :shadcn-ui-kmp:compileKotlinIosSimulatorArm64    # slow first run
    ```

## Files at a glance

- `ChartCommon.kt` — types, helpers, drawing primitives
- `BarChart.kt` / `LineChart.kt` / `AreaChart.kt` — public charts
- `composeApp/src/commonMain/kotlin/dr/shadcn/kmp/pages/components/ChartPage.kt` — demo
- `composeApp/src/commonMain/kotlin/dr/shadcn/kmp/{Route.kt,HomeContent.kt,navigation/MainNavigation.kt}` — demo route wiring (Chart route already exists; only add new demo sections, no new route needed for chart variants)
