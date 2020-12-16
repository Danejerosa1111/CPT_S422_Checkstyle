package CheckStyleChecks;


import java.util.HashSet;

import java.util.regex.Pattern;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import jdk.nashorn.internal.parser.TokenType;

public class HalsteadChecks extends AbstractCheck {
	
	private static final String CATCH_MSG = "Checks for operator count";
	 
	 
    private int numOperators;
    private int numOperands;
    private int halsteadLength;
    public HashSet<Integer> uniqueOperators = new HashSet<Integer>();
    public HashSet<String> uniqueOperands = new HashSet<String>();
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.PLUS, TokenTypes.MINUS , TokenTypes.DIV, TokenTypes.MOD, TokenTypes.STAR, TokenTypes.EQUAL, TokenTypes.NOT_EQUAL, TokenTypes.GE, 
				TokenTypes.GT, TokenTypes.LT, TokenTypes.LE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LAND, TokenTypes.UNARY_MINUS, 
				TokenTypes.UNARY_PLUS, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.ASSIGN, 
				TokenTypes.DEC, TokenTypes.INC, TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_LONG, 
				TokenTypes.NUM_INT, TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_DOUBLE,
				TokenTypes.LITERAL_INT, TokenTypes.LITERAL_FLOAT, TokenTypes.STRING_LITERAL, TokenTypes.CHAR_LITERAL};
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
				|| aAST.branchContains(TokenTypes.LITERAL_THROW)
				||aAST.branchContains(TokenTypes.LITERAL_THROWS)|| aAST.branchContains(TokenTypes.ASSIGN))
		{
			numOperators++;
			updateUniqueOperators(aAST);
		}
		else if(aAST.branchContains(TokenTypes.NUM_INT) || aAST.branchContains(TokenTypes.NUM_FLOAT)
				||aAST.branchContains(TokenTypes.NUM_DOUBLE) || aAST.branchContains(TokenTypes.NUM_LONG)
				||aAST.branchContains(TokenTypes.STRING_LITERAL) || aAST.branchContains(TokenTypes.CHAR_LITERAL)
				||aAST.branchContains(TokenTypes.LITERAL_LONG)||aAST.branchContains(TokenTypes.LITERAL_SHORT)
				||aAST.branchContains(TokenTypes.LITERAL_FLOAT)||aAST.branchContains(TokenTypes.LITERAL_INT)
				||aAST.branchContains(TokenTypes.LITERAL_DOUBLE))
		{
			numOperands++;
			updateUniqueOperands(aAST);
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
	
	public int getUniqueOperands()
	{
		return uniqueOperands.size();
	}
	
	public int getUniqueOperators()
	{
		return uniqueOperators.size();
	}
	
	public void updateUniqueOperators(DetailAST aAST) {
		
		if(uniqueOperators.contains(aAST.getType()) == false) {
			uniqueOperators.add(aAST.getType());
		}
	}
	
	public void updateUniqueOperands(DetailAST aAST) {
		
		if(uniqueOperands.contains(aAST.getText()) == false)
		{
			uniqueOperands.add(aAST.getText());
		}
		
	}
	
	@Override
	public boolean isCommentNodesRequired() {
		return false;
	}

	@Override
	public void finishTree(DetailAST rootAST) {
		final int lineMessage = 0;
		halsteadLength = getOperatorCount() + getOperandCount();
		final String count = "The halstead length is: " + halsteadLength;
		reportStyleError(lineMessage, count);
	}
	
	public void finishUniqueOperandCount()
	{
		final int lineMessage = 0;
		final String counter = "There are: " + getUniqueOperands() + " Operands";
		log(lineMessage,counter);
	}
	
	public void finishUniqueOperatorCount()
	{
		final int lineMessage = 0;
		final String counter = "There are: " + getUniqueOperators() + " Operators";
		log(lineMessage,counter);
	}
	
	public void finishOperandCount() {
		final int lineMessage = 0;
		final String counter = "There are: " + getOperandCount() + " Operands";
		log(lineMessage,counter);
	}
	
	public void finishOperatorCount() {
		final int lineMessage = 0;
		final String counter = "There are: " + getOperatorCount() + " Operators";
		log(lineMessage,counter);
	}
	
	public void finishVocabCount() {
		final int lineMessage = 0;
		final int halsteadVocab = uniqueOperands.size() + uniqueOperators.size();
		final String counter = "the halstead vocab is: " + halsteadVocab;
		log(lineMessage,counter);
	}
	
	public void finishDifficultyCount() {
		final int lineMessage = 0;
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
	
	public void finishVolumeCount() {
		final int lineMessage = 0;
		final int programLength = numOperators + numOperands;
		final int programVocab = uniqueOperands.size() + uniqueOperators.size();
		final int halsteadVolume = programLength*log2(programVocab);
		final String counter = "the halstead volume is: " + halsteadVolume;
		log(lineMessage,counter);
	}
	
	public static int log2(int N) 
    { 
        int result = (int)(Math.log(N) / Math.log(2)); 
        return result; 
    }
	
	public void finishEffortCount() {
		final int lineMessage = 0;
		int halsteadEffort = 0;
		if(uniqueOperators.size() > 0)
		{
			final int programLength = numOperators + numOperands;
			final int programVocab = uniqueOperands.size() + uniqueOperators.size();
			final int halsteadVolume = programLength*log2(programVocab);
			final int halsteadDifficulty = ((uniqueOperators.size()/2)*numOperands)/uniqueOperators.size();
			halsteadEffort = halsteadVolume*halsteadDifficulty;
		}
		else
		{
			halsteadEffort = 0;
		}
		final String counter = "the halstead effort is: " + halsteadEffort;
		log(lineMessage,counter);
		
	}
	
	
	public int getHalsteadVolume()
	{

		final int programLength = numOperators + numOperands;
		final int programVocab = uniqueOperands.size() + uniqueOperators.size();
		int halsteadVolume = programLength*log2(programVocab);
		return halsteadVolume;
	}
	
	public int getHalsteadLength()
	{
		int length = getOperatorCount() + getOperandCount();
		return length;
	}
	
	public int getHalsteadVocab()
	{
		final int halsteadVocab = uniqueOperands.size() + uniqueOperators.size();
		return halsteadVocab;
	}
	
	public int getHalsteadDifficulty()
	{
		int difficulty = 0;
		
		if(uniqueOperators.size() == 0)
		{
			difficulty  = 0;
		}
		else
		{
			difficulty = ((uniqueOperators.size()/2)*numOperands)/uniqueOperators.size();
		}
		
		return difficulty;
	}
	
	public int getHalsteadEffort()
	{
		int halsteadEffort = 0;
		if(uniqueOperators.size() > 0)
		{
			final int programLength = numOperators + numOperands;
			final int programVocab = uniqueOperands.size() + uniqueOperators.size();
			final int halsteadVolume = programLength*log2(programVocab);
			final int halsteadDifficulty = ((uniqueOperators.size()/2)*numOperands)/uniqueOperators.size();
			halsteadEffort = halsteadVolume*halsteadDifficulty;
		}
		else
		{
			halsteadEffort = 0;
		}
		return halsteadEffort;
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
				TokenTypes.LITERAL_INT, TokenTypes.LITERAL_FLOAT, TokenTypes.STRING_LITERAL, TokenTypes.CHAR_LITERAL};
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[] {TokenTypes.PLUS, TokenTypes.MINUS , TokenTypes.DIV, TokenTypes.MOD, TokenTypes.STAR, TokenTypes.EQUAL, TokenTypes.NOT_EQUAL, TokenTypes.GE, 
				TokenTypes.GT, TokenTypes.LT, TokenTypes.LE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LAND, TokenTypes.UNARY_MINUS, 
				TokenTypes.UNARY_PLUS, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.ASSIGN, 
				TokenTypes.DEC, TokenTypes.INC, TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_LONG, 
				TokenTypes.NUM_INT, TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_DOUBLE,
				TokenTypes.LITERAL_INT, TokenTypes.LITERAL_FLOAT, TokenTypes.STRING_LITERAL, TokenTypes.CHAR_LITERAL};
	}

}
