grammar Pascal;

import SimplePascal;

/** Function, procedure or variable declaration. */
decl: FUNC ID pars? COLON type SEMI
      body SEMI                       #funcDecl
    | PROC ID pars? SEMI
      body SEMI                       #procDecl
    | VAR (var SEMI)+                  #varDecl
    ;

/** Parameter list in a function or procedure declaration. */
pars: LPAR VAR? var (SEMI VAR? var)* RPAR 
    ;

/** Statement. 
 * Extends the imported rule with call and exit statements. */
stat: target ASS expr                #assStat
    | IF expr THEN stat (ELSE stat)? #ifStat
    | WHILE expr DO stat             #whileStat
    | block                          #blockStat
    | IN LPAR STR COMMA ID RPAR      #inStat  // auxiliary, not Pascal
    | OUT LPAR STR COMMA expr RPAR   #outStat // auxiliary, not Pascal
    | ID args?                       #callStat
    | EXIT                           #exitStat
    ;

 /** Arguments of a call. */   
args: LPAR (expr (COMMA expr)*)? RPAR
    ;

/** Target of an assignment.
 * Extends the imported rule with an array target.
 */
target 
    : ID               #idTarget
    | ID LSQ expr RSQ  #arrayTarget
    ;

/** Expression. 
 * Extends the imported rule with call, indexed and array expressions.
 */
expr: prfOp expr        #prfExpr
    | expr multOp expr  #multExpr
    | expr plusOp expr  #plusExpr
    | expr boolOp expr  #boolExpr
    | expr compOp expr  #compExpr
    | LPAR expr RPAR    #parExpr
    | ID args           #callExpr
    | ID                #idExpr
    | NUM               #numExpr
    | TRUE              #trueExpr
    | FALSE             #falseExpr
    | ID LSQ expr RSQ   #indexExpr
    | LSQ (expr (COMMA expr)*)? RSQ
                        #arrayExpr
    ;

/** Data type.
 * Extends the imported rule with array types. */
type: INTEGER  #intType
    | BOOLEAN  #boolType
    | ARRAY LSQ bound DOTDOT bound RSQ OF type
               #arrayType
    ;

/** Array upper or lowed bound. */
bound : MINUS? NUM;

// Keywords
ARRAY:   A R R A Y;
OF:      O F ;


DOTDOT: '..';
LSQ:    '[';
RSQ:    ']';