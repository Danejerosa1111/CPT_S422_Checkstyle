package BlackBoxTestFiles;

public class HalsteadVocabTestTwo {
	
	 static int largest() 
     { 
		 int arr[] = {10, 324, 45, 90, 9808};
         int i; 
           
         // Initialize maximum element 
         int max = arr[0]; 
        
         // Traverse array elements from second and 
         // compare every element with current max   
         for (i = 1; i < arr.length; i++) 
             if (arr[i] > max) 
                 max = arr[i]; 
        
         return max; 
     } 

}
