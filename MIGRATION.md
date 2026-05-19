# Migrating from `shadcn-ui-kmp` to KomoUI

KomoUI (`io.github.derangga:komoui`) is the rebranded continuation of `shadcn-ui-kmp`. The old artifact `io.github.derangga:shadcn-ui-kmp:0.2.2` is **end-of-life** and will receive no further releases. This document is the migration checklist for moving an existing project from `0.2.x` to KomoUI `0.3.0`.

## Why the rename?

"shadcn" is the personal brand of the original creator of shadcn/ui. To avoid potential brand confusion and respect the upstream author's preferences, the library has been rebranded to KomoUI ("Kotlin Modern UI"). The MIT license, technical scope, and design language are unchanged.

## Coordinate change

```diff
- implementation("io.github.derangga:shadcn-ui-kmp:0.2.2")
+ implementation("io.github.derangga:komoui:0.3.0")
```

## Package renames

| Old | New |
|---|---|
| `com.shadcn.ui.components.*` | `com.komoui.components.*` |
| `com.shadcn.ui.components.sidebar.*` | `com.komoui.components.sidebar.*` |
| `com.shadcn.ui.components.sooner.*` | `com.komoui.components.sooner.*` |
| `com.shadcn.ui.components.charts.*` | `com.komoui.components.charts.*` |
| `com.shadcn.ui.themes.*` | `com.komoui.themes.*` |
| `com.shadcn.ui.utils.*` | `com.komoui.utils.*` |
| `com.shadcn.ui.kmp.*` | `com.komoui.kmp.*` |

A bulk find-and-replace on `com.shadcn.ui` → `com.komoui` covers all imports.

## API renames

| Old identifier | New identifier |
|---|---|
| `ShadcnTheme { … }` | `KomoTheme { … }` |
| `ShadcnStyles` | `KomoStyles` |
| `ShadcnRadius` | `KomoRadius` |
| `ShadcnTheme(shadcnLightColors = …, shadcnDarkColors = …, shadcnRadius = …)` | `KomoTheme(komoLightColors = …, komoDarkColors = …, komoRadius = …)` |

`MaterialTheme.styles`, `MaterialTheme.radius`, and `MaterialTheme.isDark` extension properties keep their names — only the underlying types renamed.

Component composables (`Button`, `Card`, `Dialog`, `Calendar`, …) remain unprefixed and keep the same signatures.

## iOS framework rename

The XCFramework consumers `import` from has also been renamed:

```diff
- import shadcn_ui_kmpKit
+ import KomoUI
```

## Before / after — Gradle

```diff
  dependencies {
-     implementation("io.github.derangga:shadcn-ui-kmp:0.2.2")
+     implementation("io.github.derangga:komoui:0.3.0")
  }
```

## Before / after — application code

```diff
- import com.shadcn.ui.components.Button
- import com.shadcn.ui.themes.ShadcnTheme
+ import com.komoui.components.Button
+ import com.komoui.themes.KomoTheme

  setContent {
-     ShadcnTheme {
+     KomoTheme {
          Button(onClick = { /* … */ }) { Text("Submit") }
      }
  }
```

## Repository

The GitHub repository has been renamed `derangga/shadcn-ui-kmp` → `derangga/komoui`. The old URL redirects automatically via GitHub.

## Questions?

Open an issue on [github.com/derangga/komoui](https://github.com/derangga/komoui/issues).
