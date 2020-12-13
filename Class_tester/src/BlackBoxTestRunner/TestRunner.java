package BlackBoxTestRunner;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.JavaParser.Options;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;



public class TestRunner {
	
	
	private DetailAST aAST;
	private AbstractCheck checker;

	public TestRunner(String filePath, AbstractCheck check) {
		this.checker = check;
		aAST = detailaAST(filePath);
	}

	public DetailAST detailaAST(String filePath) {
		File file = new File(filePath);
		DetailAST detailAst = null;
		detailAst = buildFileContents(file, detailAst);
		return detailAst;
	}

	private DetailAST buildFileContents(File file, DetailAST detailAst) {
		try {
			FileText text = new FileText(file, "UTF-8");
			FileContents contents = new FileContents(text);
			checker.setFileContents(contents);
			
			if (checker.isCommentNodesRequired()) {
				detailAst = JavaParser.parseFile(file, Options.WITH_COMMENTS);
			} else {
				detailAst = JavaParser.parse(contents);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detailAst;
	}
	
	public void getConfigurations() throws CheckstyleException
	{
		checker.configure(new DefaultConfiguration("Local"));
		checker.contextualize(new DefaultContext());
	}

	public void walk() throws CheckstyleException {
		
			checker.beginTree(aAST);
			processIter(aAST);
			checker.finishTree(aAST);
	}

	private void processIter(DetailAST aAST) {
		
		int [] tokens = checker.getAcceptableTokens();
		DetailAST currentNode = aAST;
		
		while (currentNode != null) {
			for(int i = 0; i < tokens.length; i++)
			{
				if(tokens[i] == currentNode.getType())
				{
					checker.visitToken(currentNode);
				}
			}
			DetailAST next = currentNode.getFirstChild();
			while (currentNode != null && next == null) { 
				checker.leaveToken(currentNode);
				next = currentNode.getNextSibling();
				currentNode = currentNode.getParent();
			}
			currentNode = next;
		}
	}
	
}
