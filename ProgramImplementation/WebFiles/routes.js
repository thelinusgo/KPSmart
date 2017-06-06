/**
 * Created by Edward on 6/06/2017.
 */

/**
* Object that represents a Route object, used for calculating the shortest routes.
*
*/
function Route(origin, destination, tripDuration, gramPrice, cubicPrice){
    this.origin = origin;
    this.destination = destination;
    this.tripDuration = tripDuration;
    this.gramPrice = gramPrice;
    this.cubicPrice = cubicPrice;
};
/**
 * Represents a City object, which is represents a Node in the graph.
 * @param name
 * @constructor
 */
function City(name){
    this.name = name;
};