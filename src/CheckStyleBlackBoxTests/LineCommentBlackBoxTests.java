package CheckStyleBlackBoxTests;

import static org.junit.Assert.*;



import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;

import BlackBoxTestRunner.TestRunner;
import CheckStyleChecks.LinesOfCommentsChecks;

public class LineCommentBlackBoxTests {
	
	
	private TestRunner runner;
	LinesOfCommentsChecks checker = new LinesOfCommentsChecks();
	
	@Test
	public void multipleBlockCommentsTest() throws CheckstyleException {
		int commentLinesInBlock = 12;
		final String logMessage = "There are: " + commentLinesInBlock + " lines of comments in this program";
		String pathname = "src/BlackBoxTestFiles/MultipleBlockComments.java";
		
		runner = new TestRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		//System.out.println(checker.getNumLines());
		//System.out.println(commentLinesInBlock);
		
		
		assertTrue(commentLinesInBlock == checker.getNumLines());
		for(LocalizedMessage lm : checker.getMessages()) {
					//System.out.println(lm.getMessage());
					assertTrue(lm.getMessage().equals(logMessage));
				}
		
		System.out.println("Multiple Block Comments Check Done!");
	}
	
	@Test
	public void singleBlockCommentsTest() throws CheckstyleException {
		int commentLinesInBlock = 6;
		final String logMessage = "There are: " + commentLinesInBlock + " lines of comments in this program";
		String pathname = "src/BlackBoxTestFiles/SingleBlockComments.java";
		
		
		runner = new TestRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		//System.out.println(checker.getNumLines());
		//System.out.println(commentLinesInBlock);
		
		
		assertTrue(commentLinesInBlock == checker.getNumLines());
		
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Single Block Comment Check Done!");
	}
	
	@Test
	public void multipleSingleLinesCommentsTest() throws CheckstyleException {
		int commentLinesInBlock = 3;
		final String logMessage = "There are: " + commentLinesInBlock + " lines of comments in this program";
		String pathname = "src/BlackBoxTestFiles/MultipleLineComments.java";
		
		
		runner = new TestRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		//System.out.println(checker.getNumLines());
		//System.out.println(commentLinesInBlock);
		
		assertTrue(commentLinesInBlock == checker.getNumLines());
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Multiple Single Line Comment Check Done!");
	}
	
	@Test
	public void noCommentsTest() throws CheckstyleException {
		int commentLinesInBlock = 0;
		final String logMessage = "There are: " + commentLinesInBlock + " lines of comments in this program";
		String pathname = "src/BlackBoxTestFiles/UniversalEmptyFile.java";
		
		
		runner = new TestRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		//System.out.println(checker.getNumLines());
		//System.out.println(commentLinesInBlock);
		
		assertTrue(commentLinesInBlock == checker.getNumLines());
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("No Comments Check Done!");
	}
	
	@Test
	public void singleSingleLinesCommentsTest() throws CheckstyleException {
		//result check
		final int commentLinesInBlock = 1;
		final String logMessage = "There are: " + commentLinesInBlock + " lines of comments in this program";
		
		String pathname = "src/BlackBoxTestFiles/SingleLineComments.java";
		
		runner = new TestRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		//System.out.println(checker.getNumLines());
		//System.out.println(commentLinesInBlock);
		
		assertTrue(commentLinesInBlock == checker.getNumLines());
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Single Line Comment Check Done!");
	}
	
	@Test
	public void singleSingleLinesCommentst() throws CheckstyleException {
		//result check
		final int commentLinesInBlock = 16;
		final String logMessage = "There are: " + commentLinesInBlock + " lines of comments in this program";
		
		String pathname = "src/BlackBoxTestFiles/MultipleLineAndBlockComments.java";
		
		runner = new TestRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		//System.out.println(checker.getNumLines());
		//System.out.println(commentLinesInBlock);
		
		assertTrue(commentLinesInBlock == checker.getNumLines());
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Single Line Comment Check Done!");
	}
	
	
}
