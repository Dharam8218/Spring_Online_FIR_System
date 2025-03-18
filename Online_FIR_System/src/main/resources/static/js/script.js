document.addEventListener("DOMContentLoaded", function() {
    console.log("DOM fully loaded and parsed.");

    // Create a URLSearchParams object to check the query parameters
    var urlParams = new URLSearchParams(window.location.search);
    var messageContainer = document.getElementById("message");
    var container = document.querySelector(".form");

    if (!messageContainer) {
        console.error("Message container not found!");
        return;
    }

    var messageAdded = false;

    // Check for 'error' parameter
    if (urlParams.has("error")) {
        console.log("Error parameter detected:", urlParams.get("error"));
        var errorMessage = document.createElement("p");
        errorMessage.classList.add("invalid");
        errorMessage.textContent = "Invalid username or password";
        messageContainer.appendChild(errorMessage);
        messageAdded = true;
    }

    // Check for 'logout' parameter
    if (urlParams.has("logout")) {
        console.log("Logout parameter detected:", urlParams.get("logout"));
        var logoutMessage = document.createElement("p");
        logoutMessage.classList.add("success");
        logoutMessage.textContent = "You have been logged out";
        messageContainer.appendChild(logoutMessage);
        messageAdded = true;
    }

    // If a message was added, increase the container height to 500px
    if (messageAdded && container) {
        container.style.height = "450px";
        console.log("Container height updated to 500px");
    } else {
        console.log("No messages added.");
    }
});





