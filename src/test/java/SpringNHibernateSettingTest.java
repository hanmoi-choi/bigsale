import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 2/09/12
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */

@Transactional
public class SpringNHibernateSettingTest {
    @Autowired
    protected SessionFactory factory;


    @Test
    public void shallLoadSessionFactoryBeanFromXml(){
        Session session = factory.getCurrentSession();
    }
}
