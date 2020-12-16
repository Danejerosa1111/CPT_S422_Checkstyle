package CheckStyleWhiteBoxTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mock;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CheckStyleChecks.LinesOfCommentsChecks;
import static org.junit.Assert.*;

import org.mockito.Mock;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


import org.junit.Test;
public class LineCommentWhiteBoxTests {

private final int[] tokenizers = new int[] {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END };
	
	@Mock
	private DetailAST mockAST = mock(DetailAST.class);
	LinesOfCommentsChecks checker = spy(new LinesOfCommentsChecks());
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
	public void checkCommentRequiredTest() {
		assertTrue(checker.isCommentNodesRequired());
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
	
		final int numLineComments = 0;
		final String logMessage = "There are: " + numLineComments +" lines of comments in this program";
		doNothing().when(checker).log(ErrorMessage, logMessage);
		checker.finishTree(mockAST);
		verify(checker).finishTree(mockAST);
	}
	
	@Test
	public void testZeroLineComments()
	{	
		//init test result
		final int numLineComments = 0;
		final String logMessage = "There are: " + numLineComments +" lines of comments in this program";

		when(mockAST.branchContains(TokenTypes.SINGLE_LINE_COMMENT )).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END )).thenReturn(false);
		doNothing().when(checker).log(ErrorMessage, logMessage);

		//checker visits functions
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		checker.finishTree(mockAST);

		//verify those the mock has hit those functions with those arguments
		verify(checker).visitToken(mockAST);
		verify(checker).finishTree(mockAST);
		assertTrue(checker.getNumLines() == 0);
		verify(checker).log(ErrorMessage, logMessage);
		
	}
	
	@Test
	public void testOneSingleLineComments()
	{	//init test result
		final int numLineComments = 1;
		final String logMessage = "There are: " + numLineComments +" lines of comments in this program";

		when(mockAST.branchContains(TokenTypes.SINGLE_LINE_COMMENT )).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END )).thenReturn(false);
		doNothing().when(checker).log(ErrorMessage, logMessage);

		//checker visits functions
		checker.beginTree(mockAST);
		checker.visitToken(mockAST);
		checker.finishTree(mockAST);

		//verify those the mock has hit those functions with those arguments
		verify(checker).beginTree(mockAST);
		verify(checker).visitToken(mockAST);
		verify(checker).finishTree(mockAST);
		assertTrue(checker.getNumLines() == 1);
		verify(checker).log(ErrorMessage, logMessage);
		
	}
	
	@Test
	public void testOneBlockComment()
	{	//init test result
		final int blockStart = 1;
		final int blockEnd = 1;
		final int totalLines = (blockEnd - blockStart) + 1;
		final String logMessage = "There are: " + totalLines + " lines of comments in this program";

		when(mockAST.branchContains(TokenTypes.SINGLE_LINE_COMMENT)).thenReturn(false);
		doNothing().when(checker).reportStyleError(ErrorMessage, logMessage);

		checker.beginTree(mockAST);
		
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(false);
		when(mockAST.getLineNo()).thenReturn(blockStart);
		checker.visitToken(mockAST);
		

		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(true);
		when(mockAST.getLineNo()).thenReturn(blockEnd);
		checker.visitToken(mockAST);
		
		checker.finishTree(mockAST);

		verify(checker).beginTree(mockAST);
		verify(checker).finishTree(mockAST);
		assertTrue(checker.getNumLines() == totalLines);
		verify(checker).reportStyleError(ErrorMessage, logMessage);
	}
	
	
	@Test
	public void testOneBlockAndSingleLineComment()
	{	//init test result
		final int blockStart = 1;
		final int blockEnd = 1;
		final int singleLineComment = 1;
		final int totalLines = (blockEnd - blockStart) + 1 + singleLineComment;
		final String logMessage = "There are: " + totalLines
				+ " lines of comments in this program";

		doNothing().when(checker).reportStyleError(ErrorMessage, logMessage);
		when(mockAST.branchContains(TokenTypes.SINGLE_LINE_COMMENT)).thenReturn(false);

		//start of the single block comment
		checker.beginTree(mockAST);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(false);
		when(mockAST.getLineNo()).thenReturn(blockStart);
		checker.visitToken(mockAST);
		
		//end of the single block comment
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(true);
		when(mockAST.getLineNo()).thenReturn(blockEnd);
		checker.visitToken(mockAST);
		
		
		when(mockAST.branchContains(TokenTypes.SINGLE_LINE_COMMENT)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(false);
		checker.visitToken(mockAST);
		
		
		checker.finishTree(mockAST);
		assertTrue(checker.getNumLines() == totalLines);
		verify(checker).reportStyleError(ErrorMessage, logMessage);
	}
	
	@Test
	public void testOneBlockAndMultipleLineComments()
	{	//init test result
		int blockStart = 1;
		int blockEnd = 1;
		int singleLineComment = 25;
		final int totalLines = (blockEnd - blockStart) + 1 + singleLineComment;
		final String logMessage = "There are: " + totalLines + " lines of comments in this program";

		doNothing().when(checker).reportStyleError(ErrorMessage, logMessage);
		when(mockAST.branchContains(TokenTypes.SINGLE_LINE_COMMENT)).thenReturn(false);

		//start of the single block comment
		checker.beginTree(mockAST);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(false);
		when(mockAST.getLineNo()).thenReturn(blockStart);
		checker.visitToken(mockAST);
		
		//end of the single block comment
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(true);
		when(mockAST.getLineNo()).thenReturn(blockEnd);
		checker.visitToken(mockAST);
		
		
		when(mockAST.branchContains(TokenTypes.SINGLE_LINE_COMMENT)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(false);
		for(int i = 0; i < 25; i++)
		{
			checker.visitToken(mockAST);
		}
		checker.finishTree(mockAST);
		verify(checker).beginTree(mockAST);
		assertTrue(checker.getNumLines() == totalLines);
		verify(checker).reportStyleError(ErrorMessage, logMessage);
	}
	
	@Test
	public void testMultipleBlockAndOneSingleLineComments()
	{	//init test result
		int blockStart = 1;
		int blockEnd = 5;
		final int singleLineComment = 1;
		int totalLines = (blockEnd - blockStart) + 1 +singleLineComment;
		
		when(mockAST.branchContains(TokenTypes.SINGLE_LINE_COMMENT)).thenReturn(false);

		//start of the single block comment
		checker.beginTree(mockAST);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(false);
		when(mockAST.getLineNo()).thenReturn(blockStart);
		checker.visitToken(mockAST);
		
		//end of the single block comment
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(true);
		when(mockAST.getLineNo()).thenReturn(blockEnd);
		checker.visitToken(mockAST);
		
		//--------------------------------------Next Find
		
		blockStart = 12;
		blockEnd = 19;
		totalLines = totalLines + (blockEnd - blockStart) + 1;
		//start of the single block comment
		
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(false);
		when(mockAST.getLineNo()).thenReturn(blockStart);
		checker.visitToken(mockAST);
				
		//end of the single block comment
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(true);
		when(mockAST.getLineNo()).thenReturn(blockEnd);
		checker.visitToken(mockAST);
		
		
		//--------------------------------------Single line comment
		
		when(mockAST.branchContains(TokenTypes.SINGLE_LINE_COMMENT)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(false);
		checker.visitToken(mockAST);
		
		//System.out.println(checker.numLines);
		//System.out.println(totalLines);
		final String logMessage = "There are: " + totalLines + " lines of comments in this program";
		
		
		doNothing().when(checker).reportStyleError(ErrorMessage, logMessage);
		checker.finishTree(mockAST);
		assertTrue(checker.getNumLines() == totalLines);
		verify(checker).beginTree(mockAST);
		verify(checker).reportStyleError(ErrorMessage, logMessage);
	}
	
	
	@Test
	public void testMultipleBlockAndMultipleSingleLineComments()
	{	//init test result
		int blockStart = 1;
		int blockEnd = 5;
		final int singleLineComment = 40;
		int totalLines = (blockEnd - blockStart) + 1 +singleLineComment;
		
		when(mockAST.branchContains(TokenTypes.SINGLE_LINE_COMMENT)).thenReturn(false);

		//start of the single block comment
		checker.beginTree(mockAST);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(false);
		when(mockAST.getLineNo()).thenReturn(blockStart);
		checker.visitToken(mockAST);
		
		//end of the single block comment
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(true);
		when(mockAST.getLineNo()).thenReturn(blockEnd);
		checker.visitToken(mockAST);
		
		
		assertTrue(checker.getNumLines() == (blockEnd - blockStart) + 1);
		//--------------------------------------Next Find
		
		blockStart = 25;
		blockEnd = 31;
		totalLines = totalLines + (blockEnd - blockStart) + 1;
		//start of the single block comment
		
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(false);
		when(mockAST.getLineNo()).thenReturn(blockStart);
		checker.visitToken(mockAST);
				
		//end of the single block comment
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(true);
		when(mockAST.getLineNo()).thenReturn(blockEnd);
		checker.visitToken(mockAST);
		
		
		assertTrue(checker.getNumLines() == (totalLines - singleLineComment));
		//--------------------------------------Single line comment
		
		when(mockAST.branchContains(TokenTypes.SINGLE_LINE_COMMENT)).thenReturn(true);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_BEGIN)).thenReturn(false);
		when(mockAST.branchContains(TokenTypes.BLOCK_COMMENT_END)).thenReturn(false);
		
		for(int i = 0; i < singleLineComment; i++)
		{
			checker.visitToken(mockAST);
		}
		
		//System.out.println(checker.numLines);
		//System.out.println(totalLines);
		final String logMessage = "There are: " + totalLines + " lines of comments in this program";
		
		
		doNothing().when(checker).reportStyleError(ErrorMessage, logMessage);
		checker.finishTree(mockAST);
		assertTrue(checker.getNumLines() == totalLines);
		verify(checker).beginTree(mockAST);
		verify(checker).reportStyleError(ErrorMessage, logMessage);
	}
	
	

}
