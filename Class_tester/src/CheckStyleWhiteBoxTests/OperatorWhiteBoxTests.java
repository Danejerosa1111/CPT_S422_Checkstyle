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

public class OperatorWhiteBoxTests {

	private final int[] defaultTokens = new int[] {TokenTypes.PLUS, TokenTypes.MINUS , TokenTypes.DIV, TokenTypes.MOD, TokenTypes.STAR, TokenTypes.EQUAL, TokenTypes.NOT_EQUAL, TokenTypes.GE, 
			TokenTypes.GT, TokenTypes.LT, TokenTypes.LE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LAND, TokenTypes.UNARY_MINUS, 
			TokenTypes.UNARY_PLUS, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.ASSIGN, 
			TokenTypes.DEC, TokenTypes.INC, TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_LONG, 
			TokenTypes.NUM_INT, TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_DOUBLE,
			TokenTypes.LITERAL_INT, TokenTypes.LITERAL_FLOAT, TokenTypes.STRING_LITERAL, TokenTypes.CHAR_LITERAL};
	
	
	private final int[] tokenizers = new int[] { TokenTypes.PLUS, TokenTypes.MINUS , TokenTypes.DIV, TokenTypes.MOD, TokenTypes.STAR, TokenTypes.EQUAL, TokenTypes.NOT_EQUAL, TokenTypes.GE, 
			TokenTypes.GT, TokenTypes.LT, TokenTypes.LE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LAND, TokenTypes.UNARY_MINUS, 
			TokenTypes.UNARY_PLUS, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.ASSIGN, 
			TokenTypes.DEC, TokenTypes.INC, TokenTypes.PLUS_ASSIGN, TokenTypes.MINUS_ASSIGN, TokenTypes.BSR, TokenTypes.BSR_ASSIGN,
			TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_SHORT, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS, TokenTypes.LITERAL_DOUBLE,
			TokenTypes.LITERAL_INT, TokenTypes.LITERAL_FLOAT};
	
	
	
	@Mock
	private DetailAST mockAST = mock(DetailAST.class);
	HalsteadChecks checker = spy(new HalsteadChecks());
	private final int ErrorMessage = 0;
	
	
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
	public void noOperatorTest()
	{
		final int numOperators = 0;
		final String counter = "There are: " + numOperators + " Operands";
		doNothing().when(checker).log(ErrorMessage, counter);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		checker.finishOperandCount();


		assertTrue(checker.getOperatorCount() == numOperators);
		verify(checker).log(ErrorMessage, counter);
		
	}
	
	
	@Test
	public void oneOperatorTest()
	{
		final int numOperator = 1;
		final String counter = "There are: " + numOperator + " Operators";
		doNothing().when(checker).log(ErrorMessage, counter);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(true);
		}
		
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		checker.finishOperatorCount();

		
		assertTrue(checker.getOperatorCount() == numOperator);
		verify(checker).log(ErrorMessage, counter);
		
	}
	
	@Test
	public void oneHundredOperatorTest()
	{
		final int numOperator = 100;
		final String counter = "There are: " + numOperator + " Operators";
		doNothing().when(checker).log(ErrorMessage, counter);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(true);
		}
		checker.beginTree(mockAST);
		for(int i = 0; i < numOperator; i++)
		{
			checker.visitToken(mockAST);
		}
		
		//System.out.println(checker.getOperatorCount());
		checker.finishOperatorCount();
		assertTrue(checker.getOperatorCount() == numOperator);
		verify(checker).log(ErrorMessage, counter);
	}
	
	@Test
	public void uniqueOperatorTest()
	{
		final int numOperator = 1;
		final String counter = "There are: " + numOperator + " Operators";
		doNothing().when(checker).log(ErrorMessage, counter);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(true);
		}
		checker.beginTree(mockAST);
		for(int i = 0; i < numOperator; i++)
		{
			checker.visitToken(mockAST);
		}
		
		//System.out.println(checker.getUniqueOperators());
		checker.finishUniqueOperatorCount();
		assertTrue(checker.getUniqueOperators() == numOperator);
		verify(checker).log(ErrorMessage, counter);
	}
	
	@Test
	public void uniqueOperatorInputTest()
	{
		final int numOperator = 100;
		final String counter = "There are: " + 1 + " Operators";
		doNothing().when(checker).log(ErrorMessage, counter);
		for(int i = 0; i < tokenizers.length; i++)
		{
			when(mockAST.branchContains(tokenizers[i])).thenReturn(false);
		}
		
		when(mockAST.branchContains(TokenTypes.MINUS)).thenReturn(true);
		checker.beginTree(mockAST);
		for(int i = 0; i < numOperator; i++)
		{
			checker.visitToken(mockAST);
		}
		
		checker.finishUniqueOperatorCount();
		assertTrue(checker.getUniqueOperators() == 1);
		verify(checker).log(ErrorMessage, counter);
	}
	
	
	
	
	
	
	

}
