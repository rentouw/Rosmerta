# 📱 LifeHub – Modular Offline-First LifeLogging App

**LifeHub** is a modular, offline-first app for Android, WearOS, Android Auto, and eventually Desktop. It begins as a **LifeLogging tool**, tracking your devices' health and usage, and will evolve into an **All-in-One personal dashboard**, integrating homelab services, APIs, and desktop clients.

---

## 🚀 Project Goals

- ✅ **Offline-First**: All data is stored locally by default
- ✅ **Modular**: Each feature is independent and extensible
- ✅ **Privacy-Focused**: No unnecessary internet access
- ✅ **Sync-Aware**: Local sync between watch ↔ phone, desktop ↔ mobile
- ✅ **Future-Ready**: Homelab and API integrations planned

---

## 🧱 Phased Roadmap

### 📦 Phase 1: Android LifeLogging App (Now)
- Battery level, charging sessions, uptime, screen-on time
- App usage stats (via Digital Wellbeing or UsageStatsManager)
- Jetpack Compose UI
- Local database (Room) with optional export

### ⌚ Phase 2: WearOS + Android Auto Integration
- Sync smartwatch battery, activity, and device info via Bluetooth
- Display vehicle data (e.g., car battery, trip time via Android Auto APIs)

### 🖥️ Phase 3: Desktop/PC Companion App
- Track desktop uptime, active apps, screen usage
- Sync with Android app via LAN or direct Wi-Fi

### 🌐 Phase 4: Homelab & Service API Integration
- Integrate with self-hosted services (e.g., inventory, metrics)
- Support REST, MQTT, and other interfaces
- Custom dashboard widgets for third-party APIs

---

## 🛠️ Tech Stack

| Platform   | Stack/Tools                                             |
|------------|---------------------------------------------------------|
| Android    | Kotlin, Jetpack Compose, Room, WorkManager              |
| WearOS     | Kotlin, Health Services, Wear APIs                      |
| PC/Desktop | TBD (possibly Electron, Tauri, or Kotlin Multiplatform) |
| Sync       | Bluetooth, Local LAN, JSON/Protobuf                     |
| APIs       | REST, WebSockets, MQTT, Shell scripts                   |
| Dev Tools  | GitHub, Google IDX, Firebase Studio (for now)           |

---

## 📁 Project Structure (Planned)

```
lifehub/
├── app/                      # Android App
│   ├── core/                # Shared logic & data models
│   ├── features/            # Modular LifeLogging components
│   ├── sync/                # Sync engine (watch ↔ phone)
├── desktop-client/          # Future desktop version
├── homelab-integrations/    # API modules, dashboards
├── docs/                    # Architecture, diagrams
```

---

## 🧩 Example Future Modules

- [ ] Device usage analytics
- [ ] Focus sessions / burnout alerts
- [ ] Homelab service status tile
- [ ] Cloudless device sync via local network
- [ ] Encrypted local backups

---

## 🤝 Contributing (Planned)

> For now, this is a solo project. Contributions and suggestions are welcome in Issues or Discussions. Will open for PRs later.

---

## 📄 License

This project will be released under the MIT License. (TBD)

---

## 📌 Status

🔧 **Actively in development** – Phase 1 (Android core app) underway  
🛠️ Hosted with GitHub + Google IDX  
🌍 No cloud dependency – local-first

---

## 👤 Author

**rentouw**