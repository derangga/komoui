# Project Overview: shadcn-ui-kmp

## Purpose
A Kotlin Multiplatform (KMP) UI component library inspired by [shadcn/ui](https://ui.shadcn.com/). Provides beautifully designed, customizable Compose Multiplatform components for Android and iOS.

Published as: `io.github.derangga:shadcn-ui-kmp` (version 0.2.0) on Maven Central.

## Tech Stack
- **Language**: Kotlin 2.2.0
- **UI Framework**: Compose Multiplatform (with Material 3)
- **Build System**: Gradle with Kotlin DSL, version catalogs (`libs.versions.toml`)
- **Platforms**: Android (minSdk 24, compileSdk 36) and iOS (iosX64, iosArm64, iosSimulatorArm64)
- **Key Dependencies**: Coil (image loading), Ktor (networking), kotlinx-datetime
- **Publishing**: Vanniktech Maven Publish plugin to Maven Central (Sonatype)
- **Documentation**: Dokka

## Modules
1. **`shadcn-ui-kmp`** — The library module. Contains all UI components and theming.
   - Package: `com.shadcn.ui`
   - Sub-packages: `components`, `components/sidebar`, `components/sooner` (Sonner toasts), `themes`, `utils`, `kmp`
2. **`composeApp`** — Demo/sample app module. Depends on the library.
   - Package: `dr.shadcn.kmp`
3. **`iosApp`** — iOS app wrapper

## Available Components
Accordion, Alert, AlertDialog, Avatar, Badge, Button, Calendar, Card, Carousel, Checkbox, Combobox, DatePicker, Dialog, Drawer, DropdownMenu, Input, Popover, Progress, RadioButton, Select, Sidebar, Skeleton, Slider, Sonner (toast), Switch, Tabs

## Theming
- Custom `ShadcnTheme` composable wrapping Material3's `MaterialTheme`
- `ShadcnStyles` for colors (light/dark), `ShadcnRadius` for corner radii
- Access via extension properties: `MaterialTheme.styles`, `MaterialTheme.radius`, `MaterialTheme.isDark`
- Uses `CompositionLocalProvider` for providing theme values
