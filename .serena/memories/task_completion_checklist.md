# Task Completion Checklist

When completing a coding task in this project, ensure the following:

1. **Build check**: Run `./gradlew :shadcn-ui-kmp:build` to verify the library compiles
2. **Test check**: Run `./gradlew :shadcn-ui-kmp:allTests` to verify tests pass
3. **Code style**: Follow Kotlin official code style; use consistent patterns with existing components
4. **Theme integration**: New components must use `MaterialTheme.styles` and `MaterialTheme.radius` — never hardcode colors
5. **Multiplatform**: Keep code in `commonMain` unless platform-specific (then `androidMain`/`iosMain`)
6. **Visibility**: Public API for consumers, `internal`/`private` for helpers
7. **Documentation**: Add KDoc to all public composable functions
8. **Demo app wiring** (for new components):
   - Add page in `composeApp/src/commonMain/kotlin/dr/shadcn/kmp/pages/components/`
   - Register route in `Route.kt`
   - Add entry in `HomeContent.kt`
   - Add navigation case in `MainNavigation.kt`
