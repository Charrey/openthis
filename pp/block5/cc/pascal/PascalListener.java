// Generated from Pascal.g4 by ANTLR 4.7.1
package block5.cc.pascal;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PascalParser}.
 */
public interface PascalListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code funcDecl}
	 * labeled alternative in {@link PascalParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterFuncDecl(PascalParser.FuncDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcDecl}
	 * labeled alternative in {@link PascalParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitFuncDecl(PascalParser.FuncDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code procDecl}
	 * labeled alternative in {@link PascalParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterProcDecl(PascalParser.ProcDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code procDecl}
	 * labeled alternative in {@link PascalParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitProcDecl(PascalParser.ProcDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDecl}
	 * labeled alternative in {@link PascalParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(PascalParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDecl}
	 * labeled alternative in {@link PascalParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(PascalParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#pars}.
	 * @param ctx the parse tree
	 */
	void enterPars(PascalParser.ParsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#pars}.
	 * @param ctx the parse tree
	 */
	void exitPars(PascalParser.ParsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssStat(PascalParser.AssStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssStat(PascalParser.AssStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(PascalParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(PascalParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(PascalParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(PascalParser.WhileStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBlockStat(PascalParser.BlockStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBlockStat(PascalParser.BlockStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterInStat(PascalParser.InStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitInStat(PascalParser.InStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code outStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterOutStat(PascalParser.OutStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code outStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitOutStat(PascalParser.OutStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterCallStat(PascalParser.CallStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitCallStat(PascalParser.CallStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exitStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterExitStat(PascalParser.ExitStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exitStat}
	 * labeled alternative in {@link PascalParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitExitStat(PascalParser.ExitStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(PascalParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(PascalParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idTarget}
	 * labeled alternative in {@link PascalParser#target}.
	 * @param ctx the parse tree
	 */
	void enterIdTarget(PascalParser.IdTargetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idTarget}
	 * labeled alternative in {@link PascalParser#target}.
	 * @param ctx the parse tree
	 */
	void exitIdTarget(PascalParser.IdTargetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayTarget}
	 * labeled alternative in {@link PascalParser#target}.
	 * @param ctx the parse tree
	 */
	void enterArrayTarget(PascalParser.ArrayTargetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayTarget}
	 * labeled alternative in {@link PascalParser#target}.
	 * @param ctx the parse tree
	 */
	void exitArrayTarget(PascalParser.ArrayTargetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpr(PascalParser.ArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpr(PascalParser.ArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTrueExpr(PascalParser.TrueExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTrueExpr(PascalParser.TrueExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(PascalParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(PascalParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumExpr(PascalParser.NumExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumExpr(PascalParser.NumExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPlusExpr(PascalParser.PlusExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPlusExpr(PascalParser.PlusExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIndexExpr(PascalParser.IndexExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIndexExpr(PascalParser.IndexExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParExpr(PascalParser.ParExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParExpr(PascalParser.ParExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompExpr(PascalParser.CompExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompExpr(PascalParser.CompExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prfExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPrfExpr(PascalParser.PrfExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prfExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPrfExpr(PascalParser.PrfExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCallExpr(PascalParser.CallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCallExpr(PascalParser.CallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFalseExpr(PascalParser.FalseExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFalseExpr(PascalParser.FalseExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(PascalParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(PascalParser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(PascalParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link PascalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(PascalParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intType}
	 * labeled alternative in {@link PascalParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntType(PascalParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link PascalParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntType(PascalParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link PascalParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBoolType(PascalParser.BoolTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link PascalParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBoolType(PascalParser.BoolTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link PascalParser#type}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(PascalParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link PascalParser#type}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(PascalParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#bound}.
	 * @param ctx the parse tree
	 */
	void enterBound(PascalParser.BoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#bound}.
	 * @param ctx the parse tree
	 */
	void exitBound(PascalParser.BoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(PascalParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(PascalParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(PascalParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(PascalParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(PascalParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(PascalParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(PascalParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(PascalParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#prfOp}.
	 * @param ctx the parse tree
	 */
	void enterPrfOp(PascalParser.PrfOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#prfOp}.
	 * @param ctx the parse tree
	 */
	void exitPrfOp(PascalParser.PrfOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#multOp}.
	 * @param ctx the parse tree
	 */
	void enterMultOp(PascalParser.MultOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#multOp}.
	 * @param ctx the parse tree
	 */
	void exitMultOp(PascalParser.MultOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#plusOp}.
	 * @param ctx the parse tree
	 */
	void enterPlusOp(PascalParser.PlusOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#plusOp}.
	 * @param ctx the parse tree
	 */
	void exitPlusOp(PascalParser.PlusOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void enterBoolOp(PascalParser.BoolOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void exitBoolOp(PascalParser.BoolOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#compOp}.
	 * @param ctx the parse tree
	 */
	void enterCompOp(PascalParser.CompOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#compOp}.
	 * @param ctx the parse tree
	 */
	void exitCompOp(PascalParser.CompOpContext ctx);
}