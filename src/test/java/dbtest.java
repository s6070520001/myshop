import dbutil.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;

public class dbtest {
    @Test
    public void test(){
        Connection connection= JDBCUtil.getConnect("myshop");
        if(connection!=null){
            System.out.println("資料連結成功");
        }

    }
}
