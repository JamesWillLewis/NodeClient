package za.ac.uct.cs.rfsanc;

/**
 * Abstract super-class for all node types (such as primary or secondary).
 * 
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
public abstract class Node {

    /**
     * Web-resource for a generic node.
     */
    protected WebService nodeService;
    /**
     * The UID of this node, as specified on the server.
     */
    protected String nodeID;

    /**
     * Construct a new Node instance.
     * 
     * @param username Node authorisation username
     * @param password Node password
     * @param nodeID Node primary key
     * @param baseURI Base URI of node web-resource 
     */
    public Node(String username, String password, String nodeID, String baseURI) {
        nodeService = new WebService(baseURI, "node");
        nodeService.setUsernamePassword(username, password);
        this.nodeID = nodeID;
    }

    /**
     * Run daemon process for the node.
     */
    public abstract void runDaemon();
}