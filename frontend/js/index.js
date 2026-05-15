
let page = 0;
let totalPages = 0;
let size = 5;
let sortBy = "id";
let direction = "asc";

let isSearching = false;
let searchName = "";
let searchDept = "";

async function load() {

  try {
    let url = "";

    if (isSearching) {
      url = `${BASE}/search?name=${encodeURIComponent(searchName)}&dept=${encodeURIComponent(searchDept)}&page=${page}&size=${size}&sortBy=${sortBy}&direction=${direction}`;

    } else {
      url =`${BASE}?page=${page}&size=${size}&sortBy=${sortBy}&direction=${direction}`;
    }

    const res = await fetch(url,{
      headers:getHeaders()
    });

    if (!res.ok) throw await res.json();

    const data = await res.json();

    totalPages = data.totalPages;
    page = data.number;

    render(data.content);

    updateSortIcons();
    updatePaginationButtons(data);

  } catch (err) {

    showError(err.message || "Failed to load employees");
  }
}

function render(list) {

  const table = document.getElementById("table");

  table.innerHTML = "";

  list.forEach(emp => {

    table.innerHTML += `
      <tr class="border-t hover:bg-gray-50">

        <td class="p-2">${emp.name}</td>
        <td>${emp.email}</td>
        <td>${emp.department}</td>
        <td>${emp.phoneNumber}</td>
        <td>${formatDate(emp.dod)}</td>

        <td>
          <button onclick="edit(${emp.id})"
            class="text-yellow-600">
            Edit
          </button>

          <button onclick="del(${emp.id})"
            class="text-red-600 ml-2">
            Delete
          </button>
        </td>

      </tr>
    `;
  });
}

function updateSortIcons() {

  const fields = ["name","email","department","dod"];

  fields.forEach(field => {
    const asc = document.getElementById(`${field}Asc`);
    const desc = document.getElementById(`${field}Desc`);

    if (!asc || !desc) return;

    asc.classList.remove( "text-black", "font-bold");
    desc.classList.remove("text-black","font-bold");
    asc.classList.add("text-gray-400");
    desc.classList.add("text-gray-400");

    if (field === sortBy) {

      if (direction === "asc") {
        asc.classList.remove("text-gray-400");
        asc.classList.add("text-black","font-bold");

      } else {
        desc.classList.remove("text-gray-400");
        desc.classList.add( "text-black","font-bold");
      }
    }
  });
}

function updatePaginationButtons(data) {

  const prevBtn = document.getElementById("prevBtn");
  const nextBtn = document.getElementById("nextBtn");

  prevBtn.disabled = data.first;
  nextBtn.disabled = data.last;

  prevBtn.classList.toggle("opacity-50",data.first);
  nextBtn.classList.toggle("opacity-50",data.last);
}

window.sort = (field) => {

  if (sortBy === field) {
    direction = direction === "asc" ? "desc" : "asc";

  } else {
    sortBy = field;
    direction = "asc";
  }

  page = 0;

  load();
};

window.search = async () => {

  searchName = document.getElementById("name").value;
  searchDept = document.getElementById("dept").value;
  isSearching = true;
  page = 0;
  load();
};

window.clearSearch = () => {

  document.getElementById("name").value = "";
  document.getElementById("dept").value = "";
  isSearching = false;
  searchName = "";
  searchDept = "";
  page = 0;
  load();
};

window.del = async (id) => {

  showDeleteModal(async () => {

    try {
      const res = await fetch(`${BASE}/${id}`, {method: "DELETE",headers: getHeaders()});

      if (!res.ok)
        throw await res.json();

      showDeleteSuccess();
      setTimeout(() => {load();}, 800);

    } catch (err) {

      showError(err.message || "Delete failed");
    }
  });
};


function showDeleteModal(onConfirm) {

  const modal = document.getElementById("deleteModal");
  modal.classList.remove("hidden");
  window.confirmDelete = () => {
    modal.classList.add("hidden");
    onConfirm();
  };

  window.closeDeleteModal = () => {
    modal.classList.add("hidden");
  };
}


function showDeleteSuccess() {
  
  const popup =document.getElementById("deleteSuccess");
  popup.classList.remove("hidden");
  setTimeout(() => {popup.classList.add("hidden");}, 2000);
}

window.edit = (id) => {
  window.location.href =`update.html?id=${id}`;
};

window.next = () => {
  if (page < totalPages - 1) {
    page++;
    load();
  }
};

window.prev = () => {
  if (page > 0) {
    page--;
    load();
  }
};

window.changePageSize = () => {
  size = Number(document.getElementById("pageSize").value);
  page = 0;
  load();
};

function showError(message) {

  const popup =document.getElementById("errorPopup");
  popup.innerText = message;
  popup.classList.remove("hidden");
  setTimeout(() => {popup.classList.add("hidden");}, 3000);
}

function formatDate(date) {

  if (!date) return "";
  return new Date(date).toLocaleDateString("en-GB");
}

(async () => {
  await protectPage();
  load();
})();