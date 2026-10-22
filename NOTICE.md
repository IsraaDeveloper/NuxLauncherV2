# NOTICE

Nux Launcher 2 is an unofficial modification of **Zalith Launcher 2**.

- Upstream project: https://github.com/ZalithLauncher/ZalithLauncher2
- Upstream author: MovTery <movtery228@qq.com> and contributors
- Upstream license: GNU General Public License v3.0, with additional terms under GPLv3 section 7
  (see `LICENSE`, part "Additional Terms")

This build is **not** affiliated with, endorsed by, or supported by the Zalith Launcher
project. Bug reports about this app must go to the Nux Launcher 2 issue tracker, not upstream.

## Why the name differs

The upstream additional terms require a modified version to:

1. change the program name or version so it differs from the original, and not use the name
   "ZalithLauncher", its abbreviation "ZL", or any confusingly similar name;
2. show clearly, on the splash screen or main screen, that it is an unofficial modification;
3. keep the copyright notices shown by the program.

Nux Launcher 2 follows all three. The label appears on the splash screen and on the About page.

## Changes from upstream

Changes made in this repository, starting from Zalith Launcher 2.7.2:

- Application identity renamed to Nux Launcher 2: package `com.israadev.nuxlauncherv2`,
  Gradle module `app`, application class `NuxApplication`, launcher theme `NuxLauncherTheme`,
  version `2.0.0`.
- Project name, launcher name, short name, home URL and About links now point to
  https://github.com/IsraaDeveloper/NuxLauncherV2.
- Launcher icon and splash artwork replaced with the Nux Launcher 2 logo.
- Color themes replaced with the Nux palette. The seven upstream palettes were removed.
- Automatic update check disabled. The upstream check would have offered Zalith Launcher 2
  builds to Nux Launcher 2 users.
- Release and debug signing keys replaced with keys generated for this project. The upstream
  keystores are not used and were removed.
- Documentation, issue templates and CI paths adjusted for the new module name.

File headers keep the original copyright lines, as the license requires. New work in this
repository is Copyright (C) 2026 IsraaDeveloper.

## License

This program is free software: you can redistribute it and/or modify it under the terms of the
GNU General Public License as published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See the GNU General Public License for more details.

The full license text is in `LICENSE`.

Source code of this modified version: https://github.com/IsraaDeveloper/NuxLauncherV2

## Third-party components

The upstream acknowledgements still apply. Bundled libraries and their licenses are listed in
the app under Settings, About, Libraries, and in `app/src/main/res/raw/`.
