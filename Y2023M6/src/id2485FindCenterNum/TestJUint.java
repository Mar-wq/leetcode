package id2485FindCenterNum;

import org.junit.Assert;
import org.junit.Test;

public class TestJUint {
    @Test
    public void testP() {
        Solution a = new Solution();
        int result = a.sumUpToN(2);
        Assert.assertEquals("有问题", 3, result);
    }
}
