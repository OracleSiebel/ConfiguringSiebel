# Used by ../launch-siebel/configure to test readiness during auto-configure

import sys,json
status=json.load(sys.stdin)
if len(status['ServerDeployment']) == 0:
    print("Undeployed")
else:
    print(status['ServerDeployment'][0]['DeploymentInfo']['Status'])