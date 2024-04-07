p            console.log(route);
console.log(route.routeData);
console.log(JSON.parse(route.routeData));
var routeFeature = new ol.format.GeoJSON().readFeature(JSON.parse(route.routeData));
var routeGeometry = routeFeature.getGeometry();

var vectorLayer = new ol.layer.Vector({
    source: new ol.source.Vector({
        features: [routeFeature]
    })
});
            alert(data);
            getGpsRoutes(userId);
        });
}</script></body></html>

function uploadRandomPath() {
var path = generateRandomPath();
var geojson = convertToGeoJSON(path);

var userId = 2;a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}