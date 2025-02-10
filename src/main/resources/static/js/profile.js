document.addEventListener('DOMContentLoaded', function() {
    checkAuthStatus();
    fetchProfile();
});

async function fetchProfile() {
    const role = getRole();
    try {
        const response = await fetch(`${API_BASE_URL}/auth/me`, { // Use /auth/me for admin for common fields
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${getCookie('token')}`
            }
        });

        if (await handleFetchError(response)) {
            return;
        }

        const profileData = await response.json();
        displayProfile(profileData, role);

    } catch (error) {
        console.error('Fetching admin profile failed:', error);
        alert('Failed to load admin profile information. Check console for details.');
        const profileDetailsContainer = document.getElementById('profileDetails');
        if (profileDetailsContainer) {
            profileDetailsContainer.textContent = "Failed to load admin profile.";
        }
    }
}

function displayProfile(profile, role) {
    const profileDetailsContainer = document.getElementById('profileDetails');
    if (profileDetailsContainer) {
        profileDetailsContainer.innerHTML = ''; // Clear loading message

        let profileHTML = `
            <h3>Personal Information</h3>
            <p><strong>ID:</strong> ${profile.id}</p>
            <p><strong>Name:</strong> ${profile.name}</p>
            <p><strong>Surname:</strong> ${profile.surname}</p>
            <p><strong>Email:</strong> ${profile.email}</p>
            <p><strong>Phone:</strong> ${profile.phone || 'N/A'}</p>
            <p><strong>Address:</strong> ${profile.address || 'N/A'}</p>
            <p><strong>Age:</strong> ${profile.age != null ? profile.age : 'N/A'}</p>
            <p><strong>Date of Birth:</strong> ${profile.dateOfBirth || 'N/A'}</p>
        `;

        if (role === 'student') {
            profileHTML += `
                <hr/>
                <h3>Student Information</h3>
                <p><strong>University:</strong> ${profile.university || 'N/A'}</p>
                <p><strong>GPA:</strong> ${profile.gpa != null ? profile.gpa : 'N/A'}</p>
            `;
        } else if (role === 'employee') {
            profileHTML += `
                <hr/>
                <h3>Employee Information</h3>
                <p><strong>Company:</strong> ${profile.company || 'N/A'}</p>
                <p><strong>Position:</strong> ${profile.position || 'N/A'}</p>
                <p><strong>Salary:</strong> ${profile.salary != null ? profile.salary : 'N/A'}</p>
            `;
        } else if (role === 'admin') {
            profileHTML += `<hr/><p><strong>Role:</strong> Admin</p>`; // Indicate Admin Role, can customize further
        }

        profileDetailsContainer.innerHTML = profileHTML;
    }
}