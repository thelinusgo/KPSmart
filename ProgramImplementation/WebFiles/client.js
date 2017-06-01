/**
 * Created by linus on 31/05/17.
 */
/**
 * This JS file is responsible for sending and receiving data from the server as JS files.
 */

/**
 * Sends data from the website and converts it into a Json file.
 * @param message
 */
function sendData(json_message){
    if(json_message == null){
        alert("There must be a message to pass in");
    }

    /*Returns the message being passed in as a String. */
    var stringObj =  JSON.stringify(json_message);
    alert("String object: " + stringObj);
    //TODO need to send stringObj to server??

}
/**
 * Recieves data from the server and places it back into the website.
 * @param message
 */
function receiveData(message){
    if(message == null){
        alert("There must be a message to pass in");
    }
    message = JSON.parse(message);

    if (message.messageType.includes("event")){
        addNewEvent(message);
    }

    //if message.messageType == "businessFigures"


    //TODO need to implement the rest of this part.


}

function addNewEvent(event){

}


