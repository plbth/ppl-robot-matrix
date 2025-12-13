grammar Robot;

// --- Parser Rules ---

prog: 'BEGIN' stmt* 'END' ;

stmt
    : 'SET_PEN' color            # PenCmd
    | 'MOVE' dir distance        # MoveCmd
    | 'TURN' rotate              # TurnCmd
    | 'LOOP' count block         # LoopCmd
    ;

block: '{' stmt* '}' ;

// --- Lexer Rules ---

dir: 'FORWARD' | 'BACK' ;
rotate: 'LEFT' | 'RIGHT' ;
distance: INT ;
count: INT ;
color: STRING ;

INT: [0-9]+ ;
STRING: '"' [a-zA-Z0-9]+ '"' ;  // Example: "red", "blue"
WS: [ \t\r\n]+ -> skip ;        // Skip whitespace