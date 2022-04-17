package filetest;


public class CreateDB {
	public static void main(String[] args) {
/**TestDB对象，设定了目录名称iDB
*文件大小512字节，
*/
	   TestDB db = new TestDB("iDB", 512);//创建数据库对象,建立iDB文件夹
	   System.out.println("数据库创建成功！");	   
	   
	}

}