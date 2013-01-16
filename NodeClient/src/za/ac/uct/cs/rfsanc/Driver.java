package za.ac.uct.cs.rfsanc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import za.ac.uct.cs.rfsanc.primary.PrimaryNode;
import za.ac.uct.cs.rfsanc.secondary.SecondaryNode;

/**
 * Main class of Node-Client application.
 *
 *
 * @author James
 */
public class Driver {

    private enum NodeType {

        PRIMARY, SECONDARY
    }
    private Properties properties;
    private NodeType nodeType;
    private String nodeName;
    private String nodeID;
    private String username;
    private String password;
    private PrimaryNode primaryNode;
    private SecondaryNode secondaryNode;
    private String baseURI;
    private static final String NODE_CONFIG_FILENAME = "node_config.xml";

    /**
     * Initialize the node
     */
    public Driver() {
        properties = new Properties();
        try {
            properties.loadFromXML(new FileInputStream(NODE_CONFIG_FILENAME));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        this.nodeName = properties.getProperty("node_name");
        this.nodeID = properties.getProperty("node_id");
        this.username = properties.getProperty("username");
        this.password = properties.getProperty("password");
        this.nodeType = properties.getProperty("type").equals("primary") ? NodeType.PRIMARY : NodeType.SECONDARY;
        this.baseURI = properties.getProperty("base_uri");
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
            primaryNode = new PrimaryNode(username, password, nodeID);
            primaryNode.runDaemon();
        } else {
            System.out.println("===[ Node is a SECONDARY user ]===");
            secondaryNode = new SecondaryNode(username, password, nodeID);
            secondaryNode.runDaemon();
        }
    }
}
