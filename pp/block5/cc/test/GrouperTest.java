package block5.cc.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import block5.cc.ParseException;


@SuppressWarnings("javadoc")
public class GrouperTest {
	private block5.cc.antlr.NumWordProcessor flat = new block5.cc.antlr.NumWordProcessor();
	private block5.cc.antlr.NumWordGroupProcessor level = new block5.cc.antlr.NumWordGroupProcessor();

	@Test
	public void compareTest() {
		compare("1sock2shoes 3 holes");
		compare("4 strands 10 blocks 11 weeks 15 credits");
	}

	@Test
	public void failTest() {
		shouldFail("1 2 3");
	}

	private void compare(String text) {

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			System.setOut(new PrintStream(out));
		int flatTotal = 0;
		try {
			flatTotal = this.flat.group(text);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String flatResult = out.toString();
			out = new ByteArrayOutputStream();
			System.setOut(new PrintStream(out));
		int levelTotal = 0;
		try {
			levelTotal = this.level.group(text);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String levelResult = out.toString();
			assertThat(flatResult, equalTo(levelResult));
			assertThat(flatTotal, equalTo(levelTotal));

	}

	private void shouldFail(String text) {

		try {
			this.flat.group(text);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		fail("Should have been rejected by flat grouper but wasn't: "
					+ text);


		try {
			this.level.group(text);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		fail("Should have been rejected by level grouper but wasn't: "
					+ text);

	}
}
