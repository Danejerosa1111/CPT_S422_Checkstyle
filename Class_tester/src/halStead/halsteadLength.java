package halStead;

import java.util.regex.Pattern;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class halsteadLength  extends AbstractCheck {
	
	private static final String CATCH_MSG = "Checks for operator count";
	 
	 
    private int numOperators = 0;
    private int numOperands = 0;
    private int lineMessage = 0;
    private int halsteadLength = 0;
	
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
		
		numOperands = numOperands + aAST.getNumberOfChildren();
	}
	
	public int getOperatorCount()
	{
		return numOperators;
	}
	
	public int getOperandCount()
	{
		return numOperands;
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		halsteadLength = getOperatorCount() + getOperandCount();
		final String count = "The halstead length is: " + halsteadLength;
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
