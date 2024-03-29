
[![Contributors][contributors-shield]][contributors-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://medium.com/@mericcana/mailbox-core-92fb5527b13b">
    <img src="https://www.cdnlogo.com/logos/m/64/mail-ios.svg" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">MailBox Core</h3>
</div>

Task Table --> [@MailDashboard](https://github.com/users/Canaxs/projects/1)

Medium Article --> [@Medium](https://medium.com/@mericcana/mailbox-core-92fb5527b13b)

<!-- ABOUT THE PROJECT -->
## About The Project

Dependencies used --> 

[Spring Boot Starters Addiction Definition]

* [Data-Jpa] 
* [Security] 
* [Web]
* [Mail]
* [Validation]
* [Test]

[Other Addictions]

* [SpringDoc-OpenAPI]
* [Lombok]            
* [Mysql-Connector]
* [Jsoup]
* [JWT]
* [Mapstruct]

### What does the project do?

By connecting to the e-mail addresses that users have registered in the system, it reads and sorts the e-mails in the inbox and lists the e-mails in 7 different types according to their content.

### Mail Types

* [SOCIAL]
* [MALWARE]
* [ATTACHMENTS]
* [PROMOTION]
* [MUSIC]
* [CHECK]
* [UNKNOWN]

### Built With

First of all, you need to install the docker-compose file that I prepared for Mysql installation.
---> Open Terminal in the mailbox directory and run the docker-compose up command
---> You can use it when it is ready for connection. You can use the Docker desktop application to perform Docker container control.

<!-- GETTING STARTED -->
## Getting Started

Follow these steps to get the Mailbox project up and running:
---> New -> Project From Existing Sources -> src/mailbox-core -> Maven -> OK (Intellij Idea)
---> Maven Clean -> Maven Install

## Service addresses

## User Controller
* [localhost:8080/mailbox/user/create]

## Authentication Controller
* [localhost:8080/mailbox/auth/login]

## MailBox Controller
* [localhost:8080/mailbox/mailControl] Authenticated


<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.



<!-- CONTACT -->
## Contact

Mail and Twitter - [@your_twitter](https://twitter.com/cana_meric) - mmericcana@gmail.com

Project Link: [https://github.com/Canaxs/mailbox-core](https://github.com/Canaxs/mailbox-core)



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

Use this space to list resources you find helpful and would like to give credit to. I've included a few of my favorites to kick things off!

* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Pages](https://pages.github.com)


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/Canaxs/spring-twitter-api/graphs/contributors
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/mericcana/

