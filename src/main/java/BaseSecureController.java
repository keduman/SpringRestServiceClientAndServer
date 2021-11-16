import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/secure")
public class BaseSecureController {
    @Autowired private SessionInstanceService sessionInstanceService;
}
