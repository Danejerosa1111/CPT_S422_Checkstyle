package CheckStyleBlackBoxTests;


import static org.junit.Assert.*;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;

import BlackBoxTestRunner.TestRunner;
import CheckStyleChecks.CommentChecks;

public class CommentBlackBoxTests {
	
	private TestRunner runner;
	CommentChecks checker = new CommentChecks();
	
	@Test
	public void noCommentsTest() throws CheckstyleException
	{
		int totalComments = 0;
		final String logMessage = "There are " + totalComments + " comments";;
		String pathname = "src/BlackBoxTestFiles/UniversalEmptyFile.java";
		
		runner = new TestRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		assertTrue(totalComments== checker.getTotalComments());
		for(LocalizedMessage lm : checker.getMessages()) {
					//System.out.println(lm.getMessage());
					assertTrue(lm.getMessage().equals(logMessage));
				}
		
		System.out.println("No Total Comments Check Done!");
	}
	
	@Test
	public void oneCommentsTest() throws CheckstyleException
	{
		int totalComments = 1;
		final String logMessage = "There are " + totalComments + " comments";;
		String pathname = "src/BlackBoxTestFiles/SingleLineComments.java";
		
		runner = new TestRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		assertTrue(totalComments== checker.getTotalComments());
		for(LocalizedMessage lm : checker.getMessages()) {
					//System.out.println(lm.getMessage());
					assertTrue(lm.getMessage().equals(logMessage));
				}
		
		System.out.println("One Total Comments Check Done!");
	}
	
	@Test
	public void multipleCommentsTest() throws CheckstyleException
	{
		int totalComments = 5;
		final String logMessage = "There are " + totalComments + " comments";;
		String pathname = "src/BlackBoxTestFiles/MultipleLineAndBlockComments.java";
		
		runner = new TestRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		assertTrue(totalComments== checker.getTotalComments());
		for(LocalizedMessage lm : checker.getMessages()) {
					//System.out.println(lm.getMessage());
					assertTrue(lm.getMessage().equals(logMessage));
				}
		
		System.out.println("Multiple Block Comments Check Done!");
	}

	
}
