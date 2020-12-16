package HalsteadChecks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class OperandChecks extends AbstractCheck {
	
	private static final String CATCH_MSG = "Checks for operand count";
	 
	 
    private int numOperands = 0;
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,TokenTypes.STRING_LITERAL};
	}

	@Override
	public void beginTree(DetailAST rootAST) {
		numOperands = 0;
	}

	@Override
	public void visitToken(DetailAST aAST) {
		if(aAST.branchContains(TokenTypes.NUM_INT) || aAST.branchContains(TokenTypes.NUM_FLOAT)
				||aAST.branchContains(TokenTypes.NUM_DOUBLE) || aAST.branchContains(TokenTypes.NUM_LONG)
				||aAST.branchContains(TokenTypes.STRING_LITERAL))
		{
			numOperands++;
		}
	}
	
	@Override
	public boolean isCommentNodesRequired() {
		return false;
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		final int lineMessage = 0;
		final String count = "There are: " + numOperands +" operands in the program";
		log(lineMessage, count);
		
	}
	public void reportStyleError(final int lineMessage, final String errorMessage) {
		log(lineMessage, errorMessage);
	}
	
	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,TokenTypes.STRING_LITERAL};
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[] {TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,TokenTypes.STRING_LITERAL};
	}

}
