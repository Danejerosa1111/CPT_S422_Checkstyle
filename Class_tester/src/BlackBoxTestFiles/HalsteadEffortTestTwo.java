package BlackBoxTestFiles;

public class HalsteadEffortTestTwo {
	
	public void mainTest()
	{
		long highest_palindrome = 0L;
		int storeI = 0;
		int storeJ = 0;
		long product;
		for (int i = 999; i >= 100; i--) {
		    for (int j = 999; j >= 100; j--) {
		        product = j * i;
		        if (product < highest_palindrome) break;
		        if (reverse(product)) {
		            if (product > highest_palindrome) {
		                highest_palindrome = product;
		                storeI = i;
		                storeJ = j;
		            }
		            continue;
		        }
		    }
		}
	}
	private static final boolean reverse(long value) {
        String str = String.valueOf(value);
        return str.equals(new StringBuilder(str).reverse().toString());
    }

}
