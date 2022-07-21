package testData.DemoProject.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class UserDataModel
{
    public static UserDataModel getValue(String filename){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(filename), UserDataModel.class);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    static class Register{

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("middlename")
    private String middlename;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
    }

    @JsonProperty("Register")
    Register register;


    public String getPassword() {
        return register.password;
    }

    public String getFirstname() {
        return register.firstname;
    }

    public String getMiddlename() {
        return register.middlename;
    }

    public String getLastname() {
        return register.lastname;
    }

    public String getEmail() {
        return register.email;
    }
}
