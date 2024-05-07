window.addEventListener("DOMContentLoaded", (event) => {
    if(document.getElementById("submittime")) {
        document.getElementById("submittime").addEventListener("click", () => {
            var timeInput = document.getElementById("addTime").value.replace(/ /g,'').replace(/:/g, '');
            if(timeInput.length <= 2) { 
                let seconds = timeInput.slice(0, timeInput.length);
                console.log("only seconds = ", seconds);
            } else if (timeInput.length <= 4) {
                console.log("length = ", timeInput.length);
                let minutes = timeInput.slice(0, timeInput.length - 2);
                let seconds = timeInput.slice(timeInput.length - 2, timeInput.length);
                console.log("seconds = ", seconds, "and minutes = ", minutes*60)
            } else if (timeInput.length <= 6) {
                let hours = timeInput.slice(0, timeInput.length - 4);
                let minutes = timeInput.slice(timeInput.length - 4, timeInput.length - 2);
                let seconds = timeInput.slice(timeInput.length - 2,timeInput.length);
                console.log("seconds = ", seconds, "and minutes = ", minutes*60, "and hours = ", hours*3600)
            }
        });
    }
});


function addTab() {
    var timeInput = document.getElementById("addTime").value;
    let added = false;
    let added2 = false;
    console.log(0);
    if(timeInput.length == 3) {
        if(added == false) {
            document.getElementById("addTime").value = timeInput.slice(0,2)+ " : " + timeInput.slice(2, timeInput.length);
            added = true;
            console.log("added = ", added);
        } else if(added && added2) {
            added2 = false;
        }
    } else if(timeInput.length == 8) {
        if(added2 == false) {
            document.getElementById("addTime").value = timeInput.slice(0,7) + " : " + timeInput.slice(7, timeInput.length);
            added2 = true;
            console.log("added2 = ", added2);
            console.log(timeInput.length);
        }
    } else {
        console.log("chota");
    }
}