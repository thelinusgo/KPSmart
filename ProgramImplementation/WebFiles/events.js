/**
 * Created by Edward on 1/06/2017.
 */

var eventsArray = []

/** example event: {"messageType":"event","event":{"eventType":"delivery","origin":"a","destination":"b","mailPriority":"InternAir",
* "weight":"1","volume":"2"}}
*/
function addNewEvent(event){
    eventsArray.push(event);
}