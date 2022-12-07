![image](https://user-images.githubusercontent.com/72072309/205083754-e000dd47-8302-4cf8-9791-33826d9d9bf9.png)

> Este proyecto ha sido desarrollado con Java, MySQL y Spring!🐱‍💻



## UML del Proyecto

![image](https://user-images.githubusercontent.com/72072309/205115044-f51ff48d-1867-48c5-9e4a-a2e052cf4b16.png)


## Diagrama de Clases

![image](https://user-images.githubusercontent.com/72072309/205298752-b837bcac-42ab-419f-8d06-f752332f9371.png)

## Rutas - Postman - Supabase_Servidor

Rutas disponibles para postman

| roles  | ruta | metodo |roles  | ruta | metodo |
| ------------- | ------------- | ------------- |------------- | ------------- | ------------- |
| ADMIN   | "/checkings"  | GET  | ADMIN   | "/checkings"  | POST  |
| ADMIN  | "/accounts" | GET  | ADMIN   | "/accountholders" | POST  |
| ADMIN  | "/studentcheckings" | GET  | ADMIN   | "/users"  | POST  |
| ADMIN  | "/savings" | GET  | ADMIN   | "/admins"  | POST  |
| ADMIN ,USER | "/mysavingsaccount/{id}" | GET  | ADMIN   | "/thirdpartyusers"  | POST  |
| ADMIN  | "/creditcards" | GET  | ADMIN , USER | "/transfer"  | PATCH  |
| ADMIN USER | "/mycreditaccount/{id}" | GET  | ADMIN , USER | "/retrieveMoney"  | PATCH  |
| ADMIN , USER| "/myaccounts/{name}" | GET  | ADMIN , CONTRIBUTOR | "/thirdpartyusers/{hashedKey}"  | PATCH  |
| ADMIN  | "/accountholders" | GET  | ADMIN , CONTRIBUTOR | "/thirdpartyusers/recieve/{hashedKey}" | PATCH  |
| ADMIN  | "/users" | GET  | ADMIN   | "/checkings/{id}"  | DELETE  |
| ADMIN  | "/admins" | GET  | ADMIN   | "/admins/{name}" (las mismas que POST)  | DELETE  |
| ADMIN  | "/thirdpartyusers" | GET  | ADMIN   | "/thirdpartyusers/{name}" | DELETE  |



Proyecto desplegado en los servidores de *Supabase*
Para acceder hay que añadir en los headers:

>  apikey = eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InhsdHVjc2pya2FsZHloemhkYnVwIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzAzMzQ2MzUsImV4cCI6MTk4NTkxMDYzNX0.bjkp1lmwxzmf4C7Ke1i_Zt0Ha3JOgxyNHw8VK39tP4Q

> Authorization = Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InhsdHVjc2pya2FsZHloemhkYnVwIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzAzMzQ2MzUsImV4cCI6MTk4NTkxMDYzNX0.bjkp1lmwxzmf4C7Ke1i_Zt0Ha3JOgxyNHw8VK39tP4Q

Y podremos acceder a toda la base de datos , se añade como ejemplo las siguientes rutas:

GET

https://xltucsjrkaldyhzhdbup.supabase.co/rest/v1/account?select=*
https://xltucsjrkaldyhzhdbup.supabase.co/rest/v1/checking?select=minimum_balance,id,status
https://xltucsjrkaldyhzhdbup.supabase.co/rest/v1/user?select=*

POST

https://xltucsjrkaldyhzhdbup.supabase.co/rest/v1/user

DELETE

https://xltucsjrkaldyhzhdbup.supabase.co/rest/v1/user?password=eq.supabase

SECURITY
Listado de contraseñas y cuentas para acceder con postman y basic auth

| Usuario  | Contraseña | Contraseña con Hash | Usuario  | Contraseña | Contraseña con Hash |
| ------------- | ------------- | ------------- | ------------- | ------------- | ------------- |
| Erin Carr  | ironhack  | $2a$10$59iLMyW4idSHRYhCmT7XWuqSAifxdgs1L83K9GkSdU9EepokO/Bwe  | Administrador1  | Administrador1  | $2a$10$aNbrPfc4HN.aiaCJcWNLzuL4mrtebb8c828X0RL0UStAIAAaWNCRO  |
| Naomi Barr  | ironhack1  | $2a$10$fCfTguY1kqyVhCqW/30Ld.YCRCUDwa81YjFQ51oKkYrUEQFRAHHDu | Administrador2  | Administrador2  | $2a$10$QHGf2H2SlH4X2Huf9vQx3e4LDvtetWYYGJslu6QuNrWCNvAoaw8hC  |
| Elinor Mcgee | ironhack2  | $2a$10$A4F3lhvNMwXrC5O0dvldCO8Y2tRR/fV9PPDxWPBhn2wyrVC4K1Jii  | UsuarioExterno1  | UsuarioExterno1  | $2a$10$AxUCPszLtqLpv7Gh0/VH5eodHmh0Q.kANC5SUT72rvg9LUhfNqS3W |
| Hana Jennings  | ironhack3  | $2a$10$UL9/nfLr.1Pt7mpwBBiIluTDjEQ6ds/44PwJO8v2n3mquLA7PtLUS  | UsuarioExterno2  | UsuarioExterno2  | $2a$10$.x.Dxo0igRQebJXuqcHgMecJScWsaxwtp41gOcMJKu6ZnJyzB/Yc6  |
| Luisa Foley  | ironhack4 | $2a$10$7qPNDprFGa7SteLUSSasGum6FBOTarKTVnDn.zf9ELoxhNO0ux6o2  | UsuarioExterno3  | UsuarioExterno3  | $2a$10$Jh5tdfXa83J0qnnq45Q17eXlUfW9y1WRUCwvKyqlFb1t9uIcO6G5S  |
| Hamzah Mejia  | ironhack5  | $2a$10$mk59gmke3LOCUELBeowbj.2EtcXX6POriTyKFaB8XKTWJEIt/2UN2  |



## Setup

- Fork this repo
- Clone this repo

```shell
$ git clone <NameOfRepository>
$ cd IronLibrary
```

- Open folder in IntelliJ IDEA
- Run program

## Autor
El proyecto ha sido realizado por:

(☞ﾟヮﾟ)☞   [Gerard López](https://github.com/GerardLopezGarcia)   ☜(ﾟヮﾟ☜)

