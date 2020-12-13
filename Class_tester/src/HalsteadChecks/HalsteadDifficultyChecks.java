package HalsteadChecks;

import java.util.HashSet;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadDifficultyChecks extends AbstractCheck {
	
	private static final String CATCH_MSG = "Checks for operator count";
	 
	 
    private int numOperators = 0;
    private int numOperands = 0;
    private int lineMessage = 0;
    private int halsteadLength = 0;
    private HashSet<Integer> uniqueOperators = new HashSet<Integer>();
    private HashSet<String> uniqueOperands = new HashSet<String>();
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.PLUS, TokenTypes.MINUS , TokenTypes.DIV, TokenTypes.MOD, TokenTypes.STAR, TokenTypes.EQUAL, TokenTypes.NOT_EQUAL, TokenTypes.GE, 
				TokenTypes.GT, TokenTypes.LT, TokenTypes.LE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LAND, TokenTypes.UNARY_MINUS, 
				TokenTypes.UNARY_PLUS, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.ASSIGN, 
				TokenTypes.DEC, TokenTypes.INC, TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_LONG, 
				TokenTypes.NUM_INT, TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_DOUBLE,
				TokenTypes.LITERAL_INT, TokenTypes.LITERAL_FLOAT, TokenTypes.STRING_LITERAL};
	}

	@Override
	public void beginTree(DetailAST rootAST) {
		numOperators = 0;
		numOperands = 0;
		halsteadLength = 0;
	}

	@Override
	public void visitToken(DetailAST aAST) {	
		if(aAST.branchContains(TokenTypes.PLUS) || aAST.branchContains(TokenTypes.MINUS) 
				|| aAST.branchContains(TokenTypes.DIV) || aAST.branchContains(TokenTypes.MOD) 
				|| aAST.branchContains(TokenTypes.STAR) || aAST.branchContains(TokenTypes.EQUAL) 
				|| aAST.branchContains(TokenTypes.NOT_EQUAL) || aAST.branchContains(TokenTypes.GE) 
				|| aAST.branchContains(TokenTypes.GT) || aAST.branchContains(TokenTypes.LT) 
				|| aAST.branchContains(TokenTypes.LE) || aAST.branchContains(TokenTypes.LNOT)
				|| aAST.branchContains(TokenTypes.LOR)|| aAST.branchContains(TokenTypes.LAND)
				|| aAST.branchContains(TokenTypes.UNARY_MINUS)||aAST.branchContains(TokenTypes.UNARY_PLUS)
				|| aAST.branchContains(TokenTypes.POST_DEC)|| aAST.branchContains(TokenTypes.INC)
				||aAST.branchContains(TokenTypes.PLUS_ASSIGN)|| aAST.branchContains(TokenTypes.MINUS_ASSIGN)
				||aAST.branchContains(TokenTypes.BSR)|| aAST.branchContains(TokenTypes.BSR_ASSIGN)
				||aAST.branchContains(TokenTypes.LITERAL_LONG)||aAST.branchContains(TokenTypes.LITERAL_SHORT)
				||aAST.branchContains(TokenTypes.LITERAL_FLOAT)||aAST.branchContains(TokenTypes.LITERAL_INT)
				||aAST.branchContains(TokenTypes.LITERAL_DOUBLE)|| aAST.branchContains(TokenTypes.LITERAL_THROW)
				||aAST.branchContains(TokenTypes.LITERAL_THROWS))
		{
			numOperators++;
			if(uniqueOperators.contains(aAST.getType()) == false) {
				uniqueOperators.add(aAST.getType());
			}
		}
		else if(aAST.branchContains(TokenTypes.NUM_INT) || aAST.branchContains(TokenTypes.NUM_FLOAT)
				||aAST.branchContains(TokenTypes.NUM_DOUBLE) || aAST.branchContains(TokenTypes.NUM_LONG)
				||aAST.branchContains(TokenTypes.STRING_LITERAL))
		{
			numOperands++;
			if(uniqueOperands.contains(aAST.getText()) == false)
			{
				uniqueOperands.add(aAST.getText());
			}
		}
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
	public boolean isCommentNodesRequired() {
		return false;
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		if(uniqueOperators.size() == 0)
		{
			final int halsteadDifficulty = 0;
			final String counter = "the halstead difficulty is: " + halsteadDifficulty;
			log(lineMessage,counter);
		}
		else
		{
			final int halsteadDifficulty = ((uniqueOperators.size()/2)*numOperands)/uniqueOperators.size();
			final String counter = "the halstead difficulty is: " + halsteadDifficulty;
			log(lineMessage,counter);
		}
	}
	


	public void reportStyleError(final int lineMessage, final String errorMessage) {
		log(lineMessage, errorMessage);
	}
	
	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.PLUS, TokenTypes.MINUS , TokenTypes.DIV, TokenTypes.MOD, TokenTypes.STAR, TokenTypes.EQUAL, TokenTypes.NOT_EQUAL, TokenTypes.GE, 
				TokenTypes.GT, TokenTypes.LT, TokenTypes.LE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LAND, TokenTypes.UNARY_MINUS, 
				TokenTypes.UNARY_PLUS, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.ASSIGN, 
				TokenTypes.DEC, TokenTypes.INC, TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_LONG, 
				TokenTypes.NUM_INT, TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_DOUBLE,
				TokenTypes.LITERAL_INT, TokenTypes.LITERAL_FLOAT,TokenTypes.STRING_LITERAL};
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[] {TokenTypes.PLUS, TokenTypes.MINUS , TokenTypes.DIV, TokenTypes.MOD, TokenTypes.STAR, TokenTypes.EQUAL, TokenTypes.NOT_EQUAL, TokenTypes.GE, 
				TokenTypes.GT, TokenTypes.LT, TokenTypes.LE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LAND, TokenTypes.UNARY_MINUS, 
				TokenTypes.UNARY_PLUS, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.ASSIGN, 
				TokenTypes.DEC, TokenTypes.INC, TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_LONG, 
				TokenTypes.NUM_INT, TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_DOUBLE,
				TokenTypes.LITERAL_INT, TokenTypes.LITERAL_FLOAT,TokenTypes.STRING_LITERAL};
	}

}
