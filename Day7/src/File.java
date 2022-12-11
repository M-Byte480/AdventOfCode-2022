public class File {
    String name;
    int size;
    File(String content){
        String[] contents = content.split(" ");
        String name = contents[1];
        int size = Integer.parseInt(contents[0]);
    }

    public int getSize() {
        return size;
    }
}
