$('#phone').mask('(00) 0000-00000');

let alunos = [];
let courses = [];
let periods = ["Manhã", "Tarde", "Noite"];

getCourses();
getAlunos();

function getCourses() {
  $.ajax({
    url: "http://localhost:8080/courses",
    type: "GET",
    async: false,
    success: (response) => {
      courses = response;
    }
  });
}

function getAlunos() {
  $.getJSON("http://localhost:8080/students", (response) => {
    alunos = response;
    for (let aluno of alunos) {
      addRow(aluno)
    }
  });
}

function addRow(data) {
  const tableBody = document.getElementById("tableBody");
  tableBody.innerHTML += `
    <tr>
        <td>${data.id}</td>
        <td>${data.name}</td>
        <td class="d-none d-md-table-cell">${data.email}</td>
        <td class="d-none d-md-table-cell">${data.phone}</td>
        <td class="d-none d-md-table-cell">${courses[data.idCourse - 1].name}</td>
        <td class="d-none d-md-table-cell">${periods[data.period - 1]}</td>
    </tr>
  `
}

function addStudent() {
  const nameText = document.getElementById("name").value;
  const emailText = document.getElementById("email").value;
  const phoneText = document.getElementById("phone").value;
  const courseText = document.getElementById("courses").value;
  const shiftText = document.querySelector('input[name="inputRadioTurno"]:checked').value;

  const aluno = {
    id: (alunos.length + 1),
    name: nameText,
    email: emailText,
    phone: phoneText,
    course: courseText,
    shift: shiftText
  }

  alunos.push(aluno);
  addRow(aluno);

  const form = document.getElementById("formNewStudent");
  form.reset();
}