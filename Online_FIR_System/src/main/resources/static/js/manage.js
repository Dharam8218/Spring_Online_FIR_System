function submitForm(actionType) {
    const form = document.getElementById('manageComplaintForm');
    const uniqueID = document.getElementById('uniqueID').value;

    if (!uniqueID) {
        alert("Please enter a Unique ID!");
        return;
    }

    let actionUrl = '';
    let method = 'get'; // Default for Check Status & Get Complaint Details

    if (actionType === 'delete') {
        if (!confirm("Are you sure you want to delete your complaint?")) {
            return;
        }
        actionUrl = `/OnlineFirSystem/delete-complain/${uniqueID}`;
        method = 'post'; // HTML forms don't support DELETE directly
        form.innerHTML += '<input type="hidden" name="_method" value="DELETE">';
    } else if (actionType === 'status') {
        actionUrl = `/OnlineFirSystem/check-status/${uniqueID}`;
    } else if (actionType === 'details') {
        //actionUrl = `/OnlineFirSystem/get-complain-details/${uniqueID}`;
        actionUrl = `/OnlineFirSystem/get-complain-response/${uniqueID}`;
    }else if(actionType === 'update'){
       window.location.href="/update-complain?uniqueID="+ encodeURIComponent(uniqueID);
       return;
    }

    form.action = actionUrl;
    form.method = method;
    form.submit();
}

console.log("script.js loaded successfully!");