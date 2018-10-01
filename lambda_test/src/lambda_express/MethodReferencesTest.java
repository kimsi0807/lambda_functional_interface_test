package lambda_express;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * <pre> 
 * MethodReferencesTest.java
 * </pre>
 *
 * @author SEOL
 * @date : 2018. 10. 1.
 * @version : 
 * 
 */

//람다 표현식을 익명 메소드를 만들 수 있습니다. 그러나 때로는 람다식이 기존 메서드를 호출하는 것 외에는 아무것도 수행하지 않습니다. 
//이러한 경우 기존 메서드를 이름으로 참조하는 것이 더 명확합니다. 메서드 참조를 사용하면이 작업을 수행
public class MethodReferencesTest {

	public static <T, S extends Collection<T>, D extends Collection<T>> 
			D transferElements(
			S sourceCollection, Supplier<D> supplierCollectionFactory) {
		D result = supplierCollectionFactory.get();
		for(T t : sourceCollection) {
			result.add(t);
		}
		return result;
		
	}
	public static void main(String... args) {
		List<Person> person = Person.createPerson();
		for (Person p : person) {
			p.printPerson();
		}
		Person[] personAsArray = person.toArray(new Person[person.size()]);
		
		class PersonAgeComparator implements Comparator<Person>{
			public int compare(Person a, Person b) {
				return a.getBirthday().compareTo(b.getBirthday());
			}
		}
		//1
		Arrays.sort(personAsArray, new PersonAgeComparator());
		
		//2 람다식으로 표현해보면..
		Arrays.sort(personAsArray, 
				(Person a, Person b) -> {
					return a.getBirthday().compareTo(b.getBirthday());
				}
		);
		
		/*메소드 참조에는 네 가지 종류가 있습니다.

		종류	예
		a 정적 메서드에 대한 참조	ContainingClass::staticMethodName
		b 특정 객체의 인스턴스 메소드 참조	containingObject::instanceMethodName
		c 특정 유형의 임의의 객체에 대한 인스턴스 메소드 참조	ContainingType::methodName
		d 생성자에 대한 참조	ClassName::new
		*/
		//3  정적 method 참조(a)
		Arrays.sort(personAsArray, Person::compareByAge);
		
		//4 특정 객체의 인스턴스 메소드 참조 (b)
		class ComparisonProvider{
			public int compareByName(Person a, Person b) {
				return a.getName().compareTo(b.getName());
			}
			public int compareByAge(Person a, Person b) {
				
				return a.getBirthday().compareTo(b.getBirthday());
			}
		}
		
		ComparisonProvider comparisonProvider = new ComparisonProvider();
		Arrays.sort(personAsArray, comparisonProvider::compareByAge);
		
		//특정 유형의 임의의 객체에 대한 인스턴스 메소드 참조(c)
		String[] stringArray = {"a","b","c"};
		Arrays.sort(stringArray, String::compareToIgnoreCase);
		
		Set<Person> setPersonLambda = transferElements(person, () -> { return new HashSet<>(); });
		
		//생성자에 대한 참조(d)
		Set<Person> setPerson = transferElements(person, HashSet::new);
		setPerson.stream().forEach(p -> p.printPerson());
		
		
		
	}
	
	
}
