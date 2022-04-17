package dbms.suiyuan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author suiyuan
 * @description: 实现create SQL语句功能实现
 */
public class Create {

    private static final String path = SQLConstant.getPath();

    /**
     * @param sql
     * @description: 创建数据库, 一个文件夹代表一个数据库
     */
    public static void createSql(String sql) throws Exception {
        boolean flag1 = sql.contains("database");
        boolean flag2 = sql.contains("table");
        //判断是否为创建数据库的方法
        if (flag1) {
            String dbName = sql.substring(16, sql.length() - 1);
            //System.out.println(dbName);
            createDB(dbName);
            Input.get();
        }
        //判断是否为创建表格的方法
        else if (flag2) {
            int index = sql.indexOf("(");
            String tableName = sql.substring(13, index).trim();
            createTable(sql, tableName);
            Input.get();
        }
    }

    /**
     * @param dbName 数据库名称(dir名称)
     * @description: 创建数据库(文件夹)的方法
     */
    private static void createDB(String dbName) throws Exception {
        SQLConstant.setNowPath(dbName);
        String path = SQLConstant.getNowPath();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
            System.out.println("数据库成功建立!");
        } else {
            System.out.println("错误,数据库已经存在!");
        }
    }


    /**
     * @param tableName
     * @param sql
     * @description: 创建表格的方法, 使用txt文件存储表
     */
    private static void createTable(String sql, String tableName) throws Exception {
        //获取当前数据库所在路径
        String tablePath = SQLConstant.getNowPath();
        tableName = tableName + "_info.txt";
        //创建表
        File table = new File(tablePath, tableName);
        if (!table.exists()) {
            table.createNewFile(); //创建新文件
            BufferedWriter createOut = new BufferedWriter(new FileWriter(table)); //创建缓冲区;
            //定义存储数组;
            ArrayList colName = new ArrayList();
            ArrayList type = new ArrayList();
            ArrayList restriction = new ArrayList();
            //创建规则
            Pattern pattern = Pattern.compile("\\(.*\\)");
            Matcher matcher = pattern.matcher(sql);
            if (matcher.find()) {
                //获得括号内的内容
                String content = matcher.group().replaceAll("\\(|\\)", "");
                //使用,进行字符串切分
                String[] splitContent = content.split(",");
                for (String i : splitContent) {
                    String[] temp = i.trim().split(" ");
                    colName.add(temp[0]);
                    type.add(temp[1]);
                    restriction.add(temp[2]);
                }
                createOut.write(colName.toString().replaceAll("\\[|\\]|,", "") + "\r\n");
                createOut.write(type.toString().replaceAll("\\[|\\]|,", "") + "\r\n");
                createOut.write(restriction.toString().replaceAll("\\[|\\]|,", "") + "\r\n");
                createOut.flush();// 将缓冲区内容压入文件
                createOut.close();// 写入文件完成后关闭
                System.out.println("数据表成功建立!");
            }
        } else {
            System.out.println("错误,数据表已经存在!");
        }
    }
}