# Nexus Bridge

The Nexus Bridge is a development from Ideport Riga, which helps developers to bridge the gap between the jQuery-based API for OpenUI and modern frameworks, including vue.js, React, amongst others.

The Siebel team at Oracle has reviewed the code of the Nexus Bridge JS library, paying careful attention to how it uses Open UI methods and properties. Based on our comments, Ideaport refactored the code, and all major concerns have been addressed. We would have implemented some aspects of the Nexus Bridge differently. Since this is not Oracle product, we don't insist on changing the Nexus Bridge our way. 

At this stage, Nexus Bridge can be considered a standard OpenUI development that any customer could have developed. Ideaport have kindly made this available for any Siebel customer or partner to use and develop with.

Overall, Oracle does not object Siebel customers building custom PRs using various JS frameworks, including the Nexus Bridge, as long as the customers are aware of the following:

* Nexus Bridge is not an Oracle product, so Oracle Support won't address service requests related to it, except where the issue can be shown to lie with the documented Open UI API. Nexus Bridge is an open-source project under the MIT license and Nexus Bridge developers have no obligation to fix Nexus Bridge defects either. Requests for help should be raised as [issues for the Nexus Bridge](https://github.com/ideaportriga/siebel-nexus-bridge/issues) repository.
* Building custom Physical Renderers (PR) requires expertise both in the web framework of choice and Siebel Open UI.
* Your custom PR is not an Oracle product, and Oracle Support won't be able to address service requests outside the realms of the supported API methods listed in the Siebel Bookshelf.

To get started with [Nexus Bridge, follow this link](https://github.com/ideaportriga/siebel-nexus-bridge) to their repository.
