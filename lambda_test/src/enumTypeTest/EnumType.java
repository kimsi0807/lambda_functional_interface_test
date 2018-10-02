package enumTypeTest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
public class EnumType {

	public enum Day {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
		THURSDAY, FRIDAY, SATURDAY
	}
	
	@RequiredArgsConstructor
	public static class EnumTest{
		@NonNull
		Day day;
		
		public void tellItLikeItIs() {
			switch(day) {
			case MONDAY:
				System.out.println("ITS MONDAY...");
				break;
			case FRIDAY :
				System.out.println("WOW ITS FRIDAY..");
				break;
			case SATURDAY:
				break;
			case SUNDAY:
				break;
			case THURSDAY:
				break;
			case TUESDAY:
				break;
			case WEDNESDAY:
				break;
			default:
				break;
				
			}
		}
		public static void main(String... args) {
			EnumTest firstday = new EnumTest(Day.FRIDAY);
			firstday.tellItLikeItIs();
		}
	}
}
//https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html

