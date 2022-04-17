package dbms.suiyuan;

/**
 * @author suiyuan
 * @description: 存储数据库常量
 */
public class SQLConstant {

    //数据库的根路径
    private static final String path = "D:\\workSpace\\toy_dbms_4_course\\database";
    //自定义的分隔符
    private static final String separateVerb = " ";
    //数据库的当前路径
    private static String nowPath = path + "\\" + "testDb";

    public static String getPath() {
        return path;
    }

    public static String getNowPath() {
        return nowPath;
    }

    public static void setNowPath(String name) {
        nowPath = path + "\\" + name;
    }

    public static String getSeparate() {
        return separateVerb;
    }

}
