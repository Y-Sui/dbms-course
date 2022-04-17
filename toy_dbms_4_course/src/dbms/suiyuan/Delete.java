package dbms.suiyuan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 实现 delete SQL语句实现
 * @author suiyuan
 */
public class Delete {
    public static void deleteSql(String sql) throws Exception {
        int indexSet = sql.indexOf("set");
        int indexWhere = sql.indexOf("where");
        int indexEqual = sql.indexOf("=");
        //通过判断(位置进行定位
        String value = sql.substring(indexEqual + 1, sql.length() - 1).trim();
        String tableName = sql.substring(12, indexWhere - 1).trim();

        //得到当前路径
        String path = SQLConstant.getNowPath();
        System.out.println(path);
        String nowPath = path + "\\" + tableName + "_info.txt";
        String nowDataPath = path + "\\" + tableName + ".txt";
        File in = new File(nowDataPath);
        String line = "";
        List<String> temp = new ArrayList<String>();
        BufferedReader inDelete = new BufferedReader(new FileReader(in));
        while ((line = inDelete.readLine()) != null) {
            if (!line.contains(value))
                temp.add(line);
        }
        inDelete.close();
        File out = new File(nowDataPath);
        BufferedWriter outDelete = new BufferedWriter(new FileWriter(out));
        for (String i : temp) {
            outDelete.write(i + "\r\n");
        }
        System.out.println("成功删除数据!");
        outDelete.flush();
        outDelete.close();
        Input.get();
    }
}
