package CheckStyleWhiteBoxTests;

import static org.junit.Assert.*;



import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.*;

import org.mockito.Mock;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CheckStyleChecks.CommentChecks;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Test;
public class CommentWhiteBoxTests {

	private final int[] tokenizers = new int[] { TokenTypes.COMMENT_CONTENT};
	@Mock
	private DetailAST mockAST = mock(DetailAST.class);
	CommentChecks checker = spy(new CommentChecks());
	private int logLine = 0;
	
	
	
	//Functional tests
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
		assertTrue(checker.isCommentNodesRequired());
	}
	
	@Test
	public void checkBeginTree()
	{
		checker.beginTree(mockAST);
		assertTrue(checker.getTotalComments() == 0);
		verify(checker).beginTree(mockAST);
		
	}
	
	@Test
	public void checkVisitToken()
	{
		checker.beginTree(mockAST);
		when(mockAST.branchContains(TokenTypes.COMMENT_CONTENT)).thenReturn(true);
		checker.visitToken(mockAST);
		assertTrue(checker.getTotalComments() == 1);
		verify(checker).visitToken(mockAST);
	}
	
	@Test
	public void checkFinishTree()
	{
		final int numberOfComments = 0;
		final String expectedCheckstyleMessage = "There are " + numberOfComments + " comments";
		doNothing().when(checker).log(logLine, expectedCheckstyleMessage);
		checker.finishTree(mockAST);
		verify(checker).finishTree(mockAST);
		verify(checker).log(logLine, expectedCheckstyleMessage);
	}
	
	
	//Unit Tests
	@Test
	public void zeroCommentTest() {
		//init test result
		final int numberOfComments = 0;
		final String expectedCheckstyleMessage = "There are " + numberOfComments + " comments";

		when(mockAST.branchContains(TokenTypes.COMMENT_CONTENT)).thenReturn(false);
		doNothing().when(checker).log(logLine, expectedCheckstyleMessage);

		//checker visits functions
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		checker.finishTree(mockAST);

		//verify those the mock has hit those functions with those arguments
		verify(checker).visitToken(mockAST);
		verify(checker).finishTree(mockAST);
		assertTrue(checker.getTotalComments() == 0);
		verify(checker).log(logLine, expectedCheckstyleMessage);
	}
	
	@Test
	public void oneCommentTest() {
		//init test results
		final int numComments = 1;
		final String expectedCheckstyleMessage = "There are " + numComments + " comments";

		when(mockAST.branchContains(TokenTypes.COMMENT_CONTENT)).thenReturn(true);
		doNothing().when(checker).log(logLine, expectedCheckstyleMessage);
		
		//checker visits functionschecker.beginTree(mockAST);
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		checker.finishTree(mockAST);

		//verify those the mock has hit those functions with those arguments
		verify(checker).beginTree(mockAST);
		verify(checker).visitToken(mockAST);
		verify(checker).finishTree(mockAST);
		assertTrue(checker.getTotalComments() == 1);
		verify(checker).log(logLine, expectedCheckstyleMessage);
	}
	
	@Test
	public void oneHundredCommentTest() {
		//init test result
		final int numComments = 100;
		final String logMessage = "There are " + numComments + " comments";

		when(mockAST.branchContains(TokenTypes.COMMENT_CONTENT)).thenReturn(true);
		doNothing().when(checker).log(logLine, logMessage);
		
		checker.beginTree(mockAST);
		
		//checker visits functions
		for(int i = 0; i < numComments; i++)
		{
			checker.visitToken(mockAST);
		}
		checker.finishTree(mockAST);

		
		//verify those the mock has hit those functions with those arguments
		verify(checker).beginTree(mockAST);
		verify(checker).finishTree(mockAST);
		assertTrue(checker.getTotalComments() == 100);
		verify(checker).log(logLine, logMessage);
	}

}
