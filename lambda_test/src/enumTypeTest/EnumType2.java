package enumTypeTest;

/**
 * <pre> 
 * EnumType2.java
 * </pre>
 *
 * @author SEOL
 * @date : 2018. 10. 2.
 * @version : 
 * 
 */
public class EnumType2 {
	
	public enum Planet{
		MERCURY(3,2),
		VENUS(5,6),
		EARTH(5,6),
		MARTS(6,3),
		JUPITER(1,7);
	
	private final int mass;
	private final int radius;
	
	Planet(int mass, int radius){
		this.mass = mass;
		this.radius = radius;
	}
	
	private int mass() {
		return mass;
	}
	private int radius() {
		return radius;
	}
	
	public static final int G = 6;
	
	int surfaceGravity() {
		return G * mass/ radius*radius;
		
	}
	int surfaceWeight(int otherMass) {
		return otherMass * surfaceGravity();
	}
	
	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("usage...:java planet <earth_weight>");
			System.exit(-1);
		}
		int earthWeight = Integer.parseInt(args[0]);
		int mass = earthWeight/EARTH.surfaceGravity();
		for (Planet p : Planet.values())
			System.out.println(p.toString()+p.surfaceWeight(mass));
		
	}
	
	}
	

}
