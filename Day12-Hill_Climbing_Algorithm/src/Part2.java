import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Part2 extends Part1 implements Runnable {

    public Part2(boolean t) {
        super(t);
    }

    @Override
    public void run() {
        breathFirstSearch();
    }

    public void breathFirstSearch() {
        String[] input = this.input.split("\n");
        String[][] map = new String[input.length][input[0].length()];

        int sr = 0;
        int sc = 0;
        int er = 0;
        int ec = 0;

        for (int r = 0; r < input.length; r++) {
            for (int c = 0; c < input[0].length(); c++) {
                String v = String.valueOf(input[r].charAt(c));
                if (v.equals("S")) {
                    v = "a";
                    sr = r;
                    sc = c;
                }
                if (v.equals("E")) {
                    v = "z";
                    er = r;
                    ec = c;
                }
                map[r][c] = v;
            }
        }

        int smallest = 800;
        letter:
        for (int rw = 0; rw < map.length; rw++) {
            for (int cm = 0; cm < map[0].length; cm++) {

                if(!map[rw][cm].equals("a")) continue;


                LinkedList<Integer[]> queue = new LinkedList<>();
                ArrayList<Integer[]> visited = new ArrayList<>();

                queue.add(new Integer[]{0, rw, cm});
                visited.add(new Integer[]{rw, cm});


                int nr = 0;
                int nc = 0;
                while (true) {
                    Integer[] vals = queue.pollFirst();
                    int d = vals[0];
                    int r = vals[1];
                    int c = vals[2];

                    label:
                    for (int[] next : new int[][]{{r + 1, c}, {r - 1, c}, {r, c - 1}, {r, c + 1}}) {
                        nr = next[0];
                        nc = next[1];

                        if (nr < 0 || nc < 0 || nr >= map.length || nc >= map[0].length) {
                            continue;
                        }

                        for (int i = 0; i < visited.size(); i++) {
                            if (visited.get(i)[0].equals(nr) && visited.get(i)[1].equals(nc)) {
                                continue label;
                            }
                        }
                        if ((map[nr][nc].charAt(0)) - (map[r][c].charAt(0)) > 1) {
                            continue;
                        }
                        if (nr == er && nc == ec) {
                            if((d+1) < smallest){ smallest = (d+1);}
                            continue letter;
                        }
                        visited.add(new Integer[]{nr, nc});
                        queue.add(new Integer[]{d + 1, nr, nc});
                    }
                }
            }
        }
        System.out.println(smallest);
    }
}