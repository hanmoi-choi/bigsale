package controller;

import com.bigsale.controller.admin.QuerySellerFormController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import template.AbstractDispatcherServletTest;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 7/10/12
 * Time: 8:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class AdminQuerySellerFormControllerTest extends AbstractDispatcherServletTest {
    public static final String APP_CONTEXT = "../testconf/web-application-context.xml";
    public static final String REQUEST_URI = "/admin/querySellerForm.html";
    private Map<String, String> paramMap;
    private ModelAndView sut;

    @Before
    public void initSutEnv(){
        paramMap = new HashMap<String, String>();
    }

    @Test
    public void shallInitializeInitialFormAndModel() throws ServletException, IOException {
        sut = initMavWithRequestUri(REQUEST_URI);

        assertThat(sut).isNotNull();
    }

    private ModelAndView initMavWithRequestUri(String requestUri) throws ServletException, IOException
    {
        return setRelativeLocations(APP_CONTEXT)
                .setClasses(QuerySellerFormController.class)
                .initRequest(requestUri)
                .runService()
                .getModelAndView();
    }
}
