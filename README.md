# Send To CyberChef
Send to CyberChef context menu extension for Burp Suite

This extension simply adds a context menu item to Burp Suite that sends the selected text (from a message request or response) to CyberChef (https://gchq.github.io/CyberChef/) with the Magic operation in your system default web browser.

So for example if you had selected "NRZSAL3WMFZC63DJMIXXI33NMNQXINRPO5SWEYLQOBZS6USPJ5KA" in a response, and hit Send to CyberChef it would send you here: https://gchq.github.io/CyberChef/#recipe=Magic(3,false,false,'')&input=TlJaU0FMM1dNRlpDNjNESk1JWFhJMzNOTU5RWElOUlBPNVNXRVlMUU9CWlM2VVNQSjVLQT09PT0

Building
--------

First thing you'll need to do is export the Burp Extender API Interface files somewhere and then copy them all into the src/burp/ directory. This is done from within Burp Suite itself on the Extender/APIs tab.

After that all you need is Apache Ant installed (it is likely called just 'ant' in your package manager) and running 'ant' will build the jar.
