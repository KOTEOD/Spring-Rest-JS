$(async function() {
    await newUser();
});
async function newUser() {
    await fetch("http://localhost:8080/api/admin")
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                let el = document.createElement("option");
                el.text = role.name.substring(5);
                el.value = role.id;
                $('#newUserRoles')[0].appendChild(el);
            })
        })
    const form = document.forms["formNewUser"];
    form.addEventListener('submit', addNewUser)
    function addNewUser(e) {
        e.preventDefault();
        let newUserRoles = [];
        if (form.roles !== undefined) {
            for (let i = 0; i < form.roles.options.length; i++) {
                if (form.roles.options[i].selected) newUserRoles.push({
                    id: form.roles.options[i].value,
                    name: "ROLE_" + form.roles.options[i].text
                })
            }
        }
        fetch("http://localhost:8080/api/admin", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: form.username.value,
                email: form.email.value,
                password: form.password.value,
                roles: newUserRoles
            })
        }).then(() => {
            form.reset();
            allUsers();
   
            $('#editFormCloseButton').click();
        })
    }
}