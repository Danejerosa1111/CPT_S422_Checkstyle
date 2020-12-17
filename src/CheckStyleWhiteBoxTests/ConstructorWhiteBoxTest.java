package CheckStyleWhiteBoxTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;


import CheckStyleChecks.MissingCtorCheck;

public class ConstructorWhiteBoxTest {
	
	private DetailAST mockAST = mock(DetailAST.class);
	MissingCtorCheck checker = spy(new MissingCtorCheck());
	private int logLine = 0;
	public static final String MSG_KEY = "missing.ctor";
	
	private final int[] tokenizers = new int[] {TokenTypes.CLASS_DEF};
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
		assertFalse(checker.isCommentNodesRequired());
	}
	
	@Test
	public void checkBeginTree()
	{
		checker.beginTree(mockAST);
		verify(checker).beginTree(mockAST);
		
	}
	
	/*@Test
	public void checkBeginVisitToken()
	{
		DetailAST modifiers = mock(DetailAST.class);
		checker.beginTree(mockAST);
		when(mockAST.findFirstToken(TokenTypes.MODIFIERS)).thenReturn(modifiers);
		when(modifiers.findFirstToken(TokenTypes.ABSTRACT)).thenReturn(null);
		when(mockAST.findFirstToken(TokenTypes.OBJBLOCK).findFirstToken(TokenTypes.CTOR_DEF)).thenReturn(null);
		doNothing().when(checker).log(logLine, MSG_KEY);
		checker.visitToken(mockAST);
	}*/

}
