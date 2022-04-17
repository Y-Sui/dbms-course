//package dbms.suiyuan;
//
//import java.io.*;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import static dbms.suiyuan.Select.getTableDescribe;
//
//public class test {
//    public static void main(String[] args) throws IOException {
//        String sql = "create table one (personID int notNull, FirstName varchar(10) notNull)";
//        //定义存储数组;
//        ArrayList colName = new ArrayList();
//        ArrayList type = new ArrayList();
//        ArrayList restriction = new ArrayList();
//        Pattern pattern = Pattern.compile("\\(.*\\)");
//        Matcher matcher = pattern.matcher(sql);
//        File writename = new File("test.txt");
//        writename.createNewFile(); // 创建新文件
//        BufferedWriter ss = new BufferedWriter(new FileWriter(writename));
//        if (matcher.find()) {
//            System.out.println(matcher.group());
//            String s = matcher.group().replaceAll("\\(|\\)", "");
//            String[] out = s.split(",");
//            for (String i:out){
//                String[] temp = i.trim().split(" ");
//                System.out.println(i);
//                for(String j:temp){
//                    System.out.println(j);
//                }
//                colName.add(temp[0]);
//                type.add(temp[1]);
//                restriction.add(temp[2]);
//            }
//            System.out.println(colName.toString().replaceAll("\\[|\\]|,",""));
//
//            ss.close();
//        }
//
//    }
//// 获取insert命令中两个括号中的内容,使用content进行保存
//        String sql = "insert into test (name, url, alexa, country) VALUES ('百度','https://www.baidu.com/','4','CN');";
//        List<String> content = new ArrayList<>();
//        Pattern pattern = Pattern.compile("\\(.*?\\)");
//        Matcher matcher = pattern.matcher(sql);
//        while (matcher.find()) {
//            content.add(matcher.group());
//        }
//        System.out.println(content);
//        String s1 = content.get(0).substring(1, content.get(0).length() - 1).trim(); //第一个括号,字段
//        String s2 = content.get(1).substring(1, content.get(1).length() - 1).trim(); //第二个括号,值
//        String [] ss1 = s1.split(",");
//        String [] ss2 = s2.split(",");
//        File writename = new File("test.txt");
//        writename.createNewFile(); // 创建新文件
//        BufferedWriter ss = new BufferedWriter(new FileWriter(writename));
//        for (String out:ss2){
//            System.out.println(out);
//            out = out.replaceAll("\\'|\\'","")+" ";
//            ss.write(out);
//            ss.flush();
//        }
//    }
//        String path = "test_info.txt";
//        List<String> list = new ArrayList<>();
//
//        //对文件进行读取
//        try {
//            File file = new File(path);
//            FileReader reader = new FileReader(file);
//            BufferedReader bufferedReader = new BufferedReader(reader);
//            String s = "";
//            int index = 1;
//            while ((s = bufferedReader.readLine()) != null && index < 4) {
//                index++;
//                list.add(s);
//            }
//            System.out.println(list);
//            System.out.println(list.get(0));
//            String s0 = list.get(0);
//
//            String [] kl = s0.split(" ");
//
//            bufferedReader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//        //当前表的完整路径
//        //String sql = "select * from test;";
//        String sql = "select name, age from test;";
//        //String sql = "select age from test;";
//        String path = "database/test";
//        int index = sql.indexOf("from");
//        String colName = sql.substring(7,index).trim();
//        String [] colNameList = colName.split(",");
//        String tableName = sql.substring(index + 5, sql.length() - 1);
//        String nowPath = path + "\\" + tableName + "_info.txt";
//        String nowDataPath = path + "\\" + tableName + ".txt";
//        if (sql.contains("*")){
//            List<String> list = getTableDescribe(nowDataPath);
//            List<List<String>> lists = new ArrayList<>();
//            int i = 0;
//            for (String s : list) {
//                String[] strings = list.get(i++).trim().split(" ");
//                List<String> list1 = new ArrayList<>(Arrays.asList(strings));
//                lists.add(list1);
//            }
//            List<String> columnName = Insert.getColumn(nowPath, 1); //获得列名
//            System.out.println(TableGenerator.generateTable(columnName, lists));
//        }
//        else if(colNameList.length==1 && colNameList[0].equals("name")){
//            List<String> list = getTableDescribe(nowDataPath);
//            List<List<String>> lists = new ArrayList<>();
//            List<String> columnName = Insert.getColumn(nowPath, 1, colNameList); //获得列名
//            int i = 0;
//            for (String s : list) {
//                String[] strings = list.get(i++).trim().split(" ");
//                List<String> list1 = new ArrayList<>();
//                list1.add(strings[0]);
//                lists.add(list1);
//            }
//            System.out.println(TableGenerator.generateTable(columnName, lists));
//        }
//        else if(colNameList.length==1 && colNameList[0].equals("age")){
//            List<String> list = getTableDescribe(nowDataPath);
//            List<List<String>> lists = new ArrayList<>();
//            List<String> columnName = Insert.getColumn(nowPath, 1, colNameList); //获得列名
//            int i = 0;
//            for (String s : list) {
//                String[] strings = list.get(i++).trim().split(" ");
//                List<String> list1 = new ArrayList<>();
//                list1.add(strings[1]);
//                lists.add(list1);
//            }
//            System.out.println(lists);
//            System.out.println(columnName);
//            System.out.println(TableGenerator.generateTable(columnName, lists));
//        }
//        else{
//            List<String> list = getTableDescribe(nowDataPath);
//            List<List<String>> lists = new ArrayList<>();
//            List<String> columnName = Insert.getColumn(nowPath, 1, colNameList); //获得列名
//            int i = 0;
//            for (String s : list) {
//                String[] strings = list.get(i++).trim().split(" ");
//                List<String> list1 = new ArrayList<>(Arrays.asList(strings));
//                lists.add(list1);
//            }
//            System.out.println(TableGenerator.generateTable(columnName, lists));
//        }
//    }
//}
