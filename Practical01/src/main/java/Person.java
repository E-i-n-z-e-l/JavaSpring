import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.google.gson.Gson;



public class Person {
    private String firstName;
    private String lastName;
    private int age;
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE) автоматически создает
     * строку, которая содержит имя класса, а также значения всех полей объекта Person.
     * ToStringStyle.SHORT_PREFIX_STYLE определяет стиль форматирования строки.
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * Метод переопределяет метод equals(Object obj) класса Object для сравнения объектов
     * класса Person. Используем библиотеку EqualsBuilder из commons-lang3, чтобы упростить
     * генерацию кода для сравнения полей.
     * <p></p>
     * Вначале мы проверяем, является ли obj нулевым или равным текущему объекту (this).
     * Затем мы проверяем, является ли obj объектом того же класса (Person). Если это так,
     * мы выполняем фактическое сравнение полей, используя EqualsBuilder.
     * <p></p>
     * Метод append() используется для добавления полей в сравнение. Наконец, мы вызываем
     * isEquals(), чтобы получить результат сравнения.
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Person other = (Person) obj;
        return new EqualsBuilder()
                .append(firstName, other.firstName)
                .append(lastName, other.lastName)
                .append(age, other.age)
                .isEquals();
    }

    /**
     * Метод переопределяет метод hashCode() класса Object для генерации хэш-кода объекта Person.
     * Используем библиотеку HashCodeBuilder из commons-lang3, чтобы упростить генерацию кода.
     * Добавляем все поля объекта Person в хэш-код с помощью append(). Затем вызываем toHashCode()
     * для получения финального хэш-кода.
     * @return
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(firstName)
                .append(lastName)
                .append(age)
                .toHashCode();
    }

    /**
     * Метод сериализует объект класса Person в формат JSON. Вначале создаем новый объект Gson из
     * библиотеки Gson.
     * <p></p>
     * Затем вызываем метод toJson(this), где this ссылается на текущий объект Person.
     * <p></p>
     * Метод toJson() преобразует объект Person в JSON-строку и возвращает ее.
     * @return
     */
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * Метод десериализует JSON-строку в объект класса Person.
     * <p></p>
     * Создаем новый объект Gson. Затем вызываем метод fromJson(json, Person.class),
     * где json - это JSON-строка, которую надо преобразовать, и Person.class указывает на класс,
     * в который нужно преобразовать JSON.
     * <p></p>
     * Метод fromJson() выполняет обратное преобразование и возвращает объект Person.
     * @param json
     * @return
     */
    public static Person fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Person.class);
    }


}
