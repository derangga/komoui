package com.komoui.demo.previews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.komoui.components.Accordion
import com.komoui.components.AccordionItemData

@Preview
@Composable
internal fun AccordionPreview() {
    PreviewSurface {
        Accordion(
            items = listOf(
                AccordionItemData(
                    id = "item-1",
                    header = { Text("Is it accessible?") },
                    content = { Text("Yes. It adheres to the WAI-ARIA design pattern.") },
                ),
                AccordionItemData(
                    id = "item-2",
                    header = { Text("Is it styled?") },
                    content = { Text("Yes. It comes with default styles that match the rest of the design.") },
                ),
                AccordionItemData(
                    id = "item-3",
                    header = { Text("Is it animated?") },
                    content = { Text("Yes. It is animated by default, but you can disable it if you prefer.") },
                ),
            ),
            defaultOpenItemId = "item-1",
        )
    }
}
