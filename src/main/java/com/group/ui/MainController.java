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
     * Thêm prompt text mẫu vào TextArea
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
        
        codeArea.setPromptText(sampleCode);
    }
    
    // Logic D-Pad 
    private void setupDPad() {
        System.out.println("Setting up D-Pad buttons...");
        
        // Sử dụng fx:id trực tiếp - cách này an toàn hơn
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
     * Helper method để append command vào codeArea
     */
    private void appendCommand(String command) {
        String currentText = codeArea.getText();
        
        // Thêm newline nếu cần
        if (!currentText.isEmpty() && !currentText.endsWith("\n")) {
            codeArea.appendText("\n");
        }
        
        // Append command
        codeArea.appendText(command);
        
        // Auto-scroll đến cuối
        codeArea.positionCaret(codeArea.getLength());
        
        // Thêm newline sau command để chuẩn bị cho command tiếp theo
        codeArea.appendText("\n");
    }
    
     //Controller xử lý RUN button
    private void setupRunButton() {
        runB.setOnAction(e -> {
            System.out.println("Run button clicked");
            
            try {
                // 1. Lấy code từ codeArea
                String sourceCode = codeArea.getText().trim();
                if (sourceCode.isEmpty()) {
                    updateStatus("Error: Please enter robot commands");
                    return;
                }
                
                // 2. Validate basic syntax
                if (!isValidCode(sourceCode)) {
                    updateStatus("Error: Code must start with BEGIN and end with END");
                    return;
                }
                
                updateStatus("Parsing and executing code...");
                
                // 3. Gửi sang backend
                List<RobotState> history = executeBackend(sourceCode);
                
                // 4. Hiển thị số bước
                updateStatus("Found " + history.size() + " robot states");
                
                // 5. Truyền cho renderer (Member 4)
                renderer.animateRobot(history);
                
                // 6. Hiển thị kết quả cuối
                RobotState lastState = history.get(history.size() - 1);
                updateStatus("✓ Execution complete. " + lastState.toString());
                
            } catch (Exception ex) {
                handleError(ex);
            }
        });
    }
    
    /**
     * Kiểm tra code cơ bản
     */
    private boolean isValidCode(String code) {
        return code.startsWith("BEGIN") && code.contains("END");
    }
    
    /**
     * Giao tiếp với Backend
     */
    private List<RobotState> executeBackend(String sourceCode) throws Exception {
        System.out.println("Executing backend with code length: " + sourceCode.length());
        
        try {
            // 1. Lexer
            RobotLexer lexer = new RobotLexer(CharStreams.fromString(sourceCode));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            
            // 2. Parser
            RobotParser parser = new RobotParser(tokens);
            
            // 3. Parse tree
            ParseTree tree = parser.prog();
            System.out.println("Parse tree created successfully");
            
            // 4. Interpreter
            RobotInterpreter interpreter = new RobotInterpreter();
            interpreter.visit(tree);
            
            // 5. Get history
            List<RobotState> history = interpreter.getHistory();
            System.out.println("Generated " + history.size() + " robot states");
            
            return history;
            
        } catch (Exception e) {
            System.err.println("Backend error: " + e.getMessage());
            throw new Exception("Failed to execute robot commands: " + e.getMessage());
        }
    }
    
    /**
     * Xử lý lỗi chi tiết
     */
    private void handleError(Exception ex) {
        String errorMessage;
        
        if (ex.getMessage().contains("BEGIN")) {
            errorMessage = "❌ Missing BEGIN statement";
        } else if (ex.getMessage().contains("END")) {
            errorMessage = "❌ Missing END statement";
        } else if (ex.getMessage().contains("FORWARD") || ex.getMessage().contains("BACK")) {
            errorMessage = "❌ MOVE command requires direction (FORWARD/BACK) and distance";
        } else if (ex.getMessage().contains("SET_PEN")) {
            errorMessage = "❌ SET_PEN requires a color in quotes (e.g., \"red\")";
        } else if (ex.getMessage().contains("LOOP")) {
            errorMessage = "❌ LOOP requires count and block { }";
        } else {
            errorMessage = "❌ Error: " + ex.getMessage();
        }
        
        updateStatus(errorMessage);
        System.err.println("Detailed error: " + ex.getClass().getName() + " - " + ex.getMessage());
    }
    
    /**
     * Thiết lập RESET button
     */
    private void setupResetButton() {
        resetB.setOnAction(e -> {
            System.out.println("Reset button clicked");
            
            // 1. Clear canvas
            renderer.clearCanvas();
            renderer.drawGrid();
            
            // 2. Clear code area
            codeArea.clear();
            
            // 3. Reset status
            updateStatus("✓ Reset complete. Ready for new commands.");
            
            // 4. Focus lại vào code area
            codeArea.requestFocus();
        });
    }
    
    /**
     * Cập nhật statusLabel an toàn
     */
    private void updateStatus(String message) {
        Platform.runLater(() -> {
            statusLabel.setText(message);
        });
    }
    
    /**
     * Getter cho canvas (cho Member 4)
     */
    public Canvas getGameCanvas() {
        return gameCanvas;
    }
}