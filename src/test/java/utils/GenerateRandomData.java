package utils;

import com.github.javafaker.Faker;
import io.codearte.jfairy.producer.person.Person;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateRandomData {

    private WebDriver driver;
    private Person person;
    private Faker faker;
    private TestHelpers th;

    public GenerateRandomData(WebDriver driver, Person person, Faker faker) {
        this.driver = driver;
        this.person = person;
        this.faker = faker;
        th = new TestHelpers(driver);
    }

    public String getFirstName() {
        return person.getFirstName();
    }

    public String getMiddleName() {
        return person.getMiddleName();
    }

    public String getLastName() {
        return person.getLastName();
    }

    public String getEmail() {
        return person.getEmail();
    }

    public String getStreet() {
        return faker.address().streetAddress();
    }

    public String getCity() {
        return faker.address().city();
    }

    public String getState() {
        return faker.address().state();
    }

    public String getZipCode() {
//        return faker.address().zipCode();
        return "84111";
    }

    public String getCellPhone() {
        return faker.phoneNumber().cellPhone();
    }

    public String getBirthDate() {
        DateTime dob = person.getDateOfBirth();
        Date date = dob.toLocalDate().toDate();

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String birthday = df.format(date);
        int dobYear = Integer.valueOf(StringUtils.right(birthday, 4));

        DateTime now = new DateTime();
        int nowYear = Integer.valueOf(StringUtils.substring(now.toString(), 0, 4));

        if (nowYear - dobYear < 19) {
            birthday = birthday.replaceAll("\\d{4}", String.valueOf(nowYear - 19));
        }

        return birthday;
    }

    public String getRandomIntent() {
        int rand = th.getRandomNumberBetween(1, 3);

        switch (rand) {
            case 1:
                return "Full Access";
            case 2:
                return "Role Number 1";
            case 3:
                return "Role Number 2";
            default:
                return "Full Access";
        }
    }

    public String getRandomHighestGrade() {
        int rand = th.getRandomNumberBetween(1, 6);

        switch (rand) {
            case 1:
                return "High School Diploma/GED";
            case 2:
                return "Some College";
            case 3:
                return "Associate Degree";
            case 4:
                return "Bachelor Degree";
            case 5:
                return "Master Degree";
            case 6:
                return "Doctoral Degree";
            default:
                return "Bachelor Degree";
        }
    }

    public String getRandomTrainingProgram() {
        int rand = th.getRandomNumberBetween(1, 6);

        switch (rand) {
            case 1:
                return "Salt Lake Community College";
            case 2:
                return "Utah Valley University";
            case 3:
                return "DATC";
            case 4:
                return "Dixie State University";
            case 5:
                return "Out of State Program";
            case 6:
                return "Other";
            default:
                return "Utah Valley University";
        }
    }

    public String getRandomLearnSign() {
        int rand = th.getRandomNumberBetween(1, 9);

        switch (rand) {
            case 1:
                return "School";
            case 2:
                return "Interpreter Training Program";
            case 3:
                return "Church";
            case 4:
                return "Deaf parents";
            case 5:
                return "Deaf immediate family";
            case 6:
                return "Deaf extended family";
            case 7:
                return "Deaf friend";
            case 8:
                return "Other";
            default:
                return "School";
        }
    }

    public String getUniversity() {
        return faker.university().name();
    }

}
