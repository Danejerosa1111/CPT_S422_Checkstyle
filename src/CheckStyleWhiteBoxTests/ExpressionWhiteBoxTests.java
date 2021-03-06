package CheckStyleWhiteBoxTests;

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
	private int logLine = 0;
	
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
	public void checkBeginTree()
	{
		checker.beginTree(mockAST);
		assertTrue(checker.getNumExpressions() == 0);
		verify(checker).beginTree(mockAST);
		
	}
	
	@Test
	public void checkVisitToken()
	{
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		verify(checker).visitToken(mockAST);
		
	}
	
	@Test
	public void checkFinishTree()
	{
		final int numExpressions = 0;
		final String logMessage = "There are: " + numExpressions +" expressions in this program";
		doNothing().when(checker).log(logLine, logMessage);
		checker.finishTree(mockAST);
		verify(checker).finishTree(mockAST);
		verify(checker).log(logLine, logMessage);
	}
	
	@Test
	public void checkReportingStyleError()
	{
		final int numExpressions = 0;
		final String logMessage = "There are: " + numExpressions +" expressions in this program";
		doNothing().when(checker).log(logLine, logMessage);
		checker.reportStyleError(logLine, logMessage);
		verify(checker).reportStyleError(logLine, logMessage);
		verify(checker).log(logLine, logMessage);
	}
	
	
	@Test
	public void zeroExpressionTest() {
		//init test result
		final int numExpressions = 0;
		final String logMessage = "There are: " + numExpressions +" expressions in this program";

		when(mockAST.branchContains(TokenTypes.EXPR)).thenReturn(false);
		doNothing().when(checker).log(logLine, logMessage);

		//checker visits functions
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		checker.finishTree(mockAST);

		//verify those the mock has hit those functions with those arguments
		verify(checker).visitToken(mockAST);
		verify(checker).finishTree(mockAST);
		assertTrue(checker.getNumExpressions() == 0);
		verify(checker).log(logLine, logMessage);
	}
	
	@Test
	public void oneExpressionTest() {
		//init test result
		final int numExpressions = 1;
		final String logMessage = "There are: " + numExpressions +" expressions in this program";

		when(mockAST.branchContains(TokenTypes.EXPR)).thenReturn(true);
		doNothing().when(checker).log(logLine, logMessage);

		//checker visits functions
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		checker.finishTree(mockAST);

		//verify those the mock has hit those functions with those arguments
		verify(checker).visitToken(mockAST);
		verify(checker).finishTree(mockAST);
		assertTrue(checker.getNumExpressions() == 1);
		verify(checker).log(logLine, logMessage);
	}
	
	@Test
	public void oneHundredExpressionTest() {
		//init test result
		final int numExpressions = 100;
		final String logMessage = "There are: " + numExpressions +" expressions in this program";

		when(mockAST.branchContains(TokenTypes.EXPR)).thenReturn(true);
		doNothing().when(checker).log(logLine, logMessage);

		//checker visits functions
		checker.beginTree(mockAST);
		
		for(int i = 0; i < numExpressions; i++)
		{
			checker.visitToken(mockAST);
		}
		checker.finishTree(mockAST);

		//verify those the mock has hit those functions with those arguments
		verify(checker).finishTree(mockAST);
		assertTrue(checker.getNumExpressions() == 100);
		verify(checker).log(logLine, logMessage);
	}

}
