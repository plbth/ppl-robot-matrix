package com.group.logic;

import com.group.antlr.RobotBaseVisitor;
import com.group.antlr.RobotParser;
import com.group.model.RobotState;

import java.util.ArrayList;
import java.util.List;

/**
 * Visitor Implementation.
 * Walks the AST and generates a sequence of RobotStates.
 */
public class RobotInterpreter extends RobotBaseVisitor<Void> {

    private final List<RobotState> history = new ArrayList<>();
    private RobotState currentState;

    public RobotInterpreter() {
        // Initialize with default state
        this.currentState = RobotState.initialState();
        this.history.add(this.currentState);
    }

    public List<RobotState> getHistory() {
        return new ArrayList<>(history);
    }

    @Override
    public Void visitMoveCmd(RobotParser.MoveCmdContext ctx) {
        String dir = ctx.dir().getText(); // FORWARD or BACK
        int distance = Integer.parseInt(ctx.distance().getText());

        // Update logic: Move -> New State
        this.currentState = this.currentState.move(dir, distance);
        this.history.add(this.currentState);
        
        return null;
    }

    @Override
    public Void visitTurnCmd(RobotParser.TurnCmdContext ctx) {
        String rotate = ctx.rotate().getText(); // LEFT or RIGHT

        // Update logic: Turn -> New State
        this.currentState = this.currentState.turn(rotate);
        this.history.add(this.currentState);
        
        return null;
    }

    @Override
    public Void visitPenCmd(RobotParser.PenCmdContext ctx) {
        String color = ctx.color().getText();
        this.currentState = this.currentState.changeColor(color);
        this.history.add(this.currentState);
        return null;
    }

    @Override
    public Void visitLoopCmd(RobotParser.LoopCmdContext ctx) {
        int loopCount = Integer.parseInt(ctx.count().getText());

        // Execute the inner block N times
        for (int i = 0; i < loopCount; i++) {
            visit(ctx.block());
        }
        return null;
    }
}