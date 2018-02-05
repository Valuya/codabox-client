# codabox-client
This project implements a Java client for the Codabox API. It's based on standard (as in 'specified') Java technologies: _JAX-RS_, _JSON-B_.

# implemented services
Get Documents API only for now: get document list, download, mark as downloaded + list customers / fiduciaries / ...

# approach
KISS, POJOs for configuration and domain objects, simple client that uses those POJOs.

# testing
At the moment, you need following configuration in your Maven's _settings.xml_ to run the tests (of course, changing it with the right ones):

    <codabox.client.url>https://sandbox-api.codabox.com</codabox.client.url>
    <codabox.client.username>AB-34127856-cdab-ef01-0203-abcd</codabox.client.username>
    <codabox.client.password>R5qRcx9ABD</codabox.client.password>
    <codabox.client.software.company>aaaaaa-bbbb-cccc-dddd-eeff00123456</codabox.client.software.company>
