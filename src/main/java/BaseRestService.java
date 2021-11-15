import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BaseRestService {

    //private final Logger logger = LoggerFactory.getLogger(BaseRestService.class);

    @Autowired
    private RestTemplate restTemplete;

    protected HttpHeaders getHeaders(String username){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("userName",username);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    HttpHeaders getHeaders(String uname, String pwd){
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = uname + ":" + pwd;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        httpHeaders.add("Authorization", authHeader);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;

    }

    public RestTemplate getRestTemplete() {
        return restTemplete;
    }
}
