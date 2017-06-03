/**
 * Created by Edward on 1/06/2017.
 */


/** example event: {"messageType":"event","event":{"eventType":"delivery","origin":"a","destination":"b","mailPriority":"InternAir",
* "weight":"1","volume":"2","date":{"day":"1","month":"2","year":"2017"}}}
*/

/**
 * Adds a new event to the local storage of events
 * @param event : event to be added
 */
function addNewEvent(event){
    var events = JSON.parse(sessionStorage.getItem("events"));
    events.push(event);
    sessionStorage.setItem("events",JSON.stringify(events))
}

/**
 * Imports events from server
 */
function importEvents(){
    //TODO: implement once intergrated with server
}