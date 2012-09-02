package com.bigsale.spikes;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 30/08/12
 * Time: 9:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServletTest {

    @Test
    public void shallReturnResponseWithExecutionResult() throws Exception{
       SimpleGetServlet servlet = new SimpleGetServlet();
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/hello");
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("name", "Spring");

        servlet.service(request, response);

        assertThat(response.getContentAsString(), is(equalTo("<HTML><BODY>Hello Spring</BODY></HTML>")));

    }
}
