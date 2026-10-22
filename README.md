# Nux Launcher 2

A Minecraft: Java Edition launcher for Android, built on the Zalith Launcher 2 codebase.

- Package: `com.israadev.nuxlauncherv2`
- Version: 2.0.0
- Platform: Android 8.0 (API 26) and newer
- License: GPL-3.0 with upstream additional terms, see `LICENSE` and `NOTICE.md`

This is an unofficial modification. It is not affiliated with or supported by the Zalith
Launcher project.

## Build

Requirements:

- JDK 17
- Android SDK with platform 37 and build tools 37
- Android NDK 25.2.9519653 (the version pinned in `app/build.gradle.kts`)
- Gradle is provided by the wrapper, no separate install needed

`local.properties` must point at your SDK:

```
sdk.dir=/path/to/Android/Sdk
```

Then:

```bash
./gradlew :app:assembleDebug
```

The APK lands in `app/build/outputs/apk/debug/`. To build a single ABI, pass `-Darch`:

```bash
./gradlew :app:assembleDebug -Darch=arm64
```

Accepted values: `all` (default), `arm`, `arm64`, `x86`, `x86_64`.

Release builds need signing credentials in `.store_password.txt` and `.key_password.txt`,
or the `STORE_PASSWORD` and `KEY_PASSWORD` environment variables. Both files are gitignored.
The release keystore is `app/nux_launcher.jks`. Keep a backup: losing it means you cannot
update the app on Google Play or anywhere else that verifies signatures.

## Module layout

| Module | Contents |
|---|---|
| `app` | Launcher UI, game launching, downloads, accounts, settings |
| `LWJGL` | Patched LWJGL bindings (3.3.3 and 3.4.1) plus patches |
| `LayerController` | Control layout engine |
| `ColorPicker` | Color picker used by the theme editor |
| `CardGrid` | Reorderable card grid used on the home screen |
| `Terracotta` | Terracotta multiplayer support |
| `InputMap` | Input mapping |
| `Guide` | First-run guide overlay |

## Credits

- Zalith Launcher 2 by MovTery and contributors, the base of this project
- PojavLauncher, LWJGL, SDL, Mesa, ANGLE and the other libraries listed under
  Settings, About, Libraries
- All upstream translators, whose work is carried over

## License

GPL-3.0. Read `LICENSE` before distributing a build. Read `NOTICE.md` for the changes made
in this fork and for the attribution requirements that come with them.
