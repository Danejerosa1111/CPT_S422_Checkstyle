package CheckStyleWhiteBoxTests;

import static org.junit.Assert.*;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CheckStyleChecks.HalsteadChecks;

public class HalsteadWhiteBoxTest {

	private final int[] tokenizers = new int[] {TokenTypes.PLUS, TokenTypes.MINUS , TokenTypes.DIV, TokenTypes.MOD, TokenTypes.STAR, TokenTypes.EQUAL, TokenTypes.NOT_EQUAL, TokenTypes.GE, 
			TokenTypes.GT, TokenTypes.LT, TokenTypes.LE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LAND, TokenTypes.UNARY_MINUS, 
			TokenTypes.UNARY_PLUS, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.ASSIGN, 
			TokenTypes.DEC, TokenTypes.INC, TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_LONG, 
			TokenTypes.NUM_INT, TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_DOUBLE,
			TokenTypes.LITERAL_INT, TokenTypes.LITERAL_FLOAT, TokenTypes.STRING_LITERAL, TokenTypes.CHAR_LITERAL};
	
	
	private final int[] operandTokenizers = new int[] { TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,TokenTypes.STRING_LITERAL};
	
	private final int[] operatorTokenizers = new int[] { TokenTypes.PLUS, TokenTypes.MINUS , TokenTypes.DIV, TokenTypes.MOD, TokenTypes.STAR, TokenTypes.EQUAL, TokenTypes.NOT_EQUAL, TokenTypes.GE, 
			TokenTypes.GT, TokenTypes.LT, TokenTypes.LE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LAND, TokenTypes.UNARY_MINUS, 
			TokenTypes.UNARY_PLUS, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.ASSIGN, 
			TokenTypes.DEC, TokenTypes.INC, TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN,
			TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_DOUBLE,
			TokenTypes.LITERAL_INT, TokenTypes.LITERAL_FLOAT};
	
	
	@Mock
	private DetailAST mockAST = mock(DetailAST.class);
	HalsteadChecks checker = spy(new HalsteadChecks());
	private final int logLine = 0;
	
	
	@Test
	public void getDefaultTokenCheckeTest() {
		assertArrayEquals(tokenizers,checker.getDefaultTokens());
	}
	
	@Test
	public void getAcceptedTokenCheckeTest() {
		assertArrayEquals(tokenizers,checker.getAcceptableTokens());
	}
	
	@Test
	public void getRequiredTokenCheckeTest() {
		assertArrayEquals(tokenizers,checker.getRequiredTokens());
	}
	
	@Test
	public void checkCommentRequiredTest() {
		assertFalse(checker.isCommentNodesRequired());
	}
	
	@Test
	public void testBeginTree()
	{
		checker.beginTree(mockAST);
		verify(checker).beginTree(mockAST);
		
	}
	
	@Test
	public void testVisitToken()
	{
		checker.visitToken(mockAST);
		verify(checker).visitToken(mockAST);
	}
	
	@Test
	public void testFinishTree()
	{
		final String count = "The halstead length is: " + 0;
		doNothing().when(checker).log(logLine, count);
		checker.finishTree(mockAST);
		verify(checker).finishTree(mockAST);
	}
	
	@Test
	public void checkReportingStyleError()
	{
		final String logMessage = "The halstead length is: " + 0;
		doNothing().when(checker).log(logLine, logMessage);
		checker.reportStyleError(logLine, logMessage);
		verify(checker).reportStyleError(logLine, logMessage);
		verify(checker).log(logLine, logMessage);
	}
	
