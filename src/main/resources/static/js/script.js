// JavaScript Document
document.addEventListener("DOMContentLoaded", () => {
	const nav = document.querySelector(".nav__link");
	const nav1 = document.querySelector("#btnNav");
	const nav2 = document.querySelector("#btnNav1");
	const nav3 = document.querySelector("#btnNav2");
				
		document.querySelector("#btnNav").addEventListener("click", () => {
			nav.classList.add("nav__link--active");
			nav2.classList.remove("nav__link--active");
			nav3.classList.remove("nav__link--active");
		});
				
		document.querySelector("#btnNav1").addEventListener("click", () => {
			nav2.classList.add("nav__link--active");
			nav1.classList.remove("nav__link--active");
			nav3.classList.remove("nav__link--active");
		});
				
		document.querySelector("#btnNav2").addEventListener("click", () => {
			nav3.classList.add("nav__link--active");
			nav2.classList.remove("nav__link--active");
			nav1.classList.remove("nav__link--active");
		});
				
});