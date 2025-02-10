document.addEventListener("DOMContentLoaded", function () {
  checkAuthStatus();
  checkAdminRole();
  fetchEmployees();
});

function checkAdminRole() {
  const role = getRole();
  if (role !== "admin") {
    alert("Admin access required.");
    window.location.href = "/"; // Redirect to home page if not admin
  }
}

async function fetchEmployees() {
  try {
    const response = await fetch(`${API_BASE_URL}/employees`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${getCookie("token")}`,
      },
    });

    if (await handleFetchError(response)) {
      return;
    }

    const employees = await response.json();
    displayEmployees(employees);
  } catch (error) {
    console.error("Fetching employees failed:", error);
    alert("Failed to load employees. Check console for details.");
  }
}

function displayEmployees(employees) {
  const employeesContainer = document.getElementById("employeesList");
  if (employeesContainer) {
    employeesContainer.innerHTML = "";
    if (employees && employees.length > 0) {
      const table = document.createElement("table");
      table.className = "table table-striped";
      const thead = document.createElement("thead");
      thead.innerHTML = `
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Email</th>
                    <th>Company</th>
                    <th>Position</th>
                    <th>Salary</th>
                    <th>Actions</th>
                </tr>
            `;
      table.appendChild(thead);
      const tbody = document.createElement("tbody");
      employees.forEach((employee) => {
        const row = document.createElement("tr");
        row.innerHTML = `
                    <td>${employee.id}</td>
                    <td>${employee.name}</td>
                    <td>${employee.surname}</td>
                    <td>${employee.email}</td>
                    <td>${employee.company}</td>
                    <td>${employee.position}</td>
                    <td>${employee.salary}</td>
                    <td>
                        <button class="btn btn-sm btn-danger" onclick="deleteEmployee(${employee.id})">Delete</button>
                    </td>
                `;
        tbody.appendChild(row);
      });
      table.appendChild(tbody);
      employeesContainer.appendChild(table);
    } else {
      employeesContainer.textContent = "No employees available.";
    }
  }
}

async function deleteEmployee(employeeId) {
  if (!confirm("Are you sure you want to delete this employee?")) {
    return;
  }

  try {
    const response = await fetch(`${API_BASE_URL}/employees/${employeeId}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${getCookie("token")}`,
      },
    });

    if (await handleFetchError(response)) {
      return;
    }

    alert("Employee deleted successfully.");
    fetchEmployees(); // Refresh employee list after deletion
  } catch (error) {
    console.error("Deleting employee failed:", error);
    alert("Failed to delete employee. Check console for details.");
  }
}
