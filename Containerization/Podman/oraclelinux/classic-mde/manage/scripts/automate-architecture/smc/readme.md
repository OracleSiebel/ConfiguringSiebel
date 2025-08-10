# SMC


Configure script code gives error for some commands execution
Below commands to create and gateway and server profiles

smc folder can be copied to /siebsrvr/log folder inside ses container and then below commands can be run

```

bash setGateway -u sadmin -p sadmin -g cgw-DEV.company.com -h 4430 -a sai-DEV.company.com -b 4430
bash setGatewaySecProfile -u sadmin -p sadmin -a sai-DEV.company.com -b 4430 -d oracle19c -e 1521 -f DEV -t SIEBEL -v SADMIN -w Welcome1
bash bootstrapGateway -u SADMIN -p Welcome1 -a sai-DEV.company.com -b 4430 -g SADMIN -h Welcome1
bash createEnterpriseProfile -u SADMIN -p Welcome1 -a sai-DEV.company.com -b 4430 -n DEV_profile -s /sfs -t SIEBEL -c SIEBELDB
bash deployEnterprise -u SADMIN -p Welcome1 -a sai-DEV.company.com -b 4430 -d DEV_profile -f DEV_ENT
bash createServerProfile -u SADMIN -p Welcome1 -a sai-DEV.company.com -b 4430 -n SES_DEV_profile -c adm,callcenter,workflow,siebelwebtools,eai -l GUESTCST -m Welcome1


```
AI profile can be created and deployed manually
Migration profile can be created and deplpoyed manually

