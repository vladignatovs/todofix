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

window.addEventListener("DOMContentLoaded", (event) => {
    if(document.getElementById("addTime")) {
        let input = document.getElementById("addTime");
        input.addEventListener('keydown', function (event) {
            const key = event.key;
            if ((key === "Backspace" || key === "Delete") && input.value.length == 6) {
                document.getElementById("addTime").value = document.getElementById("addTime").value.slice(0,3);
            } else if((key === "Backspace" || key === "Delete") && input.value.length == 11){ 
                document.getElementById("addTime").value = document.getElementById("addTime").value.slice(0,8);
            }
        });
    }
});

function addTab() {
    var timeInput = document.getElementById("addTime").value;
    if(timeInput.length == 3) { 
        document.getElementById("addTime").value = timeInput.slice(0,2)+ " : " + timeInput.slice(2, timeInput.length);
    } else if(timeInput.length == 8) {
        document.getElementById("addTime").value = timeInput.slice(0,7) + " : " + timeInput.slice(7, timeInput.length);
    }
}
