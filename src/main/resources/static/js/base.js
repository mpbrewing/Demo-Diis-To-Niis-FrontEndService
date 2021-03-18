// JavaScript Document
/* exported over normal overs normals*/
function over(x) {
  x.style.filter = "invert(76%) sepia(0%) saturate(576%) hue-rotate(230deg) brightness(96%) contrast(92%)"
}

function normal(x) {
  x.style.filter = "invert(98%) sepia(0%) saturate(9%) hue-rotate(162deg) brightness(90%) contrast(93%)";
}

let ids = ['b1', 'b2', 'b3', 'b4'];
let icons = ['icon1', 'icon2', 'icon3', 'icon4'];

function overs(x, x_id) {
  x.style.color = "#ffffff";
  x.style.borderColor = "transparent";
  x.style.backgroundColor = "#66cc33"
  x.style.filter = "none";
  var i;
  for (i = 0; i < ids.length; i++) {
    if (ids[i] == x_id) {
      let testing = document.getElementById(icons[i]);
      testing.style.filter = "invert(99%) sepia(0%) saturate(2303%) hue-rotate(277deg) brightness(114%) contrast(100%)";
    }
  }
}

function normals(x, x_id) {
  x.style.color = "#000000";
  x.style.borderColor = "#000000";
  x.style.backgroundColor = "transparent";
  x.style.filter = "invert(61%) sepia(56%) saturate(461%) hue-rotate(59deg) brightness(98%) contrast(88%)";

  var i;
  for (i = 0; i < ids.length; i++) {
    if (ids[i] == x_id) {
      let testing = document.getElementById(icons[i]);
      testing.style.filter = "none";
    }
  }
}

document.addEventListener("DOMContentLoaded", () => {

  document.querySelectorAll('.hold').forEach(item => {
    item.addEventListener('focusin', event => {
      event.target.style.borderColor = '#66cc33';
      let id = event.target.getAttribute('id');
      let testing = document.getElementById(id);
      var testLabel = testing.labels[0]
      testLabel.classList.add("active");
    })

    item.addEventListener('focusout', (event) => {
      if (event.target.value.length > 0) {
        event.target.style.borderColor = '#66cc33';
      } else {
        event.target.style.borderColor = '#cccccc';
        let id = event.target.getAttribute('id');
        let testing = document.getElementById(id);
        var testLabel = testing.labels[0]
        testLabel.classList.remove("active");
      }
    });
  })

});
