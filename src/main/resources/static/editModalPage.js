const role_ed = document.getElementById('updateUserRoles');
const form_ed = document.getElementById('formForEditing');
const id_ed = document.getElementById('idUpdateUser');
const name_ed = document.getElementById('firstNameUpdateUser');
const lastname_ed = document.getElementById('lastNameUpdateUser')
const age_ed = document.getElementById('ageUpdateUser')
const email_ed = document.getElementById('emailUpdateUser');
const editModal = document.getElementById("exampleModal");
const closeEditButton = document.getElementById("updateFormCloseButton")
const bsEditModal = new bootstrap.Modal(editModal);

async function loadDataForEditModal(id) {
    const urlDataEd = '/app/admin/user' + '?id=' + id;
    const usersPageEd = await fetch(urlDataEd);

    if (usersPageEd.ok) {
        await usersPageEd.json().then(user => {
            console.log(user.firstName)
            console.log('userData', JSON.stringify(user))

            id_ed.value = user.id;
            name_ed.value = user.firstName;
            lastname_ed.value = user.lastName;
            age_ed.value = user.age;
            email_ed.value = user.email;


            const roleIds = user.authorities.map(role => Number(role.id));
            for (const option of role_ed.options) {
                if (roleIds.includes(Number(option.value))) {
                    option.setAttribute('selected', 'selected');
                } else {
                    option.removeAttribute('selected');
                }
            }
        })

        console.log("id_ed: " + id_ed.value + " !!")
        bsEditModal.show();
    } else {
        alert(`Error, ${usersPageEd.status}`)
    }
}

async function editUser() {
    let urlEdit = 'app/admin/edit?id=' + id_ed.value;
    let listOfRole = [];
    console.dir(form_ed)
    for (let i = 0; i < form_ed.roles.options.length; i++) {
        if (form_ed.roles.options[i].selected) {
            let tmp = {};
            tmp["id"] = form_ed.roles.options[i].value
            listOfRole.push(tmp);
        }
    }
    let method = {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            firstName: form_ed.firstName.value,
            lastName: form_ed.lastName.value,
            age: form_ed.age.value,
            email: form_ed.email.value,
            password: form_ed.password.value,
            listRoles: listOfRole
        })
    }
    console.log(urlEdit, method)
    await fetch(urlEdit, method).then(() => {
        closeEditButton.click();
        getAdminPage();
    })
}