# Suggested Commands

## Build
```bash
./gradlew build                          # Build all modules
./gradlew :shadcn-ui-kmp:build           # Build library module only
./gradlew :composeApp:build              # Build demo app only
```

## Testing
```bash
./gradlew test                           # Run all tests
./gradlew :shadcn-ui-kmp:allTests        # Run library tests (all platforms)
./gradlew :shadcn-ui-kmp:testDebugUnitTest  # Run Android unit tests
```

## Publishing
```bash
./gradlew :shadcn-ui-kmp:publishToMavenLocal  # Publish to local Maven repo
```
CI/CD publishes to Maven Central via GitHub Actions on version tag push (e.g., `0.2.2`).

## Documentation
```bash
./gradlew :shadcn-ui-kmp:dokkaGenerate   # Generate API docs with Dokka
```

## Gradle Utilities
```bash
./gradlew clean                          # Clean build outputs
./gradlew dependencies                   # Show dependency tree
./gradlew :shadcn-ui-kmp:dependencies    # Library dependency tree
```

## System Utilities (macOS/Darwin)
```bash
git status / git diff / git log          # Git operations
ls / find / grep                         # File system navigation
```
