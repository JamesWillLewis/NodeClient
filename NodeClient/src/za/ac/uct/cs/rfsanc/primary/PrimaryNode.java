package za.ac.uct.cs.rfsanc.primary;

import za.ac.uct.cs.rfsanc.Node;
import za.ac.uct.cs.rfsanc.WebService;

/**
 *
 * @author James
 */
public class PrimaryNode extends Node {

    private WebService primaryNodeService;

    public PrimaryNode(String username, String password, String nodeID) {
        super(username, password, nodeID);
        primaryNodeService = new WebService("node/primary");
        primaryNodeService.setUsernamePassword(username, password);
    }

    @Override
    public void runDaemon() {
    
    }
}
