/**
 * Created by oznaprazzi on 16/06/2017.
 */
var ws = new WebSocket("ws://10.140.76.130:8080/");

ws.sendMessage = function(){
    if(document.getElementById("usermsg").value != "" || document.getElementById("usermsg").value != null) {
        ws.send(document.getElementById("usermsg").value);
    }else{
        return;
    }
};