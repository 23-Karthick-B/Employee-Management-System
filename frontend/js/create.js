window.save = async () => {

  const emp = {
    name: document.getElementById("name").value,
    email: document.getElementById("email").value,
    department: document.getElementById("dept").value,
    phoneNumber: document.getElementById("phone").value,
    dod: document.getElementById("dod").value
  };

  try {
    const res = await fetch(BASE, {
      method: "POST",
      headers: getHeaders(true),
      body: JSON.stringify(emp)
    });

    if (!res.ok) throw await res.json();

    showSuccessPopup();

    setTimeout(() => {window.location.href = "index.html";}, 1500);

  } catch (err) {
    document.getElementById("error").innerText =
      err.message || "Create failed";
  }
};

(async () => {
  await protectPage();
})();