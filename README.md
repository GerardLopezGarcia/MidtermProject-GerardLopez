![image](https://user-images.githubusercontent.com/72072309/205083754-e000dd47-8302-4cf8-9791-33826d9d9bf9.png)

> Este proyecto ha sido desarrollado con Java, MySQL y Spring!🐱‍💻


## Proyecto:

users controller - todo el servicio de usuarios
accounts + transfer


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

Security 
contraseñas y cuentas

| First Header  | Second Header |
| ------------- | ------------- |
| Content Cell  | Content Cell  |
| Content Cell  | Content Cell  |

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

