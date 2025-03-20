// Show details when a row is clicked
function showDetails(complain) {
    document.getElementById('username').innerText = complain.userResponse.userName;
    document.getElementById('email').innerText = complain.userResponse.email;
    document.getElementById('mobileNo').innerText = complain.userResponse.mobileNo;
    document.getElementById('policeStation').innerText = complain.nearestPoliceStation;
    document.getElementById('complainType').innerText = complain.complainType;
    document.getElementById('placeOfIncident').innerText = complain.placeOfIncident;
    document.getElementById('description').innerText = complain.description;
    document.getElementById('status').innerText = complain.statusOfComplaint;

    // Set evidence link dynamically
    if (complain.evidence) {
        document.getElementById('evidenceLink').href = '/OnlineFirSystem/view-evidence/' + complain.evidence;
        document.getElementById('evidenceLink').innerText = "View Evidence";
    } else {
        document.getElementById('evidenceLink').innerText = "No Evidence Available";
        document.getElementById('evidenceLink').removeAttribute('href');
    }

    document.getElementById('detailsSection').style.display = 'block';
}

// Hide the details section
function hideDetails() {
    document.getElementById('detailsSection').style.display = 'none';
}
