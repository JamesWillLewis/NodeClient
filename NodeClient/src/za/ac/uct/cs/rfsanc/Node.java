package za.ac.uct.cs.rfsanc;

/**
 *
 * @author James
 */
public abstract class Node {

    protected WebService nodeService;
    protected String nodeID;

    public Node(String username, String password, String nodeID) {
        nodeService = new WebService("node");
        nodeService.setUsernamePassword(username, password);
        this.nodeID = nodeID;
    }

    public abstract void runDaemon();
}
