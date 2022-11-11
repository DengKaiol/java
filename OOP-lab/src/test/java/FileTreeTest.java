import org.junit.Before;
import org.junit.Test;

public class FileTreeTest {
    FileNode root = new FileNode();

    @Before
    public void constructTree(){
        root.setTitle("0");
        FileNode fileNode1 = new FileNode();
        FileNode fileNode2 = new FileNode();
        FileNode fileNode3 = new FileNode();
        FileNode fileNode4 = new FileNode();
        FileNode fileNode5 = new FileNode();
        fileNode1.setTitle("1");
        fileNode2.setTitle("2");
        fileNode3.setTitle("3");
        fileNode4.setTitle("4");
        fileNode5.setTitle("5");
        fileNode5.setFatherNode(fileNode2);
        fileNode1.addChildNode(fileNode3);
        fileNode2.addChildNode(fileNode5);
        root.addChildNode(fileNode1);
        root.addChildNode(fileNode2);
        root.addChildNode(fileNode4);

    }
    @Test
    public void deepFirstIterator(){
        FileTree tree = new FileTree(root);
        tree.addNode("6","","3");
        tree.deleteNode("5");
        tree.undo();
        tree.redo();
        FileTree.DeepFirstIterator iterator = new FileTree.DeepFirstIterator(tree.getRootNode());
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getTitle());
        }
    }

}