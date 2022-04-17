package dbms.suiyuan;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * @author suiyuan
 * @description: 实现drop SQL语句功能实现
 */
public class Drop {

    private static String sep;     //分隔符
    private static String dbName;  //数据库名称
    private static String tableName;   //表名

    /**
     * @param sql
     * @description: 在该方法中对drop命令进行分类, 并执行特定的子命令
     */
    public static void dropSql(String sql) throws Exception {
        //如果是删除数据库的操作
        if (sql.contains("database")) {
            dbName = sql.substring(14, sql.length() - 1);
            dropDatabase(); //删除该数据库
        } else if (sql.contains("table")) {
            tableName = sql.substring(11, sql.length() - 1);
            dropTable(tableName); //删除该表
        } else {
            System.out.println("错误,无法识别该指令!");
        }

    }

    /**
     * @description: 删除数据库
     */
    private static void dropDatabase() throws Exception {
        //获得数据库根路径
        String path = SQLConstant.getPath();
        //判断是否有该数据库
        List<String> dbList = Utils.getAllDatabase(path);
        boolean a = dbList.contains(dbName);
        if (a) {
            boolean b = confirm();
            if (b) {
                String nowPath = path + "\\" + dbName;
                deleteFile(nowPath);
                System.out.println("成功删除该数据库!");
            } else {
                System.out.println("撤回成功,数据库未被删除!");
            }
        } else {
            System.out.println("错误,该数据库不存在!");
        }

        Input.get();
    }

    /**
     * @description: 删除数据表
     */
    private static void dropTable(String tableName) throws Exception {
        //获得当前路径
        String path = SQLConstant.getNowPath();
        //判断是否有该数据表(需要同时具有table表和tableInfo表)
        File fileInfo = new File(path + "\\" + tableName + "_info.txt");
        File file = new File(path + "\\" + tableName + ".txt");
        boolean flag1 = (file.exists() && fileInfo.exists());
        if (flag1) {
            boolean flag2 = confirm();
            if (flag2) {
                try {
                    file.delete();
                    fileInfo.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("成功删除该表!");
            } else {
                System.out.println("撤回成功,数据库未被删除!");
            }
        } else {
            System.out.println("错误,该数据表不存在!");
        }
        Input.get();
    }

    /**
     * @param delpath
     * @throws Exception
     * @description: 级联删除文件夹delpath下内容
     */
    public static void deleteFile(String delpath) throws Exception {
        File file = new File(delpath);
        // 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
        if (!file.isDirectory() && file.delete()) {
            System.out.println(file.getAbsoluteFile() + "删除文件成功");
        } else if (file.isDirectory()) {
            String[] filelist = file.list();
            for (String s : filelist) {
                File delfile = new File(delpath + "\\" + s);
                if (!delfile.isDirectory() && delfile.delete()) {
                    System.out.println(delfile.getAbsolutePath() + "删除文件成功");
                } else if (delfile.isDirectory()) {
                    deleteFile(delpath + "/" + s);
                }
            }
            if (file.delete()) {
                System.out.println(file.getAbsolutePath() + "删除成功");
            }
        }
    }

    /**
     * @return
     * @description: 确认是否删除数据库;
     */
    private static boolean confirm() {
        System.out.println("确认删除: \"Yes\" or \"No\"");
        System.out.print("请输入: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();
        if ("yes".equals(input)) {
            return true;
        } else if ("no".equals(input)) {
            return false;
        } else {
            System.out.println("无法识别输入单词");
            return false;
        }
    }
}
