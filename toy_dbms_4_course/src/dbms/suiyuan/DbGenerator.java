package dbms.suiyuan;

import dbms.baseline.BlockID;
import dbms.baseline.File;
import dbms.baseline.Page;

/**
 * @author suiyuan
 * @description: 该类建立数据库目录及默认名称的日志文件
 */
public class DbGenerator extends SQLConstant {
    /**
     * 指定磁盘块映射大小BLOCK_SIZE=512
     */
    public static int BLOCK_SIZE = 512;
    /**
     * 指定缓冲区的数目BUFFER_NUM = 6,本包未用
     */
    public static int BUFFER_NUM = 6;
    /**
     * 待指定的数据库文件目录名称，默认是项目目录下
     */
    public static String DB_DIR;
    /**
     * 待指定的日志文件名称，默认是"testDB_logfile.log"
     */
    public static String LOG_NAME = "DB_logfile.log";
    /**
     * 文件类对象
     */
    private File fl;

    /**
     * 该构造方法主要为了调试方便而写
     */
    public DbGenerator(String dirname, int blocksize) {
        DB_DIR = dirname;//数据库文件目录
        fl = new File(DB_DIR, blocksize);//建立数据库目录
    }

    /**
     * 得到文件对象
     */
    public File gFile() {
        return fl;
    }

    /**
     * 创建日志文件testDB_logfile.log
     */
    private void createLog(String dirfn) {
        int spos = 0;//写入的起始位置
        String fn = "testDB_datafile.data";
        String s = DB_DIR + "/" + fn;//目录+数据文件名称
        int npos = spos + Page.maxLength(s.length());//s的结束位置
        byte[] b = new byte[npos + 2 * Integer.BYTES];//记录的总长度，4字节是整数长度
        //字节数组b，是内存页的实际存储空间
        Page p = new Page(b);//按照记录长度（即，字节数组）建立内存页
        BlockID blk = new BlockID(LOG_NAME, 0);//建立块
        p.setString(spos, s);//把该条记录的字符串按位置spos写入内存页
        fl.write(blk, p);
        s = " " + blk.getBlknum();
        p.setString(npos + Integer.BYTES - 2, s);
        fl.write(blk, p);
    }
}
