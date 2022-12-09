import java.util.ArrayList;

public class FileNode {
    private int depth;
    private String title;
    private String link;
    private FileNode fatherNode;
    private ArrayList<FileNode> childNode;

    public FileNode() {
        this.depth = 0;
        this.title = "";
        this.link = "";
        this.fatherNode = null;
        this.childNode = new ArrayList<>();
    }

    public void clear(){
        this.title = "";
        this.childNode.clear();
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public FileNode getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(FileNode fatherNode) {
        this.fatherNode = fatherNode;
    }

    public void setChildNode(ArrayList<FileNode> childNode) {
        this.childNode = childNode;
    }

    public ArrayList<FileNode> getChildNode() {
        return childNode;
    }

    public void addChildNode(FileNode fileNode) {
        this.childNode.add(fileNode);
    }

    public void deleteChildNode(String title) {
        childNode.removeIf(fileNode -> fileNode.title.equals(title));
    }


    public void deleteFatherNode() {
        this.fatherNode = null;
    }
}
