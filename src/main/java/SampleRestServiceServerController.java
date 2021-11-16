import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import request.SampleRequest;
import response.SampleResponse;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class SampleRestServiceServerController extends BaseSecureController{

    @Autowired
    SampleService sampleService;

    @RequestMapping(path = "/rest/service/getData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<SampleResponse>> getData(@Valid @RequestBody final SampleRequest req){
        List<SampleResponse> responses = new ArrayList<>();
        responses = sampleService.getData();
        if(!CollectionUtils.isEmpty(responses)){
            return new ResponseEntity<>(responses, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(responses, HttpStatus.NO_CONTENT);
        }
    }
}
