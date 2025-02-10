document.addEventListener("DOMContentLoaded", function () {
  checkAuthStatus();
  fetchTasks();
});

async function fetchTasks() {
  try {
    const response = await fetch(`${API_BASE_URL}/tasks`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${getCookie("token")}`,
      },
    });

    if (await handleFetchError(response)) {
      return;
    }

    const tasks = await response.json();
    displayTasks(tasks);
  } catch (error) {
    console.error("Fetching tasks failed:", error);
    alert("Failed to load tasks. Check console for details.");
  }
}

function displayTasks(tasks) {
  const addTasksButton = document.getElementById("add-new-task-button");
  const role = getRole()
  if(role === "admin"){
    addTasksButton.hidden = false;
  }
  const tasksContainer = document.getElementById("tasksList");
  if (tasksContainer) {
    tasksContainer.innerHTML = "";
    if (tasks && tasks.length > 0) {
      const table = document.createElement("table");
      table.className = "table table-striped";
      const thead = document.createElement("thead");
      thead.innerHTML = `
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Employee</th>
                    <th>Status</th>
                    <th>Created At</th>
                    <th>Updated At</th>
                    <th>Actions</th>
                </tr>
            `;
      table.appendChild(thead);
      const tbody = document.createElement("tbody");
      tasks.forEach((task) => {
        const row = document.createElement("tr");
        row.innerHTML = `
                    <td>${task.id}</td>
                    <td>${task.title}</td>
                    <td>${task.description}</td>
                    <td>${task.employeeName}</td>
                    <td>${task.statusName}</td>
                    <td>${new Date(task.createdAt).toLocaleString()}</td>
                    <td>${new Date(task.updatedAt).toLocaleString()}</td>
                    <td>
                        <button class="btn btn-sm btn-warning" onclick="editTaskForm(${
                          task.id
                        })">Edit</button>
                        <button class="btn btn-sm btn-danger" onclick="deleteTask(${
                          task.id
                        })">Delete</button>
                    </td>
                `;
        tbody.appendChild(row);
      });
      table.appendChild(tbody);
      tasksContainer.appendChild(table);
    } else {
      tasksContainer.textContent = "No tasks available.";
    }
  }
}

async function fetchTask(taskId) {
  try {
    const response = await fetch(`${API_BASE_URL}/tasks/${taskId}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      if (response.status === 404) {
        alert("Task not found.");
        return;
      }
      const message = `Error! status: ${response.status}`;
      throw new Error(message);
    }

    const task = await response.json();
    displayTaskDetails(task);
  } catch (error) {
    console.error("Fetching task details failed:", error);
    alert("Failed to load task details. Check console for details.");
  }
}

function displayTaskDetails(task) {
  const taskDetailsContainer = document.getElementById("taskDetails");
  if (taskDetailsContainer) {
    taskDetailsContainer.innerHTML = `
            <h3>Task Details</h3>
            <p><strong>ID:</strong> ${task.id}</p>
            <p><strong>Title:</strong> ${task.title}</p>
            <p><strong>Description:</strong> ${task.description}</p>
            <p><strong>Employee ID:</strong> ${task.employeeId}</p>
            <p><strong>Employee Name:</strong> ${task.employeeName}</p>
            <p><strong>Status ID:</strong> ${task.statusId}</p>
            <p><strong>Status Name:</strong> ${task.statusName}</p>
            <p><strong>Created At:</strong> ${new Date(
              task.createdAt
            ).toLocaleString()}</p>
            <p><strong>Updated At:</strong> ${new Date(
              task.updatedAt
            ).toLocaleString()}</p>
            <button class="btn btn-secondary" onclick="closeTaskDetails()">Close</button>
        `;
    taskDetailsContainer.style.display = "block";
  }
}

function closeTaskDetails() {
  const taskDetailsContainer = document.getElementById("taskDetails");
  if (taskDetailsContainer) {
    taskDetailsContainer.style.display = "none";
  }
}

async function deleteTask(taskId) {
  if (!confirm("Are you sure you want to delete this task?")) {
    return;
  }

  try {
    const response = await fetch(`${API_BASE_URL}/tasks/${taskId}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${getCookie("token")}`,
      },
    });

    if (await handleFetchError(response)) {
      return;
    }

    alert("Task deleted successfully.");
    fetchTasks();
  } catch (error) {
    console.error("Deleting task failed:", error);
    alert("Failed to delete task. Check console for details.");
  }
}

function editTaskForm(taskId) {
  window.location.href = `/edit-task?id=${taskId}`;
}
