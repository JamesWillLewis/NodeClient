package nodeclient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import nodeclient.primary.PrimaryNode;
import nodeclient.secondary.SecondaryNode;

/**
 *
 * @author James
 */
public class Driver {

    private enum NodeType {

        PRIMARY, SECONDARY
    }
    private WebService nodeResource;
    private WebService primaryNodeResource;
    private WebService secondaryNodeResource;
    private Properties properties;
    private NodeType nodeType;
    private String nodeName;
    private String nodeID;
    private String username;
    private String password;

    /**
     * Initialize the node
     */
    public Driver() {
        properties = new Properties();
        try {
            properties.loadFromXML(new FileInputStream("node_config.xml"));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        this.nodeName = properties.getProperty("node_name");
        this.nodeID = properties.getProperty("node_id");
        this.username = properties.getProperty("username");
        this.password = properties.getProperty("password");
        this.nodeType = properties.getProperty("type").equals("primary") ? NodeType.PRIMARY : NodeType.SECONDARY;

        nodeResource = new WebService("node");
        nodeResource.setUsernamePassword(username, password);
        primaryNodeResource = new WebService("node/primary");
        primaryNodeResource.setUsernamePassword(username, password);
        secondaryNodeResource = new WebService("node/secondary");
        secondaryNodeResource.setUsernamePassword(username, password);
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
        if(nodeType == NodeType.PRIMARY){
            PrimaryNode primaryNode = new PrimaryNode();
            primaryNode.runDaemon();
        } else{
            SecondaryNode secondaryNode = new SecondaryNode();
            secondaryNode.runDaemon();
        }
    }
}
