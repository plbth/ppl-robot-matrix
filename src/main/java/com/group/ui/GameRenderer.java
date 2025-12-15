package com.group.ui;

import java.util.List;

import com.group.model.RobotState;

import javafx.scene.canvas.Canvas;

/**
 * Interface cho GameRenderer
 * Member 4 
 */
public class GameRenderer {
    protected Canvas canvas;
    
    public GameRenderer(Canvas canvas) {
        this.canvas = canvas;
        System.out.println("GameRenderer initialized with canvas: " + 
                          canvas.getWidth() + "x" + canvas.getHeight());
    }
    
    // Các phương thức cần triển khai bởi Member 4
    public void drawGrid() {
        System.out.println("drawGrid() - To be implemented by Member 4");
        // Member 4 
    }
    
    public void animateRobot(List<RobotState> history) {
        System.out.println("animateRobot() called with " + history.size() + " states");
        // Debug: hiển thị các states
        for (int i = 0; i < history.size(); i++) {
            System.out.println("  State " + i + ": " + history.get(i));
        }
        System.out.println("To be implemented by Member 4");
        // Member 4 
    }
    
    public void clearCanvas() {
        System.out.println("clearCanvas() - To be implemented by Member 4");
        // Member 4 
    }
}
