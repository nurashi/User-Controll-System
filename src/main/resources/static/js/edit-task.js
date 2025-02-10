document.addEventListener('DOMContentLoaded', function() {
  checkAuthStatus();
  loadTaskForEdit();
});

async function loadTaskForEdit() {
  const urlParams = new URLSearchParams(window.location.search);
  const taskId = urlParams.get('id');
  if (taskId) {
      document.getElementById('taskId').value = taskId;
      document.getElementById('taskIdHidden').value = taskId;
      await fetchTaskForEdit(taskId);
  } else {
      alert('Task ID not provided for editing.');
      window.location.href = '/tasks';
  }
}

async function fetchTaskForEdit(taskId) {
  try {
      const response = await fetch(`/api/tasks/${taskId}`, {
          method: 'GET',
          headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${getCookie('token')}`
          }
      });

      if (!response.ok) {
          if (response.status === 404) {
              alert('Task not found.');
              window.location.href = '/tasks';
              return;
          }
          const message = `Error! status: ${response.status}`;
          throw new Error(message);
      }

      const task = await response.json();
      populateEditForm(task);

  } catch (error) {
      console.error('Fetching task details failed:', error);
      alert('Failed to load task details for editing. Check console for details.');
      window.location.href = '/tasks'; // Redirect back to tasks page on error
  }
}

function populateEditForm(task) {
  document.getElementById('taskTitle').value = task.title;
  document.getElementById('taskDescription').value = task.description;
  document.getElementById('employeeId').value = task.employeeId;
  document.getElementById('statusId').value = task.statusId;
}


async function updateTask(event) {
  event.preventDefault();

  const formData = new FormData(event.target);
  const taskData = Object.fromEntries(formData.entries());
  const taskId = taskData.id;

  taskData.employeeId = Number(taskData.employeeId);
  taskData.statusId = Number(taskData.statusId);

  try {
      const response = await fetch(`${API_BASE_URL}/tasks/${taskId}`, {
          method: 'PUT',
          headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${getCookie('token')}`
          },
          body: JSON.stringify(taskData)
      });

      if (!response.ok) {
          const message = `Error! status: ${response.status}`;
          throw new Error(message);
      }

      alert('Task updated successfully.');
      window.location.href = '/tasks';

  } catch (error) {
      console.error('Updating task failed:', error);
      alert('Failed to update task. Check console for details.');
  }
}

function logout() {
  deleteCookie('token');
  window.location.href = '/login';
}