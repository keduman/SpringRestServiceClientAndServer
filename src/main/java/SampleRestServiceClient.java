import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import request.SampleRequest;
import response.SampleResponse;

import java.util.List;

@Component
public class SampleRestServiceClient extends BaseRestService{

    public List<SampleResponse> getData(SampleRequest request, User user) throws Exception {
        HttpEntity<List<SampleResponse>> req = new HttpEntity(request, getHeaders(user.getUserName()));
        String endPointUrl = "localhost:8080/rest/service/getData";
        ResponseEntity<List<SampleResponse>> response = getRestTemplete().exchange(endPointUrl, HttpMethod.POST, req, new ParameterizedTypeReference<List<SampleResponse>>() {});
        if(response.getStatusCode() == HttpStatus.NO_CONTENT){
            throw new Exception("no content");
        }else if(response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR){
            throw new Exception("internal error");
        }else{
            return response.getBody();
        }
    }
}
