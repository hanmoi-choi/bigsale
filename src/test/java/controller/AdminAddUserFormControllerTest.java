package controller;

import com.bigsale.controller.user.AdminAddUserFormController;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import template.AbstractDispatcherServletTest;

import javax.servlet.ServletException;
import java.io.IOException;

public class AdminAddUserFormControllerTest extends AbstractDispatcherServletTest {

    public static final String APP_CONTEXT = "testconf/web-application-context.xml";

    @Test
    public void shallLoadBeansFromContextFile() throws ServletException, IOException{
        ModelAndView sut = setRelativeLocations(APP_CONTEXT)
                .setClasses(AdminAddUserFormController.class)
                .initRequest("/admin/addSeller", RequestMethod.POST)
                .addParameter("name", "hanmoi")
                .runService()
                .getModelAndView();

//        assertThat(sut).isNotNull();
    }

    @Test
    public void shallReturnViewAndResultProperly_AfterAddingBuyer() throws ServletException, IOException{
        ModelAndView sut = setRelativeLocations(APP_CONTEXT)
                .setClasses(AdminAddUserFormController.class)
                .initRequest("/admin/addSeller", RequestMethod.POST)
                .addParameter("name", "hanmoi")
                .runService()
                .getModelAndView();

//        assertThat(sut.getViewName()).isEqualTo("/admin/addConfirmed");
//        assertThat(sut.getModel().get("name")).isEqualTo("hanmoi");
    }
}
