import java.util.ArrayList;
import java.util.Stack;

public class FileTree {
    private FileNode rootNode;
    private ArrayList<Object> orders;

    public FileTree(FileNode fileNode) {
        this.rootNode = fileNode;
    }

    public FileNode getRootNode() {
        return rootNode;
    }


    public static class DeepFirstIterator implements Iterator {

        private Stack<FileNode> stack = new Stack<>();

        DeepFirstIterator(FileNode fileNode){
            this.stack.push(fileNode);
        }

        @Override
        public Boolean hasNest() {
            return !stack.isEmpty();
        }

        @Override
        public FileNode next() {
            FileNode current = stack.pop();
            if (!current.getChildNode().isEmpty()){
                for (FileNode fileNode : current.getChildNode()){
                    stack.push(fileNode);
                }
            }
            return current;
        }
    }

}
