package CheckStyleChecks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class ExpressionChecks extends AbstractCheck {
	
public int numExpressions = 0;
	
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.EXPR};
	}
	
	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.EXPR};
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[] {TokenTypes.EXPR};
	}

	@Override
	public void beginTree(DetailAST rootAST) {
		numExpressions = 0;
	}
	
	@Override
	public boolean isCommentNodesRequired() {
		return false;
	}


	@Override
	public void visitToken(DetailAST aAST) {
		if(aAST.branchContains(TokenTypes.EXPR))
		numExpressions++;
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		final int lineMessage = 0;
		final String count = "There are: " + numExpressions +" expressions in this program";
		reportStyleError(lineMessage,count);

	}

	public void reportStyleError(final int lineMessage, final String errorMessage) {
		log(lineMessage, errorMessage);
	}
	
	public int getNumExpressions()
	{
		return numExpressions;
	}

}
