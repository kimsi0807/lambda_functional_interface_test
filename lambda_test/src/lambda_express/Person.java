package lambda_express;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Person {

	public enum Sex{
		MALE,
		FEMALE
	}
	
	String name;
	LocalDate birthday;
	Sex gender;
	String emailAddress;
	int age;
	
	public void printPerson() {
		System.out.println("..........print person : "+this.toString());
	}
	
	
}
