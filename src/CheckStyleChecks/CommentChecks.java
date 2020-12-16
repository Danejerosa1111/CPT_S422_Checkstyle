package CheckStyleChecks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CommentChecks extends AbstractCheck {
	
	public static final String MSG_KEY = "totalNumberOfComments";
	private int numComments = 0;

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.COMMENT_CONTENT };
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] { TokenTypes.COMMENT_CONTENT };
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[] { TokenTypes.COMMENT_CONTENT };
	}

	@Override
	public boolean isCommentNodesRequired() {
		return true;
	}

	@Override
	public void visitToken(DetailAST aAST) {
		if (aAST.branchContains(TokenTypes.COMMENT_CONTENT)) {
			numComments++;
		}
	}

	@Override
	public void beginTree(DetailAST rootAST) {
		numComments = 0;
	}
	

	@Override
	public void finishTree(DetailAST rootAST) {
		final int showMessageAtLine = 0;
		final String errorMessage = "There are " + numComments + " comments";
		reportStyleError(showMessageAtLine, errorMessage);
	}

	public void reportStyleError(final int showMessageAtLine, final String errorMessage) {
		log(showMessageAtLine, errorMessage);
	}
	
	public int getTotalComments()
	{
		return numComments;
	}
	
	

}
