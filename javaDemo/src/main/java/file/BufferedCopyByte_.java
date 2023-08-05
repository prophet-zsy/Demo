package file;

import java.io.*;

public class BufferedCopyByte_ {
    public static void main(String[] args) throws IOException {
//        既可以操作二进制文件，当然也可以操作文本文件
        String br = "./1.jpg";
        String bw = "./11.jpg";
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(br));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(bw));
        byte[] content = new byte[1024];
        int readLen = 0;
        while ((readLen = bufferedInputStream.read(content)) != -1) {
            bufferedOutputStream.write(content,0, readLen);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }
}
