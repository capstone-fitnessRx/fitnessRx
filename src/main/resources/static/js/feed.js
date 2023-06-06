"use strict";

$(document).ready(function() {
    var commentDropLink = $(".dropdown-toggle");
    var commentDropDownDisplay = $(".dropdown-comments");

    commentDropLink.on("click", function() {
        commentDropDownDisplay.toggleClass("show");
    });

    $(document).on("click", function(event) {
        if (!commentDropLink.is(event.target) && !commentDropDownDisplay.has(event.target).length) {
            commentDropDownDisplay.removeClass("show");
        }
    });
});