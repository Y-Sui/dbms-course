package filetest;
/**该类用于表示对应磁盘块在内存中映射的逻辑块ID*/
public class BlkID {
   private String filename;
   private int blknum;
/**该构造方法仅仅记录下逻辑块号blknum及块上的文件名filename，
 * 不过也与对应的物理磁盘块一样*/
   public BlkID(String filename, int blknum) {
      this.filename = filename;
      this.blknum   = blknum;
   }/**filename是私有属性，用公共方法fileName返回其值*/
   public String fileName() {
      return filename;
   }/**blknum是私有属性，用公共方法blkNum返回其值*/
   public int blkNum() {
      return blknum;
   }/**与其它块比较快上文件名和块号，即比较BlkID对象内容*/ 
   public boolean equals(Object obj) {
	   BlkID blk = (BlkID) obj;
      return filename.equals(blk.filename) && blknum == blk.blknum;
   }/**返回BlkID对象内容，即块blk上的文件名及块号*/   
   public String toString() {
      return "[文件名：" + filename + ", 块号：" + blknum + "]";
   }/**返回此对象内容的哈希代码*/   
   public int hashCode() {
      return toString().hashCode();
   }
}