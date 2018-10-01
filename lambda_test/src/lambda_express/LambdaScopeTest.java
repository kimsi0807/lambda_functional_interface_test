package lambda_express;

import java.util.function.Consumer;

/**
 * <pre> 
 * LambdaScopeTest.java
 * </pre>
 *
 * @author SEOL
 * @date : 2018. 10. 1.
 * @version : 
 * 
 */
public class LambdaScopeTest {

	public int x = 0;
	
	class FirstLevel {
		
		public int x = 1;
		 
		void methodInFirstLevel(int x) {
			
			//x = 99;
/*			Exception in thread "main" java.lang.Error: Unresolved compilation problem: 
				Local variable x defined in an enclosing scope must be final or effectively final
*/
			Consumer<Integer> consumer = y -> {
				System.out.println("x = " + x);
				System.out.println("y= "+ y);
				System.out.println("this.x = "+ this.x);
				System.out.println("LambdaScopeTest.this.x" + LambdaScopeTest.this.x);
			};
			consumer.accept(x);
		}
	}
	public static void main(String... args) {
		LambdaScopeTest lct = new LambdaScopeTest();
		LambdaScopeTest.FirstLevel fl = lct.new FirstLevel();
		fl.methodInFirstLevel(23);
	}


}

//https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#approach1
