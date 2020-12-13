package CheckStyleBlackBoxTests;

import static org.junit.Assert.*;



import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;

import BlackBoxTestRunner.TestRunner;
import CheckStyleChecks.LoopChecks;

public class LoopBlackBoxTests {
	
	private TestRunner runner;
	LoopChecks checker = new LoopChecks();
	
	@Test
	public void noLoopTest() throws CheckstyleException {
		final int numLoops = 0;
		final String logMessage = "There are: " + numLoops +" loops";
		
		String pathname = "src/BlackBoxTestFiles/UniversalEmptyFile.java";
		
		runner = new TestRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		//System.out.println(checker.getNumLoops());
		
		assertTrue(checker.getNumLoops() == numLoops);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("No Loop Check Done!");
	}
	
	@Test
	public void oneLoopTest() throws CheckstyleException {
		final int numLoops = 1;
		final String logMessage = "There are: " + numLoops +" loops";
		
		String pathname = "src/BlackBoxTestFiles/OneLoop.java";
		
		runner = new TestRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		//System.out.println(checker.getNumLoops());
		
		assertTrue(checker.getNumLoops() == numLoops);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Single Loop Check Done!");
	}
	
	@Test
	public void multipleLoopTest() throws CheckstyleException {
		final int numLoops = 3;
		final String logMessage = "There are: " + numLoops +" loops";
		
		String pathname = "src/BlackBoxTestFiles/MultipleLoops.java";
		
		runner = new TestRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		//System.out.println(checker.getNumLoops());
		
		assertTrue(checker.getNumLoops() == numLoops);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Multiple Loop Check Done!");
	}
	
	
}
