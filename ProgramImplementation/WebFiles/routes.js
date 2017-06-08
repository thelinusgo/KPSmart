/**
 * Created by Edward on 6/06/2017.
 */

if (sessionStorage.getItem("routes") == null)sessionStorage.setItem("routes", []);
var routes = sessionStorage.getItem("routes");

/**
* Object that represents a Route object, used for calculating the shortest routes.
*
*/
function Route(origin, destination, transportFirm, transportType, pricePerGram, pricePerCC, departureDay, departsEvery, tripDuration){
    this.origin = origin;
    this.destination = destination;
    this.transportFirm = transportFirm;
    this.transportType = transportType;
    this.pricePerGram = pricePerGram;
    this.pricePerCC = pricePerCC;
    this.departureDay = departureDay;
    this.departsEvery = departsEvery;
    this.tripDuration = tripDuration;
};

function DijkstraNode(city, fromNode, costToHere){
    this.city = city;
    this.fromNode = fromNode;
    this.costToHere = costToHere;
}

function startSearch(origin, destination){
    startNode = new DijkstraNode(origin, null, 0);
    endNode = new DijkstraNode(destination, null, null);
}