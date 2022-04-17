package dbms.suiyuan;

/**
 * @author suiyuan
 * @description: 给予用户帮助模块, 方便使用本数据库管理系统.
 */
public class Help {

    /**
     * @description: 列出本数据库管理系统支持的SQL语句, 便于用户使用.
     */
    public static void help() throws Exception {
        System.out.println("---1.create database 数据库名;: 用于创建数据库---");
        System.out.println("---2.show databases;: 列出目前已经创建的数据库---");
        System.out.println("---3.drop database 数据库名;: 删除一个数据库---");
        System.out.println("---4.use 数据库名;: 使用一个数据库---");
        System.out.println("---5.show tables; : 列出当前数据库中存在的所有表---");
        System.out.println("---6.create table(列名 类型 约束[, 列名2 类型2 约束2,......]);---");
        System.out.println("---7.describe 表名; : 打印一个表的详细信息;---");
        System.out.println("---8.insert into 表名(列名[,列名2,.....] values (值1[,值2,....]);---");
        System.out.println("---9.select * from 表名;---");
        System.out.println("---10.drop table 表名;---");
        System.out.println("---11.delete from 表名 where 列名 = 值;---");
        System.out.println("---12.update 表名 set 字段名 = 值 where 字段名 = 值;---");
        System.out.println("---13.help;:给予帮助信息;---");
        System.out.println("---14.quit;:退出;---");
        Input.get();
    }
}
