"use strict";

$(document).ready(function() {

    var anchorEdit = $("#modalEdit");
    var modalEdit = $("#myModalEdit");

//open
    anchorEdit.on("click", function(e) {
        e.preventDefault(); // Prevent the default anchor behavior
        console.log("clicked");

        modalEdit.css("display", "block");
    });

    // close
    $(".close").on("click", function() {

        modalEdit.css("display", "none");
    });


    $(document).on("click", function(event) {
        if (!$(event.target).closest(".modal-content").length && !$(event.target).is(anchorEdit)) {

            modalEdit.css("display", "none");
        }
    });
});

$(document).ready(function() {

    var anchorSettings = $("#modalSettings");
    var modalSettings = $("#myModalSettings");

  //open modal
    anchorSettings.on("click", function(e) {
        e.preventDefault();
        console.log("clicked");

        modalSettings.css("display", "block");
    });
//close modal
    $(".close").on("click", function() {
        // Hide the modal
        modalSettings.css("display", "none");
    });


    $(document).on("click", function(event) {
        if (!$(event.target).closest(".modal-content").length && !$(event.target).is(anchorSettings)) {

            modalSettings.css("display", "none");
        }
    });
});


$(document).ready(function() {

    var anchorFriends = $("#modalFriends");
    var modalFriends = $("#myModalFriends");

    //open modal
    anchorFriends.on("click", function(e) {
        e.preventDefault();
        console.log("clicked");

        modalFriends.css("display", "block");
    });
//close modal
    $(".close").on("click", function() {
        // Hide the modal
        modalFriends.css("display", "none");
    });


    $(document).on("click", function(event) {
        if (!$(event.target).closest(".modal-content").length && !$(event.target).is(anchorFriends)) {

            modalFriends.css("display", "none");
        }
    });
});