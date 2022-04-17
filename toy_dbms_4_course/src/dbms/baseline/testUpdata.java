package dbms.baseline;

import dbms.suiyuan.SQLConstant;

import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testUpdata {
    public static void main(String[] args) throws IOException {
        String sql = "update testTable set age = 20 where name = zhangsan;";
        //String sql = "delete from testTable where name = zhangsan;";
        int indexSet = sql.indexOf("set");
        int indexWhere = sql.indexOf("where");
        int indexEqual1 = sql.indexOf("=");
        int indexEuqal2 = sql.indexOf("=", indexWhere);
        //通过判断(位置进行定位
        String value1 = sql.substring(indexEqual1 + 1, indexWhere - 1).trim();
        String value2 = sql.substring(indexEuqal2 + 1, sql.length() - 1).trim();
        String tableName = sql.substring(7, indexSet).trim();
        System.out.println(tableName);

        //得到当前路径
        String path = SQLConstant.getNowPath();
        System.out.println(path);
        String nowDataPath = path + "\\" + tableName + ".txt";
        System.out.println(nowDataPath);
        java.io.File in = new java.io.File(nowDataPath);
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
        java.io.File out = new File(nowDataPath);
        BufferedWriter outUpdate = new BufferedWriter(new FileWriter(out));
        for (String i : temp) {
            outUpdate.write(i + "\r\n");
        }
        System.out.println("成功更新数据!");
        outUpdate.flush();
        outUpdate.close();
    }
}

