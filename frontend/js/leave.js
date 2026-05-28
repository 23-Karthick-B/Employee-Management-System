async function applyLeave() {

  const leaveDate = document.getElementById("leaveDate").value;
  const reason = document.getElementById("reason").value.trim();
  const errorEl =document.getElementById("error");

  errorEl.innerText = "";

  if (!leaveDate) {
    errorEl.innerText ="Please select a leave date";
    return;
  }

  if (!reason) {
    errorEl.innerText ="Please enter leave reason";
    return;
  }


  const leaveData = {
    leaveDate: leaveDate,
    reason: reason
  };

  try {

    const res = await fetch( "http://localhost:8080/employee/leave",
      {
        method: "POST",
        headers: { "Content-Type":"application/json",
                    Authorization: `Bearer ${localStorage.getItem("token")}`
        },
        body:JSON.stringify(leaveData)
      }
    );

    if (!res.ok) {

      const msg = await res.text();
      throw new Error( msg || "Failed to apply leave");
    }

    const popup =document.getElementById( "successPopup");
    popup.classList.remove("hidden");

    document.getElementById( "leaveDate").value = "";
    document.getElementById("reason").value = "";

    setTimeout(() => {window.location.href ="dashboard.html";}, 2500);

  } catch (err) {
    errorEl.innerText = err.message;
  }
}