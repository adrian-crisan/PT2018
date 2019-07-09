package PT2018.assig4;

import junit.framework.TestCase;

public class TestUnit extends TestCase {

	
	public void testFindPerson() {
		
		Person person = new Person(1, "Crisan", "Baii");
		
		Bank bank = new Bank();
		
		Person toFind = bank.findPerson("Crisan");
		
		assertEquals(person.getName(), toFind.getName());
		assertEquals(person.getPersonId(), toFind.getPersonId());
		assertEquals(person.getAddress(), toFind.getAddress());
		
	}
}
