import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestAgent {

    Agent testAgent;

    @Before
    public void init() {
        HashMap<String, String> map = new HashMap<>();
        this.testAgent = new Agent("testName", "testSurname", "testTache", "testpasswd", map);
    }

    @Test
    public void testPathName(){
        assertEquals(testAgent.surname.toLowerCase().charAt(0) + testAgent.name.toLowerCase(), testAgent.getPathNameAgent());
    }

}
