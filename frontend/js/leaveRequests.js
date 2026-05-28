const BASE = "http://localhost:8080/api/admin/leave/pending";
loadLeaves();

async function loadLeaves() {

  try {
    const res = await fetch(BASE,{headers: {Authorization:`Bearer ${localStorage.getItem("token")}`}});

    if (!res.ok) {
      throw new Error( "Failed to load leaves");
    }

    const leaves = await res.json();

    const container =document.getElementById("leaveContainer");
    container.innerHTML = "";

    if (leaves.length === 0) {
      document.getElementById("emptyState").classList.remove("hidden");
      return;
    }

    document.getElementById("emptyState").classList.add( "hidden");

    leaves.forEach(leave => {
        const card = document.createElement( "div");
        card.id = `leave-${leave.id}`;

        card.className =
            `
            group
            bg-white/90
            backdrop-blur-lg
            border border-white/30
            rounded-3xl
            shadow-sm
            hover:shadow-2xl
            hover:-translate-y-1
            transition-all duration-300
            overflow-hidden
            `;


        card.innerHTML = `

          <div class="
            h-1.5
            bg-gradient-to-r
            from-blue-500
            via-indigo-500
            to-purple-500
          "></div>

          <div class="p-5">

            <div class="flex items-start justify-between">

              <div>

                <h2 class="
                  text-lg
                  font-black
                  text-gray-800
                  leading-tight
                ">

                  ${leave.employeeName}

                </h2>

                <p class="
                  text-sm
                  text-gray-500
                  mt-1
                ">

                  ${leave.department}

                </p>

              </div>

              <div class="
                px-3 py-1
                rounded-full
                bg-yellow-100
                text-yellow-700
                text-[11px]
                font-bold
                tracking-wide
                shadow-sm
              ">

                PENDING

              </div>

            </div>

            <div class="
              mt-4
              flex items-center gap-2
              text-sm
              text-gray-600
            ">

              <div class="
                w-8 h-8
                rounded-lg
                bg-blue-100
                flex items-center justify-center
              ">

                📅

              </div>

              <span class="font-semibold">

                ${leave.leaveDate}

              </span>

            </div>

            <div class="
              mt-4
              bg-gradient-to-r
              from-gray-50
              to-gray-100
              rounded-2xl
              p-4
              border border-gray-100
            ">

              <p class="
                text-xs
                uppercase
                tracking-wide
                text-gray-400
                font-bold
                mb-2
              ">

                Reason

              </p>

              <p class="
                text-sm
                text-gray-700
                leading-relaxed
              ">

                ${leave.reason}

              </p>

            </div>

            <div class="flex gap-3 mt-5">

              <button
                onclick="approveLeave(${leave.id})"
                class="
                  flex-1
                  py-2.5
                  rounded-xl
                  bg-gradient-to-r
                  from-green-500
                  to-emerald-600
                  hover:from-green-600
                  hover:to-emerald-700
                  text-white
                  font-bold
                  text-sm
                  shadow-lg
                  shadow-green-500/20
                  transition-all
                  duration-300
                "
              >

                Approve

              </button>

              <button
                onclick="rejectLeave(${leave.id})"
                class="
                  flex-1
                  py-2.5
                  rounded-xl
                  bg-gradient-to-r
                  from-red-500
                  to-rose-600
                  hover:from-red-600
                  hover:to-rose-700
                  text-white
                  font-bold
                  text-sm
                  shadow-lg
                  shadow-red-500/20
                  transition-all
                  duration-300
                "
              >

                Reject

              </button>

            </div>

          </div>
        `;

        container.appendChild(card);
      }
    );

  } catch (err) {alert(err.message);}
}

async function approveLeave(id) {
  await updateLeave(id, "approve", "Leave Approved Successfully");
}

async function rejectLeave(id) {
  await updateLeave(id,"reject","Leave Rejected Successfully");
}

async function updateLeave(id,action,message) {
  try {
    const res = await fetch(`http://localhost:8080/api/admin/leave/${id}/${action}`,
      {
        method: "PATCH",
        headers: {Authorization:`Bearer ${localStorage.getItem("token")}`}
      }
    );

    if (!res.ok) {
      throw new Error( "Action failed");
    }

    const card =document.getElementById(`leave-${id}`);

    card.classList.add("scale-95", "opacity-0");

    setTimeout(() => {card.remove();

      if ( document.getElementById("leaveContainer").children.length === 0) {
        document.getElementById("emptyState").classList.remove("hidden");
      }
    }, 300);

    showPopup(message);

  } catch (err) {alert(err.message);}
}

function showPopup(message) {

  document.getElementById("popupMessage").innerText = message;
  const popup =document.getElementById( "successPopup");
  popup.classList.remove( "hidden" );

  setTimeout(() => { popup.classList.add("hidden");}, 2500);
}