import java.util.Stack;

public class FileTree {
    private FileNode rootNode;
    private Stack<Order> orders;

    public FileTree(FileNode fileNode) {
        this.rootNode = fileNode;
        this.orders = new Stack<>();
    }

    public FileNode getRootNode() {
        return rootNode;
    }

    public void addNode(String title, String link, String fatherTitle) {
        FileNode node = new FileNode();
        Add add = new Add();
        node.setTitle(title);
        node.setLink(link);
        DeepFirstIterator iterator = new DeepFirstIterator(this.rootNode);
        while (iterator.hasNext()) {
            FileNode currentNode = iterator.next();
            if (currentNode.getTitle().equals(fatherTitle)) {
                node.setFatherNode(currentNode);
                node.setDepth(currentNode.getDepth() + 1);
                currentNode.addChildNode(node);
                add.setFatherNode(currentNode);
                add.setCurrentNode(node);
                this.orders.push(add);
                break;
            }
        }
    }

    public void deleteNode(String title) {
        Delete delete = new Delete();
        DeepFirstIterator iterator = new DeepFirstIterator(this.rootNode);
        while (iterator.hasNext()) {
            FileNode currentNode = iterator.next();
            if (currentNode.getTitle().equals(title)) {
                currentNode.getFatherNode().deleteChildNode(title);
                delete.setCurrentNode(currentNode);
                delete.setFatherNode(currentNode.getFatherNode());
                this.orders.push(delete);
                break;
            }
        }
    }

    public static class DeepFirstIterator implements Iterator {

        private Stack<FileNode> stack = new Stack<>();

        public DeepFirstIterator(FileNode fileNode) {
            this.stack.push(fileNode);
        }

        @Override
        public Boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public FileNode next() {
            FileNode current = stack.pop();
            if (!current.getChildNode().isEmpty()) {
                for (FileNode fileNode : current.getChildNode()) {
                    stack.push(fileNode);
                }
            }
            return current;
        }
    }

}
