function getAllGroupMembers2() {
    fetch("api/groupmembers/all")
            .then(res => res.json())
            .then(data => {
                let tableString = "<table> <tr> <th>Name</th><th>Student Id</th><th>Color</th></tr>";
                let groupMemberTableArray = data.map(data => data = `<tr><td>${data.name} </td><td>${data.studentId} </td><td>${data.color}</td></tr>`);
                groupMemberTableArray.forEach(data => {
                    tableString += data;
                });
                tableString += "</table>";
                document.getElementById("groupMemberTable").innerHTML = tableString;
                console.log("Page reloaded");
            });

}

function getAllGroupMembers() {
    fetch("api/groupmembers/all")
            .then(res => res.json())
            .then(data => populateTable(data));
    console.log("Page reloaded");

}
;

const populateTable = data => {
    const dataArray = data.map(data => `<tr><td>${data.name}</td><td>${data.studentId}</td><td>${handleColor(data.color)}</td></tr>`);
    document.getElementById('tableBody').innerHTML = dataArray.join('');
};

const handleColor = dataColor => {
    switch(dataColor){
        case 'Red':
            return '<span class="badge badge-danger">Red</span>';
        case 'Yellow':
            return '<span class="badge badge-warning">Yellow</span>';
        default:
            return '<span class="badge badge-success">Green</span>';
            
    }
};



document.getElementById('reloadNames').addEventListener('click', getAllGroupMembers);
document.addEventListener('DOMContentLoaded', getAllGroupMembers);

