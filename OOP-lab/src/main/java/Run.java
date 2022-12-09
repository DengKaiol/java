import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class Run {
    public static void main(String[] args) throws IOException {
        while (true) {
            FileTree tree = constructTree();
            String str = "";
            ArrayList<String> arrayList;
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                str = scanner.nextLine();
                arrayList = getLine(str);
                doCommand(arrayList, tree);
            }
        }
    }

    private static FileTree constructTree() {
        FileNode rootNode = new FileNode();
        return new FileTree(rootNode);
    }

    public static ArrayList<String> getLine(String str) {
        String[] strings = str.split(" |@|\"|at");
        ArrayList<String> keyStrings = new ArrayList<>();
        for (String s : strings) {
            if (!s.isEmpty()) {
                keyStrings.add(s);
            }
        }
        return keyStrings;
    }


    public static void doCommand(ArrayList<String> arrayList, FileTree tree) throws IOException {
        switch (arrayList.get(0)) {
            case "bookmark":
                bookmark(arrayList, tree);
                break;
            case "add-title":
                addTitle(arrayList, tree);
                break;
            case "add-bookmark":
                addBookmark(arrayList, tree);
                break;
            case "delete":
            case "delete-title":
            case "delete-bookmark":
                delete(arrayList, tree);
                break;
            case "show-tree":
                showTree(tree);
                break;
            case "undo":
                undo(tree);
                break;
            case "redo":
                redo(tree);
                break;
            case "open":
                openTree(arrayList, tree);
                break;
            case "save":
                save(tree);
                break;
            case "ls-tree":
                lsTree(0,new File("src/main/resources"));
                break;
            case "read-bookmark":
                readBookmark(arrayList, tree);
                break;
            default:
                commandError();
        }
    }

    private static void commandError() {
        System.out.println("command error");
    }

    private static void readBookmark(ArrayList<String> arrayList, FileTree tree) {
        FileTree.DeepFirstIterator iterator = new FileTree.DeepFirstIterator(tree.getRootNode());
        while (iterator.hasNext()) {
            FileNode currentNode = iterator.next();
            if (!currentNode.getLink().isEmpty()) {
                if (currentNode.getTitle().equals(arrayList.get(1))) {
                    currentNode.setTitle("*" + currentNode.getTitle());
                }
            }
        }
    }

    private static void lsTree(int depth,File file) {
        for (int i = 0; i < depth; i++)
            System.out.print('-');
        if (file.isDirectory()){
            System.out.println(file.getName());
            File[] files = file.listFiles();
            for (int i = 0; i < Objects.requireNonNull(files).length; i++)
                lsTree(depth + 4, files[i]);
        }else {
            System.out.println("\""+file.getName()+"\"");
        }
    }

    private static void save(FileTree tree) throws IOException {
        File file = new File("src/main/resources/" + tree.getName());
        BufferedWriter fileOut = new BufferedWriter(new FileWriter(file));
        FileTree.DeepFirstIterator iterator = new FileTree.DeepFirstIterator(tree.getRootNode());
        while (iterator.hasNext()) {
            FileNode currentNode = iterator.next();
            int depth = currentNode.getDepth();
            if (currentNode.getLink().isEmpty()) {
                String str = getBeforeString(depth, "#");
                fileOut.write(str + currentNode.getTitle() + "\n");
            } else {
                fileOut.write("[" + currentNode.getTitle() + "]" + "(" + currentNode.getLink() + ")" + "\n");
            }
        }
        fileOut.close();
    }

    private static void openTree(ArrayList<String> arrayList, FileTree tree) throws IOException {
        tree.clear();
        tree.setName(arrayList.get(1));
        Stack<FileNode> stack = new Stack<>();
        File file = new File("src/main/resources/" + tree.getName());
        if (!file.exists()){
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String str = "";
        while ((str = reader.readLine()) != null) {
            FileNode fileNode = new FileNode();
            if (getDepthByString(str) == 0) {
                tree.setRootNodeTitle(deleteDepth(str));
                stack.push(tree.getRootNode());
            } else if (getDepthByString(str) > 0) {
                fileNode.setTitle(deleteDepth(str));
                fileNode.setDepth(getDepthByString(str));
                FileNode topNode = stack.peek();
                while (getDepthByString(str) <= topNode.getDepth()) {
                    stack.pop();
                    topNode = stack.peek();
                }
                fileNode.setFatherNode(topNode);
                topNode.addChildNode(fileNode);
                stack.push(fileNode);
            } else {
                fileNode.setTitle(deleteDepthWithLink(str).get(0));
                fileNode.setLink(deleteDepthWithLink(str).get(1));
                FileNode topNode = stack.peek();
                fileNode.setDepth(topNode.getDepth() + 1);
                fileNode.setFatherNode(topNode);
                topNode.addChildNode(fileNode);
            }

        }
    }

    public static ArrayList<String> deleteDepthWithLink(String str) {
        String[] strings = str.split("\\[|\\]|\\(|\\)");
        ArrayList<String> keyStrings = new ArrayList<>();
        for (String s : strings) {
            if (!s.isEmpty()) {
                keyStrings.add(s);
            }
        }
        return keyStrings;
    }

    public static String deleteDepth(String str) {
        String[] strings = str.split("#");
        for (String s : strings) {
            if (!s.isEmpty()) {
                return s;
            }
        }
        return "";
    }

    public static int getDepthByString(String str) {
        int depth = -1;
        for (char s : str.toCharArray()) {
            if (s == '#') {
                depth += 1;
            }
        }
        return depth;
    }

    private static void redo(FileTree tree) {
        tree.redo();
    }

    private static void undo(FileTree tree) {
        tree.undo();
    }

    private static void showTree(FileTree tree) {
        FileTree.DeepFirstIterator iterator = new FileTree.DeepFirstIterator(tree.getRootNode());
        while (iterator.hasNext()) {
            FileNode currentNode = iterator.next();
            int depth = currentNode.getDepth();
            String str = getBeforeString(depth, "--");
            if (currentNode.getLink().isEmpty()) {
                System.out.println(str + currentNode.getTitle());
            } else {
                System.out.println(str + "\"" + currentNode.getTitle() + "\"");
            }
        }
    }

    private static String getBeforeString(int depth, String s) {
        StringBuilder str = new StringBuilder();
        int i = 0;
        while (i <= depth) {
            str.append(s);
            i++;
        }
        return str.toString();
    }

    private static void delete(ArrayList<String> arrayList, FileTree tree) {
        String title = arrayList.get(1);
        tree.deleteNode(title);
    }

    private static void addBookmark(ArrayList<String> arrayList, FileTree tree) {
        String title = arrayList.get(1);
        String link = arrayList.get(2);
        String fatherTitle = arrayList.get(3);
        tree.addNode(title, link, fatherTitle);
    }

    private static void addTitle(ArrayList<String> arrayList, FileTree tree) {
        if (arrayList.size() == 2) {
            tree.setRootNodeTitle(arrayList.get(1));
        } else {
            String title = arrayList.get(1);
            String fatherTitle = arrayList.get(2);
            tree.addNode(title, "", fatherTitle);
        }
    }

    private static void bookmark(ArrayList<String> arrayList, FileTree tree) {
        tree.setName(arrayList.get(1));
    }


}
