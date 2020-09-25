package operatorTest;
import com.puppycrawl.tools.checkstyle.api.*;


public class operatorCheck  extends AbstractCheck{
	
	private static final String CATCH_MSG = "Checks for operator count";
	 
	 
    private int numOperators = 0;
    private int lineMessage = 0;
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.PLUS, TokenTypes.MINUS , TokenTypes.DIV, TokenTypes.MOD, TokenTypes.STAR };
	}

	@Override
	public void beginTree(DetailAST rootAST) {
		numOperators = 0;
	}

	@Override
	public void visitToken(DetailAST aAST) {	
		numOperators++;
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		final String count = "There are: " + numOperators +" operators dude";
		log(lineMessage, count);
	}

	public void reportStyleError(final int lineMessage, final String errorMessage) {
		log(lineMessage, errorMessage);
	}
	
	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.PLUS, TokenTypes.MINUS , TokenTypes.DIV, TokenTypes.MOD, TokenTypes.STAR };
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[] {TokenTypes.PLUS, TokenTypes.MINUS , TokenTypes.DIV, TokenTypes.MOD, TokenTypes.STAR };
	}
	
	

}
