package burp;

import java.util.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.JMenuItem;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;


public class BurpExtender implements IBurpExtender, IContextMenuFactory
{
    private IExtensionHelpers helpers;

    private final static String ExtensionName = "Send to CyberChef";
    
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        helpers = callbacks.getHelpers();
        callbacks.setExtensionName(ExtensionName);
        callbacks.registerContextMenuFactory(this);
    }

    @Override
    public List<JMenuItem> createMenuItems(IContextMenuInvocation invocation) {
        final byte context = invocation.getInvocationContext();
        final IHttpRequestResponse message = invocation.getSelectedMessages()[0];
        String text;
        
        if (message == null) return null;
        
        if (context == IContextMenuInvocation.CONTEXT_MESSAGE_EDITOR_REQUEST || context == IContextMenuInvocation.CONTEXT_MESSAGE_VIEWER_REQUEST)
            text = helpers.bytesToString(message.getRequest());
        else
            text = helpers.bytesToString(message.getResponse());
        
        String selectedText = text.substring(invocation.getSelectionBounds()[0], invocation.getSelectionBounds()[1]);
        
        JMenuItem i = new JMenuItem(ExtensionName);
        i.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doit(selectedText);
            }
        });
        return Collections.singletonList(i);
    }

    private void doit(String selectedText) {
        StringBuilder url = new StringBuilder("https://gchq.github.io/CyberChef/#input=");
        url.append(helpers.base64Encode(selectedText).replace("=", ""));

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url.toString()));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}

