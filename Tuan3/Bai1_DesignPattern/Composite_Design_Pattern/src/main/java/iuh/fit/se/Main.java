package iuh.fit.se;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        File file1 = new File("a.txt");
        File file2 = new File("b.txt");

        Directory folder = new Directory("Folder1");
        folder.add(file1);
        folder.add(file2);

        folder.showInfo();
    }
}