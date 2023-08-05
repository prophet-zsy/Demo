package file.DecorDesignPattern;

public class BufferReaderr implements Readerr{
    private Readerr readerr;
    private int num;
    BufferReaderr(Readerr readerr, int num) {
        this.readerr = readerr;
        this.num = num;
    }

    @Override
    public void read() {
        for (int i = 0; i < num; i ++) {
            this.readerr.read();
        }
    }
}
