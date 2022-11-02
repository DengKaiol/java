import org.junit.Assert;
import org.junit.Test;

public class RunTest {
    @Test
    public void add(){
        Run run = new Run();
        System.out.println(run.add(10,10));
        Assert.assertEquals(20,run.add(10,10));
    }
}