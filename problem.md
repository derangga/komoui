# Branding & Naming Update

## Summary

We have decided to rename this project from **shadcn-ui-kmp** to **KomoUI**.

### The Problem

- The original repository and code used the "shadcn" name heavily (repo name, package namespace, component names, theme, etc.).
- Although the code is licensed under MIT and porting is allowed, **"shadcn" is the personal brand** of the original creator (shadcn).
- Recent cases have shown that the author actively requests projects to stop using the "shadcn" name when they appear as official variants or strong brand extensions.
- Continuing to use "shadcn" in the project name and identifiers created a risk of confusion and potential future requests to rebrand.

### The Solution

We have fully rebranded the project to **KomoUI** (Kotlin Modern UI).

**New Identity**
- **Project Name**: KomoUI
- **Repository**: `derangga/komoui` (or your new repo name)
- **Maven Coordinate**: `io.github.derangga:komoui`
- **Package**: `io.github.derangga.komoui.*`
- **Theme**: `KomoTheme`
- **Website**: (update your domain accordingly)

### What Changed

- Repository name
- All package names and import paths
- Component and theme naming (`ShadcnTheme` → `KomoTheme`, etc.)
- Documentation and website
- Removed all direct "shadcn" branding from code and assets

### Relationship with Original Project

**KomoUI** is an **independent open-source project** and is **not affiliated** with shadcn or shadcn/ui.

It is a port inspired by the excellent shadcn/ui design system and component style, adapted for **Jetpack Compose** and **Kotlin Multiplatform**.

We sincerely thank shadcn for creating and open-sourcing the original components under the MIT license, which made this port possible.

### License & Attribution

- This project remains under the **MIT License**.
- Original copyright notice from shadcn/ui is preserved where relevant.
- We respect the original author's branding and wish the shadcn/ui project continued success.

---

**Last updated**: May 2026
