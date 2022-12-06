import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Part2 extends Part1 {

    public String run() {
        Part2 aoc = new Part2();
        String inputs = aoc.getInput();

        String[] concept = inputs.split("\n\n"); // Split the input data into the [0] creates+columnNumbers and the [1] instructions
        ArrayList<String> firstGrid = new ArrayList<>();

//        firstGrid = getData(concept[0]);
        ArrayList<Stack<String>> stacks = new ArrayList<>();
        stacks = getData(concept[0]);
        String[][] instructions = getInstructions(concept[1]);
        // getInstructions



//        ArrayList<Stack<String>> stacks = new ArrayList<>(getData(concept[0]));


        return getMessageAfterInstructions(stacks, instructions);
    }

    public ArrayList<Stack<String>> getData(String dataInput){
        String[] eachLineFromTop = dataInput.split("\n");

        ArrayList<Stack<String>> stacks = new ArrayList<>();

        ArrayList<ArrayList<String>> lines = new ArrayList<>();
        for(int i =0 ; i < eachLineFromTop.length - 1 ; i++){
            ArrayList<String> line = new ArrayList<>();
            for (int j = 1; j < eachLineFromTop[i].length(); j += 4) {
                line.add(String.valueOf(eachLineFromTop[i].charAt(j)));
            }
            lines.add(line);
        }
        for (int column = 0; column < lines.get(0).size() ; column++) {
            Stack<String> stack = new Stack<>();
            for (int row = lines.size() - 1; row >= 0; row--) {
                stack.push(lines.get(row).get(column));
            }
            stacks.add(stack);
        }


        for (int i = 0 ; i < stacks.size() ; i++) {
            Stack<String> stack = stacks.get(i);
            if(stack.peek().equals(" ")){
                stack.pop();
                i--;
            }
        }
//        stacks.forEach(System.out::println);

        return stacks;
    }

    public String[][] getInstructions(String input){
        String[] lines = input.split("\n");
        String[][] output = new String[lines.length][3];

        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split(" from ");
            String numberOfMoves = line[0].substring("move ".length());


            output[i][0] = numberOfMoves;
            output[i][1] = line[1].substring(0, line[1].indexOf(' '));
            output[i][2] = String.valueOf(line[1].charAt(line[1].length() - 1));
        }

        for (String[] s : output) {
            System.out.println(Arrays.toString(s));
        }
        return output;
    }
    public String getMessageAfterInstructions(ArrayList<Stack<String>> stacksArrayList, String[][] instructions) {
//        .get(row).get(column);
        stacksArrayList.forEach(System.out::println);


        for (String[] instruction : instructions) {
            int moveAmount = Integer.parseInt(instruction[0]);
            int from = Integer.parseInt(instruction[1]) - 1;
            int to = Integer.parseInt(instruction[2]) - 1;

            // Logic
            Stack<String> stackFrom = stacksArrayList.get(from);
            Stack<String> stackTo = stacksArrayList.get(to);
            Stack<String> temp = new Stack<String>();
            for (int i = 0; i < moveAmount; i++) { // For each move
                temp.push(stackFrom.pop());
            }
            for (int i = 0; i < moveAmount; i++) {
                stackTo.push(temp.pop());
            }
//            System.out.println("=".repeat(6) + " moved " + moveAmount + " from: " + from + " to: " + to);
        }


        stacksArrayList.forEach(System.out::println);
        StringBuilder b = new StringBuilder();

        stacksArrayList.forEach(j -> b.append(j.peek()));
        return b.toString();
    }


}
