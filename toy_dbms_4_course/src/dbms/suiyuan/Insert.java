package dbms.suiyuan;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author suiyuan
 * @description: 实现insert SQL语句功能实现
 */
public class Insert {

    private static final String path = SQLConstant.getNowPath();
    private static String tableName;

    public static void insertSql(String sql) throws Exception {
        //正则表达式判断(个数
        Pattern pattern1 = Pattern.compile("\\(");
        Matcher matcher1 = pattern1.matcher(sql);
        int count = 0;
        while (matcher1.find()) {
            count++;
        }
        if (count == 2) {
            int index = sql.indexOf("(");
            //通过判断(位置进行定位
            String name = sql.substring(12, index);
            tableName = name.trim();
            //判断是否有该表
            File file = new File(path + "\\" + tableName + "_info.txt");
            if (file.exists()) {
                //得到当前路径
                String nowPath = path + "\\" + tableName + ".txt";

                //获取insert命令中两个括号中的内容,使用content进行保存
                List<String> content = new ArrayList<>();
                Pattern pattern2 = Pattern.compile("\\(.*?\\)");
                Matcher matcher2 = pattern2.matcher(sql);
                while (matcher2.find()) {
                    content.add(matcher2.group());
                }
                String contentCol = content.get(0).substring(1, content.get(0).length() - 1).trim();
                String contentVal = content.get(1).substring(1, content.get(1).length() - 1).trim();
                String[] col = contentCol.split(",");
                String[] val = contentVal.split(",");
                File out = new File(nowPath);
                out.createNewFile(); // 创建新文件
                BufferedWriter insertOut = new BufferedWriter(new FileWriter(out, true)); //不覆盖式写入;
                for (String i : val) {
                    i = i.replaceAll("\\'|\\'|\"\"", "");
                    insertOut.write(i);
                }
                insertOut.write("\r\n");
                insertOut.flush();
                insertOut.close();
                System.out.println("成功插入数据!");
            } else {
                System.out.println("该表并未创建,请先创建该表!");
            }
            Input.get();
        } else if (count == 1) {
            int index = sql.indexOf("values") - 1;
            //通过判断(位置进行定位
            String name = sql.substring(12, index);
            tableName = name.trim();
            //判断是否有该表
            File file = new File(path + "\\" + tableName + "_info.txt");
            if (file.exists()) {
                //得到当前路径
                String nowPath = path + "\\" + tableName + ".txt";

                //获取insert命令中括号中的内容,使用content进行保存
                List<String> content = new ArrayList<>();
                Pattern pattern2 = Pattern.compile("\\(.*?\\)");
                Matcher matcher2 = pattern2.matcher(sql);
                while (matcher2.find()) {
                    content.add(matcher2.group());
                }
                String contentVal = content.get(0).substring(1, content.get(0).length() - 1).trim();
                String[] val = contentVal.split(",");
                File out = new File(nowPath);
                out.createNewFile(); // 创建新文件
                BufferedWriter insertOut = new BufferedWriter(new FileWriter(out, true)); //不覆盖式写入;
                for (String i : val) {
                    i = i.replaceAll("\\'|\\'|\"\"", "") + " ";
                    insertOut.write(i);
                }
                insertOut.write("\r\n");
                insertOut.flush();
                insertOut.close();
                System.out.println("成功插入数据!");
            } else {
                System.out.println("该表并未创建,请先创建该表!");
            }
            Input.get();
        } else {
            System.out.println("输入格式有错误,请重新输入!");
            Input.get();
        }
    }

    /**
     * @param path
     * @param i    行数(存储结构为行存储)
     * @return
     * @description: 获取该表的所有列名 (列名, 类型, 约束条件)
     */
    public static List<String> getColumn(String path, int i) {
        List<String> list = new ArrayList<>();

        //对文件进行读取
        try {
            File file = new File(path);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String s = "";

            int index = 0;

            //读取第i行
            while ((s = bufferedReader.readLine()) != null) {
                index++;
                if (index == i) {
                    String sep = SQLConstant.getSeparate(); //获取分隔符
                    String[] strings = s.split(sep);
                    Collections.addAll(list, strings);
                    return list;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getColumn(String path, int i, String [] tag){
        List<String> list = new ArrayList<>();

        //对文件进行读取
        try {
            File file = new File(path);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String s = "";

            int index = 0;

            //读取第i行
            while ((s = bufferedReader.readLine()) != null) {
                index++;
                if (index == i) {
                    String sep = SQLConstant.getSeparate(); //获取分隔符
                    String[] strings = s.split(sep);
                    for(String tagChar :strings){
                        if(tagChar.equals(tag[0].trim())){
                            list.add(tagChar);
                        }
                        if(tag.length>1){
                            if(tagChar.equals(tag[1].trim())){
                                list.add(tagChar);
                            }
                        }
                    }
                    return list;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param s
     * @description: 将字符串写入数据库文件中
     */
    private static void writeFile(String s) {
        //获取分隔符
        String sep = SQLConstant.getSeparate();
        //获取当前表的路径
        String path = SQLConstant.getNowPath();
        String nowPath = path + "\\" + tableName + ".txt";
        try {
            FileOutputStream fos = new FileOutputStream(
                    new File(nowPath), true);
            s += "\r\n";
            fos.write(s.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param type
     * @param str
     * @description: 检查输入值的类型与之前定义字段的类型是否匹配
     */
    private static String check(String type, String str) {
        boolean flag = type.contains("char");
        if (flag) {
            Pattern pattern = Pattern.compile("\".*\"");
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                return str.replaceAll("\"", "");
            } else {
                System.out.println("输入值的格式错误!");
                return null;
            }
        } else
            return null;
    }
}