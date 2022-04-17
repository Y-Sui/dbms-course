package dbms.suiyuan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author suiyuan
 * @description: SQL语句解析器, 实现对用户键入的SQL语句的解析处理
 */
public class SqlAnalysis {

    /**
     * SQL语句
     */
    private static final String create = "create";
    private static final String help = "help";
    private static final String show = "show";
    private static final String use = "use";
    private static final String quit = "quit";
    private static final String describe = "describe";
    private static final String insert = "insert";
    private static final String select = "select";
    private static final String drop = "drop";
    private static final String update = "update";
    private static final String delete = "delete";

    /**
     * @param sql
     * @description: SQL语句解析器, 采用正则表达式进行匹配, 并根据第一个单词判断该语句的作用.
     */
    public static void analysis(String sql) throws Exception {
        String start = "";
        //正则表达式的匹配规则
        String regex = "^[a-z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sql);
        //获取匹配值
        while (matcher.find()) {
            try {
                start = matcher.group();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //根据第一个单词判断该语句的作用
        switch (start) {
            case create:
                Create.createSql(sql);
                break;
            case help:
                Help.help();
                break;
            case show:
                Show.showSql(sql);
                break;
            case use:
                Use.useSql(sql);
                break;
            case quit:
                Quit.quitSql();
                break;
            case describe:
                Describe.describeSql(sql);
                break;
            case insert:
                Insert.insertSql(sql);
                break;
            case select:
                Select.selectSql(sql);
                break;
            case drop:
                Drop.dropSql(sql);
                break;
            case update:
                Update.updateSql(sql);
                break;
            case delete:
                Delete.deleteSql(sql);
                break;
            default:
                System.out.println("无法识别输入的命令,请键入help;");
                Input.get();
                break;
        }
        //System.out.println(start);
    }
}
