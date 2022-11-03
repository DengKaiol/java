import java.util.ArrayList;

public class FileNode {
    private int depth;
    private String title;
    private String bookmarkName;
    private String bookmarkLink;
    private FileNode fatherNode;
    private ArrayList<FileNode> childNode;

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

    public String getBookmarkName() {
        return bookmarkName;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }

    public String getBookmarkLink() {
        return bookmarkLink;
    }

    public FileNode getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(FileNode fatherNode) {
        this.fatherNode = fatherNode;
    }

    public void setBookmarkLink(String bookmarkLink) {
        this.bookmarkLink = bookmarkLink;
    }

    public void addChildNode(FileNode fileNode) {
        this.childNode.add(fileNode);
    }

    public void deleteChildNode(FileNode fileNode) {
        if (this.childNode.contains(fileNode)) {
            childNode.removeIf(fileNode1 -> fileNode1.equals(fileNode));
        }
    }

    public void deleteTitle() {
        this.title = "";
    }

    public void deleteBookmark() {
        this.bookmarkName = "";
        this.bookmarkLink = "";
    }
}
