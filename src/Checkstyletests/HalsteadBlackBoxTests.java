package Checkstyletests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;

import BlackBoxTestRunner.HalsteadAutoRunner;
import BlackBoxTestRunner.TestRunner;
import CheckStyleChecks.HalsteadChecks;

public class HalsteadBlackBoxTests {
	
	private HalsteadAutoRunner runner;
	private TestRunner lengthRunner;
	HalsteadChecks checker = new HalsteadChecks();
	
	
	@Test
	public void testLengthZero() throws CheckstyleException
	{
		final int halsteadLength = 0;
		final String logMessage = "The halstead length is: "+halsteadLength;
		String pathname = "src/BlackBoxTestFiles/UniversalEmptyFile.java";
		
		lengthRunner = new TestRunner(pathname, checker);
		lengthRunner.getConfigurations();
		lengthRunner.walk();
		
		assertTrue(checker.getHalsteadLength() == halsteadLength);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Halstead Length of 0 Check Done!");
	}
	
	@Test
	public void testDifficultyZero() throws CheckstyleException
	{
		final int halsteadDifficulty = 0;
		final String logMessage = "the halstead difficulty is: " + halsteadDifficulty;
		String pathname = "src/BlackBoxTestFiles/UniversalEmptyFile.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		checker.finishDifficultyCount();
		assertTrue(checker.getHalsteadDifficulty() == halsteadDifficulty);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Halstead Difficulty of 0 Check Done!");
	}
	
	@Test
	public void testEffortZero() throws CheckstyleException
	{
		final int halsteadEffort = 0;
		final String logMessage = "the halstead effort is: " + halsteadEffort;
		String pathname = "src/BlackBoxTestFiles/UniversalEmptyFile.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		
		checker.finishEffortCount();
		assertTrue(checker.getHalsteadEffort() == halsteadEffort);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Halstead Effort of 0 Check Done!");
	}

	@Test
	public void testVocabZero() throws CheckstyleException
	{
		final int halsteadVocab = 0;
		final String logMessage = "the halstead vocab is: " + halsteadVocab;
		String pathname = "src/BlackBoxTestFiles/UniversalEmptyFile.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		

		checker.finishVocabCount();
		assertTrue(checker.getHalsteadVocab() == halsteadVocab);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Halstead Vocab of 0 Check Done!");
	}
	
	@Test
	public void testVolumeZero() throws CheckstyleException
	{
		final int halsteadVolume = 0;
		final String logMessage = "the halstead volume is: " + halsteadVolume;
		String pathname = "src/BlackBoxTestFiles/UniversalEmptyFile.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		

		checker.finishVolumeCount();
		assertTrue(checker.getHalsteadVolume() == halsteadVolume);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Halstead Volume of 0 Check Done!");
	}
	
	@Test
	public void difficultyTestOne() throws CheckstyleException
	{
		final int halsteadDifficulty = 6;
		final String logMessage = "the halstead difficulty is: " + halsteadDifficulty;
		String pathname = "src/BlackBoxTestFiles/HalsteadDifficultyTestOne.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		
		checker.finishDifficultyCount();
		assertTrue(checker.getHalsteadDifficulty() == halsteadDifficulty);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Halstead Difficulty of 6 Check Done!");
		
	}
	
	@Test
	public void difficultyTestTwo() throws CheckstyleException
	{
		final int halsteadDifficulty = 7;
		final String logMessage = "the halstead difficulty is: " + halsteadDifficulty;
		String pathname = "src/BlackBoxTestFiles/HalsteadDifficultyTestTwo.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		checker.finishDifficultyCount();
		assertTrue(checker.getHalsteadDifficulty() == halsteadDifficulty);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Halstead Difficulty of 7 Check Done!");
		
	}
	
