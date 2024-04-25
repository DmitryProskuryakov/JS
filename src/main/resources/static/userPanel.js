const urlUser = '/app/user';

const authUser = fetch(urlUser).then(response => response.json())
authUser.then(user => {
        let roles = '';
        user.authorities.forEach(role => {
            roles += ' '
            roles += role.name.substring(role.name.indexOf('_')+1)
        })
        document.getElementById("navbar-firstName").innerHTML = user.firstName
        document.getElementById("navbar-roles").innerHTML = roles
    }
)

async function getUserPage() {
    let page = await fetch(urlUser);

    if (page.ok) {
        let user = await page.json();
        getInformationAboutUser(user);
    } else {
        alert(`Error, ${page.status}`)
    }
}

function getInformationAboutUser(user) {
    const tableBody = document.getElementById('userTable');
    let dataHtml;
    let roles = [];
    console.log('userData', JSON.stringify(user))
    for (let role of user.authorities) {
        roles.push(" " + role.name.substring(role.name.indexOf('_')+1))
    }

    dataHtml =
        `<tr>
    <td>${user.id}</td>
    <td>${user.firstName}</td>
    <td>${user.lastName}</td>
    <td>${user.age}</td>
    <td>${user.email}</td>
    <td>${roles.join(',')}</td>

</tr>`;

    tableBody.innerHTML = dataHtml;
}

getUserPage();