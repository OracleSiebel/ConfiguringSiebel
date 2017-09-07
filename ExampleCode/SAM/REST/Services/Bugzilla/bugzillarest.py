# This is a sample BugZilla REST API GET example script
# Tested with Python 2.7
#!/usr/bin/python
from flask import Flask, jsonify, request, abort
from flask_restful import Resource, Api
from bugzillaconnect import get_bug

app = Flask(__name__)
api = Api(app)

class BugDetails(Resource):
    def get(self, id):
        bugInfo = get_bug(id)
        results  = [
        	{
 	      		'bugNumber': str(bugInfo[0]),
         		'assigned': bugInfo[1],
        		'priority' : bugInfo[2],
        		'fixBy' : bugInfo[3],
        		'product' : bugInfo[4],
        		'summary' : bugInfo[5]        		,
        		'status' : bugInfo[6]        		
        	}
        ]
        return jsonify(results)

class BugFields(Resource):
    def get(self):
		fields = [
		  {
			'fieldName': 'bugNumber',
			'fieldId': 0
		  },
		  {
			"fieldName": "assigned",
			"fieldId": 1
		  },
		  {
			'fieldName': 'priority',
			'fieldId': 2
		  },
		  {
			"fieldName": "fixBy",
			"fieldId": 3
		  },
		  {
			'fieldName': 'product',
			'fieldId': 4
		  },
		  {
			"fieldName": "summary",
			"fieldId": 5
		  },
		  {
			"fieldName": "status",
			"fieldId": 6
		  }

		]
		return jsonify(fields)

# This is a stub post method to work with SAM just returning a dummy message
# as SAM expects to have one POST action
class BugUpdate(Resource):
	def post(self):
		if not request.json:
			abort(400)
		return jsonify({"message": "POST Action successful","data": None}) 

	
# GET URL - http://<hostname>:<port>/bugzillarest/bugs/<bug#>
api.add_resource(BugDetails, '/bugzillarest/bugs/<int:id>')
# FIELDS URL - http://<hostname>:<port>/bugzillarest/fields
api.add_resource(BugFields,'/bugzillarest/fields')
# POST URL - http://<hostname>:<port>/bugzillarest/bugs
api.add_resource(BugUpdate,'/bugzillarest/bugs')

# debug should not be true in production
if __name__ == '__main__':
    app.run(debug=True)
