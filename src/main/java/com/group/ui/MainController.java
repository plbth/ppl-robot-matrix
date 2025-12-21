package com.group.ui;

import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.group.antlr.RobotLexer;
import com.group.antlr.RobotParser;
import com.group.logic.RobotInterpreter;
import com.group.model.RobotState;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class MainController {
    
    @FXML private TextArea codeArea;
    @FXML private Button runB;
    @FXML private Button resetB;
    @FXML private GridPane dPad;
    @FXML private Canvas gameCanvas;
    @FXML private Label statusLabel;
    
    @FXML private Button upBtn;
    @FXML private Button leftBtn;
    @FXML private Button rightBtn;
    @FXML private Button downBtn;
    
    private GameRenderer renderer;
    
    
    @FXML
    public void initialize() {
        String css = getClass().getResource("/style.css").toExternalForm();       

        System.out.println("MainController initialized!");
        
        checkComponents();  
        
        renderer = new GameRenderer(gameCanvas);
        renderer.drawGrid();
        
        
        setupRunButton();
        setupResetButton();
        setupDPad();
        
        updateStatus("Ready - Enter robot commands");
        
        addSamplePrompt();
    }
    
    /**
     * Check components
     */
    private void checkComponents() {
        System.out.println("=== Checking FXML Components ===");
        System.out.println("codeArea: " + (codeArea != null ? "OK" : "NULL"));
        System.out.println("runB: " + (runB != null ? "OK" : "NULL"));
        System.out.println("resetB: " + (resetB != null ? "OK" : "NULL"));
        System.out.println("dPad: " + (dPad != null ? "OK" : "NULL"));
        System.out.println("gameCanvas: " + (gameCanvas != null ? "OK" : "NULL"));
        System.out.println("statusLabel: " + (statusLabel != null ? "OK" : "NULL"));
        System.out.println("upBtn: " + (upBtn != null ? "OK" : "NULL"));
        System.out.println("leftBtn: " + (leftBtn != null ? "OK" : "NULL"));
        System.out.println("rightBtn: " + (rightBtn != null ? "OK" : "NULL"));
        System.out.println("downBtn: " + (downBtn != null ? "OK" : "NULL"));
        System.out.println("=================================");
    }
    
    /**
     * Add sample code template to editor
     */
     private void addSamplePrompt() {
        String sampleCode = """
            BEGIN
            SET_PEN "red"
            MOVE FORWARD 3
            TURN RIGHT
            LOOP 4 {
                MOVE FORWARD 2
                TURN RIGHT
            }
            END""";
        
        codeArea.setText(sampleCode);
        // Move cursor to the end for convenient continued typing
        codeArea.positionCaret(codeArea.getLength());
    }
    /**
     * Setup D-Pad button handlers
     */
    private void setupDPad() {
        System.out.println("Setting up D-Pad buttons...");
        
        upBtn.setOnAction(e -> {
            appendCommand("MOVE FORWARD 1");
            updateStatus("Added: MOVE FORWARD 1");
            System.out.println("Up button clicked - added MOVE FORWARD 1");
        });
        
        downBtn.setOnAction(e -> {
            appendCommand("MOVE BACK 1");
            updateStatus("Added: MOVE BACK 1");
            System.out.println("Down button clicked - added MOVE BACK 1");
        });
        
        leftBtn.setOnAction(e -> {
            appendCommand("TURN LEFT");
            updateStatus("Added: TURN LEFT");
            System.out.println("Left button clicked - added TURN LEFT");
        });
        
        rightBtn.setOnAction(e -> {
            appendCommand("TURN RIGHT");
            updateStatus("Added: TURN RIGHT");
            System.out.println("Right button clicked - added TURN RIGHT");
        });
        
        System.out.println("D-Pad configuration complete");
    }
    
    /**
     * Append command to code editor
     */
    private void appendCommand(String command) {
        String currentText = codeArea.getText();
        
        if (!currentText.isEmpty() && !currentText.endsWith("\n")) {
            codeArea.appendText("\n");
        }
        
        codeArea.appendText(command);
        codeArea.positionCaret(codeArea.getLength());
        codeArea.appendText("\n");
    }
    
    /**
     * Setup RUN button handler
     */
    private void setupRunButton() {
        runB.setOnAction(e -> {
            System.out.println("Run button clicked");
            
            try {
                String sourceCode = codeArea.getText().trim();
                if (sourceCode.isEmpty()) {
                    updateStatus("Error: Please enter robot commands");
                    return;
                }
                
                if (!isValidCode(sourceCode)) {
                    updateStatus("Error: Code must start with BEGIN and end with END");
                    return;
                }
                
                updateStatus("Parsing and executing code...");
                
                List<RobotState> history = executeBackend(sourceCode);
                updateStatus("Found " + history.size() + " robot states");
                renderer.animateRobot(history);
                
                RobotState lastState = history.get(history.size() - 1);
                updateStatus("[SUCCESS] Execution complete. " + lastState.toString());
                
            } catch (Exception ex) {
                handleError(ex);
            }
        });
    }
    
    /**
     * Validate basic code structure (BEGIN and END keywords)
     */
    private boolean isValidCode(String code) {
        return code.startsWith("BEGIN") && code.contains("END");
    }
    
    /**
     * Execute code through ANTLR parser and interpreter
     */
    private List<RobotState> executeBackend(String sourceCode) throws Exception {
        System.out.println("Executing backend with code length: " + sourceCode.length());
        
        try {
            // 1. Lexer setup
            RobotLexer lexer = new RobotLexer(CharStreams.fromString(sourceCode));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            
            // 2. Parser setup
            RobotParser parser = new RobotParser(tokens);
            
            // Remove default console error listener to avoid duplicate messages
            parser.removeErrorListeners(); 
            
            // Add a custom error listener to throw an exception on syntax errors
            final StringBuilder errorMessages = new StringBuilder(); // Collect all error messages
            parser.addErrorListener(new org.antlr.v4.runtime.BaseErrorListener() {
                @Override
                public void syntaxError(org.antlr.v4.runtime.Recognizer<?, ?> recognizer, 
                                        Object offendingSymbol, int line, int charPositionInLine, 
                                        String msg, org.antlr.v4.runtime.RecognitionException e) {
                    // Append error details to be shown in UI
                    errorMessages.append(String.format("Syntax Error at line %d:%d - %s%n", line, charPositionInLine, msg));
                }
            });

            // 3. Parse tree
            ParseTree tree = parser.prog();
            System.out.println("Parse tree created successfully.");
            
            // Check if any syntax errors were collected
            if (errorMessages.length() > 0) {
                throw new RuntimeException(errorMessages.toString()); // Throw collected errors
            }

            // 4. Interpreter execution
            RobotInterpreter interpreter = new RobotInterpreter();
            interpreter.visit(tree);
            
            // 5. Get robot's history
            List<RobotState> history = interpreter.getHistory();
            System.out.println("Generated " + history.size() + " robot states.");
            
            return history;
            
        } catch (Exception e) {
            // Catch any exception from parsing or interpreting
            System.err.println("Backend execution error: " + e.getMessage());
            // Re-throw as a generic exception to be handled by the UI
            throw new Exception("Execution failed: " + e.getMessage(), e);
        }
    }
    
    /**
     * Handle and display execution errors
     */
    private void handleError(Exception ex) {
        String errorMessage;
        
        if (ex.getMessage().contains("BEGIN")) {
            errorMessage = "[ERROR] Missing BEGIN statement";
        } else if (ex.getMessage().contains("END")) {
            errorMessage = "[ERROR] Missing END statement";
        } else if (ex.getMessage().contains("FORWARD") || ex.getMessage().contains("BACK")) {
            errorMessage = "[ERROR] MOVE command requires direction (FORWARD/BACK) and distance";
        } else if (ex.getMessage().contains("SET_PEN")) {
            errorMessage = "[ERROR] SET_PEN requires a color in quotes (e.g., \"red\")";
        } else if (ex.getMessage().contains("LOOP")) {
            errorMessage = "[ERROR] LOOP requires count and block { }";
        } else {
            errorMessage = "[ERROR] " + ex.getMessage();
        }
        
        updateStatus(errorMessage);
        System.err.println("Detailed error: " + ex.getClass().getName() + " - " + ex.getMessage());
    }
    
    /**
     * Setup RESET button handler
     */
    private void setupResetButton() {
        resetB.setOnAction(e -> {
            System.out.println("Reset button clicked");
            
            renderer.clearCanvas();
            renderer.drawGrid();
            codeArea.clear();
            updateStatus("[SUCCESS] Reset complete. Ready for new commands.");
            codeArea.requestFocus();
        });
    }
    
    /**
     * Update status label safely on UI thread
     */
    private void updateStatus(String message) {
        Platform.runLater(() -> {
            statusLabel.setText(message);
        });
    }
    
    /**
     * Get reference to game canvas
     */
    public Canvas getGameCanvas() {
        return gameCanvas;
    }
}