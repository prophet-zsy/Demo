package netSocket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils_ {
    static public byte[] streamToByteArray(InputStream inputStream) throws IOException {
        byte[] buf = new byte[1024];
        int readLen = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while ((readLen = inputStream.read(buf)) != -1) {
            byteArrayOutputStream.write(buf, 0, readLen);
        }
        byte[] res = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return res;
    }
}
