import com.bigsale.controller.user.AdminAddUserFormController;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;

public class SimpleMVCTest extends AbstractDispatcherServletTest {

    @Test
    public void shallLoadBeansFromContextFile() throws ServletException, IOException{
        ModelAndView sut = setRelativeLocations("conf/web-application-context.xml")
                .setClasses(AdminAddUserFormController.class)
                .initRequest("/admin/addUser", RequestMethod.POST)
                .addParameter("name", "hanmoi")
                .runService()
                .getModelAndView();

        assertThat(sut).isNotNull();
    }
	
}
