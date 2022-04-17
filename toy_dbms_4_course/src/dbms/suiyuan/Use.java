package dbms.suiyuan;

import java.util.List;

/**
 * @author suiyuan
 * @description: 实现use SQL语句功能实现
 */
public class Use {

    public static void useSql(String sql) throws Exception {
        String dbName = sql.substring(4, sql.length() - 1);
        String path = SQLConstant.getPath();

        List<String> dbList = Utils.getAllDatabase(path);

        //判断使用的数据库是否存在
        boolean b = dbList.contains(dbName);
        if (b) {
            SQLConstant.setNowPath(dbName);
            System.out.println("数据库改变!");
            Input.get();
        } else {
            System.out.println("错误,数据库不存在!");
            Input.get();
        }
    }
}
