public class Add implements Order {

    private FileNode currentNode;
    private FileNode fatherNode;


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

    @Override
    public void execute() {
        this.fatherNode.addChildNode(this.currentNode);
    }

    @Override
    public void reverseExecute() {
        this.fatherNode.deleteChildNode(this.currentNode.getTitle());
    }
}
