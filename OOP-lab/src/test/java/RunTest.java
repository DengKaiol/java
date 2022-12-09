import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class RunTest {
    @Test
    public void  deleteDepthWithLinkTest(){
        String str = "[FDUCS](https://cs.fudan.edu.cn/)";
        System.out.println(Run.deleteDepthWithLink(str).get(0));
        System.out.println(Run.deleteDepthWithLink(str).get(1));
    }

    @Test
    public void deleteDepthTest(){
        String str ="##标题3";
        System.out.println(Run.deleteDepth(str));
    }
    @Test
    public void getDepthByStringTest(){
        String str = "#标题3";
        System.out.println(Run.getDepthByString(str));
    }

}