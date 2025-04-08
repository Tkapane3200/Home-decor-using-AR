"use strict";

let btn = document.querySelector("button");
console.log(btn);

function sayHiMsg(msg) {
    console.log(msg);
}

function sayHiHover() {
    console.log("Hover");
}

btn.addEventListener("click", function () {
    sayHiMsg("Hello");
});

btn.addEventListener("mouseover", sayHiHover);


// Q. Which one better? addEventListener or onclick?
// A. addEventListener is better. Because it can add multiple event handler.
//    onclick can add only one event handler.


// Type of Event
// 1. Mouse Event
// 2. Keyboard Event
