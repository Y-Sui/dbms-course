package dbms.suiyuan;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suiyuan
 * @description: 实现show SQL语句功能实现
 */
public class Show {

    /**
     * @param sql
     * @description: 列出所有的数据库和列出当前数据库下的数据表
     */
    public static void showSql(String sql) throws Exception {

        //列出所有数据库
        boolean b = sql.endsWith(" databases;");
        if (b) {
            String path = SQLConstant.getPath();
            List<String> dbList = Utils.getAllDatabase(path);
            List<String> db = new ArrayList<>();
            db.add("Database");
            List<List<String>> list = new ArrayList<>();
            for (int i = 0; i < dbList.size(); i++) {
                List<String> ls = new ArrayList<>();
                ls.add(dbList.get(i));
                list.add(ls);
            }
            System.out.println(TableGenerator.generateTable(db, list));
            Input.get();
        }

        //列出所有该数据库下所有的表格
        else {
            boolean c = sql.endsWith("tables;");
            if (c) {
                String nowPath = SQLConstant.getNowPath();
                List<String> tableList = Utils.getAllTables(nowPath);
                List<String> list = new ArrayList<>();

                //获取数据库名
                int index = nowPath.lastIndexOf("\\");
                String dbName = nowPath.substring(index + 1, nowPath.length());
                list.add(dbName);

                List<List<String>> tList = new ArrayList<>();
                for (String t : tableList) {
                    List<String> list1 = new ArrayList<>();
                    list1.add(t);
                    tList.add(list1);
                }

                System.out.println(TableGenerator.generateTable(list, tList));
                Input.get();
            }
        }
    }


}
