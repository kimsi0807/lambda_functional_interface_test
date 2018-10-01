package lambda_express;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;

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
	
	
	public Person(String nameArg, LocalDate dateArg,
			Sex sexArg, String emailArg) {
		// TODO Auto-generated constructor stub
		this.name = nameArg;
		this.birthday = dateArg;
		this.gender = sexArg;
		this.emailAddress = emailArg;
	}
	
	public void printPerson() {
		System.out.println("..........print person : "+this.toString());
	}
	//정적 메소드~
	public static int compareByAge(Person a, Person b) {
		return a.birthday.compareTo(b.birthday);
	}
	public static List<Person> createPerson (){
		List<Person> person = new ArrayList<Person>();
		person.add(new Person(
				"kim"
				,IsoChronology.INSTANCE.date(1980,6,20)
				,Person.Sex.MALE
				,"kim@email.com"
				));
		person.add(new Person(
				"kim2"
				,IsoChronology.INSTANCE.date(1980,6,20)
				,Person.Sex.MALE
				,"kim@email.com"
				));
		person.add(new Person(
				"kim3"
				,IsoChronology.INSTANCE.date(1980,6,20)
				,Person.Sex.MALE
				,"kim@email.com"
				));
		person.add(new Person(
				"kim4"
				,IsoChronology.INSTANCE.date(1980,6,20)
				,Person.Sex.MALE
				,"kim@email.com"
				));
		return person;
	}
}
