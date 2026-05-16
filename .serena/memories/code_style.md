# Code Style & Conventions

## General
- **Code style**: Kotlin official (`kotlin.code.style=official` in gradle.properties)
- **File encoding**: UTF-8
- No comments unless WHY is non-obvious; no multi-line comment blocks

## Naming Conventions
- Components are named as `@Composable fun ComponentName(...)` — PascalCase
- Enum classes use PascalCase for both class and values (e.g., `ButtonVariant.Default`, `ButtonSize.Sm`)
- Internal helpers use `internal` visibility; private helpers use `private` visibility
- Package structure: `com.shadcn.ui.components` for components, `com.shadcn.ui.themes` for theming

## Component Patterns
- Each component is in its own file (e.g., `Button.kt`, `Card.kt`)
- `Modifier` is the second parameter (after primary callback like `onClick`)
- Components use `MaterialTheme.styles` and `MaterialTheme.radius` for theming — never hardcode colors
- KDoc on all public composable functions
- Enums for variants/sizes defined in the same file as the component
- All code in `commonMain` unless platform-specific behavior is needed

## Theme Access
- `MaterialTheme.styles` — access `ShadcnStyles` (colors)
- `MaterialTheme.radius` — access `ShadcnRadius`
- `MaterialTheme.isDark` — check dark mode

## New Component Authoring Pattern
1. Create file in `shadcn-ui-kmp/src/commonMain/kotlin/com/shadcn/ui/components/`
2. Define variant/size enums if applicable
3. Use theme via `MaterialTheme.styles` and `MaterialTheme.radius`
4. Add a demo page in `composeApp/src/commonMain/kotlin/dr/shadcn/kmp/pages/components/`
5. Register the route in `Route.kt`, `HomeContent.kt`, and `MainNavigation.kt`

## File Organization
- One component per file
- Complex/multi-file components grouped in subdirectories (e.g., `sidebar/`, `sooner/`)
