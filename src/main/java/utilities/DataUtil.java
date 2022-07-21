package utilities;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataUtil {
    private Locale locale = new Locale("en");
    private Faker faker;

    public static DataUtil getData() {
        return new DataUtil();
    }

    public DataUtil(){
        faker = new Faker(locale);
    }

    public String getEmailAddress() {
        return faker.internet().emailAddress();
    }

    public String getPassword() {
        return faker.internet().password();
    }

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getMiddleName() {
        return faker.name().nameWithMiddle();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

}