	@Test
	public void lengthTestOne() throws CheckstyleException
	{
		final int halsteadLength = 24;
		final String logMessage = "The halstead length is: "+halsteadLength;
		String pathname = "src/BlackBoxTestFiles/HalsteadLengthTestOne.java";
		
		lengthRunner = new TestRunner(pathname, checker);
		lengthRunner.getConfigurations();
		lengthRunner.walk();
		
		
		assertTrue(checker.getHalsteadLength() == halsteadLength);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Halstead Difficulty of 24 Check Done!");
		
	}
	
	@Test
	public void lengthTestTwo() throws CheckstyleException
	{
		final int halsteadLength = 35;
		final String logMessage = "The halstead length is: "+halsteadLength;
		String pathname = "src/BlackBoxTestFiles/HalsteadLengthTestTwo.java";
		
		lengthRunner = new TestRunner(pathname, checker);
		lengthRunner.getConfigurations();
		lengthRunner.walk();
		
		assertTrue(checker.getHalsteadLength()== halsteadLength);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Halstead Difficulty of 35 Check Done!");
		
	}
	
	
	@Test
	public void volumeTestOne() throws CheckstyleException
	{
		final int halsteadVolume = 81;
		final String logMessage = "the halstead volume is: " + halsteadVolume;
		String pathname = "src/BlackBoxTestFiles/HalsteadVolumeTestOne.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		checker.finishVolumeCount();
		assertTrue(checker.getHalsteadVolume() == halsteadVolume);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Halstead Volume of 81 Check Done!");
	}
	
	@Test
	public void volumeTestTwo() throws CheckstyleException
	{
		final int halsteadVolume = 6;
		final String logMessage = "the halstead volume is: " + halsteadVolume;
		String pathname = "src/BlackBoxTestFiles/HalsteadVolumeTestTwo.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		checker.finishVolumeCount();
		assertTrue(checker.getHalsteadVolume() == halsteadVolume);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Halstead Volume of 6 Check Done!");
	}
	
	@Test
	public void vocabTestOne() throws CheckstyleException
	{
		final int halsteadVocab = 4;
		final String logMessage = "the halstead vocab is: " + halsteadVocab;
		String pathname = "src/BlackBoxTestFiles/HalsteadVocabTestOne.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		checker.finishVocabCount();
		assertTrue(checker.getHalsteadVocab() == halsteadVocab);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Halstead Vocab of 4 Check Done!");
	}
	
	@Test
	public void vocabTestTwo() throws CheckstyleException
	{
		final int halsteadVocab = 11;
		final String logMessage = "the halstead vocab is: " + halsteadVocab;
		String pathname = "src/BlackBoxTestFiles/HalsteadVocabTestTwo.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		
		checker.finishVocabCount();
		assertTrue(checker.getHalsteadVocab() == halsteadVocab);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Halstead Vocab of 11 Check Done!");
	}
	
	@Test
	public void effortTestOne() throws CheckstyleException
	{
		final int halsteadEffort = 40;
		final String logMessage = "the halstead effort is: " + halsteadEffort;
		String pathname = "src/BlackBoxTestFiles/HalsteadEffortTestOne.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		checker.finishEffortCount();
		assertTrue(checker.getHalsteadEffort() == halsteadEffort);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Halstead Effort of 40 Check Done!");
	}
	
	@Test
	public void effortTestTwo() throws CheckstyleException
	{
		final int halsteadEffort = 651;
		final String logMessage = "the halstead effort is: " + halsteadEffort;
		String pathname = "src/BlackBoxTestFiles/HalsteadEffortTestTwo.java";
		
		runner = new HalsteadAutoRunner(pathname, checker);
		runner.getConfigurations();
		runner.walk();
		
		checker.finishEffortCount();
		assertTrue(checker.getHalsteadEffort() == halsteadEffort);
		for(LocalizedMessage lm : checker.getMessages()) {
			//System.out.println(lm.getMessage());
			assertTrue(lm.getMessage().equals(logMessage));
		}
		System.out.println("Halstead Effort of 651 Check Done!");
	}
	
	
	

}
