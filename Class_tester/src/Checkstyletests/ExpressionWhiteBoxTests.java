package Checkstyletests;

import static org.junit.Assert.*;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CheckStyleChecks.ExpressionChecks;

public class ExpressionWhiteBoxTests {

private final int[] tokenizers = new int[] { TokenTypes.EXPR};
	
	@Mock
	private DetailAST mockAST = mock(DetailAST.class);
	ExpressionChecks checker = spy(new ExpressionChecks());
	private int ErrorMessage = 0;
	
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
	public void checkCommentRequired() {
		assertFalse(checker.isCommentNodesRequired());
	}
	
	
	@Test
	public void zeroExpressionTest() {
		//init test result
		final int numExpressions = 0;
		final String logMessage = "There are: " + numExpressions +" expressions in this program";

		when(mockAST.branchContains(TokenTypes.EXPR)).thenReturn(false);
		doNothing().when(checker).log(ErrorMessage, logMessage);

		//checker visits functions
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		checker.finishTree(mockAST);

		//verify those the mock has hit those functions with those arguments
		verify(checker).visitToken(mockAST);
		verify(checker).finishTree(mockAST);
		verify(checker).log(ErrorMessage, logMessage);
	}
	
	@Test
	public void oneExpressionTest() {
		//init test result
		final int numExpressions = 1;
		final String logMessage = "There are: " + numExpressions +" expressions in this program";

		when(mockAST.branchContains(TokenTypes.EXPR)).thenReturn(true);
		doNothing().when(checker).log(ErrorMessage, logMessage);

		//checker visits functions
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		checker.finishTree(mockAST);

		//verify those the mock has hit those functions with those arguments
		verify(checker).visitToken(mockAST);
		verify(checker).finishTree(mockAST);
		verify(checker).log(ErrorMessage, logMessage);
	}
	
	@Test
	public void oneHundredExpressionTest() {
		//init test result
		final int numExpressions = 100;
		final String logMessage = "There are: " + numExpressions +" expressions in this program";

		when(mockAST.branchContains(TokenTypes.EXPR)).thenReturn(true);
		doNothing().when(checker).log(ErrorMessage, logMessage);

		//checker visits functions
		checker.beginTree(mockAST);
		
		for(int i = 0; i < numExpressions; i++)
		{
			checker.visitToken(mockAST);
		}
		checker.finishTree(mockAST);

		//verify those the mock has hit those functions with those arguments
		verify(checker).finishTree(mockAST);
		verify(checker).log(ErrorMessage, logMessage);
	}

}
