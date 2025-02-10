document.addEventListener('DOMContentLoaded', function() {
    checkAuthStatus();
});

async function addTask(event) {
    event.preventDefault();

    const formData = new FormData(event.target);
    const taskData = Object.fromEntries(formData.entries());

    taskData.employeeId = Number(taskData.employeeId);
    taskData.statusId = Number(taskData.statusId);

    try {
        const response = await fetch(`${API_BASE_URL}/tasks`, {
            method: 'POST',
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

        alert('Task added successfully.');
        window.location.href = '/tasks';

    } catch (error) {
        console.error('Adding task failed:', error);
        alert('Failed to add task. Check console for details.');
    }
}