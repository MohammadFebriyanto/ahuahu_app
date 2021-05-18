import flask
from flask import request, jsonify

app = flask.Flask(__name__)
app.config["DEBUG"] = True

hasil = [
    {'message': 'API SUCCESS',
     'disclaimer': 'Ini cuma coba-coba'}
]


@app.route('/', methods=['GET'])
def home():
    return '''<h1>GADO-GADO API</h1>'''



@app.route('/api/v1/resources/air', methods=['GET'])
def api_id():

    results = []

    if ('long' in request.args) & ('lat' in request.args):
        results.append(hasil)
    else:
        return "Error: No location field provided. Please specify longitude and latitude."
    return jsonify(results)

app.run()