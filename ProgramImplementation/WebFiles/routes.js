/**
 * Created by Edward on 6/06/2017.
 */

var testing = true;

// setup discontinued routes
var jsonNodes;
var discontinuedRoutes;
if (testing){
    jsonNodes = require('./cities.json'); // reads json cities.json into JSON object
    discontinuedRoutes = {"routes":[]};
    discontinueRoute("Sydney", "Auckland");//for testing
    addVertices(); // for testing
}
else{
    if (JSON.parse(sessionStorage.getItem("discontinuedRoutes")) == null){
        console.log("null discontinue")
        sessionStorage.setItem("discontinuedRoutes", '{"routes":[]}');
    }
    discontinuedRoutes = JSON.parse(sessionStorage.getItem("discontinuedRoutes"));var jsonNodes;
    $.getJSON('./cities.json', function(response){
        jsonNodes = response;
    })
}

function PriorityQueue () {
    this.nodes = [];

    this.enqueue = function (priority, key) {
        this.nodes.push({key: key, priority: priority });
        this.sort();
    };
    this.dequeue = function () {
        return this.nodes.shift().key;
    };
    this.sort = function () {
        this.nodes.sort(function (a, b) {
            return a.priority - b.priority;
        });
    };
    this.isEmpty = function () {
        return !this.nodes.length;
    };
}

function Map() {
    this.vertices = {}; // city edges pairs e.g. Manila: [ { CityName: 'Auckland', Distance: '7' } ]

    /**
     * Adds a vertex to vertices
     * @param cityName name of city e.g. Manila
     * @param edges array of connecting cities and costs e.g. [ { CityName: 'Auckland', Distance: '7' } ]
     */
    this.addVertex = function(cityName, edges){
        this.vertices[cityName] = edges;
    }
}

/**
 * adds routes from JSON object to vertices in graph
 */
function addVertices(){
    // console.log(jsonNodes);
    var map = new Map();
    for (var i in jsonNodes.cities){
        var city  = jsonNodes.cities[i];
        // delete discontinued routes
        for (var j in discontinuedRoutes.routes){
            var discontinuedRoute = discontinuedRoutes.routes[j];
            if (discontinuedRoute.origin == city.CityName){
                for (var k in city.NeighbouringCities){
                    if (discontinuedRoute.destination == city.NeighbouringCities[k].CityName){
                        city.NeighbouringCities.splice(k,1);
                    }
                }
            }
        }
        if (city.NeighbouringCities.length==0)continue;
        //add vertex
        map.addVertex(city.CityName, city.NeighbouringCities);
    }
    console.log(map.vertices);
}


function discontinueRoute(origin, destination){
    console.log("routes 1"+JSON.stringify(discontinuedRoutes));
    discontinuedRoutes.routes.push({"origin":origin,"destination":destination});
    if (!testing)sessionStorage.setItem("discontinuedRoutes", JSON.stringify(discontinuedRoutes));
    console.log("routes "+JSON.stringify(discontinuedRoutes));
}