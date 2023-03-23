import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow implements Runnable{

    JFrame frame; // поле на котором будем рисовать
    Box[][] boxes;
    @Override
    public void run() {
        initFrame();
        initBox();
        initTimer();
    }

    private void initFrame() {
        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setSize(Config.SIZE * Config.WIDTH, Config.SIZE * Config.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ЧТОБЫ програма закрывалась на x
        frame.setLocationRelativeTo(null);  // окно по центру экрана
        frame.setVisible(true);
        frame.setTitle("GameOfLife");
    }

    void initBox() {
        boxes = new Box[Config.WIDTH][Config.HEIGHT];

        for (int i = 0; i < Config.WIDTH; i++) {
            for (int j = 0; j < Config.HEIGHT; j++) {
                boxes[i][j] = new Box(i, j);
                frame.add(boxes[i][j]);
            }
        }
        for (int i = 0; i < Config.WIDTH; i++) {
            for (int j = 0; j < Config.HEIGHT; j++) {
                for (int sx = -1; sx <= +1; sx++) {
                    for (int sy = -1; sy <= +1; sy++) {
                        if (!(sx == 0 && sy == 0)) {
                            boxes[i][j].cell.addNear(boxes
                                    [(i + sx + Config.WIDTH) % Config.WIDTH]
                                    [(j + sy + Config.HEIGHT) % Config.HEIGHT].cell);
                        }
                    }
                }
            }
        }
        for (int x = 10; x < 15; x++) {
            boxes[x][10].cell.status = Status.LIVE;
            boxes[x][10].setColor();
        }
    }
    private void initTimer() {
       TimerListener t1 = new TimerListener();
       Timer timer = new Timer(Config.SLEEPMS, t1);
       timer.start();
    }

    private class TimerListener implements ActionListener {
        boolean flop = false;
        @Override
        public void actionPerformed(ActionEvent e) {
            flop = !flop;
            for (int i = 0; i < Config.WIDTH; i++)
                for (int j = 0; j < Config.HEIGHT; j++) {
                    if (flop) {
                        boxes[i][j].step1();
                    } else {
                        boxes[i][j].step2();
                    }
                }
        }
    }
}
