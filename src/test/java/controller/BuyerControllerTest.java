package controller;

import com.bigsale.controller.buyer.BuyerController;
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
 * Date: 6/10/12
 * Time: 7:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuyerControllerTest extends AbstractDispatcherServletTest {

    public static final String APP_CONTEXT = "../testconf/web-application-context.xml";

    @Test
    public void shouldHandleInitialRequest() throws IOException, ServletException {
        ModelAndView sut = getMAVWithRequestUri("/buyer/welcome.html");

        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo("/buyer/index");

    }

    @Test
    public void shouldHandleCheckOrderRequest() throws IOException, ServletException {
        ModelAndView sut = getMAVWithRequestUri("/buyer/checkOrder.html");

        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo("redirect:/buyer/checkOrderForm.html");

    }

    @Test
    public void shouldHandleLogoutRequest() throws IOException, ServletException {
        ModelAndView sut = getMAVWithRequestUri("/buyer/logout.html");

        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo("redirect:/j_spring_security_logout");

    }

    private ModelAndView getMAVWithRequestUri(String requestUri) throws ServletException, IOException
    {
        return setRelativeLocations(APP_CONTEXT)
                .setClasses(BuyerController.class)
                .initRequest(requestUri, RequestMethod.POST)
                .runService()
                .getModelAndView();
    }
}
