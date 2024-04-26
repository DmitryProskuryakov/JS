const urlAdm = 'app/admin/users';
const currentUserURL = '/app/admin/currentUser';

const authAdm = fetch(currentUserURL).then(response => response.json())
authAdm.then(user => {
    let roles = '';
    user.authorities.forEach(role => {
        roles += ' '
        roles += role.name.substring(role.name.indexOf('_') + 1)
    })
    document.getElementById("navbar-firstName").innerHTML = user.firstName
    document.getElementById("navbar-roles").innerHTML = roles
})

async function getAdminPage() {
    let page = await fetch(urlAdm);

    if (page.ok) {
        let listUsers = await page.json();
        getTable(listUsers);
    } else {
        alert(`Error, ${page.status}`)
    }
}

function getTable(listUsers) {
    const tableBody = document.getElementById('adminTable');
    let dataHtml = '';
    for (let user of listUsers) {
        let roles = [];
        for (let role of user.authorities) {
            if (role.name) {
                roles.push(" " + role.name.substring(role.name.indexOf('_') + 1));
            }
        }
        dataHtml += `<tr>
    <td>${user.id}</td>
    <td>${user.firstName}</td>
    <td>${user.lastName}</td>
    <td>${user.age}</td>
    <td>${user.email}</td>
    <td>${roles}</td>
    <td>
        <button type="button" class="btn btn-primary" data-bs-toogle="modal"
        data-bs-target="#editModal" 
        onclick="loadDataForEditModal(${user.id})">Edit</button>
    </td>
        
    <td>
        <button class="btn btn-danger" data-bs-toogle="modal"
        data-bs-target="#deleteModal" 
        onclick="deleteModalData(${user.id})">Delete</button>
    </td>
   
</tr>`
    }
    tableBody.innerHTML = dataHtml;
}

getAdminPage();