package block5.cc.simple;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import block5.cc.pascal.SimplePascalBaseVisitor;
import block5.cc.pascal.SimplePascalLexer;
import block5.cc.pascal.SimplePascalParser.AssStatContext;
import block5.cc.pascal.SimplePascalParser.BlockContext;
import block5.cc.pascal.SimplePascalParser.BlockStatContext;
import block5.cc.pascal.SimplePascalParser.BodyContext;
import block5.cc.pascal.SimplePascalParser.BoolExprContext;
import block5.cc.pascal.SimplePascalParser.CompExprContext;
import block5.cc.pascal.SimplePascalParser.FalseExprContext;
import block5.cc.pascal.SimplePascalParser.IdExprContext;
import block5.cc.pascal.SimplePascalParser.IdTargetContext;
import block5.cc.pascal.SimplePascalParser.IfStatContext;
import block5.cc.pascal.SimplePascalParser.InStatContext;
import block5.cc.pascal.SimplePascalParser.MultExprContext;
import block5.cc.pascal.SimplePascalParser.NumExprContext;
import block5.cc.pascal.SimplePascalParser.OutStatContext;
import block5.cc.pascal.SimplePascalParser.ParExprContext;
import block5.cc.pascal.SimplePascalParser.PlusExprContext;
import block5.cc.pascal.SimplePascalParser.PrfExprContext;
import block5.cc.pascal.SimplePascalParser.ProgramContext;
import block5.cc.pascal.SimplePascalParser.TrueExprContext;
import block5.cc.pascal.SimplePascalParser.WhileStatContext;
import iloc.Simulator;
import iloc.model.Label;
import iloc.model.Num;
import iloc.model.Op;
import iloc.model.OpCode;
import iloc.model.Operand;
import iloc.model.Program;
import iloc.model.Reg;
import iloc.model.Str;

/** Class to generate ILOC code for Simple Pascal. */
public class Generator extends SimplePascalBaseVisitor<Op> {
	/** The representation of the boolean value <code>false</code>. */
	public final static Num FALSE_VALUE = new Num(Simulator.FALSE);
	/** The representation of the boolean value <code>true</code>. */
	public final static Num TRUE_VALUE = new Num(Simulator.TRUE);

	/** The base register. */
	private Reg arp = new Reg("r_arp");
	/** The outcome of the checker phase. */
	private block5.cc.simple.Result checkResult;
	/** Association of statement nodes to labels. */
	private ParseTreeProperty<Label> labels;
	/** The program being built. */
	private Program prog;
	/** Register count, used to generate fresh registers. */
	private int regCount;
	/** Association of expression and target nodes to registers. */
	private ParseTreeProperty<Reg> regs;

	/** Generates ILOC code for a given parse tree,
	 * given a pre-computed checker result.
	 */
	public Program generate(ParseTree tree, block5.cc.simple.Result checkResult) {
		this.prog = new Program();
		this.checkResult = checkResult;
		this.regs = new ParseTreeProperty<>();
		this.labels = new ParseTreeProperty<>();
		this.regCount = 0;
		tree.accept(this);
		return this.prog;
	}

	@Override
	public Op visitAssStat(AssStatContext ctx) {
		Op result = visit(ctx.expr());
		setReg(ctx.target(), reg(ctx.expr()));
		visit(ctx.target());
		return result;
	}

	@Override
	public Op visitBlock(BlockContext ctx) {
		Op result = null;
		for (ParseTree stat : ctx.stat()) {
			Op statOp = visit(stat);
			if (result == null) {
				result = statOp;
			}
		}
		return result == null ? emit(OpCode.nop) : result;
	}

	@Override
	public Op visitBlockStat(BlockStatContext ctx) {
		return visit(ctx.block());
	}

	@Override
	public Op visitBody(BodyContext ctx) {
		return super.visit(ctx.block());
	}

