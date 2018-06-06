package block5.cc.test;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import block5.cc.ErrorListener;
import block5.cc.ParseException;
import block5.cc.pascal.PascalLexer;
import block5.cc.pascal.PascalParser;

@SuppressWarnings("javadoc")
public class FullCheckerTest {
	private final static String BASE_DIR = "pp/block5/cc/sample";
	private final static String EXT = ".pascal";

	@Test
	public void testGCD() throws IOException, ParseException {
		check("fib");
	}

	@Test
	public void testCalcs() throws IOException, ParseException {
		check("../../../block6/cc/sample/calcs");
	}

	private void check(String filename) throws IOException, ParseException {
		File file = new File(filename + EXT);
		if (!file.exists()) {
			file = new File(BASE_DIR, filename + EXT);
		}
		parse(file);
	}

	/** Compiles a given Simple Pascal string into a parse tree. */
	public ParseTree parse(File file) throws ParseException, IOException {
		return parse(CharStreams.fromPath(file.toPath()));
	}

	private ParseTree parse(CharStream chars) throws ParseException {
		ErrorListener listener = new ErrorListener();
		Lexer lexer = new PascalLexer(chars);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);
		TokenStream tokens = new CommonTokenStream(lexer);
		PascalParser parser = new PascalParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		ParseTree result = parser.program();
		listener.throwException();
		return result;
	}
}
