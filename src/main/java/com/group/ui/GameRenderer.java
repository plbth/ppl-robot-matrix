package com.group.ui;

import java.util.List;

import com.group.model.RobotState;

import javafx.scene.canvas.Canvas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;
/**
 * Interface cho GameRenderer
 * Member 4 
 */
public class GameRenderer {
    protected Canvas canvas;
    protected GraphicsContext gc;

    private static final int GRID_SIZE = 10;
    private static final double ANIMATION_DELAY = 0.5; // Delay giữa mỗi bước robot (giây)
    
    public GameRenderer(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        System.out.println("GameRenderer initialized with canvas: " + 
                          canvas.getWidth() + "x" + canvas.getHeight());
    }
    public void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    // Các phương thức cần triển khai bởi Member 4
    public void drawGrid() {
        double w = canvas.getWidth();
        double h = canvas.getHeight();

        double cellW = w / GRID_SIZE;
        double cellH = h / GRID_SIZE;

        gc.setStroke(Color.LIGHTGRAY);
// Horizontal lines
        for (int i = 0; i <= GRID_SIZE; i++) {
            gc.strokeLine(0, i * cellH, w, i * cellH);
        }
        for (int i = 0; i <= GRID_SIZE; i++) {
            gc.strokeLine(i * cellW, 0, i * cellW, h);
        }
    }
    
    public void animateRobot(List<RobotState> history) {
if (history == null || history.isEmpty()) return;

        double w = canvas.getWidth();
        double h = canvas.getHeight();

        double cellW = w / GRID_SIZE;
        double cellH = h / GRID_SIZE;

        Timeline timeline = new Timeline();

        for (int i = 0; i < history.size(); i++) {
            RobotState state = history.get(i);

            KeyFrame frame = new KeyFrame(
                Duration.seconds(i * ANIMATION_DELAY),
                e -> {
                    clearCanvas();
                    drawGrid();

                    // Robot color from backend
                    gc.setFill(Color.web(state.color(), Color.BLUE));

                    // Draw robot (square)
                    gc.fillRect(
                        state.x() * cellW,
                        state.y() * cellH,
                        cellW,
                        cellH
                    );

                    // Draw robot heading
                    drawHeadingArrow(state, cellW, cellH);
                }
            );

            timeline.getKeyFrames().add(frame);
        }

        timeline.play();
    }
    private void drawHeadingArrow(RobotState state, double cellW, double cellH) {
        double cx = state.x() * cellW + cellW / 2;
        double cy = state.y() * cellH + cellH / 2;

        double len = cellW / 2;

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        switch (state.headingIndex()) {
            case 0 -> gc.strokeLine(cx, cy, cx, cy - len); // NORTH
            case 1 -> gc.strokeLine(cx, cy, cx + len, cy); // EAST
            case 2 -> gc.strokeLine(cx, cy, cx, cy + len); // SOUTH
            case 3 -> gc.strokeLine(cx, cy, cx - len, cy); // WEST
        }
    }
}
