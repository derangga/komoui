# Sidebar

A composable, themeable sidebar inspired by the [shadcn/ui](https://ui.shadcn.com/docs/components/sidebar) component, ported to Compose Multiplatform.

The API mirrors the React component one-to-one, so any shadcn recipe translates directly: `Sidebar` and `SidebarInset` are placed as **siblings inside `SidebarProvider`**, and the provider arranges them as a Row on desktop or as a `ModalNavigationDrawer` on mobile (viewport < `SidebarDefaults.MobileBreakpoint`, default 768.dp).

## Quick start

```kotlin
SidebarProvider(
    side = SidebarSide.Left,
    variant = SidebarVariant.Sidebar,
    collapsible = SidebarCollapsible.Icon,
) {
    Sidebar {
        SidebarHeader { Text("My App", fontWeight = FontWeight.Bold) }
        SidebarContent {
            SidebarGroup {
                SidebarGroupLabel("Navigation")
                SidebarGroupContent {
                    SidebarMenu {
                        SidebarMenuButton(
                            text = "Dashboard",
                            onClick = { /* ... */ },
                            isActive = selected == "Dashboard",
                            icon = { Icon(Icons.Default.Home, null) },
                        )
                    }
                }
            }
        }
        SidebarFooter { Text("v1.0.0") }
        SidebarRail() // optional: thin clickable outer-edge rail
    }
    SidebarInset {
        // Your app content; on mobile this is the host of the modal drawer.
        AppContent()
    }
}
```

## Composition model

`Sidebar` and `SidebarInset` are **slot-registering** composables: they don't emit their own subtree, they record their content into provider-owned slots. The provider then renders the slots in the right layout for the viewport. Practically:

- You always place `Sidebar` and `SidebarInset` as direct children of `SidebarProvider`.
- The composition order matches the React docs.
- The body of `Sidebar`/`SidebarInset` may freely read `LocalSidebarState`; the state is in scope at the position the provider eventually renders them.

## Controlled vs uncontrolled

```kotlin
// Uncontrolled — provider owns the state.
SidebarProvider(defaultOpen = true) { /* ... */ }

// Controlled — you own the state.
var open by rememberSaveable { mutableStateOf(true) }
SidebarProvider(open = open, onOpenChange = { open = it }) { /* ... */ }
```

Mobile open state is always uncontrolled and session-scoped (matches React behavior).

## Variants

`SidebarVariant.Sidebar` (default), `SidebarVariant.Floating`, `SidebarVariant.Inset`:

| Variant   | Sidebar chrome                                  | SidebarInset chrome                              |
|-----------|-------------------------------------------------|--------------------------------------------------|
| Sidebar   | Full-bleed Column on `styles.sidebar`           | Flush content, `styles.background`               |
| Floating  | Padded outer container; rounded bordered card   | Same as Sidebar                                  |
| Inset     | Sidebar background extends to the screen edge   | Margin + `radius.xl` rounded card + 2.dp shadow  |

## Collapsible modes

`SidebarCollapsible.Offcanvas` (default) slides off; `.Icon` collapses to an icon rail using `widthIcon`; `.None` always shows.

When `.Icon` is active and the sidebar is closed:

- Labels, badges, sub-menus, group labels, group actions, and the rail-text hide.
- `SidebarMenuButton(tooltip = ...)` activates a Material3 `PlainTooltip` on long-press / hover.
- `SidebarMenuSub` disappears entirely (matches React).

## Tunables

Override per-provider or globally via wrapper:

```kotlin
SidebarProvider(
    width = 280.dp,
    widthMobile = 320.dp,
    widthIcon = 56.dp,
    mobileBreakpoint = 600.dp,
    animationSpec = tween(durationMillis = 250),
) { /* ... */ }
```

Defaults live in `SidebarDefaults`.

## API surface

| Composable                  | Purpose                                                            |
|-----------------------------|--------------------------------------------------------------------|
| `SidebarProvider`           | State, viewport detection, mobile drawer; slot orchestrator        |
| `Sidebar`                   | Sidebar root; registers content into the sidebar slot              |
| `SidebarInset`              | Main content area; registers into the inset slot                   |
| `SidebarTrigger`            | Ghost icon button bound to `state.toggleSidebar()`                 |
| `SidebarRail`               | Marker — opts the sidebar into rendering an outer-edge click rail  |
| `SidebarHeader` / `Footer`  | Top / bottom sticky sections                                       |
| `SidebarSeparator`          | Themed horizontal divider                                          |
| `SidebarInput`              | Themed text field (uses `styles.background`)                       |
| `SidebarContent`            | Scrollable middle section; must be inside `Sidebar`'s `ColumnScope`|
| `SidebarGroup`              | Container for a group of items                                     |
| `SidebarGroupLabel`         | Group heading; hidden in icon mode                                 |
| `SidebarGroupAction`        | Trailing button for the group; hidden in icon mode                 |
| `SidebarGroupContent`       | Items wrapper inside a group                                       |
| `SidebarMenu`               | Vertical list of menu items                                        |
| `SidebarMenuItem`           | Box wrapper for a row + its action/badge                           |
| `SidebarMenuButton`         | Clickable row, variant/size/tooltip aware                          |
| `SidebarMenuAction`         | Trailing affordance; hidden in icon mode                           |
| `SidebarMenuBadge`          | Trailing counter / status pill; hidden in icon mode                |
| `SidebarMenuSkeleton`       | Loading placeholder; adapts to icon mode                           |
| `SidebarMenuSub` / `SubItem`/ `SubButton` | Nested level; hidden in icon mode                  |

## Theming

Reads from `MaterialTheme.styles`:

- `sidebar`, `sidebarForeground` — root colors
- `sidebarAccent`, `sidebarAccentForeground` — active row colors
- `sidebarBorder` — separators / outline variant border / rail
- `sidebarRing` — focus ring (reserved, not yet applied)

Corner radii come from `MaterialTheme.radius` (`sm`, `md`, `lg`, `xl`).
