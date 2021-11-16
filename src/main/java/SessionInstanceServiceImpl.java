import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
@Component("sessionInstanceService")
public class SessionInstanceServiceImpl implements SessionInstanceService{

    @Autowired
    CustomUserNamePasswordAuthenticationProvider customUserNamePasswordAuthenticationProvider;
    @Override
    public SessionInstance getSessionInstance() {
        final SessionInstance sessionInstance = getCustomUsernamePasswordAuthenticationToken().getSessionInstance();
        return null;
    }

    private CustomUserNamePasswordAuthenticationToken getCustomUsernamePasswordAuthenticationToken() {
        if(SecurityContextHolder.getContext().getAuthentication() instanceof OAuth2Authentication){
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            try {
                UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) oAuth2Authentication.getUserAuthentication();
                Authentication authentication = customUserNamePasswordAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(token.getPrincipal(),"##oauth2##"));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e){
                throw new BadCredentialsException("invalid username");
            }

        }
        return (CustomUserNamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User getLoginUser() {
        return null;
    }
}
