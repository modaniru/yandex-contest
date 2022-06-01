package com.modaniru.platform.list;

import java.util.*;

public class FourthExercise {
    public static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int HEIGHT = scanner.nextInt();
        int WIDTH = scanner.nextInt();
        String[] field = new String[HEIGHT];
        StringBuilder[] sbField = new StringBuilder[HEIGHT];
        for (int i = 0; i < HEIGHT; i++) {
            String row = scanner.next();
            field[i] = row;
            sbField[i] = new StringBuilder(row);
        }
        Node node = null;
        for (int i = 0; i < HEIGHT; i++) {
            if (field[i].contains("S")) {
                for (int j = 0; j < WIDTH; j++) {
                    if (field[i].charAt(j) == 'S') {
                        node = new Node(j, i);
                    }
                }
            }
        }
        ArrayDeque<Node> nodes = new ArrayDeque<Node>();
        nodes.add(node);
        while (!nodes.isEmpty()) {
            Node sampleNode = nodes.pop();
            if (sbField[sampleNode.y+1].charAt(sampleNode.x)=='.'){
                nodes.push(new Node(sampleNode.x, sampleNode.y+1));
                sbField[sampleNode.y+1].replace(sampleNode.x,sampleNode.x+1,"D");
            }
            if (sbField[sampleNode.y-1].charAt(sampleNode.x)=='.'){
                nodes.push(new Node(sampleNode.x, sampleNode.y-1));
                sbField[sampleNode.y-1].replace(sampleNode.x,sampleNode.x+1,"U");
            }
            if (sbField[sampleNode.y].charAt(sampleNode.x+1)=='.'){
                nodes.push(new Node(sampleNode.x+1, sampleNode.y));
                sbField[sampleNode.y].replace(sampleNode.x+1,sampleNode.x+2,"R");
            }
            if (sbField[sampleNode.y].charAt(sampleNode.x-1)=='.'){
                nodes.push(new Node(sampleNode.x-1, sampleNode.y));
                sbField[sampleNode.y].replace(sampleNode.x-1,sampleNode.x,"L");
            }
        }
        for(StringBuilder stringBuilder: sbField){
            System.out.println(stringBuilder);
        }
    }
}
