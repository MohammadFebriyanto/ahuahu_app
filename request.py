import requests

def req(lat,long):
    myToken = '2052161a02054a46ef00bde649f8b727fd58b14d'
    base_url = "https://api.waqi.info"
    r = requests.get(base_url + f"/feed/geo:{lat};{long}/?token={myToken}")
    def balikinindex(r, par):
        try:
            result = r.json()['data']['iaqi'][par]['v']
        except:
            result = 0
        return result
    pm10 = balikinindex(r, 'pm10')
    so2 = balikinindex(r, 'so2')
    co = balikinindex(r, 'co')
    o3 = balikinindex(r, 'o3')
    no2 = balikinindex(r, 'no2')
    
    return pm10, so2, co, o3, no2