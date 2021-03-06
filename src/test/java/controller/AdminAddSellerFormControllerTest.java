package controller;

import com.bigsale.controller.admin.AddSellerFormController;
import com.bigsale.controller.dto.UserSignUpDto;
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
import static org.mockito.Mockito.mock;

public class AdminAddSellerFormControllerTest extends AbstractDispatcherServletTest {

    public static final String APP_CONTEXT = "../testconf/web-application-context.xml";
    private static final String FORM_PAGE_ONE = "/admin/addSellerFormPageOne";
    private static final String FORM_PAGE_CONFIRM = "/admin/addSellerFormPageConfirm";
    private static final String SUCCESS_PAGE = "/admin/registrationSuccess";
    private static final String REDIRECT_TO_ADMIN_INDEX = "redirect:/bigsale/admin";
    public static final String REQUEST_URI = "/admin/addSellerForm.html";

    private UserSignUpDto userSignUpDto;

    private ModelAndView sut;
    private Map<String, String> paramMap;

    @Before
    public void initSutEnv(){
        userSignUpDto = mock(UserSignUpDto.class);
        userSignUpDto.setPassword("1111");
        userSignUpDto.setPasswordConfirm("1111");

        paramMap = new HashMap<String, String>();
    }

    @Test
    public void shallInitializeInitialFormAndModel() throws ServletException, IOException{
        sut = initMavWithRequestUri(REQUEST_URI);

        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo(FORM_PAGE_ONE);
        assertThat(sut.getModel().get("userSignUpDto")).isInstanceOf(UserSignUpDto.class);

    }

    @Test
    public void shallProceedFormPageTwoWithNextSubmit_WhenPageOne() throws ServletException, IOException{
        sut = initMavWithRequestUri(REQUEST_URI);

        paramMap.put("_page", "0");
        paramMap.put("_target1", "_target1");

        sut = mavWhenSubmit(paramMap);

        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo(FORM_PAGE_CONFIRM);
    }

    @Test
    public void shallProceedAdminInitWithCancelSubmit() throws ServletException, IOException{
        sut = initMavWithRequestUri(REQUEST_URI);

        paramMap.put("_page", "0");
        paramMap.put("_target1", "_target1");
        paramMap.put("_cancel", "_cancel");

        sut = mavWhenSubmit(paramMap);

        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo(REDIRECT_TO_ADMIN_INDEX);
    }

    private ModelAndView initMavWithRequestUri(String requestUri) throws ServletException, IOException
    {
       return setRelativeLocations(APP_CONTEXT)
                    .setClasses(AddSellerFormController.class)
                    .initRequest(requestUri)
                    .addSessionValue("userSignUpDto", userSignUpDto)
                    .runService()
                    .getModelAndView();
    }


    private ModelAndView mavWhenSubmit(Map<String, String> paramMap) throws ServletException, IOException
    {
        AbstractDispatcherServletTest servletTest = setRelativeLocations(APP_CONTEXT)
                .setClasses(AddSellerFormController.class)
                .initRequest(REQUEST_URI, RequestMethod.POST);

        for(Map.Entry entry : paramMap.entrySet()){
            servletTest.addParameter((String)entry.getKey(), (String)entry.getValue());
        }

        return servletTest.addSessionValue("userSignUpDto", userSignUpDto)
                .runService()
                .getModelAndView();
    }


}
