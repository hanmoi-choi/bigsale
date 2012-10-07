package controller;

import com.bigsale.controller.admin.AdminController;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import template.AbstractDispatcherServletTest;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 30/09/12
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminControllerTest extends AbstractDispatcherServletTest {

    public static final String APP_CONTEXT = "../testconf/web-application-context.xml";

    @Test
    public void shouldHandleInitialRequest() throws ServletException, IOException {
        ModelAndView sut = getMAVWithRequestUri("/admin/welcome.html");
        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo("/admin/index");
    }

    @Test
    public void shouldHandleAddFormRequest() throws ServletException, IOException {
        ModelAndView sut = getMAVWithRequestUri("/admin/addSeller.html");
        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo("redirect:/admin/addSellerForm.html");
    }

    @Test
    public void shouldHandleQuerySellerRequest() throws ServletException, IOException {
        ModelAndView sut = getMAVWithRequestUri("/admin/querySeller.html");
        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo("redirect:/admin/querySellerForm.html");
    }

    @Test
    public void shouldHandleMakeReportequest() throws ServletException, IOException {
        ModelAndView sut = getMAVWithRequestUri("/admin/makeReport.html");
        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo("redirect:/admin/makeReportForm.html");
    }

    @Test
    public void shouldHandleLogoutRequest() throws ServletException, IOException {
        ModelAndView sut = getMAVWithRequestUri("/admin/logout.html");
        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo("redirect:/index.html");
    }

    private ModelAndView getMAVWithRequestUri(String requestUri) throws ServletException, IOException
    {
        return setRelativeLocations(APP_CONTEXT)
                    .setClasses(AdminController.class)
                    .initRequest(requestUri, RequestMethod.POST)
                    .runService()
                    .getModelAndView();
    }

}
