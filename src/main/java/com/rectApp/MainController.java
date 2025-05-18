package com.rectApp;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Контроллер JavaFX-интерфейса.
 * Обрабатывает перемещение прямоугольника, ручной ввод координат и синхронизацию с UI.
 */
public class MainController {

    @FXML private Pane      drawPane;
    @FXML private Rectangle rectangle;
    @FXML private Label     coordLabel;
    @FXML private TextField xField;
    @FXML private TextField yField;
    @FXML private Label     warningLabel;

    private double offsetX, offsetY;
    private static final double PADDING = 1;

    /**
     * Инициализация интерфейса.
     * Обработчики перетаскивания и слушатели координат.
     */
    @FXML
    private void initialize() {
        initRectangleDrag();
    }

    /** Обработчики мыши для перетаскивания прямоугольника. */
    private void initRectangleDrag() {
        rectangle.setOnMousePressed(this::handleMousePressed);
        rectangle.setOnMouseDragged(this::handleMouseDragged);
    }


    /** Возвращает левую область (drawPane). */
    public Pane getDrawPane() {
        return drawPane;
    }

    /** Возвращает прямоугольник. */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /** Обработчик нажатия мыши на прямоугольник. */
    private void handleMousePressed(MouseEvent e) {
        offsetX = e.getX() - rectangle.getX();
        offsetY = e.getY() - rectangle.getY();
    }

    /** Обработчик перетаскивания прямоугольника. */
    private void handleMouseDragged(MouseEvent e) {
        double targetX = e.getX() - offsetX;
        double targetY = e.getY() - offsetY;
        moveWithCheck(targetX, targetY);
    }

    /** Обработка ручного ввода координат с проверкой. */
    @FXML
    private void handleSetButton() {
        try {
            double x = Double.parseDouble(xField.getText());
            double y = Double.parseDouble(yField.getText());
            moveWithCheck(x, y);
        } catch (NumberFormatException ex) {
            warningLabel.setText("Введите целые числа.");
        }
    }

    /**
     * Перемещает прямоугольник в заданные координаты,
     * ограничивая их допустимыми значениями.
     */
    private void moveWithCheck(double x, double y) {
        double maxX = drawPane.getWidth()  - rectangle.getWidth()  - PADDING;
        double maxY = drawPane.getHeight() - rectangle.getHeight() - PADDING;

        boolean out = x < 0 || y < 0 || x > maxX || y > maxY;
        // перемещение прямоугольника для того, чтобы он не был потерян при ресайзе
        forceMoveRectangle(clamp(x, 0, maxX), clamp(y, 0, maxY));

        warningLabel.setText(out
                ? "Прямоугольник выходит \nза границы панели!"
                : "");
    }

    /**
     * Принудительно перемещает прямоугольник без проверки или предупреждения.
     */
    public void forceMoveRectangle(double x, double y) {
        rectangle.setX(x);
        rectangle.setY(y);
        updateCoordinateUI();
    }

    /** Обновляет текст координат и поля ввода. */
    private void updateCoordinateUI() {
        coordLabel.setText(String.format("Координаты прямоугольника:\n X = %.0f, Y = %.0f",
                rectangle.getX(), rectangle.getY()));
        xField.setText(String.valueOf((int) rectangle.getX()));
        yField.setText(String.valueOf((int) rectangle.getY()));
    }

    /** Ограничение значений в пределах [min; max]. */
    private double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
}
