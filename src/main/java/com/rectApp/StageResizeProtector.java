package com.rectApp;

import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Класс, обеспечивающий защиту от уменьшения окна,
 * если прямоугольник выходит за пределы рабочей области (drawPane).
 */
public class StageResizeProtector {

    private final Stage stage;
    private final Pane drawPane;
    private final Rectangle rectangle;
    private final double padding;

    /**
     * Конструктор: подключает защиту.
     *
     * @param stage     главное окно
     * @param drawPane  панель, в пределах которой должен находиться прямоугольник
     * @param rectangle прямоугольник
     * @param padding   минимальное расстояние от краёв панели
     */
    public StageResizeProtector(Stage stage, Pane drawPane, Rectangle rectangle, double padding) {
        this.stage = stage;
        this.drawPane = drawPane;
        this.rectangle = rectangle;
        this.padding = padding;
        init();
    }

    /** Инициализация слушателей размера главного окна для избежания выходя прямоугольника за границы */
    private void init() {
        drawPane.widthProperty().addListener((obs, oldW, newW) -> onResize());
        drawPane.heightProperty().addListener((obs, oldH, newH) -> onResize());
    }


    /** Обработчик изменения размеров панели. */
    private void onResize() {
        ensureInside();
    }

    /** Перемещает прямоугольник с помощью слушателя
     * внутрь области при необходимости. */
    private void ensureInside() {
        double maxX = drawPane.getWidth()  - rectangle.getWidth()  - padding;
        double maxY = drawPane.getHeight() - rectangle.getHeight() - padding;

        double newX = clamp(rectangle.getX(), 0, maxX);
        double newY = clamp(rectangle.getY(), 0, maxY);

        rectangle.setX(newX);
        rectangle.setY(newY);
    }


    /** Ограничивает значение в пределах допустимого диапазона. */
    private double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
}
