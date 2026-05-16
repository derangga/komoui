#!/bin/bash
set -e

# Branch guard
current_branch=$(git rev-parse --abbrev-ref HEAD)
if [ "$current_branch" != "master" ]; then
  echo "Error: You must be on the master branch to release. Current branch: $current_branch"
  exit 1
fi

# Prompt for version
read -p "What is the new release version? (i.e. 1.0.0): " VERSION

# Validate version format (X.Y.Z)
if ! echo "$VERSION" | grep -qE '^[0-9]+\.[0-9]+\.[0-9]+$'; then
  echo "Error: Invalid version format '$VERSION'. Expected format: X.Y.Z (e.g. 1.0.0)"
  exit 1
fi

# Update version in build.gradle.kts
BUILD_FILE="shadcn-ui-kmp/build.gradle.kts"
sed -i '' "s/version = \"[^\"]*\"/version = \"$VERSION\"/" "$BUILD_FILE"
echo "Updated $BUILD_FILE to version $VERSION"

# Commit
git add "$BUILD_FILE"
git commit -m "release: new version $VERSION"

# Create tag
git tag "$VERSION"

# Prompt to push
read -p "Tag $VERSION created, ready to release new version by pushing this tag? (y/n): " CONFIRM

if [ "$CONFIRM" = "y" ] || [ "$CONFIRM" = "Y" ]; then
  git push origin "$VERSION"
  echo "Tag $VERSION pushed successfully."
else
  echo "You can push the tag manually by: git push origin $VERSION"
fi
