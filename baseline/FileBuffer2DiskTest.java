package filetest;

/*利用文件管理器，通过缓冲页和磁盘block进行交互*/
/**感受用文件管理器对数据文件testdb_datafile.data进行读时，数据库系统
 * 的处理过程,显著特点是用内存页Page作为读写中介，块BlkID对象为辅助*/
public class FileBuffer2DiskTest {
	public static void main(String[] args) {
/**TestDB对象，设定了目录名称testDB、默认日志文件testDB_logfile.log、
*文件大小512字节，并创建文件File等对象
*/
	   TestDB db = new TestDB("iDB2", 512);//创建数据库对象及空日志文件
	   System.out.println("数据库创建成功！");
//	   System.out.println("当前testDB文件夹下的内容(只有日志文件)：");
//	   showDir("TestDB");//建立了日志文件
	   
	   File2 fl = db.gFile();//得到文件对象
	   String datafileName ="iDB_datafile.dbf";
	   BlkID blk = new BlkID(datafileName, 0);//创建块对象
	   
	   int pos1 = 10;//操作的位置
	   Page p1 = new Page(fl.blkSize());//依据块大小创建Page对象
	   p1.setString(pos1, "Hello iDatabase!");//在内存页指定位置pos1写入字符串
	   fl.write(blk, p1);//将块对象写入物理块，即数据文件testdb_datafile
	   System.out.println("数据文件 "+datafileName+" 创建成功！");
	   System.out.println("数据文件"+datafileName+ " 写入成功！");
	   
	   Page p2 = new Page(fl.blkSize());//按照物理块大小建立内存页p2
	   fl.read(blk, p2);// 将物理块中的内容读入p2
	   System.out.println(datafileName+"数据文件中的内容为：");
	   System.out.println(p2.getString(pos1));//输出内存页p2中的字符串内容
	   //下面输出偏移位置即读取位置处的字符串内容
	   
	   System.out.println("offset " + pos1 + " 包含 " + p2.getString(pos1));		
//	   System.out.println("当前testDB文件夹下的内容：");
//	   showDir("TestDB");
	}
}