	@Test
	public void noOperatorOrOperandTest()
	{
		final int numOperators = 0;
		final int numOperands = 0;
		
		final String logMessage = "The halstead length is: " + (numOperators + numOperands);
		doNothing().when(checker).log(logLine, logMessage);
		
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		checker.finishTree(mockAST);
		assertTrue(checker.getHalsteadLength() == (numOperators + numOperands));
		verify(checker).log(logLine, logMessage);
		
	}
	
	
	@Test
	public void oneOperatorNoOperandTest()
	{
		final int numOperators = 1;
		final int numOperands = 0;
		
		final String logMessage = "The halstead length is: " + (numOperators + numOperands);
		doNothing().when(checker).log(logLine, logMessage);
		
		checker.beginTree(mockAST);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operatorTokenizers.length; i++)
		{
			when(mockAST.branchContains(operatorTokenizers[i])).thenReturn(true);
		}
		
		
		checker.visitToken(mockAST);
		checker.finishTree(mockAST);
		assertTrue(checker.getHalsteadLength() == (numOperators + numOperands));
		verify(checker).log(logLine, logMessage);
	}
	
	
	@Test
	public void oneOperandNoOperatorTest()
	{
		final int numOperators = 0;
		final int numOperands = 1;
		
		final String count = "The halstead length is: " + (numOperators + numOperands);
		doNothing().when(checker).log(logLine, count);
		checker.beginTree(mockAST);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operandTokenizers.length; i++)
		{
			when(mockAST.branchContains(operandTokenizers[i])).thenReturn(true);
		}
		
		
		checker.visitToken(mockAST);
		checker.finishTree(mockAST);
		assertTrue(checker.getHalsteadLength() == (numOperators + numOperands));
		verify(checker).log(logLine, count);
	}
	
	@Test
	public void oneOperandOneOperatorTest()
	{
		final int numOperators = 1;
		final int numOperands = 1;
		
		final String logMessage = "The halstead length is: " + (numOperators + numOperands);
		doNothing().when(checker).log(logLine, logMessage);
		
		
		checker.beginTree(mockAST);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operandTokenizers.length; i++)
		{
			when(mockAST.branchContains(operandTokenizers[i])).thenReturn(true);
		}
		checker.visitToken(mockAST);
		
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operatorTokenizers.length; i++)
		{
			when(mockAST.branchContains(operatorTokenizers[i])).thenReturn(true);
		}
		checker.visitToken(mockAST);
		

		checker.finishTree(mockAST);
		assertTrue(checker.getHalsteadLength() == (numOperators + numOperands));
		verify(checker).log(logLine, logMessage);
	}
	
	
	@Test
	public void multipleOperandOneOperatorTest()
	{
		final int numOperators = 1;
		final int numOperands = 33;
		
		final String logMessage = "The halstead length is: " + (numOperators + numOperands);
		doNothing().when(checker).log(logLine, logMessage);
		
		
		checker.beginTree(mockAST);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operandTokenizers.length; i++)
		{
			when(mockAST.branchContains(operandTokenizers[i])).thenReturn(true);
		}
		
		for(int i = 0; i < numOperands; i++)
		{
			checker.visitToken(mockAST);
		}
		
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operatorTokenizers.length; i++)
		{
			when(mockAST.branchContains(operatorTokenizers[i])).thenReturn(true);
		}

		checker.visitToken(mockAST);
		

		
		//System.out.println(checker.getOperandCount());
		//System.out.println(checker.getOperatorCount());
		checker.finishTree(mockAST);
		assertTrue(checker.getHalsteadLength() == (numOperators + numOperands));
		verify(checker).log(logLine, logMessage);
	}
	
	@Test
	public void oneOperandMultipleOperatorTest()
	{
		final int numOperators = 72;
		final int numOperands = 1;
		
		final String logMessage = "The halstead length is: " + (numOperators + numOperands);
		doNothing().when(checker).log(logLine, logMessage);
		
		
		checker.beginTree(mockAST);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operandTokenizers.length; i++)
		{
			when(mockAST.branchContains(operandTokenizers[i])).thenReturn(true);
		}
		checker.visitToken(mockAST);
		
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operatorTokenizers.length; i++)
		{
			when(mockAST.branchContains(operatorTokenizers[i])).thenReturn(true);
		}
		
		for(int i = 0; i < numOperators; i++)
		{
			checker.visitToken(mockAST);
		}

		
		//System.out.println(checker.getOperandCount());
		//System.out.println(checker.getOperatorCount());
		checker.finishTree(mockAST);
		assertTrue(checker.getHalsteadLength() == (numOperators + numOperands));
		verify(checker).log(logLine, logMessage);
	}
	
	@Test
	public void multipleOperandMultipleOperatorTest()
	{
		final int numOperators = 38;
		final int numOperands = 25;
		
		final String logMessage = "The halstead length is: " + (numOperators + numOperands);
		doNothing().when(checker).log(logLine, logMessage);
		
		
		checker.beginTree(mockAST);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operandTokenizers.length; i++)
		{
			when(mockAST.branchContains(operandTokenizers[i])).thenReturn(true);
		}
		
		for(int i = 0; i < numOperands; i++)
		{
			checker.visitToken(mockAST);
		}
		
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operatorTokenizers.length; i++)
		{
			when(mockAST.branchContains(operatorTokenizers[i])).thenReturn(true);
		}
		
		for(int i = 0; i < numOperators; i++)
		{
			checker.visitToken(mockAST);
		}

		//System.out.println(checker.getOperandCount());
		//System.out.println(checker.getOperatorCount());
		checker.finishTree(mockAST);
		assertTrue(checker.getHalsteadLength() == (numOperators + numOperands));
		verify(checker).log(logLine, logMessage);
	}
	
	
	
	@Test
	public void halsteadVocabOneOperandOneOperator()
	{
		final int numOperators = 1;
		final int numOperands = 1;
		
		final String logMessage = "the halstead vocab is: " + 2;
		doNothing().when(checker).log(logLine, logMessage);
		
		
		checker.beginTree(mockAST);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operandTokenizers.length; i++)
		{
			when(mockAST.branchContains(operandTokenizers[i])).thenReturn(true);
		}
		checker.visitToken(mockAST);
		
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operatorTokenizers.length; i++)
		{
			when(mockAST.branchContains(operatorTokenizers[i])).thenReturn(true);
		}
		checker.visitToken(mockAST);
		
		
		checker.finishVocabCount();
		assertTrue(checker.getHalsteadVocab() == 2);
		verify(checker).log(logLine, logMessage);
	}
	
	
	@Test
	public void halsteadVocabMultipleOperandOneOperator()
	{
		final int numOperators = 3;
		final int numOperands = 1;
		
		final String logMessage = "the halstead vocab is: " + (numOperators + numOperands);
		doNothing().when(checker).log(logLine, logMessage);
		
		
		checker.beginTree(mockAST);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operandTokenizers.length; i++)
		{
			when(mockAST.branchContains(operandTokenizers[i])).thenReturn(true);
		}
		
		when(mockAST.getType()).thenReturn(TokenTypes.PLUS);
		checker.updateUniqueOperators(mockAST);

		when(mockAST.getType()).thenReturn(TokenTypes.MINUS);
		checker.updateUniqueOperators(mockAST);

		when(mockAST.getType()).thenReturn(TokenTypes.DIV);
		checker.updateUniqueOperators(mockAST);

	
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operatorTokenizers.length; i++)
		{
			when(mockAST.branchContains(operatorTokenizers[i])).thenReturn(true);
		}
		when(mockAST.getType()).thenReturn(TokenTypes.STRING_LITERAL);
		checker.visitToken(mockAST);
		
		checker.finishVocabCount();
		assertTrue(checker.getHalsteadVocab() == 4);
		verify(checker).log(logLine, logMessage);
	}
	
	
	//--------------------------------------------Halstead Vocab-------------------------------------------------------
	
	@Test
	public void halsteadVocabOneOperandMultipleOperator()
	{
		final int numOperators = 1;
		final int numOperands = 4;
		
		final String counter = "the halstead vocab is: " + (numOperators + numOperands);
		doNothing().when(checker).log(logLine, counter);
		
		
		checker.beginTree(mockAST);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operandTokenizers.length; i++)
		{
			when(mockAST.branchContains(operandTokenizers[i])).thenReturn(true);
		}
		
		when(mockAST.getType()).thenReturn(TokenTypes.PLUS);
		checker.updateUniqueOperators(mockAST);
	
		//System.out.println(checker.getUniqueOperators());
	
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operatorTokenizers.length; i++)
		{
			when(mockAST.branchContains(operatorTokenizers[i])).thenReturn(true);
		}
		
		for(int i = 0; i < 4; i++)
		{
			when(mockAST.getText()).thenReturn(String.valueOf(operandTokenizers[i]));
			checker.updateUniqueOperands(mockAST);
		}
		
		
		
		checker.finishVocabCount();
		assertTrue(checker.getHalsteadVocab() == 5);
		verify(checker).log(logLine, counter);
	}
	
	
	@Test
	public void halsteadVocabMultipleOperandMultipleOperator()
	{
		final int numOperators = 9;
		final int numOperands = 3;
		
		final String logMessage = "the halstead vocab is: " + (numOperators + numOperands);
		doNothing().when(checker).log(logLine, logMessage);
		
		
		checker.beginTree(mockAST);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operandTokenizers.length; i++)
		{
			when(mockAST.branchContains(operandTokenizers[i])).thenReturn(true);
		}
		

		for(int i = 0; i < numOperators; i++)
		{
			when(mockAST.getType()).thenReturn(operatorTokenizers[i]);
			checker.updateUniqueOperators(mockAST);
		}
	
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operatorTokenizers.length; i++)
		{
			when(mockAST.branchContains(operatorTokenizers[i])).thenReturn(true);
		}
		
		for(int i = 0; i < numOperands; i++)
		{
			when(mockAST.getText()).thenReturn(String.valueOf(operandTokenizers[i]));
			checker.updateUniqueOperands(mockAST);
		}
		
		checker.finishVocabCount();
		verify(checker).log(logLine, logMessage);
	}
	
	//--------------------------------------------------Halstead Difficulty--------------------------------------------------------
	
	
	@Test
	public void zeroHalsteadDifficulty()
	{
		int numOperators = 3;
		int halsteadDiffuculty = 0;
		final String logMessage = "the halstead difficulty is: " + halsteadDiffuculty;
		doNothing().when(checker).log(logLine, logMessage);
		
		
		checker.beginTree(mockAST);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operandTokenizers.length; i++)
		{
			when(mockAST.branchContains(operandTokenizers[i])).thenReturn(true);
		}
		
		for(int i = 0; i < numOperators; i++)
		{
			checker.visitToken(mockAST);
		}
		
		checker.finishDifficultyCount();
		assertTrue(checker.getHalsteadDifficulty() == 0);
		verify(checker).log(logLine, logMessage);
		
	}
	
	@Test
	public void nonZeroHalsteadDifficulty()
	{
		int numOperands = 14;
		int numOperatorsSize = 3;
		int halsteadDiffuculty = (numOperatorsSize/2)*numOperands/numOperatorsSize;
		final String logMessage = "the halstead difficulty is: " + halsteadDiffuculty;
		doNothing().when(checker).log(logLine, logMessage);
		
		
		checker.beginTree(mockAST);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operandTokenizers.length; i++)
		{
			when(mockAST.branchContains(operandTokenizers[i])).thenReturn(true);
		}
		
		for(int i = 0; i < numOperands; i++)
		{
			checker.visitToken(mockAST);
		}
		
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		for(int i = 0; i < numOperatorsSize; i++)
		{
			when(mockAST.branchContains(operatorTokenizers[i])).thenReturn(true);
			when(mockAST.getType()).thenReturn(operatorTokenizers[i]);
			checker.updateUniqueOperators(mockAST);
		}
		
		checker.finishDifficultyCount();
		assertTrue(checker.getHalsteadDifficulty() == halsteadDiffuculty);
		verify(checker).log(logLine, logMessage);
		
	}
	
	
	//--------------------------------------------------Halstead Effort--------------------------------------------------------
	
	public static int log2(int N) 
    { 
        int result = (int)(Math.log(N) / Math.log(2)); 
        return result; 
    }
	
	
	
	@Test
	public void halsteadVolumeTest()
	{
		int numOperands = 14;
		int numOperators = 12;
		int numOperatorsSize = 3;
		int numOperandsSize = 2;
		int halsteadVolume = (numOperands + numOperators)*log2(numOperatorsSize + numOperandsSize);
		final String logMessage = "the halstead volume is: " + halsteadVolume;;
		doNothing().when(checker).log(logLine, logMessage);
		
		
		checker.beginTree(mockAST);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operandTokenizers.length; i++)
		{
			when(mockAST.branchContains(operandTokenizers[i])).thenReturn(true);
		}
		
		for(int i = 0; i < numOperandsSize; i++)
		{
			when(mockAST.branchContains(operandTokenizers[i])).thenReturn(true);
			when(mockAST.getType()).thenReturn(operandTokenizers[i]);
			checker.updateUniqueOperators(mockAST);
		}
		
		for(int i = 0; i < numOperands; i++)
		{
			checker.visitToken(mockAST);
		}
		
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		for(int i = 0; i < numOperatorsSize; i++)
		{
			when(mockAST.branchContains(operatorTokenizers[i])).thenReturn(true);
			when(mockAST.getType()).thenReturn(operatorTokenizers[i]);
			checker.updateUniqueOperators(mockAST);
		}
		
		for(int i = 0; i < numOperators; i++)
		{
			checker.visitToken(mockAST);
		}
		
		
		
		checker.finishVolumeCount();
		assertTrue(checker.getHalsteadVolume() == halsteadVolume);
		verify(checker).log(logLine, logMessage);
	}
	
	
	//--------------------------------------------------Halstead Effort--------------------------------------------------------
	
	@Test
	public void zerohalsteadEffortTest()
	{

		int halsteadEffort = 0;
		final String logMessage = "the halstead effort is: " + halsteadEffort;
		doNothing().when(checker).log(logLine, logMessage);
		
		
		checker.beginTree(mockAST);
		
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < 10; i++)
		{
			checker.visitToken(mockAST);
		}
		checker.finishEffortCount();
		assertTrue(checker.getHalsteadEffort() == halsteadEffort);
		verify(checker).log(logLine, logMessage);
		
	}
	
	@Test
	public void halsteadEffortTest()
	{
		int numOperands = 14;
		int numOperators = 12;
		int numOperatorsSize = 5;
		int numOperandsSize = 3;
		int vocab =  numOperatorsSize + numOperandsSize;
		int length = numOperands + numOperators;
		int volume = length*log2(vocab);
		int halsteadVolume = (numOperands + numOperators)*log2(numOperatorsSize + numOperandsSize);
		int difficulty = numOperatorsSize/2*numOperands/numOperatorsSize;
		int halsteadEffort = halsteadVolume*difficulty;
		final String logMessage = "the halstead effort is: " + halsteadEffort;
		doNothing().when(checker).log(logLine, logMessage);
		
		
		checker.beginTree(mockAST);
		
		
		for(int i = 0; i < numOperandsSize; i++)
		{
			when(mockAST.getText()).thenReturn(String.valueOf(operandTokenizers[i]));
			checker.updateUniqueOperands(mockAST);
		}
		
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		for(int i = 0; i < operandTokenizers.length; i++)
		{
			when(mockAST.branchContains(operandTokenizers[i])).thenReturn(true);
		}
		
		for(int i = 0; i < numOperands; i++)
		{
			checker.visitToken(mockAST);
		}
		
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		for(int i = 0; i < numOperatorsSize; i++)
		{
			when(mockAST.branchContains(operatorTokenizers[i])).thenReturn(true);
			when(mockAST.getType()).thenReturn(operatorTokenizers[i]);
			checker.updateUniqueOperators(mockAST);
		}
		
		for(int i = 0; i < numOperators; i++)
		{
			checker.visitToken(mockAST);
		}
		checker.finishEffortCount();
		assertTrue(checker.getHalsteadEffort() == halsteadEffort);
		verify(checker).log(logLine, logMessage);
		
	}

}
