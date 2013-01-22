package za.ac.uct.cs.rfsanc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import za.ac.uct.cs.rfsanc.primary.PrimaryNode;
import za.ac.uct.cs.rfsanc.secondary.SecondaryNode;

/**
 * Main class of Node-Client application.
 *
 * @author James William Lewis (james.will.lewis@gmail.com)
 */
public class Driver {

    /**
     * Types of Nodes.
     */
    private enum NodeType {

        PRIMARY, SECONDARY
    }
    /**
     * Persistent node-configuration profile.
     */
    private Properties properties;
    /**
     * The type of node running this application.
     */
    private NodeType nodeType;
    /**
     * The name of this node.
     */
    private String nodeName;
    /**
     * The primary-key of this node.
     */
    private String nodeID;
    /**
     * The authorisation username of this node.
     */
    private String username;
    /**
     * The authorisation password of this node.
     */
    private String password;
    /**
     * The node instance for this context.
     */
    private Node node;
    /**
     * Base URI of the server web-resources.
     */
    private String baseURI;
    /**
     * Path-name of node configuration XML file.
     */
    private static final String NODE_CONFIG_FILENAME = "node_config.xml";

    /**
     * Initialise the node properties.
     */
    public Driver() {
        properties = new Properties();
        try {
            properties.loadFromXML(new FileInputStream(NODE_CONFIG_FILENAME));
            this.nodeName = properties.getProperty("node_name");
            this.nodeID = properties.getProperty("node_id");
            this.username = properties.getProperty("username");
            this.password = properties.getProperty("password");
            this.nodeType = properties.getProperty("type").equals("primary") ? NodeType.PRIMARY : NodeType.SECONDARY;
            this.baseURI = properties.getProperty("base_uri");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.runDaemon();
    }

    /**
     * This is where the main divergence of the program execution occurs,
     * depending on whether this is a primary or secondary user node.
     */
    private void runDaemon() {
        System.out.printf("===[ Node %1$s is now starting execution ]===%n", nodeName);

        if (nodeType == NodeType.PRIMARY) {
            System.out.println("===[ Node is a PRIMARY user ]===");
            node = new PrimaryNode(username, password, nodeID, baseURI);
            node.runDaemon();
        } else {
            System.out.println("===[ Node is a SECONDARY user ]===");
            node = new SecondaryNode(username, password, nodeID, baseURI);
            node.runDaemon();
        }
    }
}