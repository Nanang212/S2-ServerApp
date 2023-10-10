const setCaret = (el) => {
    if (!el) return;

    try {
        const range = document.createRange();
        const sel = window.getSelection();

        range.setStart(el.childNodes[0], el.innerText.length);
        range.collapse(true);

        sel.removeAllRanges();
        sel.addRange(range);
    } catch (err) {
        console.log("Error Setting Caret: ", err);
    }
};

const togglePassword = (button) => {
    button.classList.toggle("showing");
    const input = document.getElementById("password");
    input.type = input.type === "password" ? "text" : "password";
    input.focus();
    setCaret(input);
};

async function submitForm(event) {
    event.preventDefault(); // Prevent the default form submission

    try {
        const username = document.getElementById("username").value;
        const phone = document.getElementById("phone").value;
        const password = document.getElementById("password").value;
        const urlParams = new URLSearchParams(window.location.search);
        const myParam = urlParams.get('token');

        const formData = {
            username: username,
            phone: phone,
            password: password,
            token: myParam
        };

        const response = await axios.put("/register", formData, {
            headers: {
                "Content-Type": "application/json"
            }
        });

        console.log("Response from Spring Controller:", response.data);
        // Optionally, redirect to another page or perform other actions based on response.data
    } catch (error) {
        console.error("An error occurred:", error);
    }
}