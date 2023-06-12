"use strict"
document.addEventListener("DOMContentLoaded", function () {
    var navdropButton = document.querySelector(".profile-nav-pic");

    var navdropdownMenu = document.querySelector(".drop-nav-menu");

    navdropButton.addEventListener("click", function() {
        navdropdownMenu.classList.toggle("show");
    });

    document.addEventListener("click", function(event) {
        if (!navdropButton.contains(event.target)) {
            navdropdownMenu.classList.remove("show");
        }
    });
});