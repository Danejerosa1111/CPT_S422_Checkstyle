package testerStuff;

import com.puppycrawl.tools.checkstyle.api.*;
import java.util.regex.Pattern;
 
public class testerCheck extends AbstractCheck {
 
    private static final String CATCH_MSG = "Checks for loop count";
 
 
    private int numLoops = 0;
    private int lineMessage = 0;
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.DO_WHILE, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE};
	}

	@Override
	public void beginTree(DetailAST rootAST) {
		numLoops = 0;
	}

	@Override
	public void visitToken(DetailAST aAST) {	
		numLoops++;
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		final String errorMessage = "There are: " + numLoops +" loops";
		log(lineMessage, errorMessage);
	}

	public void reportStyleError(final int lineMessage, final String errorMessage) {
		log(lineMessage, errorMessage);
	}
	
	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.DO_WHILE, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE};
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[] {TokenTypes.DO_WHILE, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE};
	}
}