package com.group.model;

/**
 * Represents the state of the robot in a 10x10 GRID system.
 * Coordinates (x, y) are INTEGERS representing Cell Indices (0 to 9).
 * This class is Immutable.
 */
public record RobotState(int x, int y, int headingIndex, String color) {

    // Grid Boundaries
    private static final int MAX_GRID = 9;
    private static final int MIN_GRID = 0;

    // Heading Convention: 0=North, 1=East, 2=South, 3=West
    
    public static RobotState initialState() {
        // Start at (0,0) Top-Left, facing East (Right)
        return new RobotState(0, 0, 1, "blue");
    }

    /**
     * Calculates the new position based on direction and steps.
     * Includes boundary checks (0-9) to prevent out-of-bounds errors.
     */
    public RobotState move(String direction, int steps) {
        // Determine step direction (Forward = +steps, Back = -steps)
        int stepSize;
        if (direction.equalsIgnoreCase("FORWARD")) {
            stepSize = steps;
        } else if (direction.equalsIgnoreCase("BACK")) {
            stepSize = -steps;
        } else {
            // If direction is empty or invalid due to parser error -> Report error immediately
            throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        int newX = this.x;
        int newY = this.y;

        // Apply movement based on current heading
        switch (this.headingIndex) {
            case 0 -> newY -= stepSize; // North: Y decreases (goes up)
            case 1 -> newX += stepSize; // East:  X increases (goes right)
            case 2 -> newY += stepSize; // South: Y increases (goes down)
            case 3 -> newX -= stepSize; // West:  X decreases (goes left)
        }

        // CLAMPING LOGIC: Keep robot inside the 10x10 grid [0, 9]
        // This prevents the Frontend from crashing or drawing off-screen.
        newX = Math.max(MIN_GRID, Math.min(MAX_GRID, newX));
        newY = Math.max(MIN_GRID, Math.min(MAX_GRID, newY));

        return new RobotState(newX, newY, this.headingIndex, this.color);
    }

    public RobotState turn(String rotation) {
        // Right = +1 index, Left = -1 index
        int delta = rotation.equalsIgnoreCase("RIGHT") ? 1 : -1;
        
        // Calculate new heading (modulo 4)
        int newHeading = (this.headingIndex + delta) % 4;
        
        // Handle Java's negative modulo behavior
        if (newHeading < 0) newHeading += 4;

        return new RobotState(this.x, this.y, newHeading, this.color);
    }

    public RobotState changeColor(String colorRaw) {
        // Remove quotes: "red" -> red
        String cleanColor = colorRaw.replace("\"", "");
        return new RobotState(this.x, this.y, this.headingIndex, cleanColor);
    }

    @Override
    public String toString() {
        String dirName = switch (headingIndex) {
            case 0 -> "NORTH";
            case 1 -> "EAST";
            case 2 -> "SOUTH";
            case 3 -> "WEST";
            default -> "UNKNOWN";
        };
        return String.format("[Pos: (%d, %d) | Face: %s | Color: %s]", x, y, dirName, color);
    }
}