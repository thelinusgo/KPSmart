/**
 * Created by Edward on 6/06/2017.
 */


// if (JSON.parse(sessionStorage.getItem("nodes")) == null){
//     var jsonFile = require('./cities.json');
//     sessionStorage.setItem("nodes", jsonFile);
// }
// var jsonNodes = JSON.parse(sessionStorage.getItem("nodes"));

var jsonFile = require('./cities.json');
var jsonNodes = JSON.parse(jsonFile);

addVertices();

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
    this.vertices = {}

    this.addVertex = function(cityName, edges){
        this.vertices[cityName] = edges;
    }
}

/**
 * adds routes from JSON object to vertices in graph
 */
function addVertices(){
    var map = new Map();
    for (var city in jsonNodes){
        g.addVertex(city.CityName, city.NeighbouringCities);
    }
}