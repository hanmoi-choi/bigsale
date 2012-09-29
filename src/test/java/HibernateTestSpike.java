import org.hibernate.Session;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 2/09/12
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */

public class HibernateTestSpike extends AbstractHibernateTest{

    @Test
    public void shallLoadSessionFactoryBeanFromXml(){
        Session session = factory.getCurrentSession();
        assertThat(session,is(instanceOf(Session.class)));
    }
}
