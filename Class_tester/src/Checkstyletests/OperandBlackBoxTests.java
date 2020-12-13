package Checkstyletests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;

import BlackBoxTestRunner.HalsteadAutoRunner;
import CheckStyleChecks.HalsteadChecks;

public class OperandBlackBoxTests {
	
	private HalsteadAutoRunner runner;
	HalsteadChecks checker = new HalsteadChecks();

	@Test
	public void noOperandBlackBoxTest() throws CheckstyleException
	{
		final int numOperands = 0;
		final String logMessage = "There are: " + numOperands + " Operands";
		String pathname = "src/BlackBoxTestFiles/UniversalEmptyFile.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		checker.finishOperandCount();
		assertTrue(checker.getOperandCount() == numOperands);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("No Operand Check Done!");
		
	}
	
	
	@Test
	public void oneOperandBlackBoxTest() throws CheckstyleException
	{
		final int numOperands = 1;
		final String logMessage = "There are: " + numOperands + " Operands";
		String pathname = "src/BlackBoxTestFiles/OneOperand.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		checker.finishOperandCount();
		assertTrue(checker.getOperandCount() == numOperands);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("One Operand Check Done!");
		
	}
	
	@Test
	public void multipleOperandsBlackBoxTest() throws CheckstyleException
	{
		final int numOperands = 8;
		final String logMessage = "There are: " + numOperands + " Operands";
		String pathname = "src/BlackBoxTestFiles/MultipleOperand.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		checker.finishOperandCount();
		assertTrue(checker.getOperandCount() == numOperands);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Multiple Operands Check Done!");
		
	}
	
	@Test
	public void uniqueOperandsBlackBoxTest() throws CheckstyleException
	{
		final int numOperands = 4;
		final String logMessage = "There are: " + numOperands + " Operands";
		String pathname = "src/BlackBoxTestFiles/MultipleOperand.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		

		/*Iterator<String> itr = checker.uniqueOperands.iterator();
		while(itr.hasNext()){
		    System.out.println( itr.next());
		}*/
		
		checker.finishUniqueOperandCount();
		assertTrue(checker.getUniqueOperands() == numOperands);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Unique Operands Check Done!");
		
	}



}
