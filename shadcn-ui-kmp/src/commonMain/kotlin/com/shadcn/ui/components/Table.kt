package com.shadcn.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.UnfoldMore
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.shadcn.ui.themes.radius
import com.shadcn.ui.themes.styles

/**
 * Root Table primitive. Wraps content in a horizontally scrollable container with a rounded
 * border, mirroring shadcn `<Table>`. Children are typically [TableHeader], [TableBody],
 * [TableFooter], and an optional [TableCaption] outside the scroll container.
 *
 * On mobile, the table is intentionally not shrunk to viewport — set explicit widths on cells
 * (typically via per-column [Dp] widths) and let users scroll horizontally.
 *
 * @param modifier The modifier to be applied to the outer container.
 * @param scrollState Horizontal scroll state shared by header and body.
 * @param content Table sections. Provide [TableHeader], [TableBody], and optionally [TableFooter].
 */
@Composable
fun Table(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    content: @Composable ColumnScope.() -> Unit
) {
    val styles = MaterialTheme.styles
    val radius = MaterialTheme.radius
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(radius.md))
            .border(1.dp, styles.border, RoundedCornerShape(radius.md))
            .horizontalScroll(scrollState),
        content = content
    )
}

/**
 * Header section of a [Table]. Stack [TableRow]s vertically. Each header row renders a bottom
 * border to separate it from the body.
 */
@Composable
fun TableHeader(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier, content = content)
}

/**
 * Body section of a [Table]. Stack [TableRow]s vertically. The last row should pass
 * `showBottomBorder = false` to mimic shadcn `[&_tr:last-child]:border-0`.
 */
@Composable
fun TableBody(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier, content = content)
}

/**
 * Footer section of a [Table]. Renders a muted background and a top border, matching shadcn
 * `<TableFooter>`.
 */
@Composable
fun TableFooter(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    val styles = MaterialTheme.styles
    Column(
        modifier = modifier
            .background(styles.muted)
            .border(1.dp, styles.border, RoundedCornerShape(0.dp)),
        content = content
    )
}

/**
 * A single row in a [Table]. Children are laid out horizontally inside a [RowScope]. The row
 * draws a bottom border (toggleable via [showBottomBorder]) and a selected/hover background.
 *
 * @param modifier The modifier to be applied to the row. Pass [Modifier.width] to make a row
 *      span the same total width as its siblings under [Table]'s horizontal scroll.
 * @param onClick Optional click handler. When non-null, the row is clickable.
 * @param selected When true, the row paints a muted background to indicate selection.
 * @param showBottomBorder When false, the row omits its bottom border — pass false for the
 *      final body row.
 * @param content Cells of the row — typically [TableHead] or [TableCell].
 */
