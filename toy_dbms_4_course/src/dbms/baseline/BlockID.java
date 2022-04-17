package dbms.baseline;

/**
 * @description: 该类用于表示对应磁盘块在内存中映射的逻辑块ID
 * @author suiyuan
 */
public class BlockID {
    private String filename;
    private int blocknum;

    /**
     * @description: 构造函数
     * @param filename
     * @param blocknum
     */
    public BlockID(String filename, int blocknum) {
        this.filename = filename;
        this.blocknum = blocknum;
    }

    public String getFilename() {
        return filename;
    }

    public int getBlknum() {
        return blocknum;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setBlknum(int blocknum){
        this.blocknum = blocknum;
    }

    /**
     * @description: 比较块上文件名和块号，即比较BlkID对象内容
     * @param obj
     * @return
     */
    public boolean equals(Object obj) {
        BlockID blk = (BlockID) obj;
        return filename.equals(blk.filename) && blocknum == blk.blocknum;
    }

    /**
     * @description: 返回BlkID对象内容，即块blk上的文件名及块号
     * @return
     */
    public String toString() {
        return "[文件名：" + filename + ", 块号：" + blocknum + "]";
    }

    /**
     * @description: 返回此对象内容的哈希代码
     * @return
     */
    public int hashCode() {
        return toString().hashCode();
    }
}
