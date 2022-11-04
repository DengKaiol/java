public class Add implements Order{
    private FileNode currentNode;
    private FileNode fatherNode;

    Add(FileNode fatherNode){
        this.fatherNode = fatherNode;
    }

    @Override
    public void executeTitle(String title) {
        this.addTitle(title);
    }

    @Override
    public void executeBookmark(String bookmarkName,String bookmarkLink) {

    }

    public void addTitle(String title){
        this.currentNode.setTitle(title);
    }

    public FileNode getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(FileNode fatherNode) {
        this.fatherNode = fatherNode;
    }
}
