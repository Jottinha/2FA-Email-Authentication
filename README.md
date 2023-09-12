
# 2FA (two-factor authentication)

This is a personal study project to create an email authentication system, including registration and login screens. The goal is to develop both the front-end and back-end of the system.

<div>
  <img  src="https://github.com/Jottinha/2FA-Email-Authentication/assets/69482936/fa70ff1f-5029-4cf3-9f8b-543ea93f0375"  alt="Log in     page"  width="300"  height="250">
  <img  src="https://github.com/Jottinha/2FA-Email-Authentication/assets/69482936/f6ed372d-4d9a-4a1e-ab78-0a4084234a57"  alt="Sing up   page"  width="300"  height="250">
</div>

<div>
  <img  src="https://github.com/Jottinha/2FA-Email-Authentication/assets/69482936/70c82565-4069-41fa-a98a-6c2a5e49984b"  alt="Log in page"  width="300"  height="250">
  <img  src="https://github.com/Jottinha/2FA-Email-Authentication/assets/69482936/cc381e84-4d97-4411-b74a-21aba1b938be"  alt="Log in page"  width="300"  height="250"> 
</div>


## Implemented Features and Next Steps

### Front-End
- [x] Make the login, register and authenticate pages recursive.

User registration page

- [x] User registration screen where users can enter their email and password.
- [x] Validation for a valid email address during the registration process.
- [x] Validation for matching passwords during the registration process.
- [x] Check if the password entry is null.
- [x] Animate sign up button
- [x] Function to try to register the new user using the post method.

User log in page
- [x] Login screen where users can enter their email and password to access the system.
- [x] Animte sing in button
- [x] Function to try to login using the post method.
- [x] Call authentication page after login.

User authentication page
- [x] Create authentication page in html.
- [x] Style css to the html authentication page.
- [x] Function that checks if the user input code matches the sent email code.
- [x] Function that send a request to the back-end to send an email.
- [x] Create a confirm text or a new page to show on screen that user made register.


### Back-End
- [x] Create MVC Java project with Spring Boot.
- [x] Create a user class and make the relation dataBase.
- [x] Create API to contact with front-end.
- [x] Config swagger in back-end.
- [x] Create encryption to save passwords in dataBase.
- [x] Create methods to send an e-mail authentication to the user.

## Technologies Used

### Front-End
<div>
<img  aling="center"  alt="joao-html"  height="40"  width="50"  src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/html5/html5-original.svg">
<img  aling="center"  alt="joao-css"  height="40"  width="50"  src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/css3/css3-original.svg">
<img  aling="center"  alt="joao-javascript"  height="40"  width="50"  src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/javascript/javascript-original.svg">
</div>

### Back-End

<div>
<img  aling="center"  alt="joao-java"  height="40"  width="50"  src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg">
<img  aling="center"  alt="joao-spring"  height="40"  width="50"  src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg">
</div>

## How to Run the Project

1. Clone this repository to your local machine.

2. Open the `index.html`, `confirma.html` and `registro.html` file that is present in Auth-FrontEnd folter and open in your browser to access the login and register screen.

3. Open Auth-BackEnd folder and run the back-end Java, the database is the h2, so you don't need worry about conection.

4. You need to alter the properties of the file in the back-end with the email and password that will be the host that will send the emails. **(You need to setup app password in your email to use.)**

5. Open http://localhost:8080/swagger-ui/index.html after start back-end to acces swagger documentation.

## Contribution

This project is just an example to be studied and is not open to external contributions. But if you have any questions or suggestions, feel free to get in touch.

## License

This project is licensed under the [MIT License](LICENSE).
