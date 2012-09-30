package controller;

import com.bigsale.controller.user.AdminController;
import com.bigsale.orm.model.User;
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
    public void shallRedirectToProperFormPage() throws ServletException, IOException {
        ModelAndView sut = setRelativeLocations(APP_CONTEXT)
                .setClasses(AdminController.class)
                .initRequest("/admin/addSellers", RequestMethod.POST)
                .runService()
                .getModelAndView();

        assertThat(sut).isNotNull();
        assertThat(sut.getViewName()).isEqualTo("/admin/addSeller1stForm");
        assertThat(sut.getModel().get("user")).isInstanceOf(User.class);

    }

}
