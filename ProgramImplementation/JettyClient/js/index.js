var ws = new WebSocket("ws://10.140.114.22:8080/");

ws.onopen = function () {
    alert("Opened!");
    ws.send("Hello Server");
};

ws.onmessage = function (evt) {
    alert("Message: " + evt.data);
};

ws.onclose = function () {
    alert("Closed!");
};

ws.onerror = function (err) {
    alert("Error: " + err);
};