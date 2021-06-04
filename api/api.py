import flask
from flask import request, jsonify
from predict import predict
from request import req

app = flask.Flask(__name__)
app.config["DEBUG"] = True

hasil = {'rekomendasi': 'API SUCCESS'}


@app.route('/', methods=['GET'])
def home():
    return '''<h1>GADO-GADO API</h1>'''



@app.route('/api/v1/resources/air', methods=['GET'])
def api_id():


    if ('long' in request.args) & ('lat' in request.args):
        try:
            long = float(request.args['long'])
            lat = float(request.args['lat'])
            pm10, so2, co, o3, no2 = req(long,lat)
            results = int(predict(pm10, so2, co, o3, no2))
            if results==0:
                hasil = {'pm10':pm10, 'so2':so2, 'co':co, 'o3':o3, 'no2':no2, 'kualitas_udara': 'Good', 'rekomendasi': 'Let\'s do some exercise'}
            elif results==1:
                hasil = {'pm10':pm10, 'so2':so2, 'co':co, 'o3':o3, 'no2':no2, 'kualitas_udara': 'Moderate', 'rekomendasi': 'Prefer you stay at home'}
            elif results==2:
                hasil = {'pm10':pm10, 'so2':so2, 'co':co, 'o3':o3, 'no2':no2, 'kualitas_udara': 'Unhealthy', 'rekomendasi': 'Prefer you stay at home'}
            elif results==3:
                hasil = {'pm10':pm10, 'so2':so2, 'co':co, 'o3':o3, 'no2':no2, 'kualitas_udara': 'Very Unhealthy', 'rekomendasi': 'Avoid outdoor activity'}
            else:
                hasil = {'pm10':pm10, 'so2':so2, 'co':co, 'o3':o3, 'no2':no2, 'kualitas_udara': 'Hazardous', 'rekomendasi': 'Please avoid outdoor activity'}
        except:
            return "Error: longitude and latitude is not valid"
    else:
        return "Error: No location field provided. Please specify longitude and latitude."
    return jsonify(hasil)

if __name__ == "__main__":
    app.run(debug = True, host='0.0.0.0', port='80')