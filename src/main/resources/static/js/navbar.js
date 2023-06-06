"use strict"
document.addEventListener("DOMContentLoaded", function () {
    var navdropButton = document.querySelector(".dropdown-toggle");

    var navdropdownMenu = document.querySelector(".dropdown-menu");

    navdropButton.addEventListener("click", function() {
        navdropdownMenu.classList.toggle("show");
    });

    document.addEventListener("click", function(event) {
        if (!navdropButton.contains(event.target)) {
            navdropdownMenu.classList.remove("show");
        }
    });




    $(document).ready(function() {
        // Get the anchor and modal elements
        var anchorEdit = $("#modalEdit");
        var modalEdit = $("#myModalEdit");

        // Handle click event on the anchor
        anchorEdit.on("click", function(e) {
            e.preventDefault(); // Prevent the default anchor behavior

            // Show the modal
            modalEdit.css("display", "block");
        });

        // Handle click event on the close button
        $(".close").on("click", function() {
            // Hide the modal
            modalEdit.css("display", "none");
        });

        // Handle click event outside the modal content to close the modal
        $(window).on("click", function(event) {
            if (event.target == modalEdit[0]) {
                // Hide the modal
                modalEdit.css("display", "none");
            }
        });
    });


    $(document).ready(function() {
        // Get the anchor and modal elements
        var anchorSettings = $("#modalSettings");
        var modalSettings = $("#myModalSettings");

        // Handle click event on the anchor
        anchorSettings.on("click", function(e) {
            e.preventDefault(); // Prevent the default anchor behavior

            // Show the modal
            modalSettings.css("display", "block");
        });

        // Handle click event on the close button
        $(".close").on("click", function() {
            // Hide the modal
            modalSettings.css("display", "none");
        });

        // Handle click event outside the modal content to close the modal
        $(window).on("click", function(event) {
            if (event.target == modalSettings[0]) {
                // Hide the modal
                modalSettings.css("display", "none");
            }
        });
    });
});