# 📦 RectangleApp

**RectangleApp** — это JavaFX-приложение, позволяющее пользователю управлять прямоугольником на экране:  
его можно перемещать мышью или задавать координаты вручную.

---

## 🚀 Возможности

- Перемещение прямоугольника мышью
- Ручной ввод координат (X, Y)
- Ограничение перемещения по границам сцены
- Адаптивное поведение при изменении размера окна
- Поддержка FXML (если используется)
- Сборка `.jar` и упаковка в `.exe` (через Launch4j)

---

## 🛠️ Технологии

- **Java 24**
- **JavaFX 24.0.1**
- **IntelliJ IDEA**
- **Launch4j** — упаковка в `.exe`
- *(опционально)* **Maven** — автоматизация сборки

---

## 🔧 Сборка JAR-файла (без Maven)

1. В IntelliJ:  
   `File → Project Structure → Artifacts → + → JAR from Modules with Dependencies`

2. Укажи:
   - основной класс: `com.rectApp.MainApp`
   - путь к выходному JAR

3. Затем:  
   `Build → Build Artifacts → Build`

---

## 🖱️ Запуск JAR вручную

```bash
java --module-path ./lib --add-modules javafx.controls,javafx.fxml --enable-native-access=javafx.graphics -jar RectangleApp.jar
