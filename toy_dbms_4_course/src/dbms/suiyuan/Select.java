package dbms.suiyuan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author suiyuan
 * @description: 实现select SQL语句功能实现
 */
public class Select {

    /**
     * @param sql
     * @description: 实现selectSql功能实现.
     */
    public static void selectSql(String sql) throws Exception {

        //获得当前路径,到数据库文件夹
        String path = SQLConstant.getNowPath();
        int index = sql.indexOf("from");
        String colName = sql.substring(7, index).trim();
        String[] colNameList = colName.split(",");
        String tableName = sql.substring(index + 5, sql.length() - 1);
        String nowPath = path + "\\" + tableName + "_info.txt";
        String nowDataPath = path + "\\" + tableName + ".txt";
        if (sql.contains("*")) {
            List<String> list = getTableDescribe(nowDataPath);
            List<List<String>> lists = new ArrayList<>();
            int i = 0;
            for (String s : list) {
                String[] strings = list.get(i++).trim().split(" ");
                List<String> list1 = new ArrayList<>(Arrays.asList(strings));
                lists.add(list1);
            }
            List<String> columnName = Insert.getColumn(nowPath, 1); //获得列名, 1表示读取的行数
            System.out.println(TableGenerator.generateTable(columnName, lists));
            Input.get();
        } else if (colNameList.length == 1 && colNameList[0].equals("name")) {
            List<String> list = getTableDescribe(nowDataPath);
            List<List<String>> lists = new ArrayList<>();
            List<String> columnName = Insert.getColumn(nowPath, 1, colNameList); //获得列名
            int i = 0;
            for (String s : list) {
                String[] strings = list.get(i++).trim().split(" ");
                List<String> list1 = new ArrayList<>();
                list1.add(strings[0]);
                lists.add(list1);
            }
            System.out.println(TableGenerator.generateTable(columnName, lists));
            Input.get();
        } else if (colNameList.length == 1 && colNameList[0].equals("age")) {
            List<String> list = getTableDescribe(nowDataPath);
            List<List<String>> lists = new ArrayList<>();
            List<String> columnName = Insert.getColumn(nowPath, 1, colNameList); //获得列名
            int i = 0;
            for (String s : list) {
                String[] strings = list.get(i++).trim().split(" ");
                List<String> list1 = new ArrayList<>();
                list1.add(strings[1]);
                lists.add(list1);
            }
            System.out.println(TableGenerator.generateTable(columnName, lists));
            Input.get();
        } else {
            List<String> list = getTableDescribe(nowDataPath);
            List<List<String>> lists = new ArrayList<>();
            //List<String> columnName = Insert.getColumn(nowPath, 1, colNameList); //获得列名
            List<String> columnName = new ArrayList<>();
            for (String i:colNameList){
                columnName.add(i);
            }
            int i = 0;
            for (String s : list) {
                String[] strings = list.get(i++).trim().split(" ");
                List<String> list1 = new ArrayList<>(Arrays.asList(strings));
                lists.add(list1);
            }
            System.out.println(TableGenerator.generateTable(columnName, lists));
            Input.get();
        }
    }

    /**
     * @param path
     * @return
     * @description: 读出数据库中表中第3行中的数据, 因为前三行存放的是表的控制信息, 三行之后存放的是表的控制信息
     */
    static List<String> getTableDescribe(String path) {

        List<String> list = new ArrayList<>();

        //对文件进行读取
        try {
            File file = new File(path);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String s = "";
            int index = 1;
            while ((s = bufferedReader.readLine()) != null) {
                index++;
                list.add(s);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
