package com.komoui.demo.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.komoui.components.Skeleton

@Preview
@Composable
internal fun SkeletonPreview() {
    PreviewSurface {
        Text("Shapes")
        Skeleton(modifier = Modifier.size(64.dp), shape = CircleShape)
        Skeleton(modifier = Modifier.fillMaxWidth().height(16.dp))
        Skeleton(modifier = Modifier.fillMaxWidth().height(16.dp))
        Skeleton(modifier = Modifier.width(120.dp).height(16.dp))

        Text("Card placeholder")
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Skeleton(modifier = Modifier.size(48.dp), shape = CircleShape)
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Skeleton(modifier = Modifier.width(160.dp).height(14.dp))
                Skeleton(modifier = Modifier.width(100.dp).height(14.dp))
            }
        }

        Skeleton(
            modifier = Modifier.fillMaxWidth().height(120.dp),
            shape = RoundedCornerShape(12.dp),
        )
    }
}
