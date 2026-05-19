# Sidebar

A composable, themeable sidebar inspired by the [shadcn/ui](https://ui.shadcn.com/docs/components/sidebar) component, ported to Compose Multiplatform.

The API mirrors the React component one-to-one, so any shadcn recipe translates directly: `Sidebar` and `SidebarInset` are placed as **siblings inside `SidebarProvider`**, and the provider arranges them as a `Row` on desktop or as a `ModalNavigationDrawer` on mobile (viewport < `SidebarDefaults.MobileBreakpoint`, default 768.dp).

## Quick start

```kotlin
SidebarProvider(
    side = SidebarSide.Left,
    variant = SidebarVariant.Sidebar,
    collapsible = SidebarCollapsible.Icon,
) {
    Sidebar {
        // text + icon overload: in icon mode the title hides and only the icon shows.
        SidebarHeader(
            title = "My App",
            icon = { Icon(Icons.Default.Star, contentDescription = "logo") },
        )
        SidebarContent {
            SidebarGroup {
                SidebarGroupLabel("Navigation")
                SidebarGroupContent {
                    SidebarMenu {
                        SidebarMenuButton(
                            text = "Dashboard",
                            onClick = { /* ... */ },
                            isActive = selected == "Dashboard",
                            tooltip = "Dashboard",
                            icon = {
                                Icon(
                                    Icons.Default.Home,
                                    contentDescription = "Dashboard",
                                    modifier = Modifier.size(20.dp),
                                )
                            },
                        )
                    }
                }
            }
        }
        // Text-only footer disappears in icon mode (matches shadcn).
        SidebarFooter(text = "v1.0.0")
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
| Inset     | Sidebar background extends to the screen edge   | Margin + `radius.xl` rounded card + shadow      |

## Side

`SidebarSide.Left` (default) and `SidebarSide.Right` are supported on both desktop and mobile. On mobile the right-anchored `ModalNavigationDrawer` is achieved by wrapping the drawer in `LayoutDirection.Rtl`; drawer content is restored to the host's layout direction so text never mirrors.

## Collapsible modes

| Mode        | Behavior                                                                                          | Animation                              |
|-------------|---------------------------------------------------------------------------------------------------|----------------------------------------|
| `Offcanvas` | Default. Sidebar hides entirely when closed; the inset reclaims the freed width.                  | Slide + width expand/shrink (220 ms).  |
| `Icon`      | Sidebar collapses to an icon rail of `widthIcon` (48.dp).                                         | Width tween (`SidebarDefaults.AnimationSpec`). |
| `None`      | Always visible; `state.toggleSidebar()` is a no-op.                                               | None.                                  |

When `Icon` is active and the sidebar is closed:

- Labels, badges, sub-menus, group labels, group actions hide.
- `SidebarMenuButton(tooltip = ...)` activates a Material3 `PlainTooltip` on long-press / hover.
- `SidebarMenuSub` disappears entirely (matches React).
- The text-overload of `SidebarHeader` / `SidebarFooter` reduces to the icon (or hides entirely if no icon is provided).

## System bars / safe area

On Android, the sidebar background extends behind the status / navigation bars so the system bars tint with the sidebar color, while the inner content (header, content, footer) gets `windowInsetsPadding(WindowInsets.systemBars)` applied so it stays inside the safe area. The same applies to `SidebarInset` when `variant = Inset`. Mobile drawer mode delegates inset handling to Material3's `ModalNavigationDrawer`.

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

| Composable                                    | Purpose                                                                        |
|-----------------------------------------------|--------------------------------------------------------------------------------|
| `SidebarProvider`                             | State, viewport detection, mobile drawer; slot orchestrator                    |
| `Sidebar`                                     | Sidebar root; registers content into the sidebar slot                          |
| `SidebarInset`                                | Main content area; registers into the inset slot                               |
| `SidebarTrigger`                              | Ghost icon button bound to `state.toggleSidebar()`                             |
| `SidebarRail`                                 | Marker — opts the sidebar into rendering an outer-edge click rail              |
| `SidebarHeader { content }`                   | Top sticky section, free-form slot                                             |
| `SidebarHeader(title, icon)`                  | Brand-style overload — title hides in icon mode; icon (if any) remains         |
| `SidebarFooter { content }`                   | Bottom sticky section, free-form slot                                          |
| `SidebarFooter(text, icon)`                   | Convenience overload — text hides in icon mode; icon (if any) remains          |
| `SidebarSeparator`                            | Themed horizontal divider                                                      |
| `SidebarInput`                                | Themed text field (uses `styles.background`)                                   |
| `SidebarContent`                              | Scrollable middle section; must be inside `Sidebar`'s `ColumnScope`            |
| `SidebarGroup`                                | Container for a group of items                                                 |
| `SidebarGroupLabel(text)` / `{ content }`     | Group heading; hidden in icon mode                                             |
| `SidebarGroupAction`                          | Trailing button for the group; hidden in icon mode                             |
| `SidebarGroupContent`                         | Items wrapper inside a group                                                   |
| `SidebarMenu`                                 | Vertical list of menu items                                                    |
| `SidebarMenuItem`                             | Box wrapper for a row + its action/badge                                       |
| `SidebarMenuButton(text, ...)` / `{ ... }`    | Clickable row; variant/size/tooltip aware; icon slot sets its own size         |
| `SidebarMenuAction`                           | Trailing affordance; hidden in icon mode                                       |
| `SidebarMenuBadge`                            | Trailing counter / status pill; hidden in icon mode                            |
| `SidebarMenuSkeleton`                         | Loading placeholder; adapts to icon mode                                       |
| `SidebarMenuSub` / `SubItem` / `SubButton`    | Nested level; hidden in icon mode                                              |

## Recipes

### Header with logo

```kotlin
SidebarHeader(
    title = "Acme Inc.",
    icon = { Icon(Icons.Default.Star, contentDescription = "logo") },
)
```

### Footer with copyright (hidden in icon mode)

```kotlin
SidebarFooter(text = "© 2025 Shadcn Compose")
```

### Footer with NavUser (avatar + name/email, like shadcn's `NavUser`)

The icon slot of `SidebarMenuButton` no longer clamps its size, so an `Avatar` renders at its natural size beside the name/email column. When the sidebar collapses to icon mode, the menu button reduces to just the avatar.

```kotlin
SidebarFooter {
    SidebarMenu {
        SidebarMenuItem {
            SidebarMenuButton(
                onClick = { /* ... */ },
                size = SidebarMenuButtonSize.Large,
                tooltip = "shadcn",
                icon = {
                    Avatar(
                        model = null,
                        size = 32.dp,
                        fallbackText = "SH",
                    )
                },
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("shadcn", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    Text("m@example.com", fontSize = 12.sp,
                         color = MaterialTheme.styles.mutedForeground)
                }
                Icon(Icons.Default.UnfoldMore, contentDescription = "Open user menu",
                     modifier = Modifier.size(16.dp))
            }
        }
    }
}
```

## Theming

Reads from `MaterialTheme.styles`:

- `sidebar`, `sidebarForeground` — root colors
- `sidebarAccent`, `sidebarAccentForeground` — active row colors
- `sidebarBorder` — separators / outline variant border / rail
- `sidebarRing` — focus ring (reserved, not yet applied)

Corner radii come from `MaterialTheme.radius` (`sm`, `md`, `lg`, `xl`).
