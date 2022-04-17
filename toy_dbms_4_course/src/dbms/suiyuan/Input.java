package dbms.suiyuan;

import java.util.Scanner;

/**
 * @author suiyuan
 * @description: 输入SQL语句, 并进行格式化操作;
 */
public class Input {

    public static void get() throws Exception {
        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder();
        do {
            System.out.print(">>");
            input.append(" ").append(scanner.nextLine());
            input = new StringBuilder(input.toString().replaceFirst("(\\s+)$", ""));//解决;后有多个空格不能结束的问题
        } while (!input.toString().endsWith(";"));
        String sql = input.toString().trim();//删掉左右空格
        try {
            sql.replaceAll("\\s{2,}", " "); //通过正则表达式将多个空格转换为一个空格
            sql.replaceFirst("( ;)$", ";"); //去掉;之前的多余空格
        } catch (Exception e) {
            e.printStackTrace();
        }
        SqlAnalysis.analysis(sql);
    }
}
