import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

/*
 * This code is from Toby Spring 3 written by Ilmin Toby Lee
 * http://www.acornpub.co.kr/book/toby-spring3
 */
public interface AfterRunService {
	String getContentAsString() throws UnsupportedEncodingException;
	
	WebApplicationContext getContext();

	<T> T getBean(Class<T> beanType);

	ModelAndView getModelAndView();

	AfterRunService assertViewName(String viewname);

	AfterRunService assertModel(String name, Object value);
}