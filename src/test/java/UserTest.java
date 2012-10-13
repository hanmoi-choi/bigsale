import com.bigsale.orm.model.Level;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: hanmoi
 * Date: 13/10/12
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTest {

    @Test
    public void shallReturnCorrectValue(){
        assertThat(Level.BRONZE.toInt()).isEqualTo(0);
        assertThat(Level.SILVER.toInt()).isEqualTo(1);
        assertThat(Level.GOLD.toInt()).isEqualTo(2);
        assertThat(Level.PLATINUM.toInt()).isEqualTo(3);
    }

    @Test
    public void shallReturnProperNextLevel(){
        assertThat(Level.BRONZE.nextLevel()).isEqualTo(Level.SILVER);
    }
}
