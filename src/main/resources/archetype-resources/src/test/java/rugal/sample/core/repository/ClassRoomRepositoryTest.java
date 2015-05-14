package rugal.sample.core.repository;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import rugal.JUnitSpringTestBase;
import rugal.sample.core.entity.ClassRoom;

/**
 *
 * @author Administrator
 */
public class ClassRoomRepositoryTest extends JUnitSpringTestBase
{

    @Autowired
    private ClassRoomRepository repository;

    public ClassRoomRepositoryTest()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testGet()
    {
        System.out.println("get");
        ObjectId id = new ObjectId("555418d50032f55ffbf8ca78");
        ClassRoom room = repository.findOne(id);
        System.out.println(room.getName());

    }

    @Test
    public void testcount()
    {
        System.out.println("count");
        System.out.println(repository.count());
    }
}
