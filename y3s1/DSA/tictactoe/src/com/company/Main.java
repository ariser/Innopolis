package com.company;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class Main {

    private static Boolean[][] map;
    private static Map<who, Boolean> _who = new EnumMap<who, Boolean>(who.class) {{
        put(who.i, true);
        put(who.enemy, false);
        put(who.empty, null);
    }};

    public static void main(String[] args) {
        DangerCell cellForMe;
        do {
            cellForMe = getDangerCell(who.i);
        } while (map[cellForMe.x][cellForMe.y] != null && cellForMe != null);
        if (cellForMe != null) {
            mark(cellForMe);
        }

        DangerCell cellForEnemy;
        do {
            cellForEnemy = getDangerCell(who.enemy);
        } while (map[cellForEnemy.x][cellForEnemy.y] != null && cellForEnemy!= null);
        if (cellForEnemy != null) {
            mark(cellForEnemy);
        }

        Random random = new Random();
        DangerCell randomCell;
        do {
            randomCell = new DangerCell(random.nextInt(3), random.nextInt(3), who.empty);
        } while (map[randomCell.x][randomCell.y] != null);
        mark(randomCell);

        if (isThreeInLine()) {
            end();
        } else {
            startAgain();
        }
    }

    private static DangerCell getDangerCell(who player) {
        Boolean testvalue = _who.get(player);
        // horizontals
        for (int x = 0; x < 3; i++) {
            if (map[x][0] == testvalue && map[x][1] == testvalue) {
                return new DangerCell(x, 2, player);
            }
            if (map[x][1] == testvalue && map[x][2] == testvalue) {
                return new DangerCell(x, 0, player);
            }
            if (map[x][0] == testvalue && map[x][2] == testvalue) {
                return new DangerCell(x, 1, player);
            }
        }
        // verticals
        for (int y = 0; y < 3; i++) {
            if (map[0][y] == testvalue && map[1][y] == testvalue) {
                return new DangerCell(2, y, player);
            }
            if (map[1][y] == testvalue && map[2][y] == testvalue) {
                return new DangerCell(0, y, player);
            }
            if (map[0][y] == testvalue && map[2][y] == testvalue) {
                return new DangerCell(1, y, player);
            }
        }
        // diagonals
        for (int i = 0; i < 2; i++) {
            if (map[0][0] == testvalue && map[1][1] == testvalue) {
                return new DangerCell(2, 2, player);
            }
            if (map[1][1] == testvalue && map[2][2] == testvalue) {
                return new DangerCell(0, 0, player);
            }
            if (map[0][0] == testvalue && map[2][2] == testvalue) {
                return new DangerCell(1, 1, player);
            }
        }
        return null;
    }


    private static void mark(DangerCell cell) {
        map[cell.x][cell.y] = true;
    }

    private enum who {i, enemy, empty}

    private static class DangerCell {
        public int x;
        public int y;
        public who player;

        public DangerCell(int x, int y, who player) {
            this.x = x;
            this.y = y;
            this.player = player;
        }
    }


}
