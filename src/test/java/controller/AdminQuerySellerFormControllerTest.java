package controller;

import com.bigsale.controller.admin.QuerySellerFormController;
import com.bigsale.controller.dto.UserSearchDto;
import com.bigsale.orm.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public static final String FORM_PAGE_FOR_QUERY_INPUT = "/admin/querySellerFormPageOne";
    public static final String FORM_PAGE_FOR_QUERY_RESULT = "/admin/querySellerFormPageResult";
    private Map<String, String> paramMap;

    private ModelAndView sut;
    private UserSearchDto userSearchDto;

    @Before
    public void initSutEnv(){
        paramMap = new HashMap<String, String>();

    }

    @Test
    public void shallInitializeInitialFormAndModel() throws ServletException, IOException {
        sut = initMavWithRequestUri(REQUEST_URI);

        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo(FORM_PAGE_FOR_QUERY_INPUT);
    }

    @Test
    public void shallSearchSellerWithID() throws ServletException, IOException {
        sut = setRelativeLocations(APP_CONTEXT)
                .setClasses(QuerySellerFormController.class)
                .initRequest(REQUEST_URI, RequestMethod.POST)
                .addParameter("_search","_search")
                .addSessionValue("userSearchDto", userSearchDto)
                .runService()
                .getModelAndView();

        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo(FORM_PAGE_FOR_QUERY_RESULT);
        assertThat(sut.getModel().get("user")).isInstanceOf(User.class);
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
