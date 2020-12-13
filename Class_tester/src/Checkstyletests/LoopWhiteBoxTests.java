package Checkstyletests;

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

import CheckStyleChecks.LoopChecks;

public class LoopWhiteBoxTests {

private final int[] tokenizers = new int[] { TokenTypes.DO_WHILE, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE};
	
	@Mock
	private DetailAST mockAST = mock(DetailAST.class);
	LoopChecks checker = spy(new LoopChecks());
	final int ErrorMessage = 0;
	
	
	@Test
	public void getDefaultTokenCheckTest() {
		assertArrayEquals(tokenizers,checker.getDefaultTokens());
	}
	
	@Test
	public void getAcceptedTokenCheckTest() {
		assertArrayEquals(tokenizers,checker.getAcceptableTokens());
	}
	
	@Test
	public void getRequiredTokenCheckTest() {
		assertArrayEquals(tokenizers,checker.getRequiredTokens());
	}
	
	@Test
	public void checkCommentRequiredTest() {
		assertFalse(checker.isCommentNodesRequired());
	}
	
	
	@Test
	public void zeroLoopsTest() {
		//init test result
		final int numLoops = 0;
		final String logMessage = "There are: " + numLoops +" loops";

		when(mockAST.branchContains(TokenTypes.DO_WHILE)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.LITERAL_FOR)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.LITERAL_WHILE)).thenReturn(false);
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
	public void oneLoopsTest() {
		//init test result
		final int numLoops = 1;
		final String logMessage = "There are: " + numLoops +" loops";

		when(mockAST.branchContains(TokenTypes.DO_WHILE)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.LITERAL_FOR)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.LITERAL_WHILE)).thenReturn(true);
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
	public void oneHundredLoopsTest() {
		//init test result
		final int numLoops = 100;
		final String logMessage = "There are: " + numLoops +" loops";

		when(mockAST.branchContains(TokenTypes.DO_WHILE)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.LITERAL_FOR)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.LITERAL_WHILE)).thenReturn(true);
		doNothing().when(checker).log(ErrorMessage, logMessage);

		//checker visits functions
		checker.beginTree(mockAST);
		
		for(int i = 0; i < numLoops; i++)
		{
			checker.visitToken(mockAST);
		}

		checker.finishTree(mockAST);

		//verify those the mock has hit those functions with those arguments
		verify(checker).beginTree(mockAST);
		verify(checker).finishTree(mockAST);
		verify(checker).log(ErrorMessage, logMessage);
	}
	
	@Test
	public void doLoopsTest() {
		//init test result
		final int numLoops = 1;
		final String logMessage = "There are: " + numLoops +" loops";

		when(mockAST.branchContains(TokenTypes.DO_WHILE)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.LITERAL_FOR)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.LITERAL_WHILE)).thenReturn(false);
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
	public void forLoopsTest() {
		//init test result
		final int numLoops = 1;
		final String logMessage = "There are: " + numLoops +" loops";

		when(mockAST.branchContains(TokenTypes.DO_WHILE)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.LITERAL_FOR)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.LITERAL_WHILE)).thenReturn(false);
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
	public void whileLoopsTest() {
		//init test result
		final int numLoops = 1;
		final String logMessage = "There are: " + numLoops +" loops";

		when(mockAST.branchContains(TokenTypes.DO_WHILE)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.LITERAL_FOR)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.LITERAL_WHILE)).thenReturn(true);
		doNothing().when(checker).log(ErrorMessage, logMessage);


		//checker visits functions
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		checker.finishTree(mockAST);

		//verify those the mock has hit those functions with those arguments
		verify(checker).finishTree(mockAST);
		verify(checker).log(ErrorMessage, logMessage);
		
	}
	
	
	@Test
	public void allMultipleLoopsTest()
	{
		final int numLoops = 50;
		final String logMessage = "There are: " + numLoops +" loops";
		doNothing().when(checker).log(ErrorMessage, logMessage);
		
		when(mockAST.branchContains(TokenTypes.DO_WHILE)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.LITERAL_FOR)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.LITERAL_WHILE)).thenReturn(true);

		//checker visits functions
		checker.beginTree(mockAST);
		for(int i = 0; i < numLoops; i++)
		{
			checker.visitToken(mockAST);
		}

		checker.finishTree(mockAST);
		verify(checker).finishTree(mockAST);
		verify(checker).log(ErrorMessage, logMessage);
	}
	
	
	@Test
	public void allSingleLoopsTest()
	{
		final int numLoops = 3;
		final String logMessage = "There are: " + numLoops +" loops";
		doNothing().when(checker).log(ErrorMessage, logMessage);
		
		
		//do while check
		when(mockAST.branchContains(TokenTypes.DO_WHILE)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.LITERAL_FOR)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.LITERAL_WHILE)).thenReturn(false);

		//checker visits functions
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		
		//while check
		when(mockAST.branchContains(TokenTypes.DO_WHILE)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.LITERAL_FOR)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.LITERAL_WHILE)).thenReturn(true);
		checker.visitToken(mockAST);
		
		
		//for loop check
		when(mockAST.branchContains(TokenTypes.DO_WHILE)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.LITERAL_FOR)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.LITERAL_WHILE)).thenReturn(false);
		checker.visitToken(mockAST);

		checker.finishTree(mockAST);
		verify(checker).finishTree(mockAST);
		verify(checker).log(ErrorMessage, logMessage);
	}
	
	

}
