// app.js
$(document).ready(function() {
    // GET /projects/list
    $.ajax({
        url: '/projects/list',
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            let projects = response.map(project => {
                return `<li>${project.name} - ${project.description}</li>`;
            }).join('');
            $('#projects').html(`<ul>${projects}</ul>`);
        },
        error: function(error) {
            console.log(error);
        }
    });

        // Retrieve the HTML table body element
        const projectTableBody = document.querySelector('#projectTableBody');

        // Retrieve the list of projects using AJAX request
        fetch('/api/projects/list')
        .then(response => response.json())
        .then(projects => {
        // Loop through each project and create a new row in the HTML table
        projects.forEach(project => {
            const row = document.createElement('tr');
            row.innerHTML = `
          <td>
            <div class="d-flex px-2">
              <div>
                <img src="assets/img/small-logos/devto.svg" class="avatar avatar-sm rounded-circle me-2" alt="xd">
              </div>
              <div class="my-auto">
                <h6 class="mb-0 text-sm">${project.name}</h6>
              </div>
            </div>
          </td>
          <td>
            <p class="text-sm font-weight-bold mb-0">$${project.budget}</p>
          </td>
          <td>
            <span class="text-xs font-weight-bold">${project.status}</span>
          </td>
          <td class="align-middle text-center">
            <div class="d-flex align-items-center justify-content-center">
              <span class="me-2 text-xs font-weight-bold">${project.completion}%</span>
              <div>
                <div class="progress">
                  <div class="progress-bar bg-gradient-success" role="progressbar" aria-valuenow="${project.completion}" aria-valuemin="0" aria-valuemax="100" style="width: ${project.completion}%;"></div>
                </div>
              </div>
            </div>
          </td>
          <td class="align-middle">
            <button type="button" class="btn btn-outline-primary">View Project</button>
          </td>
        `;
            projectTableBody.appendChild(row);
        });
    })
        .catch(error => console.error(error));

    // GET /projects/{id}
    $.ajax({
        url: '/projects/1',
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            $('#project').html(`<p>${response.name} - ${response.description}</p>`);
        },
        error: function(error) {
            console.log(error);
        }
    });

    // POST /projects/add
    $.ajax({
        url: '/projects/add',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            name: 'New Project',
            description: 'This is a new project'
        }),
        success: function(response) {
            $('#addProject').html(`<p>${response.name} - ${response.description} added successfully</p>`);
        },
        error: function(error) {
            console.log(error);
        }
    });

    // PUT /projects/update
    $.ajax({
        url: '/projects/update',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            id: 1,
            name: 'Updated Project',
            description: 'This is an updated project'
        }),
        success: function(response) {
            $('#updateProject').html(`<p>${response.name} - ${response.description} updated successfully</p>`);
        },
        error: function(error) {
            console.log(error);
        }
    });

    // DELETE /projects/delete/{id}
    $.ajax({
        url: '/projects/delete/1',
        type: 'DELETE',
        success: function(response) {
            $('#deleteProject').html('<p>Project deleted successfully</p>');
        },
        error: function(error) {
            console.log(error);
        }
    });
});
