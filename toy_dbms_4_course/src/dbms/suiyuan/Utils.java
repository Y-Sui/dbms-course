package dbms.suiyuan;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * @author suiyuan
 * @description: 数据库工具箱utils
 */
public class Utils {

    /**
     * @param path
     * @return
     * @description: 获取所有数据库名称, 查看路径下所有文件夹
     */
    public static List<String> getAllDatabase(String path) {
        List<String> list = new ArrayList<>();
        File file = new File(path);
        File[] fileList = file.listFiles();
        for (int i = 0; i < Objects.requireNonNull(fileList).length; i++) {
            if (fileList[i].isDirectory()) {
                list.add(fileList[i].getName());
            }
        }
        return list;
    }

    /**
     * @param nowPath
     * @return
     * @description: 获取当前数据库下的所有表
     */
    public static List<String> getAllTables(String nowPath) {
        List<String> list = new ArrayList<>();
        File file = new File(nowPath);
        File[] fileList = file.listFiles();
        if (fileList != null) {
            for (File value : fileList) {
                if (value.isFile()) {
                    String name = value.getName();
                    int index = name.lastIndexOf(".");
                    String tableName = name.substring(0, index);
                    if(!name.contains("_info"))
                        list.add(tableName);
                }
            }
        }
        return list;
    }
}
