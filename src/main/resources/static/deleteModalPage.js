const id_del = document.getElementById('idDeleteUser');
const name_del = document.getElementById('firstNameDeleteUser');
const lastname_del = document.getElementById("lastNameDeleteUser")
const age_del = document.getElementById("ageDeleteUser")
const email_del = document.getElementById('emailDeleteUser');
const role_del = document.getElementById("deleteUserRoles")
const deleteModal = document.getElementById("deleteModal");
const closeDeleteButton = document.getElementById("closeDelete")
const bsDeleteModal = new bootstrap.Modal(deleteModal);

async function deleteModalData(id) {
    const urlForDel = 'app/admin/user?id=' + id;
    let usersPageDel = await fetch(urlForDel);
    if (usersPageDel.ok) {
        let userData =
            await usersPageDel.json().then(user => {
                id_del.value = `${user.id}`;
                name_del.value = `${user.firstName}`;
                lastname_del.value = `${user.lastName}`;
                age_del.value = `${user.age}`;
                email_del.value = `${user.email}`;
                role_del.value = user.authorities.map(r => r.name.substring(r.name.indexOf('_') + 1)).join(", ");
            })

        bsDeleteModal.show();
    } else {
        alert(`Error, ${usersPageDel.status}`)
    }
}

async function deleteUser() {
    let urlDel = 'app/admin/delete?id=' + id_del.value;
    let method = {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json"
        }
    }

    fetch(urlDel, method).then(() => {
        closeDeleteButton.click();
        getAdminPage();
    })
}