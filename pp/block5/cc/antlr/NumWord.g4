grammar NumWord;

@header{package pp.block5.cc.antlr;}

//B{reader}
sentence: (number word)+ EOF;
number: NUMBER;
word: WORD;
//E{reader}

fragment DIGIT: [0-9];
fragment LETTER: [a-zA-Z];
WORD: LETTER+;
NUMBER: DIGIT+;
WS: [ \t\r\n]+ -> skip;