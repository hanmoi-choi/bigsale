package controller;

import com.bigsale.controller.seller.SellerController;
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
public class SellerControllerTest extends AbstractDispatcherServletTest {

    public static final String APP_CONTEXT = "../testconf/web-application-context.xml";

    @Test
    public void shouldHandleInitialRequest() throws IOException, ServletException {
        ModelAndView sut = getMAVWithRequestUri("/seller/welcome.html");

        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo("/seller/index");

    }

    @Test
    public void shouldHandleAddItemRequest() throws IOException, ServletException {
        ModelAndView sut = getMAVWithRequestUri("/seller/addItem.html");

        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo("redirect:/seller/addItemForm.html");

    }

    @Test
    public void shouldHandleQueryItemRequest() throws IOException, ServletException {
        ModelAndView sut = getMAVWithRequestUri("/seller/queryItem.html");

        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo("redirect:/seller/queryItemForm.html");

    }

    @Test
    public void shouldHandleCheckOrderRequest() throws IOException, ServletException {
        ModelAndView sut = getMAVWithRequestUri("/seller/checkOrder.html");

        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo("redirect:/seller/checkOrderForm.html");

    }

    @Test
    public void shouldHandleLogoutRequest() throws IOException, ServletException {
        ModelAndView sut = getMAVWithRequestUri("/seller/logout.html");

        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo("redirect:/index.html");

    }

    private ModelAndView getMAVWithRequestUri(String requestUri) throws ServletException, IOException
    {
        return setRelativeLocations(APP_CONTEXT)
                .setClasses(SellerController.class)
                .initRequest(requestUri, RequestMethod.POST)
                .runService()
                .getModelAndView();
    }
}
