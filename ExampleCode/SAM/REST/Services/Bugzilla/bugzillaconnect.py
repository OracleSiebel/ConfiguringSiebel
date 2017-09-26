# This is a sample bugzilla DB connection script
# Tested using Python 2.7
#!/usr/bin/python
import sys
import mysql.connector 

# bug id can be passed to the script as an argument like
# python bugzillaconnect.py 123456
bug = sys.argv[1]

# Replace the connection parameters with your actual values
'''user = "CHANGE_ME"
passwd = "CHANGE_ME"
host = "CHANGE_ME"
database = "CHANGE_ME"'''

def get_bug(bug):
	config = {
	  'user': user,
	  'password': passwd,
	  'host': host,
	  'database': database,
	}

	cnx = mysql.connector.connect(**config)
	cursor = cnx.cursor()

	# Sample query for mysql DB of BugZilla (update as needed)
	query = ("""select  bugs.bug_id,
        profiles.realname as "Assignee",
        bugs.priority as "Priority",
        bugs.target_milestone as "Fix_By",
        products.name as "Product",
        bugs.short_desc as "Summary",
        bugs.bug_status as "Status"        
		from    bugs,
        profiles,
        products
		where   bugs.assigned_to = profiles.userid AND
        bugs.product_id = products.id AND
        bugs.bug_id = """) + str(bug)

	cursor.execute(query)
	results = cursor.fetchone()
	cursor.close()
	cnx.close()
	return results

# Uncomment the below line to verify your connection
#print get_bug(bug)
