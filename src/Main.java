
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Main extends JFrame {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        MainWindow window = new MainWindow();
        javax.swing.SwingUtilities.invokeAndWait(window);
    }
}