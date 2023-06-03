var messageImgElements = document.getElementsByClassName("message-img");

for (var i = 0; i < messageImgElements.length; i++) {
    messageImgElements[i].addEventListener("click", function() {
        var content = document.getElementById("content");
        var hiddenContent = document.getElementById("hiddenContent");

        if (content.classList.contains("opened")) {
            content.style.maxHeight = "50px";
            hiddenContent.style.maxHeight = "0";
            content.classList.remove("opened");
        } else {
            content.style.maxHeight = "0";
            hiddenContent.style.maxHeight = hiddenContent.scrollHeight + "px";
            content.classList.add("opened");

            window.scrollTo({
                top: hiddenContent.offsetTop + hiddenContent.offsetHeight,
                behavior: "smooth"
            });
        }
    });
}