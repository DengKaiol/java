public class Delete implements Order {
    private FileNode currentNode;

    public FileNode getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(FileNode currentNode) {
        this.currentNode = currentNode;
    }

    public FileNode getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(FileNode fatherNode) {
        this.fatherNode = fatherNode;
    }

    private FileNode fatherNode;



    @Override
    public void execute() {

    }

    @Override
    public void reverseExecute() {

    }
}
