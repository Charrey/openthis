package block5.cc.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import block5.cc.ErrorListener;
import block5.cc.ParseException;
import block5.cc.antlr.NumWordParser.NumberContext;
import block5.cc.antlr.NumWordParser.SentenceContext;
import block5.cc.antlr.NumWordParser.WordContext;


/** Prettyprints a (number, word)-sentence and sums up the numbers. */
public class NumWordProcessor extends NumWordBaseVisitor<Integer> {
	public static void main(String[] args) {
		NumWordProcessor grouper = new NumWordProcessor();
		if (args.length == 0) {
			process(grouper, "1sock2shoes 3 holes");
			process(grouper, "3 strands 10 blocks 11 weeks 15 credits");
			process(grouper, "1 2 3");
		} else {
			for (String text : args) {
				process(grouper, text);
			}
		}
	}

	private static void process(NumWordProcessor grouper, String text) {
		try {
			System.out.printf("Processing '%s':%n", text);
			int result = grouper.group(text);
			System.out.println("Total: " + result);
		} catch (ParseException exc) {
			exc.print();
		}
	}

	/** Groups a given sentence and prints it to stdout.
	 * Returns the sum of the numbers in the sentence.
	 */
	public int group(String text) throws ParseException {
		CharStream chars = CharStreams.fromString(text);
		ErrorListener listener = new ErrorListener();
		Lexer lexer = new NumWordLexer(chars);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);
		TokenStream tokens = new CommonTokenStream(lexer);
		NumWordParser parser = new NumWordParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		ParseTree tree = parser.sentence();
		listener.throwException();
		return visit(tree);
	}

	@Override
	public Integer visitSentence(SentenceContext ctx) {
		int result = 0;
		int count = ctx.number().size();
		for (int i = 0; i < count; i++) {
			result += visit(ctx.number(i));
			System.out.print(" ");
			visit(ctx.word(i));
			if (i < count - 2) {
				System.out.print(", ");
			} else if (i < count - 1) {
				System.out.print(" and ");
			} else {
				System.out.println();
			}
		}
		return result;
	}

	@Override
	public Integer visitNumber(NumberContext ctx) {
		System.out.print(ctx.getText());
		return Integer.parseInt(ctx.getText());
	}

	@Override
	public Integer visitWord(WordContext ctx) {
		System.out.print(ctx.getText());
		return 0;
	}
}
