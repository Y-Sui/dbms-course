package dbms.baseline;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baseline
 * @description: 该类用于处理目录创建、文件读写、追加块及文件;要占用的块数/号等
 */
public class File {
    /**
     * 指定数据库文件目录名称的对象
     */
    private java.io.File dbdir;
    /**
     * 文件分布在磁盘块的容量 blksize
     */
    private int blksize;
    /**
     * 目录是否已经存在,true代表存在
     */
    private boolean isexist;
    /**
     * 打开状态的文件列表，文件名称String+文件对象RandomAccessFile
     */
    private Map<String, RandomAccessFile> openFiles = new HashMap<>();

    /**
     *  该构造可以在项目根目录下建立数据库目录，并删除已经存在的临时内容,
     *  可以指定数据文件的路径dbdirname和块大小blksize
     */
    public File(String dbdirname, int blksize) {
        this.dbdir = new java.io.File(dbdirname);
        this.blksize = blksize;
        if (!dbdir.exists()) //不存在就创建
            dbdir.mkdirs();//建立目录文件夹
        isexist = dbdir.exists();//得到当前目录存在状态
        for (String filename : dbdir.list())//遍历目录下内容
            if (filename.startsWith("temp"))//若有临时内容
                new java.io.File(dbdir, filename).delete();//删
    }

    /**
     * 得到当前文件对象的目录是否存在，存在即true
     */
    public boolean isExist() {
        return isexist;
    }

    /**
     * 用java自带的RandomAccessFile类实现文件的打开状态并返回该对象
     */
    private RandomAccessFile getFile(String filename) throws IOException {
        RandomAccessFile f = openFiles.get(filename);
        if (f == null) {
            java.io.File dbFile = new java.io.File(dbdir, filename);
            f = new RandomAccessFile(dbFile, "rws");
            openFiles.put(filename, f);
        }
        return f;
    }

    /**
     * 把要读取的内容先通过逻辑块blk装入内存页p，再进行读取
     */
    public synchronized void read(BlockID blk, Page p) {
        try {
            RandomAccessFile f = getFile(blk.getFilename());
            f.seek((long) blk.getBlknum() * blksize);
            f.getChannel().read(p.contents());
        } catch (IOException e) {
            throw new RuntimeException("不能读取块block(blk)" + blk);
        }
    }

    /**
     * 把要写入的内容先通过逻辑块blk装入内存页p，再进行写入
     */
    public synchronized void write(BlockID blk, Page p) {
        try {
            RandomAccessFile f = getFile(blk.getFilename());
            f.seek((long) blk.getBlknum() * blksize);
            f.getChannel().write(p.contents());
        } catch (IOException e) {
            throw new RuntimeException("不能写入块block(blk)" + blk);
        }
    }

    /**
     * 返回追加逻辑块对象(被字节数组初始化过的)，失败则报异常
     */
    public synchronized BlockID append(String filename) {
        int newblknum = fileblkNum(filename);
        BlockID blk = new BlockID(filename, newblknum);
        byte[] b = new byte[blksize];
        try {
            RandomAccessFile f = getFile(blk.getFilename());
            f.seek((long) blk.getBlknum() * blksize);
            f.write(b);
        } catch (IOException e) {
            throw new RuntimeException("不能追加块blk " + blk);
        }
        return blk;
    }

    /**
     * 得到文件的逻辑块blk号=块数,因为无论建立日志还是数据文件，
     * 每个文件最小都是blksize的1倍，所以返回值一定是正整数
     */
    public int fileblkNum(String filename) {
        try {
            RandomAccessFile f = getFile(filename);
            return (int) (f.length() / blksize);
        } catch (IOException e) {
            throw new RuntimeException("不能访问 " + filename);
        }
    }

    /**
     * 返回逻辑块的大小 blksize
     */
    public int blkSize() {
        return blksize;
    }
}