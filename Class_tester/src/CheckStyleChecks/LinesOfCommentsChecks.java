package CheckStyleChecks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LinesOfCommentsChecks extends AbstractCheck{
	
	private static final String CATCH_MSG = "Checks for number of lines of comments";
	 
	 
    private int numComments = 0;
    private int numLines = 0;
    private int numLinesInBlock = 0;
    private int blockCommentBegin = 0;
    private int blockCommentEnd = 0;
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END };
	}

	@Override
	public void beginTree(DetailAST rootAST) {
		numComments = 0;
		numLines = 0;
		numLinesInBlock = 0;
		blockCommentBegin = 0;
		blockCommentEnd = 0;
	}
	
	@Override
	public boolean isCommentNodesRequired() {
		return true;
	}
	
	public int getNumLines()
	{
		return numLines;
	}


	@Override
	public void visitToken(DetailAST aAST) {
		
		if(aAST.branchContains(TokenTypes.SINGLE_LINE_COMMENT))
		{
			numLines++;
		}
		else if(aAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN))
		{
			blockCommentBegin = aAST.getLineNo();
		}
		else if(aAST.branchContains(TokenTypes.BLOCK_COMMENT_END))
		{
			blockCommentEnd = aAST.getLineNo();
			numLinesInBlock = blockCommentEnd - blockCommentBegin + 1;
			numLines += numLinesInBlock;
		}
		
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		final int lineMessage = 0;
		final String count = "There are: " + (numLines) +" lines of comments in this program";
		reportStyleError(lineMessage,count);
	}

	public void reportStyleError(final int lineMessage, final String errorMessage) {
		log(lineMessage, errorMessage);
	}
	
	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END };
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[] {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END };
	}

}
