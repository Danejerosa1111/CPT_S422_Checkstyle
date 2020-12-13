package CheckStyleChecks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LoopChecks extends AbstractCheck {

	 private static final String CATCH_MSG = "Checks for loop count";
	 
	 
	    private int numLoops = 0;
	    private int lineMessage = 0;
		
	    public int getNumLoops()
	    {
	    	return numLoops;
	    }
	    
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
			if(aAST.branchContains(TokenTypes.DO_WHILE) || aAST.branchContains(TokenTypes.LITERAL_FOR) || aAST.branchContains(TokenTypes.LITERAL_WHILE))
			numLoops++;
		}

		
		@Override
		public void finishTree(DetailAST rootAST) {
			final int lineMessage = 0;
			final String errorMessage = "There are: " + numLoops +" loops";
			reportStyleError(lineMessage, errorMessage);
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
