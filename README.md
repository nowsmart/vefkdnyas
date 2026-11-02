# Vefk Dnyası (Android App)

A modern, high-performance Android application built with **Jetpack Compose** designed to calculate and render traditional **Vefk** (numerological magic square grids) ranging from 3×3 to 5×5. It features specialized multilingual character validation, Abjad calculation logic, real-time UI updates, and persistent state management.

---

## 🚀 Key Features

* **Dynamic Grid Selection:** Switch seamlessly between grid sizes:
  * **مُثَلَّث (Üçgen) 3×3**
  * **مُرَبَّع (Kare) 4×4**
  * **مُخَمَّس (Altıgen) 5×5**
* **Multilingual Input Support:** Custom input validation supporting both **Turkish** and **Urdu** alphabets alongside spaces, ensuring smooth typing and easy clearing.
* **Real-Time Reactivity:** Instantly recalculates and updates the grid view (`CreateNaqoosh`) the moment inputs or settings change.
* **Arabic Digits Toggle:** Toggle instantly between standard and Arabic/Urdu-Indic numerals (`١٢٣٤٥٦٧٨٩٠`).
* **Data Persistence:** Automatically saves your dropdown choices and text inputs using `SharedPreferences` so your progress is preserved across app restarts.
* **Clean UI/UX:** Built natively with **Jetpack Compose** and **Material 3**, featuring smooth scrolling, responsive layouts, and automatic software keyboard management.

---

## 📱 Tech Stack & Architecture

* **UI Framework:** Jetpack Compose, Material 3 (`Surface`, `Card`, `OutlinedTextField`, `ExposedDropdownMenuBox`, `Switch`)
* **Language:** Kotlin
* **State Management:** `mutableStateListOf`, `derivedStateOf`, `LaunchedEffect`
* **Data Storage:** Android `SharedPreferences`

---

## 🛠️ Code Architecture Highlights

* **Smart Resizing:** Automatically adjusts input fields when changing grid sizes without destroying previously typed user data.
* **Optimized Validation:** Leverages strict character-by-character filtering (`.all { it in ALPHABET || it == ' ' }`) to prevent input locking and regex compilation overhead on every keystroke.

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).