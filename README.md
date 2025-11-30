# eja Keyboard

A super simple, privacy-first input method for Android, built to replace default vendor keyboards that often track keystrokes, require unnecessary network permissions, or consume heavy resources.

The entire project is **less than 500 lines of code**, making it extremely lightweight and easy to audit for security.

## Privacy First

This keyboard is designed with a single goal: **Privacy**.

*   **No Internet Access:** The app **does not** request the `android.permission.INTERNET` permission. It physically cannot send data to the cloud.
*   **No Analytics:** Zero tracking, zero telemetry.
*   **Auditable:** With a codebase under 500 LOC, read the source code in minutes to verify exactly what it does.

## Features

*   **Standard QWERTY Layout:** Familiar typing experience.
*   **Coding-Ready Symbol Layer:** Designed for developers, the `123` layer places numbers and essential coding characters (brackets `[] {}`, pipes `|`, backslashes `\`, and operators `< >`) within immediate reach.
*   **Accents & Navigation Layer:** A specialized layer (accessed via the arrow/accent key) featuring:
    *   **Cursor Control:** Dedicated Arrow Keys (Left, Up, Down, Right) for precise text editing.
    *   **Accented Characters:** Full support for accented letters (à, è, ì, ò, ù, ñ, etc.).

## Installation & Setup

### 1. Build from Source
To ensure maximum trust, build the APK yourself:

1.  Clone this repository.
2.  Open the project in **Android Studio**.
3.  Build and Run on your device.

### 2. Enable the Keyboard
Once installed, the app provides a built-in helper to get you started:

1.  Open the **eja Keyboard** app from your launcher.
2.  Tap **"1. ENABLE IN SETTINGS"** -> Toggle `ejaKeyboard` on.
3.  Tap **"2. SELECT KEYBOARD"** -> Choose `ejaKeyboard` from the list.
4.  Test typing in the provided text field.
