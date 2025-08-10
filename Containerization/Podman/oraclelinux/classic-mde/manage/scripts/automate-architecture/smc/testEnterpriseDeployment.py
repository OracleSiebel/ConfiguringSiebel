# Used by ../launch-siebel/configure to test readiness during auto-configure

import sys,json
status=json.load(sys.stdin)
if len(status['EnterpriseDeployment']) == 0:
    print("Undeployed")
else:
    print(status['EnterpriseDeployment'][0]['DeploymentInfo']['Status'])