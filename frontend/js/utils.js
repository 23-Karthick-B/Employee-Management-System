function formatDate(dateStr) {

  if (!dateStr) return "-";

  const date = new Date(dateStr);

  return date.toLocaleDateString("en-GB", {
    day: "2-digit",
    month: "short",
    year: "numeric"
  });
}

function showError(message) {
  alert(message || "Something went wrong");
}