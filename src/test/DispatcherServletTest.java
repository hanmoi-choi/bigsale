
import com.bigsale.spikes.HelloSpring;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 1/09/12
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class DispatcherServletTest {

    @Test
    public void shallReadBeanFileFromXml() throws ServletException, IOException {
        ConfigurableDispatcherServlet servlet = new ConfigurableDispatcherServlet();
        servlet.setRelativeLocations(getClass(), "com/bigsale/greeter-servlet.xml");
        servlet.setClasses(HelloSpring.class);
        servlet.init(new MockServletConfig("spring"));

        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/hello");
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.setParameter("name", "Spring");

        servlet.service(request, response);

        ModelAndView modelAndView = servlet.getModelAndView();

        assertThat(modelAndView.getViewName(), is(equalTo("hello")));
        assertThat((String)modelAndView.getModel().get("message"), is(equalTo("Hello Spring")));

    }
}
