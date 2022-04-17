package filetest;

import java.nio.ByteBuffer;
import java.nio.charset.*;
/**该类为文件读写申请内存空间*/
public class Page {
/**java自带的字节缓冲区类ByteBuffer，用于申请内存空间*/	
   private ByteBuffer bb;
/**java自带的字符集类Charset，设定文件内编码格式，默认ASCII*/  
//   public static Charset CHARSET = StandardCharsets.US_ASCII;//UTF_8 ;
   
   public static Charset CHARSET = StandardCharsets.UTF_8;//UTF_8 ;
/**该构造给数据文件在内存里创建存储空间，按照块大小申请字节缓冲空间*/
   public Page(int blksize) {
      bb = ByteBuffer.allocateDirect(blksize);
   }/**该构造给日志文件在内存里创建存储空间，按字节数组大小申请空间*/
   public Page(byte[] b) {
      bb = ByteBuffer.wrap(b);
   }/**按照指定偏移量offset即位置，返回该处的整型数值*/
   public int getInt(int offset) {
      return bb.getInt(offset);
   }/**按照指定偏移量offset即位置，设置该处的整型数值为 n*/
   public void setInt(int offset, int n) {
      bb.putInt(offset, n);
   }/**按照指定偏移量offset即位置，返回该处值的字节数组形式*/
   public byte[] getBytes(int offset) {
      bb.position(offset);
      int length = bb.getInt();
      byte[] b = new byte[length];
      bb.get(b);
      return b;
   }/**实际写入内存：按照指定偏移量offset即位置，设置该处的值为字节数组b*/
   public void setBytes(int offset, byte[] b) {
      bb.position(offset);
      bb.putInt(b.length);
      bb.put(b);
   }/**按照指定偏移量offset即位置，返回该处的字符串内容*/   
   public String getString(int offset) {
      byte[] b = getBytes(offset);
      return new String(b, CHARSET);
   }/**按照指定偏移量offset即位置，设置该处的字符串为 s*/
   public void setString(int offset, String s) {
      byte[] b = s.getBytes(CHARSET);
      setBytes(offset, b);
   }/**按照用户给定字符串长度strlen，返回需要的字节数+4*/
   public static int maxLength(int strlen) {
      float bytesPerChar = CHARSET.newEncoder().maxBytesPerChar();
      return Integer.BYTES + (strlen * (int)bytesPerChar);
   }/**从内存页起始位置，返回字节缓冲区对象，File类需要*/
   ByteBuffer contents() {
      bb.position(0);
      return bb;
   }
}
