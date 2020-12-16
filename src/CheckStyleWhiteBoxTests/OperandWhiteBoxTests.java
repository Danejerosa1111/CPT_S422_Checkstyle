package CheckStyleWhiteBoxTests;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CheckStyleChecks.HalsteadChecks;
public class OperandWhiteBoxTests {

	private final int[] tokenizers = new int[] { TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,TokenTypes.STRING_LITERAL};
	
	private final int[] defaultTokens = new int[] {TokenTypes.PLUS, TokenTypes.MINUS , TokenTypes.DIV, TokenTypes.MOD, TokenTypes.STAR, TokenTypes.EQUAL, TokenTypes.NOT_EQUAL, TokenTypes.GE, 
			TokenTypes.GT, TokenTypes.LT, TokenTypes.LE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LAND, TokenTypes.UNARY_MINUS, 
			TokenTypes.UNARY_PLUS, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.ASSIGN, 
			TokenTypes.DEC, TokenTypes.INC, TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_LONG, 
			TokenTypes.NUM_INT, TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_DOUBLE,
			TokenTypes.LITERAL_INT, TokenTypes.LITERAL_FLOAT, TokenTypes.STRING_LITERAL, TokenTypes.CHAR_LITERAL};
	
	@Mock
	private DetailAST mockAST = mock(DetailAST.class);
	HalsteadChecks checker = spy(new HalsteadChecks());
	private final int logLine = 0;
	
	
	@Test
	public void getDefaultTokenCheckeTest() {
		assertArrayEquals(defaultTokens,checker.getDefaultTokens());
	}
	
	@Test
	public void getAcceptedTokenCheckeTest() {
		assertArrayEquals(defaultTokens,checker.getAcceptableTokens());
	}
	
	@Test
	public void getRequiredTokenCheckeTest() {
		assertArrayEquals(defaultTokens,checker.getRequiredTokens());
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

		final String counter = "There are: " + 0 + " Operands";
		doNothing().when(checker).log(logLine, counter);
		checker.finishOperandCount();
		verify(checker).log(logLine, counter);
	}
	
	@Test
	public void checkReportingStyleError()
	{
		final String logMessage = "There are: " + 0 + " Operands";
		doNothing().when(checker).log(logLine, logMessage);
		checker.reportStyleError(logLine, logMessage);
		verify(checker).reportStyleError(logLine, logMessage);
		verify(checker).log(logLine, logMessage);
	}
	
	@Test
	public void noOperandsTest()
	{
		final int numOperands = 0;
		final String counter = "There are: " + numOperands + " Operands";
		doNothing().when(checker).log(logLine, counter);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		checker.finishOperandCount();


		assertTrue(checker.getOperandCount() == numOperands);
		verify(checker).log(logLine, counter);
		
	}
	
	
	@Test
	public void oneOperandsTest()
	{
		final int numOperands = 1;
		final String logMessage = "There are: " + numOperands + " Operands";
		doNothing().when(checker).log(logLine, logMessage);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(true);
		}
		
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		checker.finishOperandCount();


		assertTrue(checker.getOperandCount() == numOperands);
		verify(checker).log(logLine, logMessage);
		
	}
	
	@Test
	public void oneHundredOperandsTest()
	{
		final int numOperands = 100;
		final String logMessage = "There are: " + numOperands + " Operands";
		doNothing().when(checker).log(logLine, logMessage);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(true);
		}
		checker.beginTree(mockAST);
		for(int i = 0; i < numOperands; i++)
		{
			checker.visitToken(mockAST);
		}
		
		checker.finishOperandCount();
		//System.out.println(checker.getOperandCount());
		assertTrue(checker.getOperandCount() == numOperands);
		verify(checker).log(logLine, logMessage);
	}
	
	
	@Test
	public void oneUniqueOperandsTest()
	{
		final int numOperands = 1;
		final String logMessage = "There are: " + numOperands + " Operands";
		doNothing().when(checker).log(logLine, logMessage);
		for(int i = 1; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		when(mockAST.branchContains(TokenTypes.NUM_DOUBLE)).thenReturn(true);
		checker.beginTree(mockAST);
		for(int i = 0; i < numOperands; i++)
		{
			checker.visitToken(mockAST);
		}
		
		checker.finishUniqueOperandCount();
		//System.out.println(checker.getOperandCount());
		assertTrue(checker.getUniqueOperands() == numOperands);
		verify(checker).log(logLine, logMessage);
	}
	
	@Test
	public void oneHundredUniqueOperandsTest()
	{
		final int numOperands = 100;
		final String logMessage = "There are: " + 1 + " Operands";
		doNothing().when(checker).log(logLine, logMessage);
		for(int i = 1; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		when(mockAST.branchContains(TokenTypes.NUM_DOUBLE)).thenReturn(true);
		checker.beginTree(mockAST);
		for(int i = 0; i < tokenizers.length; i++)
		{
			checker.visitToken(mockAST);
		}
		
		checker.finishUniqueOperandCount();
		//System.out.println(checker.getOperandCount());
		assertTrue(checker.getUniqueOperands() == 1);
		verify(checker).log(logLine, logMessage);
	}
	
}
