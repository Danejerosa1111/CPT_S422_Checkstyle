package CheckStyleBlackBoxTests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import BlackBoxTestRunner.HalsteadAutoRunner;
import BlackBoxTestRunner.TestRunner;
import CheckStyleChecks.HalsteadChecks;

public class OperatorBlackBoxTests {
	
	private HalsteadAutoRunner runner;
	HalsteadChecks checker = new HalsteadChecks();

	@Test
	public void noOperatorBlackBoxTest() throws CheckstyleException
	{
		final int numOperator = 0;
		final String logMessage = "There are: " + numOperator + " Operators";
		String pathname = "src/BlackBoxTestFiles/UniversalEmptyFile.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		checker.finishOperatorCount();
		assertTrue(checker.getOperatorCount() == numOperator);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("No Operator Check Done!");
		
	}
	
	
	@Test
	public void oneOperatorBlackBoxTest() throws CheckstyleException
	{
		final int numOperator = 1;
		final String logMessage = "There are: " + numOperator + " Operators";
		String pathname = "src/BlackBoxTestFiles/OneOperator.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		/*Iterator<Integer> itr = checker.uniqueOperators.iterator();
		while(itr.hasNext()){
		    System.out.println( itr.next());
		}*/
		
		
		
		//System.out.println(checker.getOperatorCount());
		//checker.finishOperatorCount();
		assertTrue(checker.getOperatorCount() == numOperator);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("One Operator Check Done!");
	}
	
	@Test
	public void multipleOperatorBlackBoxTest() throws CheckstyleException
	{
		final int numOperator = 7;
		final String logMessage = "There are: " + numOperator + " Operators";
		String pathname = "src/BlackBoxTestFiles/MultipleOperators.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();

		checker.finishOperatorCount();
		assertTrue(checker.getOperatorCount() == numOperator);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Multiple Operators Check Done!");
		
		
		
	}
	
	@Test
	public void uniqueOperatorBlackBoxTest() throws CheckstyleException
	{
		final int numOperator = 3;
		final String logMessage = "There are: " + numOperator + " Operators";
		String pathname = "src/BlackBoxTestFiles/MultipleOperators.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		

		/*System.out.println(TokenTypes.LE);
		System.out.println(TokenTypes.PLUS);
		System.out.println(TokenTypes.LT);
		System.out.println(TokenTypes.ASSIGN);*/
		

		/*Iterator<Integer> itr = checker.uniqueOperators.iterator();
		while(itr.hasNext()){
		    System.out.println( itr.next());
		}*/
		
		checker.finishUniqueOperatorCount();
		assertTrue(checker.getUniqueOperators() == numOperator);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Unique Operators Check Done!");
		
	}
	
}
