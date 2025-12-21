package com.group;

import com.group.antlr.RobotLexer;
import com.group.antlr.RobotParser;
import com.group.logic.RobotInterpreter;
import com.group.model.RobotState;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        // Test Code: Includes Boundary Check (Trying to move 100 steps)
        String sourceCode = """
            BEGIN
                SET_PEN "red"
                MOVE FORWARD 2
                TURN RIGHT
                MOVE FORWARD 100 
                TURN LEFT
                LOOP 2 {
                    MOVE BACK 1
                }
            END
        """;

        System.out.println("--- Source Code ---");
        System.out.println(sourceCode);

        try {
            // 1. Initialize ANTLR
            RobotLexer lexer = new RobotLexer(CharStreams.fromString(sourceCode));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            RobotParser parser = new RobotParser(tokens);

            // 2. Parse
            ParseTree tree = parser.prog();

            // 3. Interpret
            RobotInterpreter interpreter = new RobotInterpreter();
            interpreter.visit(tree);

            // 4. Print Results
            List<RobotState> history = interpreter.getHistory();
            System.out.println("\n--- Robot Path History ---");
            int step = 0;
            for (RobotState state : history) {
                System.out.printf("Step %d: %s%n", step++, state);
            }
            
            // Check specifically for clamping
            RobotState lastState = history.get(history.size() - 1);
            if (lastState.x() <= 9 && lastState.y() <= 9 && lastState.x() >= 0 && lastState.y() >= 0) {
                System.out.println("\n[PASS] Boundary Check: Robot stayed within grid.");
            } else {
                System.out.println("\n[FAIL] Robot went out of bounds!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}