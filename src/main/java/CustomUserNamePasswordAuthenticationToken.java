import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserNamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public CustomUserNamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    private SessionInstance sessionInstance;

    public SessionInstance getSessionInstance() {
        return sessionInstance;
    }

    public void setSessionInstance(SessionInstance sessionInstance) {
        setDetails(sessionInstance);
        this.sessionInstance = sessionInstance;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( (sessionInstance == null) ? 0 : sessionInstance.hashCode());
        return result;
    }

    @Override
    public boolean equals (Object o){
        if (this == o)
            return true;
        if (!super.equals(o))
            return false;
        if (getClass() != o.getClass())
            return false;
        CustomUserNamePasswordAuthenticationToken other = (CustomUserNamePasswordAuthenticationToken) o;
        if (sessionInstance == null){
            if(other.sessionInstance != null)
                return false;
        } else if (!sessionInstance.equals(other.sessionInstance))
            return false;
        return true;
    }
}