@Composable
fun TableRow(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    selected: Boolean = false,
    showBottomBorder: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val styles = MaterialTheme.styles
    val background = if (selected) styles.muted else styles.card
    val clickModifier = if (onClick != null) {
        Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )
    } else Modifier
    Row(
        modifier = modifier
            .background(background)
            .then(clickModifier)
            .then(
                if (showBottomBorder) {
                    Modifier.bottomBorder(styles.border)
                } else Modifier
            ),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

/**
 * Header cell. 40.dp minimum height, 8.dp horizontal padding, medium-weight text in
 * [com.shadcn.ui.themes.ShadcnStyles.mutedForeground]. Wrap text in [Text] for the typical case.
 *
 * @param modifier The modifier to be applied to the cell. Pass [Modifier.width] to set the
 *      column's width — header and body cells should share the same width.
 * @param content Cell content, usually a [Text].
 */
@Composable
fun TableHead(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .height(40.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

/**
 * Body cell. 8.dp padding, [com.shadcn.ui.themes.ShadcnStyles.foreground] text color.
 *
 * @param modifier The modifier to be applied to the cell. Pass [Modifier.width] to set the
 *      column's width — header and body cells should share the same width.
 * @param content Cell content.
 */
@Composable
fun TableCell(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

/**
 * Caption rendered below a [Table] in muted small text. Place outside the [Table] container.
 *
 * @param text The caption text.
 * @param modifier The modifier to be applied to the caption.
 */
@Composable
fun TableCaption(text: String, modifier: Modifier = Modifier) {
    val styles = MaterialTheme.styles
    Text(
        text = text,
        color = styles.mutedForeground,
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier.padding(top = 8.dp)
    )
}

/** Sort direction for a [DataTable] column. [NONE] means the column is unsorted. */
enum class SortDirection { NONE, ASC, DESC }

/**
 * Current sort state of a [DataTable].
 *
 * @param columnId The id of the column being sorted, or null when no column is sorted.
 * @param direction The active sort direction.
 */
data class SortState(
    val columnId: String? = null,
    val direction: SortDirection = SortDirection.NONE
)

/**
 * Column definition for [DataTable].
 *
 * @param id Stable identifier used as the sort key and visibility key.
 * @param header Header label text. Use [headerContent] for fully custom headers (the [header]
 *      string is still used as the accessible label and falls back to text when no custom
 *      content is provided).
 * @param width Fixed Dp width applied to header and body cells in this column.
 * @param sortable When true, clicking the header cycles NONE -> ASC -> DESC -> NONE. Requires
 *      [comparator].
 * @param comparator Comparator used when [sortable] is true.
 * @param headerContent Optional custom header cell content. When null the [header] string is
 *      rendered as a medium-weight muted label.
 * @param cell Renders one cell for the given row item.
 */
data class DataTableColumn<T>(
    val id: String,
    val header: String,
    val width: Dp,
    val sortable: Boolean = false,
    val comparator: Comparator<T>? = null,
    val headerContent: (@Composable RowScope.() -> Unit)? = null,
    val cell: @Composable RowScope.(T) -> Unit
)

/**
 * A typed Data Table inspired by shadcn's data-table demo. Provides sorting, optional row
 * selection, and Previous/Next pagination. Sorting and selection state are managed internally
 * unless [sortState] / [selectedItems] are provided (controlled mode).
 *
 * The table scrolls horizontally — declare per-column [DataTableColumn.width] in [Dp] and the
 * row width is the sum of those plus the optional selection column.
 *
 * Note: the existing [Checkbox] does not support an indeterminate visual; the header checkbox
 * is checked iff all rows on the current page are selected, and clicking it toggles the whole
 * page.
 *
 * @param items Full unsorted, unpaginated data set.
 * @param columns Column definitions.
 * @param rowKey Stable key for an item. Used for selection lookups.
 * @param modifier Modifier applied to the outer container.
 * @param enableSelection When true a leading checkbox column is auto-prepended.
 * @param selectionColumnWidth Width of the auto-injected selection column.
 * @param pageSize Rows per page.
 * @param initialSort Initial sort state (uncontrolled).
 * @param sortState External sort state (controlled). When non-null overrides internal state.
 * @param onSortChange Sort change callback. Always invoked when sort changes.
 * @param selectedItems External selection (controlled). When non-null overrides internal state.
 *      Items are compared by [rowKey] — two items with the same key are treated as the same row.
 * @param onSelectionChange Selection change callback, emitted as a typed [Set] of selected items.
 * @param onRowClick Optional row click handler.
 * @param caption Optional caption text rendered below the table.
 */
@Composable
fun <T> DataTable(
    items: List<T>,
    columns: List<DataTableColumn<T>>,
    rowKey: (T) -> Any,
    modifier: Modifier = Modifier,
    enableSelection: Boolean = false,
    selectionColumnWidth: Dp = 48.dp,
    pageSize: Int = 10,
    initialSort: SortState = SortState(),
    sortState: SortState? = null,
    onSortChange: ((SortState) -> Unit)? = null,
    selectedItems: Set<T>? = null,
    onSelectionChange: ((Set<T>) -> Unit)? = null,
    onRowClick: ((T) -> Unit)? = null,
    caption: String? = null
) {
    val styles = MaterialTheme.styles

    var internalSort by remember { mutableStateOf(initialSort) }
    val effectiveSort = sortState ?: internalSort
    val updateSort: (SortState) -> Unit = { next ->
        if (sortState == null) {
            internalSort = next
        }
        onSortChange?.invoke(next)
    }

    var internalSelection by remember { mutableStateOf<Set<T>>(emptySet()) }
    val effectiveSelection: Set<T> = selectedItems ?: internalSelection
    val updateSelection: (Set<T>) -> Unit = { next ->
        if (selectedItems == null) {
            internalSelection = next
        }
        onSelectionChange?.invoke(next)
    }
    val selectedKeySet: Set<Any> = remember(effectiveSelection) {
        effectiveSelection.mapTo(mutableSetOf(), rowKey)
    }

    val sorted = remember(items, effectiveSort, columns) {
        val col = columns.firstOrNull { it.id == effectiveSort.columnId }
        val cmp = col?.comparator
        when {
            cmp == null || effectiveSort.direction == SortDirection.NONE -> items
            effectiveSort.direction == SortDirection.ASC -> items.sortedWith(cmp)
            else -> items.sortedWith(cmp.reversed())
        }
    }

    val pageCount = if (sorted.isEmpty()) 1 else (sorted.size + pageSize - 1) / pageSize
    var page by remember { mutableStateOf(0) }
    LaunchedEffect(pageCount) {
        if (page >= pageCount) page = pageCount - 1
        if (page < 0) page = 0
    }
    val pageItems = sorted.drop(page * pageSize).take(pageSize)
    val pageKeys = pageItems.map(rowKey).toSet()
    val allOnPageSelected = pageKeys.isNotEmpty() && selectedKeySet.containsAll(pageKeys)

    val totalWidth = columns.fold(0.dp) { acc, c -> acc + c.width } +
            (if (enableSelection) selectionColumnWidth else 0.dp)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Table {
            TableHeader {
                TableRow(modifier = Modifier.width(totalWidth)) {
                    if (enableSelection) {
                        TableHead(modifier = Modifier.width(selectionColumnWidth)) {
                            Checkbox(
                                checked = allOnPageSelected,
                                onCheckedChange = { checked ->
                                    val next = effectiveSelection.toMutableSet()
                                    if (checked) {
                                        next.addAll(pageItems)
                                    } else {
                                        next.removeAll { rowKey(it) in pageKeys }
                                    }
                                    updateSelection(next)
                                }
                            )
                        }
                    }
                    columns.forEachIndexed { index, col ->
                        val isLast = index == columns.lastIndex
                        TableHead(
                            modifier = Modifier
                                .width(col.width)
                                .then(if (isLast) Modifier.padding(end = 24.dp) else Modifier)
                        ) {
                            SortableHeader(
                                column = col,
                                sort = effectiveSort,
                                onClick = {
                                    if (col.sortable && col.comparator != null) {
                                        updateSort(nextSort(effectiveSort, col.id))
                                    }
                                }
                            )
                        }
                    }
                }
            }
            TableBody {
                if (pageItems.isEmpty()) {
                    TableRow(
                        modifier = Modifier.width(totalWidth),
                        showBottomBorder = false
                    ) {
                        TableCell(modifier = Modifier.width(totalWidth)) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(64.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No results.",
                                    color = styles.mutedForeground,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                } else {
                    pageItems.forEachIndexed { index, item ->
                        val key = rowKey(item)
                        val isSelected = key in selectedKeySet
                        TableRow(
                            modifier = Modifier.width(totalWidth),
                            onClick = onRowClick?.let { { it(item) } },
                            selected = isSelected,
                            showBottomBorder = index != pageItems.lastIndex
                        ) {
                            if (enableSelection) {
                                TableCell(modifier = Modifier.width(selectionColumnWidth)) {
                                    Checkbox(
                                        checked = isSelected,
                                        onCheckedChange = { checked ->
                                            val next = effectiveSelection.toMutableSet()
                                            if (checked) {
                                                next.add(item)
                                            } else {
                                                next.removeAll { rowKey(it) == key }
                                            }
                                            updateSelection(next)
                                        }
                                    )
                                }
                            }
                            columns.forEachIndexed { ci, col ->
                                val isLast = ci == columns.lastIndex
                                TableCell(
                                    modifier = Modifier
                                        .width(col.width)
                                        .then(if (isLast) Modifier.padding(end = 24.dp) else Modifier)
                                ) {
                                    col.cell(this, item)
                                }
                            }
                        }
                    }
                }
            }
        }

        if (caption != null) {
            TableCaption(text = caption)
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (enableSelection) {
                    Text(
                        text = "${effectiveSelection.size} of ${sorted.size} row(s) selected.",
                        color = styles.mutedForeground,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Page ${page + 1} of $pageCount",
                    color = styles.mutedForeground,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { if (page > 0) page-- },
                    variant = ButtonVariant.Outline,
                    size = ButtonSize.Sm,
                    enabled = page > 0
                ) {
                    Text("Previous")
                }
                Button(
                    onClick = { if (page < pageCount - 1) page++ },
                    variant = ButtonVariant.Outline,
                    size = ButtonSize.Sm,
                    enabled = page < pageCount - 1
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
private fun SortableHeader(
    column: DataTableColumn<*>,
    sort: SortState,
    onClick: () -> Unit
) {
    val styles = MaterialTheme.styles
    val isActive = sort.columnId == column.id && sort.direction != SortDirection.NONE
    val clickable = column.sortable && column.comparator != null

    val rowModifier = if (clickable) {
        Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )
    } else Modifier

    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        val custom = column.headerContent
        if (custom != null) {
            custom()
        } else {
            Text(
                text = column.header,
                color = styles.mutedForeground,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
            )
        }
        if (clickable) {
            val icon = when {
                !isActive -> Icons.Default.UnfoldMore
                sort.direction == SortDirection.ASC -> Icons.Default.ArrowUpward
                else -> Icons.Default.ArrowDownward
            }
            Icon(
                imageVector = icon,
                contentDescription = "Sort ${column.header}",
                tint = styles.mutedForeground,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

private fun nextSort(current: SortState, columnId: String): SortState {
    return if (current.columnId != columnId) {
        SortState(columnId, SortDirection.ASC)
    } else when (current.direction) {
        SortDirection.NONE -> SortState(columnId, SortDirection.ASC)
        SortDirection.ASC -> SortState(columnId, SortDirection.DESC)
        SortDirection.DESC -> SortState(null, SortDirection.NONE)
    }
}

private fun Modifier.bottomBorder(color: Color): Modifier = this.drawBehind {
    val strokeWidth = 1.dp.toPx()
    val y = size.height - strokeWidth / 2f
    drawLine(
        color = color,
        start = Offset(0f, y),
        end = Offset(size.width, y),
        strokeWidth = strokeWidth
    )
}
