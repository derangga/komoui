# KomoUI

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.derangga/komoui)](https://central.sonatype.com/artifact/io.github.derangga/komoui)

**KomoUI** (Kotlin Modern UI) is a Compose Multiplatform UI component library for Android and iOS. Beautifully designed, customizable components with a Material 3 foundation.

## Overview

KomoUI brings a clean, modern component system to Compose Multiplatform:

- **Accessibility** — components are designed with accessibility in mind.
- **Customization** — easily themeable and adaptable to your application's design tokens.
- **Developer Experience** — simple, intuitive APIs that feel native to Compose.

## Requirements

- Kotlin 2.2.x
- Compose Multiplatform 1.9.x
- Android: `minSdk` 26 (the Calendar component uses `java.time` APIs available from API 26)
- iOS: arm64, x64, simulatorArm64

## Installation

Add the dependency to your module's `build.gradle.kts`. The latest version lives on the [release tags](https://github.com/derangga/komoui/tags).

```kotlin
dependencies {
    implementation("io.github.derangga:komoui:0.3.0")
}
```

iOS consumers can `import KomoUI` from the published XCFramework.

## Quick Start

Wrap your content in `KomoTheme` and start using components:

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KomoTheme {
                // your screen / navigation / components
            }
        }
    }
}
```

Use any component — they're unprefixed (`Button`, `Card`, `Dialog`, …):

```kotlin
import com.komoui.components.Button
import com.komoui.components.Input

@Composable
fun MyScreen() {
    var text by remember { mutableStateOf("") }

    Input(
        value = text,
        onValueChange = { text = it },
        placeholder = "Enter your name",
    )
    Button(onClick = { /* handle click */ }) {
        Text("Submit")
    }
}
```

Access design tokens via the `MaterialTheme.styles` extension:

```kotlin
KomoTheme {
    Column {
        Text(
            text = "Account",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.styles.foreground,
        )
        Text(
            text = "Make changes to your account here.",
            fontSize = 14.sp,
            color = MaterialTheme.styles.mutedForeground,
        )
    }
}
```

`MaterialTheme.radius` and `MaterialTheme.isDark` are also available.

## Components

Accordion · Alert · AlertDialog · Avatar · Badge · Button · Calendar · Card · Carousel · Charts (Bar/Line/Area) · Checkbox · Collapsible · Combobox · DatePicker · Dialog · Drawer · DropdownMenu · Empty · Input · InputOTP · Popover · Progress · RadioButton · Select · Sidebar · Skeleton · Slider · Sonner · Spinner · Switch · Table · Tabs

## Migrating from `shadcn-ui-kmp`

KomoUI is the rebranded continuation of the `shadcn-ui-kmp` library. If you depend on `io.github.derangga:shadcn-ui-kmp:0.2.x`, see [MIGRATION.md](MIGRATION.md) for the (short) checklist: new coordinate, package rename, theme rename. The old artifact is end-of-life and will receive no further releases.

## Acknowledgments

KomoUI is an **independent open-source project** and is **not affiliated** with shadcn or shadcn/ui.

It is a port inspired by the excellent [shadcn/ui](https://github.com/shadcn-ui/ui) design system and component style, adapted for Jetpack Compose and Kotlin Multiplatform. We sincerely thank shadcn for creating and open-sourcing the original components under the MIT license, which made this port possible.

## License

[MIT](LICENSE.md) — see the LICENSE for full text, including the preserved upstream copyright notice.
