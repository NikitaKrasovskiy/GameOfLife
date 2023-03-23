import java.util.ArrayList;

public class Cell {

    ArrayList<Cell> near;
    Status status;

    public Cell() {
       status = Status.NONE;
       near = new ArrayList<>();
    }

    void addNear(Cell cell) { // добавление элементов
        near.add(cell);
    }

    void step1() { // СКОЛЬКО ячейк находиться во круг
        int around = countCells();
        status = status.step1(around);
    }
    void step2() {
        status = status.step2();
    }

    int countCells() { // сколько во круг ячейкиживых существ
        int count = 0;
        for (Cell cell : near)
               if (cell.status.isCell())
                   count++;
        return count;
    }
    void turn() {
        for (Cell cell : near) {
            cell.status = cell.status.isCell() ? Status.NONE : Status.LIVE; // переорачиваем и устанавлевыем новые клетки нажатием мыши по окну
        }
    }
}