	@Override
	public Op visitBoolExpr(BoolExprContext ctx) {
		Op result = visit(ctx.expr(0));
		visit(ctx.expr(1));
		OpCode opCode;
		switch (ctx.boolOp().getStart().getType()) {
		case SimplePascalLexer.AND:
			opCode = OpCode.and;
			break;
		case SimplePascalLexer.OR:
			opCode = OpCode.or;
			break;
		default:
			assert false;
			opCode = null;
		}
		emit(opCode, reg(ctx.expr(0)), reg(ctx.expr(1)), reg(ctx));
		return result;
	}

	@Override
	public Op visitCompExpr(CompExprContext ctx) {
		Op result = visit(ctx.expr(0));
		visit(ctx.expr(1));
		OpCode opCode;
		switch (ctx.compOp().getStart().getType()) {
		case SimplePascalLexer.EQ:
			opCode = OpCode.cmp_EQ;
			break;
		case SimplePascalLexer.NE:
			opCode = OpCode.cmp_NE;
			break;
		case SimplePascalLexer.LT:
			opCode = OpCode.cmp_LT;
			break;
		case SimplePascalLexer.LE:
			opCode = OpCode.cmp_LE;
			break;
		case SimplePascalLexer.GT:
			opCode = OpCode.cmp_GT;
			break;
		case SimplePascalLexer.GE:
			opCode = OpCode.cmp_GE;
			break;
		default:
			assert false;
			opCode = null;
		}
		emit(opCode, reg(ctx.expr(0)), reg(ctx.expr(1)), reg(ctx));
		return result;
	}

	@Override
	public Op visitFalseExpr(FalseExprContext ctx) {
		return emit(OpCode.loadI, FALSE_VALUE, reg(ctx));
	}

	@Override
	public Op visitIdExpr(IdExprContext ctx) {
		return emit(OpCode.loadAI, this.arp, offset(ctx), reg(ctx));
	}

	@Override
	public Op visitIdTarget(IdTargetContext ctx) {
		return emit(OpCode.storeAI, reg(ctx), this.arp, offset(ctx));
	}

	@Override
	public Op visitIfStat(IfStatContext ctx) {
		Op result = visit(ctx.expr());
		result.setLabel(label(ctx));
		Label endLab = createLabel(ctx, "endif");
		Label thenLab = label(ctx.stat(0));
		Label elseLab = ctx.ELSE() == null ? endLab : label(ctx.stat(1));
		emit(OpCode.cbr, reg(ctx.expr()), thenLab, elseLab);
		Op thenOp = visit(ctx.stat(0));
		thenOp.setLabel(thenLab);
		if (ctx.ELSE() != null) {
			emit(OpCode.jumpI, endLab);
			Op elseOp = visit(ctx.stat(1));
			elseOp.setLabel(elseLab);
		}
		emit(endLab, OpCode.nop);
		return result;
	}

	@Override
	public Op visitInStat(InStatContext ctx) {
		String text = ctx.STR().getText();
		Str inStr = new Str(text.substring(1, text.length() - 1));
		Op result = emit(OpCode.in, inStr, reg(ctx));
		setReg(ctx.target(), reg(ctx));
		visit(ctx.target());
		return result;
	}

	@Override
	public Op visitMultExpr(MultExprContext ctx) {
		Op result = visit(ctx.expr(0));
		visit(ctx.expr(1));
		OpCode opCode;
		switch (ctx.multOp().getStart().getType()) {
		case SimplePascalLexer.STAR:
			opCode = OpCode.mult;
			break;
		case SimplePascalLexer.SLASH:
			opCode = OpCode.div;
			break;
		default:
			assert false;
			opCode = null;
		}
		emit(opCode, reg(ctx.expr(0)), reg(ctx.expr(1)), reg(ctx));
		return result;
	}

	@Override
	public Op visitNumExpr(NumExprContext ctx) {
		return emit(OpCode.loadI,
				new Num(Integer.parseInt(ctx.NUM().getText())), reg(ctx));
	}

	@Override
	public Op visitOutStat(OutStatContext ctx) {
		Op result = visit(ctx.expr());
		String text = ctx.STR().getText();
		Str outStr = new Str(text.substring(1, text.length() - 1));
		emit(OpCode.out, outStr, reg(ctx.expr()));
		return result;
	}

