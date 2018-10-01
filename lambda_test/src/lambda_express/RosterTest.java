package lambda_express;


import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import lambda_express.RosterTest.CheckPerson;



/**
 * <pre>
 * lambda_express
 * RosterTest.java
 * </pre>
 *
 * @author pretty snake
 * @Date : 2018. 9. 19.
 * @version : 0.1
 */
public class RosterTest {
	
	//오늘은 조건이 많이 분기가 되거나 향후 조건 변동이 생길 여지가 있는 
	//api 개발에 있어 무엇이 괜찮은 방법인가..?히힛
	interface CheckPerson {
		boolean test(Person p);
	}
	
	
	/**
	 * <pre>
	 * printPersonsOlderThan
	 * </pre>
	 *
	 * @author pretty snake
	 * @Date : 2018. 9. 19.
	 * @version : 0.1
	 */
	//특정 조건에 맞는 person 객체 추출하기~
	public static void printPersonsOlderThan(List<Person> roster, int age) {
		for(Person p : roster) {
			if(p.getAge() > age) {
				p.printPerson();
			}
		}
	}

	/**
	 * <pre>
	 * printPersonWithinAgeRange
	 * </pre>
	 *
	 * @author pretty snake
	 * @Date : 2018. 9. 19.
	 * @version : 0.1
	 */
	//그런데 특정조건이 만약 바뀐다면 코드를 다시 수정해야겠지용
	//불편하겠지용..
	public static void printPersonWithinAgeRange(List<Person> personList, int low, int high) {
		for(Person person: personList) {
			if(person.getAge() > low && person.getAge() < high) {
				person.printPerson();
			}
		}
		
		//1) 요렇게 하면 인터페이스 상속한 클래스만 상속하면 되긴하는데 그래도 불편하지 않나..?
		printPersons(personList, new checkPersonEligibleForSelectiveService());

		//2) 익명 클래스를 해보면.. 양이 좀 많다..그렇다면 람다를
		printPersons(personList, new CheckPerson() {
			
			@Override
			public boolean test(Person p) {
				// TODO Auto-generated method stub
				return p.getGender() == Person.Sex.MALE &&
						p.getAge() > 18 &&
						p.getAge() < 30;
			}
		});
		
		//3) lambda expression.. for functional interface..which has only one abstract method..
		printPersons(personList, (Person p) -> 
									p.getGender() == Person.Sex.MALE &&
									p.getAge() > 18 &&
									p.getAge() <30
									);
		//4
		printPersonsWithPredicate(personList, (Person p) -> p.getGender() == Person.Sex.MALE
				&& p.getAge() > 18
				&& p.getAge() < 30
				);
		//5
		printPersonsWithPredicateAndConsumer(personList, 
				(person) -> person.getGender()==Person.Sex.MALE && person.getAge() > 18,
				(person) -> person.printPerson()
				);
		//6
		processPersonsWithFunction(personList, 
				(p) -> p.gender == Person.Sex.MALE && p.getAge() > 18,
				(p) -> p.getEmailAddress(),
				(email) -> System.out.println(email)
				);
		//7
		processGeneric(personList, 
				(p) -> p.getGender() == Person.Sex.MALE,
				(p) -> p.getEmailAddress(),
				(email) -> System.out.println(email)
				);
		
		//8 using lambda expression...
		personList
			.stream()
			.filter(p -> p.getGender() == Person.Sex.MALE 
					&& p.getAge() > 18
					)
			.map(p -> p.getEmailAddress())
			.forEach(email -> System.out.println(email));
	}
	//4) 기존 interface인 predicate를 사용해서 동일한 방식으로 체크
	public static void printPersonsWithPredicate(List<Person> personList, Predicate<Person> predicate) {
		for(Person person : personList) {
			if(predicate.test(person)) person.printPerson();
		}
	}
	
	//5) 기존 consumer interface를 같이 사용하는 case!! 
	public static void printPersonsWithPredicateAndConsumer(List<Person> personList, Predicate<Person>  predicate,
			Consumer<Person> consumer) {
		for(Person person : personList) {
			if(predicate.test(person)) consumer.accept(person);
		}
	}
	
	//6) 리턴할 데이터가 있는 경우에는 function interface 의 apply 메소드를 이용한다.
	public static void processPersonsWithFunction(List<Person> personList,
			Predicate<Person> predict,
			Function<Person, String> function,
			Consumer<String> consumer) {
		for(Person person : personList) {
			if(predict.test(person)) {
				String data = function.apply(person);
				consumer.accept(data);
			}
		}
		
	}
	//7) use generic..
	public static <x,y> void processGeneric(Iterable<x> iterableSource,
			Predicate<x> predicate,
			Function<x, y> function,
			Consumer<y> consumer){
		for(x source : iterableSource) {
			if(predicate.test(source)){
				y data = function.apply(source);
				consumer.accept(data);
			}
		}
	}
	/**
	 * <pre>
	 * printPersons
	 * </pre>
	 *
	 * @author pretty snake
	 * @Date : 2018. 9. 19.
	 * @version : 0.1
	 */
	//그래서 이렇게 조건별 케이스가 많이 나올 것 같은 경우에는 criteria code를 사용하는게 ..
	public static void printPersons(List<Person> personList, CheckPerson tester) {
		for( Person person : personList) {
			if(tester.test(person)) person.printPerson();
		}
		
	}
	
	
}

class checkPersonEligibleForSelectiveService implements CheckPerson{
	@Override
	public boolean test(Person p) {
		// TODO Auto-generated method stub
		return p.getGender() == Person.Sex.MALE &&
				p.getAge() > 18 &&
				p.getAge() < 30;
	}
}

//https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#approach1
