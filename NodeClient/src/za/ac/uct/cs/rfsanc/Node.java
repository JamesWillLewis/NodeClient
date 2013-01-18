package za.ac.uct.cs.rfsanc;

/**
 *
 * @author James
 */
public abstract class Node {

    protected WebService nodeService;
    protected String nodeID;

    public Node(String username, String password, String nodeID, String baseURI) {
        nodeService = new WebService(baseURI, "node");
        nodeService.setUsernamePassword(username, password);
        this.nodeID = nodeID;
    }

    public abstract void runDaemon();
}
