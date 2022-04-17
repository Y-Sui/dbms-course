package dbms.suiyuan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author suiyuan
 * @description: 实现update SQL语句功能实现
 */
public class Update {
    public static void updateSql(String sql) throws Exception {
        int indexSet = sql.indexOf("set");
        int indexWhere = sql.indexOf("where");
        int indexEqual1 = sql.indexOf("=");
        int indexEuqal2 = sql.indexOf("=", indexWhere);
        //通过判断(位置进行定位
        String value1 = sql.substring(indexEqual1 + 1, indexWhere - 1).trim();
        String value2 = sql.substring(indexEuqal2 + 1, sql.length() - 1).trim();
        String tableName = sql.substring(7, indexSet).trim();

        //得到当前路径
        String path = SQLConstant.getNowPath();
        System.out.println(path);
        String nowDataPath = path + "\\" + tableName + ".txt";
        File in = new File(nowDataPath);
        String line = "";
        List<String> temp = new ArrayList<String>();
        BufferedReader inUpdate = new BufferedReader(new FileReader(in));
        while ((line = inUpdate.readLine()) != null) {
            if (line.contains(value2)) {
                temp.add(value2 + " " + value1);
            } else {
                temp.add(line);
            }
        }
        inUpdate.close();
        File out = new File(nowDataPath);
        BufferedWriter outUpdate = new BufferedWriter(new FileWriter(out));
        for (String i : temp) {
            outUpdate.write(i + "\r\n");
        }
        System.out.println("成功更新数据!");
        outUpdate.flush();
        outUpdate.close();
        Input.get();
    }
}
