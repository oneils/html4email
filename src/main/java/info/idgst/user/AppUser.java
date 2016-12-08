package info.idgst.user;

import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Aliaksei Bahdanau
 */
@Document(collection = "users")
public class AppUser {

    @Id
    private String id;

    @NotEmpty
    private String userName;

    @NotEmpty
    @JsonIgnore
    private String password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private boolean admin;

    /**
     * Default constructor
     */
    public AppUser() {
    }

    /**
     * Constructor for creating Application with specified parameters.
     *
     * @param username User's name
     * @param password User's password
     * @param isAdmin  indicated if user is administrator or not.
     */
    public AppUser(String username, String password, boolean isAdmin) {
        this.userName = username;
        this.password = password;
        this.admin = isAdmin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return admin == appUser.admin && Objects.equal(id, appUser.id) && Objects.equal(userName, appUser.userName) &&
               Objects.equal(password, appUser.password) && Objects.equal(firstName, appUser.firstName) &&
               Objects.equal(lastName, appUser.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, userName, password, firstName, lastName, admin);
    }
}
