# Code Style & Conventions

## General
- **Code style**: Kotlin official (`kotlin.code.style=official` in gradle.properties)
- **File encoding**: UTF-8

## Naming Conventions
- Components are named as `@Composable fun ComponentName(...)` — PascalCase
- Enum classes use PascalCase for both class and values (e.g., `ButtonVariant.Default`, `ButtonSize.Sm`)
- Internal helpers prefixed with `internal` visibility
- Private helpers use `private` visibility
- Package structure: `com.shadcn.ui.components` for components, `com.shadcn.ui.themes` for theming

## Component Patterns
- Each component is in its own file (e.g., `Button.kt`, `Card.kt`)
- Components accept `Modifier` as a parameter (typically second parameter after primary callback)
- Components use `MaterialTheme.styles` and `MaterialTheme.radius` for theming
- KDoc comments on public composable functions documenting all parameters
- Enums for component variants and sizes defined in the same file as the component

## Theme Access
- `MaterialTheme.styles` — access `ShadcnStyles` (colors)
- `MaterialTheme.radius` — access `ShadcnRadius`
- `MaterialTheme.isDark` — check dark mode

## File Organization
- One component per file
- Related sub-components (e.g., Sidebar) grouped in subdirectories
- Complex components (e.g., Sonner) have their own subdirectory with multiple files