	@Override
	public Op visitParExpr(ParExprContext ctx) {
		Op result = visit(ctx.expr());
		emit(OpCode.i2i, reg(ctx.expr()), reg(ctx));
		return result;
	}

	@Override
	public Op visitPlusExpr(PlusExprContext ctx) {
		Op result = visit(ctx.expr(0));
		visit(ctx.expr(1));
		OpCode opCode;
		switch (ctx.plusOp().getStart().getType()) {
		case SimplePascalLexer.PLUS:
			opCode = OpCode.add;
			break;
		case SimplePascalLexer.MINUS:
			opCode = OpCode.sub;
			break;
		default:
			assert false;
			opCode = null;
		}
		emit(opCode, reg(ctx.expr(0)), reg(ctx.expr(1)), reg(ctx));
		return result;
	}

	@Override
	public Op visitPrfExpr(PrfExprContext ctx) {
		Op result = visit(ctx.expr());
		Num num;
		switch (ctx.prfOp().getStart().getType()) {
		case SimplePascalLexer.MINUS:
			num = new Num(0);
			break;
		case SimplePascalLexer.NOT:
			num = new Num(-1);
			break;
		default:
			assert false;
			num = null;
		}
		emit(OpCode.rsubI, reg(ctx.expr()), num, reg(ctx));
		return result;
	}

	@Override
	public Op visitProgram(ProgramContext ctx) {
		return super.visit(ctx.body());
	}

	@Override
	public Op visitTrueExpr(TrueExprContext ctx) {
		emit(OpCode.loadI, TRUE_VALUE, reg(ctx));
		return null;
	}

	@Override
	public Op visitWhileStat(WhileStatContext ctx) {
		Op result = visit(ctx.expr());
		result.setLabel(label(ctx));
		Label endLab = createLabel(ctx, "endwh");
		Label bodyLab = label(ctx.stat());
		emit(OpCode.cbr, reg(ctx.expr()), bodyLab, endLab);
		Op bodyOp = visit(ctx.stat());
		bodyOp.setLabel(bodyLab);
		emit(OpCode.jumpI, label(ctx));
		emit(endLab, OpCode.nop);
		return result;
	}

	/** Constructs an operation from the parameters 
	 * and adds it to the program under construction. */
	private Op emit(Label label, OpCode opCode, Operand... args) {
		Op result = new Op(label, opCode, args);
		this.prog.addInstr(result);
		return result;
	}

	/** Constructs an operation from the parameters 
	 * and adds it to the program under construction. */
	private Op emit(OpCode opCode, Operand... args) {
		return emit((Label) null, opCode, args);
	}

	/** 
	 * Looks up the label for a given parse tree node,
	 * creating it if none has been created before.
	 * The label is actually constructed from the entry node
	 * in the flow graph, as stored in the checker result.
	 */
	private Label label(ParserRuleContext node) {
		Label result = this.labels.get(node);
		if (result == null) {
			ParserRuleContext entry = this.checkResult.getEntry(node);
			result = createLabel(entry, "n");
			this.labels.put(node, result);
		}
		return result;
	}

	/** Creates a label for a given parse tree node and prefix. */
	private Label createLabel(ParserRuleContext node, String prefix) {
		Token token = node.getStart();
		int line = token.getLine();
		int column = token.getCharPositionInLine();
		String result = prefix + "_" + line + "_" + column;
		return new Label(result);
	}

	/** Retrieves the offset of a variable node from the checker result,
	 * wrapped in a {@link Num} operand. */
	private Num offset(ParseTree node) {
		return new Num(this.checkResult.getOffset(node));
	}

	/** Returns a register for a given parse tree node,
	 * creating a fresh register if there is none for that node. */
	private Reg reg(ParseTree node) {
		Reg result = this.regs.get(node);
		if (result == null) {
			result = new Reg("r_" + this.regCount);
			this.regs.put(node, result);
			this.regCount++;
		}
		return result;
	}

	/** Assigns a register to a given parse tree node. */
	private void setReg(ParseTree node, Reg reg) {
		this.regs.put(node, reg);
	}
}
