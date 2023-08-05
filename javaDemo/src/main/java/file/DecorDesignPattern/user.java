package file.DecorDesignPattern;

public class user {
    public static void main(String[] args) {
        BufferReaderr bufferReaderr = new BufferReaderr(new FileReaderr(), 10);
        bufferReaderr.read();
        bufferReaderr = new BufferReaderr(new StringReaderr(), 10);
        bufferReaderr.read();
    }
}
