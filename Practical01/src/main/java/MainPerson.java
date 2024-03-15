import java.util.ArrayList;

public class MainPerson {
    public static void main(String[] args) {
        Person personOne = new Person("John", "John", 25);
        Person personTwo = new Person("Alex", "Alex", 23);
        Person personTree = new Person("Bob", "Bob", 21);
        Person personFour = new Person("John", "John", 25);

        ArrayList<Person> people = new ArrayList<Person>();
        people.add(personOne);
        people.add(personTwo);
        people.add(personTree);

        System.out.println(people); // Вывод списка people на экран будет использовать метод toString();
        System.out.println("\n");

        boolean isEqual = personOne.equals(personTwo);
        System.out.println(isEqual);  // Выведет false;

        int hashCodeOne = personOne.hashCode();
        int hashCodeFour = personFour.hashCode();
        System.out.println("HashCode of personOne: " + hashCodeOne);
        System.out.println("HashCode of personFour: " + hashCodeFour);
        System.out.println("\n");

        // Сериализация:
        String json = personOne.toJson();
        System.out.println("JSON: " + json);
        System.out.println("\n");

        // Десериализация:
        Person deserializedPerson = Person.fromJson(json);
        System.out.println("Deserialized Person: " + deserializedPerson);
    }
}
