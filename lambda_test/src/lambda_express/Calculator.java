package lambda_express;

/**
 * <pre> 
 * Calculator.java
 * </pre>
 *
 * @author SEOL
 * @date : 2018. 10. 1.
 * @version : 
 * 
 */
public class Calculator {
	
	//변수가 2개일 때 람다식 표현
	interface IntegerMath {
		int operation(int a, int b);
	}

	public int operateBinary(int a, int b, IntegerMath integerMath) {
		return integerMath.operation(a, b);
	}
	
	public static void main(String... args) {
		Calculator cal = new Calculator();
		IntegerMath add_integerMath = (a,b) -> a+b;
		IntegerMath sub_integerMath = (a,b) -> a-b;
		int a = cal.operateBinary(20, 2, add_integerMath);
		int b = cal.operateBinary(40, 2, sub_integerMath);
		System.out.printf("%d : , %d",a,b);
		
	}
}
