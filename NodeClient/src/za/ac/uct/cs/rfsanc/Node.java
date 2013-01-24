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
     * Insert the node ID into the URI string, by replacing any occurrence of
     * '#' character with the node ID.
     *
     * @param path Path URI string
     * @return Path URI with # replaced with node ID
     */
    protected String insertPathID(String path) {
        return path.replaceAll("#", nodeID);
    }

    /**
     * Sets the URI path string's query parameter arguments, by replacing $1,
     * $2...$n parameters with values 0,1...n from arguments list.
     *
     * @param path Path URI string
     * @param params List of arguments
     * @return Path URI with parameters flags replaced with arguments
     */
    protected String setPathQueryParams(String path, String... args) {
        String result = path;
        for (int i = 0; i < args.length; i++) {
            result = result.replaceAll("$" + (i + 1), args[i]);
        }
        return result;
    }

    /**
     * Run daemon process for the node.
     */
    public abstract void runDaemon();
}