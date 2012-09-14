
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AbstractRefreshableWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 1/09/12
 * Time: 8:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConfigurableDispatcherServlet extends DispatcherServlet {

    private Class<? extends Object>[] classes;
    private String[] locations;

    private ModelAndView modelAndView;

    public ConfigurableDispatcherServlet(String[] locations) {
        this.locations = locations;
    }

    public ConfigurableDispatcherServlet(Class<? extends Object>[] classes) {
        this.classes = classes;
    }

    public ConfigurableDispatcherServlet() {
    }

    public void setLocation(String... locations) {
        this.locations = locations;
    }

    public void setRelativeLocations(Class clazz, String... relativeLocations) {
        String[] locations = new String[relativeLocations.length];
        String currentPath = ClassUtils.classPackageAsResourcePath(clazz) + "/";

        for (int i = 0; i < relativeLocations.length; i++) {
            locations[i] = currentPath + relativeLocations[i];
        }
        this.setLocation(locations);
    }

    public void setClasses(Class<? extends Object>... classes) {
        this.classes = classes;
    }

    public void service(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        modelAndView = null;
        super.service(request, response);
    }

    protected WebApplicationContext createWebApplicationContext(ApplicationContext parent) {
        AbstractRefreshableWebApplicationContext wac = new AbstractRefreshableWebApplicationContext() {
            @Override
            protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {
                if (locations != null) {
                    XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(beanFactory);
                    System.out.println(locations[0]);
                    xmlReader.loadBeanDefinitions(locations);
                }

                if (classes != null) {
                    AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
                    reader.register(classes);
                }
            }
        };

        wac.setServletContext(getServletContext());
        wac.setServletConfig(getServletConfig());
        wac.refresh();

        return  wac;
    }

    protected void render(ModelAndView mov, HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.modelAndView = mov;
        super.render(mov, request, response);
    }

    public ModelAndView getModelAndView(){
        return this.modelAndView;
    }
}
