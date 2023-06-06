"use strict"
document.addEventListener("DOMContentLoaded", function () {
    var commentDropLink = document.querySelector(".dropdown-toggle");

    var commentDropDownDisplay = document.querySelector(".dropdown-comments");

    commentDropLink.addEventListener("click", function() {
        commentDropDownDisplay.classList.toggle("show");
    });

    document.addEventListener("click", function(event) {
        if (!commentDropLink.contains(event.target)) {
            commentDropDownDisplay.classList.remove("show");
        }
    });
});