package CheckStyleBlackBoxTests;

import static org.junit.Assert.*;


import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;

import BlackBoxTestRunner.TestRunner;
import CheckStyleChecks.ExpressionChecks;

public class ExpressionBlackBoxTests {

	private TestRunner runner;
	ExpressionChecks checker = new ExpressionChecks();
	
	@Test
	public void noExpressionTest() throws CheckstyleException
	{
		final int numExpressions = 0;
		final String logMessage = "There are: " + numExpressions +" expressions in this program";
		
		String pathname = "src/BlackBoxTestFiles/UniversalEmptyFile.java";
		
		runner = new TestRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		//System.out.println(checker.getNumLines());
			
		assertTrue(numExpressions == checker.getNumExpressions());
		for(LocalizedMessage lm : checker.getMessages()) {
					//System.out.println(lm.getMessage());
					assertTrue(lm.getMessage().equals(logMessage));
				}
		
		System.out.println("No Expression Check Done!");
	}
	
	@Test
	public void oneExpressionTest() throws CheckstyleException
	{
		final int numExpressions = 1;
		final String logMessage = "There are: " + numExpressions +" expressions in this program";
		
		String pathname = "src/BlackBoxTestFiles/OneExpression.java";
		
		runner = new TestRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
			
		assertTrue(numExpressions == checker.getNumExpressions());
		for(LocalizedMessage lm : checker.getMessages()) {
					//System.out.println(lm.getMessage());
					assertTrue(lm.getMessage().equals(logMessage));
				}
		
		System.out.println("One Expression Check Done!");
	}
	
	
	@Test
	public void multipleExpressionTest() throws CheckstyleException
	{
		final int numExpressions = 3;
		final String logMessage = "There are: " + numExpressions +" expressions in this program";
		
		String pathname = "src/BlackBoxTestFiles/MultipleExpressions.java";
		
		runner = new TestRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
			
		assertTrue(numExpressions == checker.getNumExpressions());
		for(LocalizedMessage lm : checker.getMessages()) {
					//System.out.println(lm.getMessage());
					assertTrue(lm.getMessage().equals(logMessage));
				}
		
		System.out.println("Multiple Expressions Check Done!");
	}

}
