
  

# 2FA (two-factor authentication)

This is a personal study project to create an email authentication system, including registration and login screens. The goal is to develop both the front-end and back-end of the system.

<img  src="https://github.com/Jottinha/auth/assets/69482936/0c332a9e-89a4-4d14-a79d-ac8553ccdb83"  alt="Log in page"  width="300"  height="300">
<img  src="https://github.com/Jottinha/auth/assets/69482936/9437f239-a953-4dad-8df3-b90177b41a80"  alt="Sing up page"  width="300"  height="300">

## Implemented Features and Next Steps

### Front-End
- [ ] Make the login, register and authenticate pages recursive.

User registration page

- [x] User registration screen where users can enter their email and password.
- [x] Validation for a valid email address during the registration process.
- [x] Validation for matching passwords during the registration process.
- [x] Check if the password entry is null.
- [x] Animate sign up button
- [ ] Function to try to register the new user using the post method.

User log in page
- [x] Login screen where users can enter their email and password to access the system.
- [x] Animte sing in button
- [x] Function to try to login using the post method.
- [x] Call authentication page after login.
- [ ] New Animation in email and password box to help user see login failed.

User authentication page
- [x] Create authentication page in html.
- [x] Style css to the html authentication page.
- [x] Function that checks if the user input code matches the sent email code.
- [x] Function that send a request to the back-end to send an email.
- [ ] Create a confirm text or a new page to show on screen that user made login.


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

2. Open the `index.html`, `confirma.html` and `registro.html` file in your browser to access the login, register and confirm screen.

3. Open http://localhost:8080/swagger-ui/index.html after start back-end to acces swagger documentation.

## Contribution

Contributions to this project are welcome. If you find any issues, have suggestions, or want to add new features, feel free to open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
