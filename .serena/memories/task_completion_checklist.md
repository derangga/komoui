# Task Completion Checklist

When completing a coding task in this project, ensure the following:

1. **Build check**: Run `./gradlew :shadcn-ui-kmp:build` to verify the library compiles
2. **Test check**: Run `./gradlew :shadcn-ui-kmp:allTests` to verify tests pass
3. **Code style**: Follow Kotlin official code style; use consistent patterns with existing components
4. **Theme integration**: New components should use `MaterialTheme.styles` and `MaterialTheme.radius`
5. **Multiplatform**: Ensure code is in `commonMain` unless platform-specific (then `androidMain`/`iosMain`)
6. **Visibility**: Public API for consumers, `internal`/`private` for helpers
7. **Documentation**: Add KDoc to public composable functions
