package za.ac.uct.cs.rfsanc.secondary;

import za.ac.uct.cs.rfsanc.Node;
import za.ac.uct.cs.rfsanc.WebService;

/**
 *
 * @author James
 */
public class SecondaryNode extends Node {

    private WebService secondaryNodeService;

    public SecondaryNode(String username, String password, String nodeID) {
        super(username, password, nodeID);
        secondaryNodeService = new WebService("node/secondary");
        secondaryNodeService.setUsernamePassword(username, password);
    }

    @Override
    public void runDaemon() {
       
    }
}
