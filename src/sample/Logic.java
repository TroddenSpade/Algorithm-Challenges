package sample;

import java.util.ArrayList;

public class Logic {
    private ArrayList<Tile> topList = new ArrayList<>();
    private ArrayList<Tile> leftList = new ArrayList<>();


    public void divideAndConquer(int startX, int startY, int endX, int endY, int spaceX, int spaceY) {
        if (endX - startX == 1) {
            createBlock(startX, startY, endX, endY, spaceX - startX + 2 * (spaceY - startY));
            return;
        }

        // 1 1 1 1  2 2 2 2 startY
        // 1 1 1 1  2 2 2 2
        // 1 1 1 1  2 2 2 2
        // 1 1 1 1  2 2 2 2 midY
        //    midX  midX+1
        // 3 3 3 3  4 4 4 4 midY+1
        // 3 3 3 3  4 4 4 4
        // 3 3 3 3  4 4 4 4
        // 3 3 3 3  4 4 4 4 endY

        int midX = startX + (endX - startX) / 2;
        int midY = startY + (endX - startX) / 2;

        if (spaceX <= midX && spaceY <= midY) { //1
            divideAndConquer(startX, startY, midX, midY, spaceX, spaceY);
            divideAndConquer(midX + 1, startY, endX, midY, midX + 1, midY);
            divideAndConquer(startX, midY + 1, midX, endY, midX, midY + 1);
            divideAndConquer(midX + 1, midY + 1, endX, endY, midX + 1, midY + 1);
            createBlock(midX, midY, midX + 1, midY + 1, 0);

        } else if (spaceX > midX && spaceY <= midY) { // 2
            divideAndConquer(startX, startY, midX, midY, midX, midY);
            divideAndConquer(midX + 1, startY, endX, midY, spaceX, spaceY);
            divideAndConquer(startX, midY + 1, midX, endY, midX, midY + 1);
            divideAndConquer(midX + 1, midY + 1, endX, endY, midX + 1, midY + 1);
            createBlock(midX, midY, midX + 1, midY + 1, 1);

        } else if (spaceX <= midX && spaceY > midY) { // 3
            divideAndConquer(startX, startY, midX, midY, midX, midY);
            divideAndConquer(midX + 1, startY, endX, midY, midX + 1, midY);
            divideAndConquer(startX, midY + 1, midX, endY, spaceX, spaceY);
            divideAndConquer(midX + 1, midY + 1, endX, endY, midX + 1, midY + 1);
            createBlock(midX, midY, midX + 1, midY + 1, 2);

        } else if (spaceX > midX && spaceY > midY) { // 4
            divideAndConquer(startX, startY, midX, midY, midX, midY);
            divideAndConquer(midX + 1, startY, endX, midY, midX + 1, midY);
            divideAndConquer(startX, midY + 1, midX, endY, midX, midY + 1);
            divideAndConquer(midX + 1, midY + 1, endX, endY, spaceX, spaceY);
            createBlock(midX, midY, midX + 1, midY + 1, 3);
        }
    }

    public void createBlock(int startX, int startY, int endX, int endY, int position) {
        // 0 1
        // 2 3
        switch (position) {
            case 0:
                topList.add(new Tile(endX, endY));//3
                leftList.add(new Tile(endX, endY));//3
                break;
            case 1:
                topList.add(new Tile(startX, endY));//2
                leftList.add(new Tile(endX, endY));//3
                break;
            case 2:
                topList.add(new Tile(endX, endY));//3
                leftList.add(new Tile(endX, startY));//1
                break;
            case 3:
                topList.add(new Tile(startX, endY));//2
                leftList.add(new Tile(endX, startY));//1
                break;
            default:
        }
    }

    public ArrayList<Tile> getLeftList() {
        return leftList;
    }

    public ArrayList<Tile> getTopList() {
        return topList;
    }
}
