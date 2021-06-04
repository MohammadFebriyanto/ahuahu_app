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
                hasil = {'link': 'https://storage.googleapis.com/bucket_ahuahu/smile_green.png', 'pm10':pm10, 'so2':so2, 'co':co, 'o3':o3, 'no2':no2, 'kualitas_udara': 'Good', 'rekomendasi': 'Let\'s do some exercise', 'deskripsi': 'this location has good air level, level of carbon monoxide is {}, Nitrogen dioxide is {}, Ozone is {}, particullate matter 10 is {}, Sulfur dioxide is {}, you can exercise with a fresh air, enjoy to exercise around here. we recommend you to exercise outdoor to feel a good air around you. you can do cycling, running, or jogging. Have a nice day'.format(co, no2, o3, pm10, so2)}
            elif results==1:
                hasil = {'link': 'https://storage.googleapis.com/bucket_ahuahu/smile_grey.png', 'pm10':pm10, 'so2':so2, 'co':co, 'o3':o3, 'no2':no2, 'kualitas_udara': 'Moderate', 'rekomendasi': 'Prefer you stay at home','deskripsi':'this location has moderate air level, level of carbon monoxide is {}, Nitrogen dioxide is {}, Ozone is {}, particullate matter 10 is {}, Sulfur dioxide is {}. we do not recommend to exercise in this area. we recommend to exercise indoor than you exercise outdoor. you can exercise together with your friends and family. Have a nice day and stay healthy '.format(co, no2, o3, pm10, so2)}
            elif results==2:
                hasil = {'link': 'https://storage.googleapis.com/bucket_ahuahu/smile_grey.png', 'pm10':pm10, 'so2':so2, 'co':co, 'o3':o3, 'no2':no2, 'kualitas_udara': 'Unhealthy', 'rekomendasi': 'Prefer you stay at home','deskripsi':'this location has unhealthy air level, level of carbon monoxide is {}, Nitrogen dioxide is {}, Ozone is {}, particullate matter 10 is {}, Sulfur dioxide is {}, we do not recommend to exercise in this area. we recommend to exercise indoor than you exercise outdoor. you can exercise together with your friends and family. Have a nice day and stay healthy'.format(co, no2, o3, pm10, so2)}
            elif results==3:
                hasil = {'link': 'https://storage.googleapis.com/bucket_ahuahu/smile_red.png', 'pm10':pm10, 'so2':so2, 'co':co, 'o3':o3, 'no2':no2, 'kualitas_udara': 'Very Unhealthy', 'rekomendasi': 'Avoid outdoor activity', 'deskripsi': 'this location has very unhealthy air level, level of carbon monoxide is {}, Nitrogen dioxide is {}, Ozone is {}, particullate matter 10 is {}, Sulfur dioxide is {}, we do not recommend to exercise in this area. we recommend to exercise indoor than you exercise outdoor. you can exercise together with your friends and family. Have a nice day and stay healthy'.format(co, no2, o3, pm10, so2)}
            else:
                hasil = {'link': 'https://storage.googleapis.com/bucket_ahuahu/smile_red.png', 'pm10':pm10, 'so2':so2, 'co':co, 'o3':o3, 'no2':no2, 'kualitas_udara': 'Hazardous', 'rekomendasi': 'Please avoid outdoor activity','deskripsi': 'this location has dangerous air level, level of carbon monoxide is {}, Nitrogen dioxide is {}, Ozone is {}, particullate matter 10 is {}, Sulfur dioxide is {}, we do not recommend to exercise in this area. we recommend to exercise indoor than you exercise outdoor. you can exercise together with your friends and family. Have a nice day and stay healthy'.format(co, no2, o3, pm10, so2)}
        except:
            return "Error: longitude and latitude is not valid"
    else:
        return "Error: No location field provided. Please specify longitude and latitude."
    return jsonify(hasil)

if __name__ == "__main__":
    app.run(debug = True, host='0.0.0.0', port='7777')