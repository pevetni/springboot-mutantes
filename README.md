# springboot-mutantes
Prueba Mercado Libre Springboot y mongoDB sobre aws

Para configurar el proyecto solo debe importarlo como proyecto maven existente en Spring Tool Suite 4 o en su defecto Eclipse.

El codigo esta desarrollado y compilado con la ultima version Java 13.

Para realizar las pruebas solo debe con postman realizar 

a) Una peticion post a http://ec2-107-22-153-161.compute-1.amazonaws.com:8080/mutant/ 
pasando al body el siguiente formato:

{
	"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

b) Una peticion get a http://ec2-107-22-153-161.compute-1.amazonaws.com:8080/stats
esta peticion respondera algo parecido a lo siguiente:

{
    "count_mutant_dna": 1,
    "count_human_dna": 0,
    "radio": 0.0
}

La conexion a la BD esta colocada en el archivo application.propertie, es una BD noSQL
especificamente MongoDB.

y el host del servicio se encuentra en una maquina virtual en AWS especificamente una instancia de Ubuntu.


