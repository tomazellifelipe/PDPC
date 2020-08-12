
public class NumeroProcessadores {
		
		public static void main(String[] args) {
			
			// get the runtime object associated with the current Java application
			Runtime runtime = Runtime.getRuntime();
			
			// get the number of processors available to the Java virtual machine
			int numberOfProcessors = runtime.availableProcessors();
			 
			System.out.println("Number of processors available to this JVM: " + numberOfProcessors);
			
		}
}
