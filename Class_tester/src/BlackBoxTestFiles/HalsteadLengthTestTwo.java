package BlackBoxTestFiles;

public class HalsteadLengthTestTwo {
	
	
	public void testTwo()
	{
		String s1 = "Computer Science";
		int x = 307;
		String s2 = s1 + " " + x;
		String s3 = s2.substring(10,17);
		String s4 = "is fun";
		String s5 = s2 + s4;
		
		x = 3;
		int y = 5;
		String s6 = x + y + "total";
		String s7 = "total " + x + y;
		String s8 = " " + x + y + "total";
	}

}
