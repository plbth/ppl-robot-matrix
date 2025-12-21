package com.group.ui;

import java.util.List;
import com.group.model.RobotState;
import javafx.scene.canvas.Canvas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class GameRenderer {
    protected Canvas canvas;
    protected GraphicsContext gc;

    private static final int GRID_SIZE = 10;
    private static final int CELL_SIZE = 50; 
    private static final double ANIMATION_DELAY = 0.5; 
    private static final int OFFSET = 30; 

    public GameRenderer(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void drawGrid() {
        double w = canvas.getWidth();
        double h = canvas.getHeight();

        gc.setFill(Color.web("#e0e0e0")); // More subtle gray for margin
        gc.fillRect(0, 0, w, h);

        gc.setFill(Color.web("#f4f4f4"));
        gc.fillRect(OFFSET, OFFSET, GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE);

        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(1);

        for (int i = 0; i <= GRID_SIZE; i++) {
            double y = OFFSET + i * CELL_SIZE;
            gc.strokeLine(OFFSET, y, w, y);
        }
        for (int i = 0; i <= GRID_SIZE; i++) {
            double x = OFFSET + i * CELL_SIZE;
            gc.strokeLine(x, OFFSET, x, h);
        }

        // Draw coordinate
        gc.setFill(Color.DARKSLATEGRAY);
        gc.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
        gc.setTextAlign(TextAlignment.CENTER);

        for (int i = 0; i < GRID_SIZE; i++) {
            String label = String.valueOf(i);
            double labelX = OFFSET + (i * CELL_SIZE) + (CELL_SIZE / 2.0);
            double labelY = (OFFSET / 2.0) + 4;
            gc.fillText(label, labelX, labelY);

            double rowLabelX = (OFFSET / 2.0); 
            double rowLabelY = OFFSET + (i * CELL_SIZE) + (CELL_SIZE / 2.0) + 4;
            gc.fillText(label, rowLabelX, rowLabelY);
        }
    }
    
    public void animateRobot(List<RobotState> history) {
        if (history == null || history.isEmpty()) return;

        Timeline timeline = new Timeline();

        for (int i = 0; i < history.size(); i++) {
            RobotState state = history.get(i);
            KeyFrame frame = new KeyFrame(
                Duration.seconds(i * ANIMATION_DELAY),
                e -> {
                    clearCanvas();
                    drawGrid();
                    renderRobotStep(state);
                }
            );
            timeline.getKeyFrames().add(frame);
        }
        timeline.play();
    }

    private void renderRobotStep(RobotState state) {
        // Safe color parsing
        try {
            gc.setFill(Color.web(state.color()));
        } catch (Exception ex) {
            gc.setFill(Color.BLUE);
        }

        double drawX = OFFSET + state.x() * CELL_SIZE;
        double drawY = OFFSET + state.y() * CELL_SIZE;
        double padding = 4;

        gc.fillRoundRect(
            drawX + padding,
            drawY + padding,
            CELL_SIZE - padding * 2,
            CELL_SIZE - padding * 2,
            10, 10
        );

        drawHeadingArrow(state, drawX, drawY);
    }

    private void drawHeadingArrow(RobotState state, double startX, double startY) {
        double cx = startX + CELL_SIZE / 2.0;
        double cy = startY + CELL_SIZE / 2.0;
        double len = CELL_SIZE / 3.0;

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(3);

        switch (state.headingIndex()) {
            case 0 -> gc.strokeLine(cx, cy + len, cx, cy - len);
            case 1 -> gc.strokeLine(cx - len, cy, cx + len, cy);
            case 2 -> gc.strokeLine(cx, cy - len, cx, cy + len);
            case 3 -> gc.strokeLine(cx + len, cy, cx - len, cy);
        }

        double tipX = cx, tipY = cy;
        switch (state.headingIndex()) {
            case 0 -> tipY = cy - len;
            case 1 -> tipX = cx + len;
            case 2 -> tipY = cy + len;
            case 3 -> tipX = cx - len;
        }
        gc.setFill(Color.WHITE);
        gc.fillOval(tipX - 3, tipY - 3, 6, 6);
    }
}