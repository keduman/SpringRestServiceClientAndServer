import java.io.Serializable;
import java.util.Date;

public class SessionInstance implements Serializable {

    private String authenticaitonToken = null;
    private User loginUser = null;
    private final Date loginDate = new Date();
    private AuthenticationTokenEnum authenticationTokenEnum;

    public SessionInstance(User loginUser, AuthenticationTokenEnum authenticationTokenEnum) {
        super();
        this.loginUser = loginUser;
        this.authenticationTokenEnum = authenticationTokenEnum;
    }

}
